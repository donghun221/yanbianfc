$(document).ready(function()
{
	$("select").selectpicker();

	$("#create").click(create);
	$("#modify").click(modify).hide();
	$("#remove").click(remove);
	$("#cancelEdit").click(cancelEdit).hide();
	// 定义附件上传控件
	fileUploader($("#bannerImage"), $("#edit_imageId"), $("#edit_imageName"), EnvConfig.ATTACH_UPLOAD_URL);
	// 编辑器
	$("#edit_content").css("min-height", 500).wysiwyg(
	{
		hotKeys: {}
	});
	
	$("#addImageItem").click(function()
	{
		imageItem("", "");
	});
	
	getNewsList();
	
	$("#bannerdescA").mouseenter(function(){
		$("#bannerdescImg").show();
	});
	$("#bannerdescA").mouseleave(function(){
		$("#bannerdescImg").hide();
	});
});

/**
 * 添加图片上传控件
 */
function imageItem(id, name)
{
	var imageId = $("<input type='hidden' class='form-control' readonly='readonly' value='" + id + "' />");
	var imageName = $("<input type='text' class='form-control' readonly='readonly' value='" + name + "' />");
	var inputGroup = $("<span class='input-group-btn'></span>");
	var imageButton = $("<button type='button' class='btn btn-default' id='" + generateGUID() + "'><span class='glyphicon glyphicon-open'></span> 上传图片</button>");
	var insertButton = $("<button type='button' class='btn btn-success' id='" + generateGUID() + "'><span class='glyphicon glyphicon-new-window'></span> 插入</button>").click(function(){
		if (imageId.val())
		{
			$("#edit_content").append("<img src='"+EnvConfig.ATTACH_URL(imageId.val())+"' style='max-width: 90%' data-id='"+imageId.val()+"' alt='"+imageName.val()+"' />");
		}
	});
	var deleteButton = $("<button type='button' class='btn btn-danger'><span class='glyphicon glyphicon-trash'></span> 删除</button>").click(function()
	{
		imageItem.remove();
		$("#edit_content img[data-id='"+imageId.val()+"']").remove();
	});
	var imageItem = $("<div class='input-group' style='margin-bottom: .5em'></div>").append(imageId).append(imageName).append(inputGroup.append(imageButton).append(insertButton).append(deleteButton));
	$("#addImageItem").before(imageItem);
	// 定义附件上传控件
	fileUploader(imageButton, imageId, imageName, EnvConfig.ATTACH_UPLOAD_URL);
}

/**
 * 获取新闻资讯列表
 */
