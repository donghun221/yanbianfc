
$(document).ready(function()
{
	getWelcomeInfo();
	fileUpload($("#backGround"),EnvConfig.ATTACH_UPLOAD_URL,EnvConfig.ATTACH_PATH);
	fileUpload($("#companyLogo"),EnvConfig.ATTACH_UPLOAD_URL,EnvConfig.ATTACH_PATH);
	//fileUploader($("#imageBg"), $("#edit_imagegBgId"), $("#edit_imageBg"), EnvConfig.ATTACH_UPLOAD_URL,EnvConfig.ATTACH_PATH);
	$("#modify").click(modify);
});



/**
 * 获取首页管理内容
 */
function getWelcomeInfo()
{
	$.ajax(
	{
		type: "GET",
		url: EnvConfig.MANAGE_URL,
		contentType: "text/plain; charset=UTF-8",
		dataType: "json",
		success: function(response)
		{
			if (response.code == ResponseCode.Success)
			{
				var welcomeInfo = response.data;
				$("#edit_showTime").val(welcomeInfo.showTime);
				$("#edit_footContent").val(welcomeInfo.footContent);
				renderFileUploadField($("#backGround"),welcomeInfo.backGround);
				renderFileUploadField($("#companyLogo"),welcomeInfo.companyLogo);
				//console.log(response.data);
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
		showTime: $("#edit_showTime").val(),
		footContent: $("#edit_footContent").val(),
		backGround:getFileUploadJSONField($("#backGround")),
		companyLogo :getFileUploadJSONField($("#companyLogo"))
	};
	

	if (!form.showTime)
	{
		$.scojs_message("显示时间不能为空", $.scojs_message.TYPE_ERROR);
		return;
	}
	if (!form.footContent)
	{
		$.scojs_message("页脚不能为空", $.scojs_message.TYPE_ERROR);
		return;
	}
	
	

	$.ajax(
	{
		type: "PUT",
		url: EnvConfig.MANAGE_URL,
		contentType: "text/plain; charset=UTF-8",
		dataType: "json",
		data: JSON.stringify(form),
		success: function(response)
		{
			if (response.code == ResponseCode.Success)
			{
				//cancelEdit();
				//getNewsList();
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