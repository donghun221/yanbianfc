
$(document).ready(function()
{
	getList(1);
	$("#submit").click(submit);
	$("#remove").click(remove);
	$("#insert").click(function(){
		showEditModal("insert");
	});
	$("#addImg").click(function(){
		processAutoSaveMaster();
		addImgHTML();
	});
	//fileUpload($("#logoImg",$("#editModal")),EnvConfig.ATTACH_UPLOAD_URL,EnvConfig.ATTACH_PATH);//*****
	//fileUpload($("#prdImg",$("#editModal")),EnvConfig.ATTACH_UPLOAD_URL,EnvConfig.ATTACH_PATH);//*****
});


/**
 * 获取列表
 */
function getList(page)
{
	$.ajax(
	{
		type: "GET",
		url: EnvConfig.LIST_URL(page),
		contentType: "text/plain; charset=UTF-8",
		dataType: "json",
		success: function(response)
		{
			if (response.code == ResponseCode.Success)
			{
				var tbody = $("#list tbody");
				tbody.empty();
				$.each(response.data, function(index, value)
				{
					var tr = $("<tr>").data("data", value);
					tr.append("<td>" + (index + 1) + "</td>");
					tr.append("<td>" + value.name + "</td>");//*****
					
					var contentShowBtn = $("<td>").append(getContentShowBtn(value.intro));
					tr.append(contentShowBtn);
					
					var contentShowBtn = $("<td>").append(getContentShowBtn(value.content));
					tr.append(contentShowBtn);
					
					tr.append("<td>" + value.ord + "</td>");//*****
					

					var buttonGroup = $("<div class='btn-group'>").hide();
					var editButton = $("<button class='btn btn-sm btn-default' title='修改'><i class='glyphicon glyphicon-edit'></i></button>").click(function()
					{
						showEditModal("edit",value);
						//renderFileUploadField($("#logoImg",$("#editModal")),value.logoImg);//*****
						//renderFileUploadField($("#prdImg",$("#editModal")),value.prdImg);//*****
					}).tooltip(
					{
						container: tbody
					});
					var removeButton = $("<button class='btn btn-sm btn-danger' title='删除'><i class='glyphicon glyphicon-trash'></i></button>").click(function()
					{
						$("#removeModal").data("id", value.id);
						$("#removeModal").modal("show");
					}).tooltip(
					{
						container: tbody
					});
					buttonGroup.append(editButton).append(removeButton);
					tr.append($("<td>").html(buttonGroup));

					tr.hover(function()
					{
						buttonGroup.stop(true, true).fadeIn();
					}, function()
					{
						buttonGroup.stop(true, true).fadeOut();
					});

					// 追加材料行
					tbody.append(tr);
				});

				if ($("#list tbody tr").size() == 0)
				{
					$("#list tbody").html("<tr><td colspan='3' class='text-center active'><h5>列表为空<h5></td></tr>");
				}
				if (page <= response.extraData.pageCount)
				{
					$("#pagination").bootstrapPaginator(
					{
						currentPage: page,
						totalPages: response.extraData.pageCount,
						shouldShowPage: true,
						bootstrapMajorVersion: 3,
						numberOfPages: 5,
						onPageClicked: function(event, oevent, type, page)
						{
							getList(page);
						}
					});
				}

				$("#pageCount").text(response.extraData.pageCount);
				$("#pageJump").val(page);
			}else{$.scojs_message(response.message, $.scojs_message.TYPE_ERROR);}
		},
		error: function(xhr, status, msg)
		{$("#ajaxErrorModal h5").text("status" + msg);
			$("#ajaxErrorModal").modal("show");}
	});
}


function showEditModal(type,data){
	cleanUpForm($("#editModal"),data);
	$("#imgListDiv").empty();
	getListSub(data?data.id:null);
	if(type=="edit"){
		fillUpForm($("#editModal"),data);
	}
	$("#editModal").modal("show").data("type",type);
}

/**
 * 提交
 */
function submit(isSilence)
{
	var type = $("#editModal").data("type");
	var method = "POST";
	if(type=="edit"){
		method = "PUT"
	}
	var form = getFormJSON($("#editModal"));
	//form.logoImg = getFileUploadJSONField($("#logoImg",$("#editModal")));//*****
	//form.prdImg = getFileUploadJSONField($("#prdImg",$("#editModal")));//*****
	$.ajax(
	{
		type: method,
		url: EnvConfig.MANAGE_ITEM_URL,
		contentType: "text/plain; charset=UTF-8",
		dataType: "json",
		data:JSON.stringify(form),
		success: function(response)
		{
			if (response.code == ResponseCode.Success)
			{
				if(!isSilence)
					$.scojs_message(response.message, $.scojs_message.TYPE_OK);
				$("#editModal").find("#id[name='id']").val(response.data);
				getList($("#pageJump").val());
				$("#editModal").data("type","edit");
			}
			else
			{
				$("#edit").modal("hide");
				$("#bizErrorModal h5").text(response.message);
				$("#bizErrorModal").modal("show");
			}
		},
		error: function(xhr, status, msg)
		{
			$("#ajaxErrorModal h5").text("status" + msg);
			$("#ajaxErrorModal").modal("show");
		}
	});
}

