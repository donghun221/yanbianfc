$(document).ready(function()
{
	getPasswordInfo();
	$("#modify").click(modify);
});


function getPasswordInfo()
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
				var adminInfo = response.data;
				$("#id").val(adminInfo.id);
				$("#adminName").val(adminInfo.adminName);
				$("#adminPassword").val(adminInfo.adminPassword);
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


function modify()
{
	var form =
	{
		id: $("#id").val(),
		adminName: $("#adminName").val(),
		adminPassword: $("#confirm_password").val()
	};

	if (!$("#old_password").val())
	{
		$.scojs_message("原密码不能为空", $.scojs_message.TYPE_ERROR);
		return;
	}
	if ($("#old_password").val()!=$("#adminPassword").val())
	{
		$.scojs_message("原密码不正确", $.scojs_message.TYPE_ERROR);
		return;
	}
	if (!$("#new_password").val())
	{
		$.scojs_message("新密码不能为空", $.scojs_message.TYPE_ERROR);
		return;
	}
	
	if ($("#new_password").val().length>20)
	{
		$.scojs_message("新密码长度不能超过20位", $.scojs_message.TYPE_ERROR);
		return;
	}
	if (!$("#confirm_password").val())
	{
		$.scojs_message("确认新密码不能为空", $.scojs_message.TYPE_ERROR);
		return;
	}
	if($("#new_password").val() != $("#confirm_password").val()){
		$.scojs_message("确认新密码错误", $.scojs_message.TYPE_ERROR);
		return;
	}	

	$.ajax(
	{
		type: "PUT", 
		url: EnvConfig.UPDATE_URL,
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
				$("#old_password").val();
				$("#new_password").val();
				$("#confirm_password").val();
				
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