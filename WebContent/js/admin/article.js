
$(document).ready(function()
{
	getList(1);
	$("#submit").click(submit);
	$("#remove").click(remove);
	$("#insert").click(function(){
		showEditModal("insert");
	});
	//fileUpload($("#linkImg",$("#editModal")),EnvConfig.ATTACH_UPLOAD_URL,EnvConfig.ATTACH_PATH);//*****
	window.editor = KindEditor.create('#contentText',{
    	uploadJson : EnvConfig.ATTACH_UPLOAD_URL_KINDEDITOR,
    	allowFileManager : false,
    });
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
					tr.append("<td>" + value.articleName + "</td>");//*****
					tr.append("<td>" + value.orderNum + "</td>");//*****
					
					var buttonGroup = $("<div class='btn-group'>").hide();
					var preViewButton = $("<button class='btn btn-sm btn-default' title='预览'><i class='glyphicon glyphicon-eye-open'></i></button>").click(function()
					{
						showPreviewModal(value);
					}).tooltip(
					{
						container: tbody
					});
					
					var editButton = $("<button class='btn btn-sm btn-default' title='修改'><i class='glyphicon glyphicon-edit'></i></button>").click(function()
					{
						showEditModal("edit",value);
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
					buttonGroup.append(editButton).append(removeButton).append(preViewButton);
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
	window.editor.html("");
	if(type=="edit"){
		
		fillUpForm($("#editModal"),data);
		window.editor.html(data.contentText);
		//$("#contentText4Show").html(data.contentText);
	}else{
		//$("#contentText4Show").html("");
	}
	var $editModal = $("#editModal").off('shown.bs.modal').on('shown.bs.modal', function (e) {
        $(document).off('focusin.modal');//解决编辑器弹出层文本框不能输入的问题
	});;
	$editModal.modal({backdrop: 'static', keyboard: false}).data("type",type);
	$editModal.on('hidden.bs.modal', function () {
	    // 关闭Dialog前移除编辑器
	    //KindEditor.remove('#contentText');
	})
}

function showPreviewModal(data){
	$("#contentPreView").empty().html(data.contentText);
	var $editModal = $("#contentModal").modal({backdrop: 'static', keyboard: false});
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
	
	
	validateFormModal($("#editModal"),function(result){
		if(result){
			var form = getFormJSON($("#editModal"));
			form.articleType=EnvConfig.ARTICLE_TYPE;
			window.editor.sync();//editor--
			form.contentText = $("#contentText").val();
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
						$("#closeBtn").trigger("click");
						$.scojs_message(response.message, $.scojs_message.TYPE_OK);
						getList($("#pageJump").val());
					}
					else
					{
						$("#closeBtn").trigger("click");
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