function remove()
{
	var id = $("#removeModal").data("id");
	$.ajax(
			{
				type: "DELETE",
				url: EnvConfig.MANAGE_ITEM_DELETE_URL(id),
				contentType: "text/plain; charset=UTF-8",
				dataType: "json",
				success: function(response)
				{
					if (response.code == ResponseCode.Success)
					{
						getList($("#pageJump").val());
						$.scojs_message(response.message, $.scojs_message.TYPE_OK);
					}
					else
					{
						$("#bizErrorModal h5").text(response.message);
						$("#bizErrorModal").modal("show");
					}
					$("#removeModal").modal("hide");
				},
				error: function(xhr, status, msg)
				{
					$("#ajaxErrorModal h5").text("status" + msg);
					$("#ajaxErrorModal").modal("show");
				}
			});
}

function addImgHTML(initData){
	var guid = generateGUID(false);
	var imgDivHTML = 
		"<div class='input-group' id='"+guid+"' name='imgDiv' subId=''>"
			+"<input type='hidden' class='form-control imgId' readonly='readonly'/>"
			+"<input type='text' submit='false' class='form-control imgName' readonly='readonly'/>"
				+"<span class='input-group-btn'>"
				+"<button type='button' class='btn btn-default uploadBtn'><span class='glyphicon glyphicon-open'></span>上传图片</button>"
					+"</span>"
				+"<span style='position: relative;padding-left: 10px;'>"
				+"<a href='#' id='showImg'>查看</a>&nbsp;&nbsp;"
					+"<a href='#' id='delImg'>删除</a>"
					+"<img style='position: absolute;left:80px;top:20px;max-width: 500px;' id='showImgImg'></img>"
					+"</span>"
		+"</div><br/>";
	var $imgDiv = $(imgDivHTML);
	$imgDiv.find("#delImg").click(function(){
		removeSub($imgDiv.attr("subId"),function(){
			$imgDiv.remove();
		});
	});
	$("#imgListDiv").append($imgDiv);
	
	if(initData)
	{
		$imgDiv.attr("subId",initData.id);
		renderFileUploadField($imgDiv,initData.img);
	}
	
	fileUpload($imgDiv,EnvConfig.ATTACH_UPLOAD_URL,EnvConfig.ATTACH_PATH,function(imgId){
		var caseImgInfo  = {id:""};
		caseImgInfo.id = $imgDiv.attr("subId");
		caseImgInfo.img = {id:imgId};
		submitSub(caseImgInfo,function(subId){
			$imgDiv.attr("subId",subId);
		});
	});
	
}

function processAutoSaveMaster(){
	var mId = $("#editModal").find("#id[name='id']").val();
	
	if(!mId){
		submit(true);
	}
}

function removeSub(id,callBack){
	if(!id)
	{
		if(callBack)
			callBack();
		return;
	}
	$.ajax(
			{
				type: "DELETE",
				url: EnvConfig.MANAGE_ITEM_SUB_DELETE_URL(id),
				contentType: "text/plain; charset=UTF-8",
				dataType: "json",
				success: function(response)
				{
					if (response.code == ResponseCode.Success)
					{
						if(callBack)
							callBack();
					}
					else
					{
						$("#bizErrorModal h5").text(response.message);
						$("#bizErrorModal").modal("show");
					}
				},
				error: function(xhr, status, msg)
				{
					$("#ajaxErrorModal h5").text("status" + msg);
					$("#ajaxErrorModal").modal("show");
				}
			});
}

function submitSub(data,callBack){
	var method = "POST";
	if(data.id){
		method = "PUT"
	}
	data.caseId = $("#editModal").find("#id[name='id']").val();
	$.ajax(
	{
		type: method,
		url: EnvConfig.MANAGE_ITEM_SUB_URL,
		contentType: "text/plain; charset=UTF-8",
		dataType: "json",
		data:JSON.stringify(data),
		success: function(response)
		{
			if (response.code == ResponseCode.Success)
			{
				if(callBack)
					callBack(response.data);
			}
			else
			{
				$("#edit").modal("hide");
				$("#bizErrorModal h5").text(response.message);
				$("#bizErrorModal").modal("show");
			}
		},
		error: function(xhr, status, msg)
		{
			$("#ajaxErrorModal h5").text("status" + msg);
			$("#ajaxErrorModal").modal("show");
		}
	});
}

function getListSub(caseId)
{
	if(!caseId)
		return;
	$.ajax(
			{
				type: "GET",
				url: EnvConfig.LIST_SUB_URL(caseId),
				contentType: "text/plain; charset=UTF-8",
				dataType: "json",
				success: function(response)
				{
					if (response.code == ResponseCode.Success)
					{
						renderSubList(response.data);
					}else{$.scojs_message(response.message, $.scojs_message.TYPE_ERROR);}
				},
				error: function(xhr, status, msg)
				{$("#ajaxErrorModal h5").text("status" + msg);
					$("#ajaxErrorModal").modal("show");}
			});
}

function renderSubList(subList)
{
	$.each(subList,function(index,data){
		addImgHTML(data);
	});
}
