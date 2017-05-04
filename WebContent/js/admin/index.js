
$(document).ready(function()
{
	query();
	fileUpload($("#weiboImg"),EnvConfig.ATTACH_UPLOAD_URL,EnvConfig.ATTACH_PATH);
	$("#modify").click(modify);
});



/**
 * 获取首页管理内容
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
				$("#copyrightText").val(info.copyrightText);
				$("#zipCode").val(info.zipCode);
				$("#fax").val(info.fax);
				$("#email").val(info.email);
				$("#address").val(info.address);
				renderFileUploadField($("#weiboImg"),info.weiboImg);
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
		copyrightText: $("#copyrightText").val(),
		zipCode: $("#zipCode").val(),
		fax: $("#fax").val(),
		email: $("#email").val(),
		address: $("#address").val(),
		weiboImg:getFileUploadJSONField($("#weiboImg")),
		compName:"",
		site:"",
		weixinImg:null,
		id:null,
	};
	
	validateForm($("#submitForm"),function(result){
		if(result){
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
	});
}