function getNewsList()
{
	$.ajax(
	{
		type: "GET",
		url: EnvConfig.BANNER_LIST_URL(),
		contentType: "text/plain; charset=UTF-8",
		dataType: "json",
		success: function(response)
		{
			if (response.code == ResponseCode.Success)
			{
				cancelEdit();
				
				var tbody = $("#bannerList tbody");
				tbody.empty();
				$.each(response.data, function(index, value)
				{
					var tr = $("<tr>").data("data", value);
					tr.append("<td>" + (index + 1) + "</td>");
					tr.append("<td>" + value.name + "</td>");
					
					var listImage = "无";
					listImage = $("<a href='" + EnvConfig.BANNER_ITEM_BANNER_IMAGE(value.seq) + "' target='_blank'>图片</a>").popover(
					{
						placement: "bottom",
						trigger: "hover",
						content: "<img src='" + EnvConfig.BANNER_ITEM_BANNER_IMAGE(value.seq) + "' style='width: 8em' />",
						container: "body",
						html: true
					});
					tr.append($("<td>").append("&nbsp;").append(listImage));
					
					
					if (value.content)
					{
						var content=$("<div>"+value.content+"</div>");
						content.find("img").each(function(){
							$(this).attr("src", EnvConfig.BANNER_ITEM_IMG_URL(value.seq, $(this).attr("data-id")));
						});
						value.content=content.html();
					}
					
					var detail = "无";
					if (value.content)
					{
						detail = $("<a>查看</a>").popover(
						{
							placement: "left",
							trigger: "hover",
							title: "内容（点击查看详细）",
							content: $("<div>" + value.content + "</div>").text().substr(0, 250) + " ...",
							container: "body"
						}).click(function()
						{
							$("#contentModal .modal-title").html(value.name+" 的内容");
							$("#contentModal .modal-body").html(value.content);
							$("#contentModal").modal("show");
						});
					}
					tr.append($("<td>").html(detail));

					tr.append("<td>" + value.createTime + "</td>");
					tr.append("<td>" + value.updateTime + "</td>");

					var buttonGroup = $("<div class='btn-group'>").hide();
					var editButton = $("<button class='btn btn-sm btn-default' title='修改'><i class='glyphicon glyphicon-edit'></i></button>").click(function()
					{
						$("#edit_seq").val(value.seq);
						$("#edit_imageId").val("");
						$("#edit_imageName").attr("placeholder", "[留空表示不修改]");
						$("#edit_name").val(value.name);
						$("#edit_content").html(value.content);
						$("#edit_link").val(value.link);

						edit(tr);
					}).tooltip(
					{
						container: tbody
					});
					var removeButton = $("<button class='btn btn-sm btn-danger' title='删除'><i class='glyphicon glyphicon-trash'></i></button>").click(function()
					{
						$("#removeModal").modal("show");
						$("#removeModal").data("seq", value.seq);
						$("#removeModal").data("tr", tr);
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

				if ($("#bannerList tbody tr").size() == 0)
				{
					$("#bannerList tbody").html("<tr><td colspan='8' class='text-center active'><h5>列表为空<h5></td></tr>");
				}

			}
			else
			{
				$.scojs_message(response.message, $.scojs_message.TYPE_ERROR);
			}
		},
		error: function(xhr, status, msg)
		{
			$("#ajaxErrorModal h5").text("status" + msg);
			$("#ajaxErrorModal").modal("show");
		}
	});
}

/**
 * 创建
 */
function create()
{
	var form =
	{
		name: $("#edit_name").val(),
		content: $("#edit_content").html(),
		bannerImage: $("#edit_imageId").val(),
		link: $("#edit_link").val()
		
	};

	if (!form.name)
	{
		$.scojs_message("名称不能为空", $.scojs_message.TYPE_ERROR);
		return;
	}
	if (!form.bannerImage)
	{
		$.scojs_message("Banner图片不能为空", $.scojs_message.TYPE_ERROR);
		return;
	}

	$.ajax(
	{
		type: "POST",
		url: EnvConfig.BANNER_ITEM_URL("$"),
		contentType: "text/plain; charset=UTF-8",
		dataType: "json",
		data: JSON.stringify(form),
		success: function(response)
		{
			if (response.code == ResponseCode.Success)
			{
				getNewsList();
				cancelEdit();
				$.scojs_message(response.message, $.scojs_message.TYPE_OK);
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

/**
 * 修改
 */
function modify()
{
	var form =
	{
		name: $("#edit_name").val(),
		content: $("#edit_content").html(),
		bannerImage: $("#edit_imageId").val(),
		link: $("#edit_link").val()
	};

	if (!form.name)
	{
		$.scojs_message("名称不能为空", $.scojs_message.TYPE_ERROR);
		return;
	}
	
	

	$.ajax(
	{
		type: "PUT",
		url: EnvConfig.BANNER_ITEM_URL($("#edit_seq").val()),
		contentType: "text/plain; charset=UTF-8",
		dataType: "json",
		data: JSON.stringify(form),
		success: function(response)
		{
			if (response.code == ResponseCode.Success)
			{
				cancelEdit();
				getNewsList();
				$.scojs_message(response.message, $.scojs_message.TYPE_OK);
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

/**
 * 删除
 */
function remove()
{
	$.ajax(
	{
		type: "DELETE",
		url: EnvConfig.BANNER_ITEM_URL($("#removeModal").data("seq")),
		contentType: "text/plain; charset=UTF-8",
		dataType: "json",
		success: function(response)
		{
			if (response.code == ResponseCode.Success)
			{
				$("#removeModal").modal("hide").data("tr").fadeOut(function()
				{
					$(this).remove();
				});
				$.scojs_message(response.message, $.scojs_message.TYPE_OK);
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

/**
 * 编辑
 */
function edit(tr)
{
	var oldTr = $("#bannerList #editor").parents("tr");
	oldTr.hide();
	var editor = $("#editor").detach();
	oldTr.remove();
	tr.after($("<tr>").html($("<td colspan='10' style='border-top: none'>").html(editor)));
	$("#modify").show();
	$("#cancelEdit").show();
	$("#create").hide();
	$("#editorTitle").text("修改 " + tr.data("data").name);
	$("#editor").removeClass("panel-success").addClass("panel-primary");
	$("#bannerList").removeClass("table-hover");
	$("#bannerList #editor").parents("tr").each(function()
	{
		$(this).siblings().removeClass("active").find(".btn-group").children().show();
		$(this).addClass("active");
		$(this).prev().addClass("active");
		$(this).prev().find(".btn-group").children().hide();
	});
}

/**
 * 取消编辑
 */
function cancelEdit()
{
	var editorTr = $("#bannerList #editor").parents("tr");
	editorTr.prev().removeClass("active");
	$("#bannerList").after($("#editor").detach());
	editorTr.remove();
	$("#modify").hide();
	$("#cancelEdit").hide();
	$("#create").show();
	$("#editor").removeClass("panel-primary").addClass("panel-success");
	$("#editorTitle").text("创建Banner");
	$("#bannerList").addClass("table-hover");
	$("#bannerList .btn-group>*").show();

	$("#edit_seq").val("");
	$("#edit_content").html("");
	$("#edit_name").val("");
	$("#edit_link").val("");
}