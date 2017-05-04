
$(document).ready(function()
{
	query();
	fileUpload($("#imgOne"),EnvConfig.ATTACH_UPLOAD_URL,EnvConfig.ATTACH_PATH);
	fileUpload($("#imgTwo"),EnvConfig.ATTACH_UPLOAD_URL,EnvConfig.ATTACH_PATH);
	fileUpload($("#imgThree"),EnvConfig.ATTACH_UPLOAD_URL,EnvConfig.ATTACH_PATH);
	$("#modify").click(modify);
});



/**
 * 获取风采内容
 */
function query()
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
				var info = response.data;
				renderFileUploadField($("#imgOne"),info.imgOne);
				renderFileUploadField($("#imgTwo"),info.imgTwo);
				renderFileUploadField($("#imgThree"),info.imgThree);
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
		imgOne:getFileUploadJSONField($("#imgOne")),
		imgTwo:getFileUploadJSONField($("#imgTwo")),
		imgThree:getFileUploadJSONField($("#imgThree"))
	};
	

	/*if (!form.content)
	{
		$.scojs_message("公司愿景内容不能为空", $.scojs_message.TYPE_ERROR);
		return;
	}*/
	
	

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