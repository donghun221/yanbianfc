var ResponseCode =
{
	Success: "0x00",
	Failure: "0x99",
	DuplicateAppversion: "0x01"
};

$.ajaxSetup(
{
	complete: function(xhr, status)
	{
		// 由于Ajax无法捕获302状态吗，所以这里依靠程序特征来进行超时判断的页面跳转，如果返回的内容中包含loginLogo（登陆页面LOGO的div的ID），就表示返回了登陆页，即跳转
		if (xhr.responseText.indexOf("loginLogo") != -1)
		{
			location.href = "login";
		}
	}
});

function getFileUploadJSONField($uploadDiv)
{
	if(!$uploadDiv) return {};
	var paramValue = $uploadDiv.find(".imgId").val();
	var result  = {};
	result["id"] = paramValue;
	return result;
}

function renderFileUploadField($uploadDiv,basePath,imageInfo)
{
	if(!imageInfo) return;
	$uploadDiv.find(".imgId").val(imageInfo.id);
	$uploadDiv.find(".imgName").val("../"+basePath+"/"+imageInfo.path+"/"+imageInfo.fileName);
}

function fileUpload($uploadDiv,url,path,callBack)
{
	var $imageId = $uploadDiv.find(".imgId").attr("id",$uploadDiv.attr("id")+"_imgId");
	var $button = $uploadDiv.find(".uploadBtn").attr("id",$uploadDiv.attr("id")+"_uploadBtn");
	var $name = $uploadDiv.find(".imgName").attr("id",$uploadDiv.attr("id")+"_imgName");
	fileUploader($button,$imageId,$name,url,path,callBack);
	var $showImg = $uploadDiv.find("#showImg");
	var $showImgDiv = $uploadDiv.find("#showImgImg").hide();
	$showImg.hover(function() {
		var url = $name.val();
		if(url)
		$showImgDiv.show().attr("src",url); // 移入
	}, function() {
		$showImgDiv.hide(); // 移入
	});
}

/**
 * 上传附件
 * 
 * @param button
 *            上传按钮
 * @param attachId
 *            接收附件ID的Input
 * @param attachName
 *            接收附件Name的Input
 */
function fileUploader(button, attachId, attachName, url,path,callBack)
{
	var buttonId = button.attr("id");
	var form = "<form id='" + buttonId + "_attachUploadForm' action='" + url + "' method='post' target='" + buttonId + "_uploadFrame' enctype='multipart/form-data' class='hidden'>" +
			"<input type='file' id='" + buttonId + "_attachUpload' name='attach' class='hidden' />" +
			"<input type='hidden' name='path' value='"+path+"' class='hidden' />" +		
			"<iframe name='" + buttonId + "_uploadFrame' class='hidden' ></iframe></form>";
	button.after(form);

	// 点击上传图片按钮，直接选择图片上传（创建用）
	button.click(function()
	{
		$("#" + buttonId + "_attachUpload").click();
		$("#" + buttonId + "_attachUpload").val("");
		$("iframe[name=" + buttonId + "_uploadFrame]").contents().empty();
	});
	$("#" + buttonId + "_attachUpload").change(function()
	{
		if ($(this).val())
		{
			$("#" + buttonId + "_attachUploadForm").submit();
			uploadListener(callBack);
		}
	});

	/**
	 * 监听文件上传(创建用)
	 */
	function uploadListener()
	{
		var uploadResult = $("iframe[name=" + buttonId + "_uploadFrame]").contents().text();
		if (uploadResult)
		{
			var result = $.parseJSON(uploadResult).data;
			attachId.val(result.id);
			attachName.val(result.url);
			attachName.text("√");
			if(callBack){
				callBack(result.id);
			}
			return;
		}
		window.setTimeout(uploadListener, 500);
	}
}

/**
 * 对比时间
 * 
 * @param a 日期a
 * @param b 日期b
 * @returns true，如果b大于a
 */
function monthCompare(a, b)
{
	var arra = a.split("-");
	var starttime = new Date(arra[0], arra[1]);
	var starttimes = starttime.getTime();

	var arrb = b.split("-");
	var lktime = new Date(arrb[0], arrb[1]);
	var lktimes = lktime.getTime();

	if (starttimes >= lktimes)
	{
		return false;
	}
	else
	{
		return true;
	}
}

/**
 * 生成GUID唯一数
 */
function generateGUID(quote)
{
	var guid = "";
	if (quote)
	{
		guid += "{";
	}
	for (var i = 1; i <= 32; i++)
	{
		var n = Math.floor(Math.random() * 16.0).toString(16);
		guid += n;
		if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
			guid += "-";
	}
	if (quote)
	{
		guid += "}";
	}
	return guid;
}


/**
 * 转换秒数为时、分、秒
 * 
 * @param millis 毫秒数
 */
function formatSeconds(millis)
{
	var theTime = parseInt(millis / 1000);// 秒
	var theTime1 = 0;// 分
	var theTime2 = 0;// 小时
	if (theTime > 60)
	{
		theTime1 = parseInt(theTime / 60);
		theTime = parseInt(theTime % 60);
		if (theTime1 > 60)
		{
			theTime2 = parseInt(theTime1 / 60);
			theTime1 = parseInt(theTime1 % 60);
		}

		theTime2 = theTime2 >= 10? "0" + theTime2: theTime2;
		theTime1 = theTime1 >= 10? "0" + theTime1: theTime1;
		theTime = theTime >= 10? "0" + theTime: theTime;
	}

	var result = (theTime < 10? "0": "") + parseInt(theTime) + "";
	if (theTime1 > 0)
	{
		result = (theTime1 < 10? "0": "") + parseInt(theTime1) + ":" + result;
	}
	if (theTime2 > 0)
	{
		result = (theTime2 < 10? "0": "") + parseInt(theTime2) + ":" + result;
	}
	return result;
}

function validateForm($form,callBack)
{
	$form.find("input[type='text'],select").each(function(){
		var val = $(this).val();
		if(!val)
		{
			if($(this).attr("required")=="required"){
				var inputName = $(this).attr("inputName");
				$.scojs_message(inputName+"不能为空", $.scojs_message.TYPE_ERROR);
				if(callBack)
					callBack(false);
				callBack=null;
				return false;
			}
		}
	});
	
	$form.find("textarea").each(function(){
		var val = $(this).val();
		if(!val)
		{
			if($(this).attr("required")=="required"){
				var inputName = $(this).attr("inputName");
				$.scojs_message(inputName+"不能为空", $.scojs_message.TYPE_ERROR);
				if(callBack)
					callBack(false);
				callBack=null;
				return false;
			}
		}
	});
	
	if(callBack)
		callBack(true);
}

function validateFormModal($form,callBack)
{
	$form.find("input[type='text'],textarea,select").each(function(){
		var val = $(this).val();
		if(!val)
		{
			if($(this).attr("required")=="required"){
				var inputName = $(this).attr("inputName");
				$form.find("#errorMsgSpan").text(inputName+"不能为空");
				setTimeout(function(){
					$form.find("#errorMsgSpan").text("");
				},3000);
				if(callBack)
					callBack(false);
				callBack=null;
				return false;
			}
		}
	});
	
	if(callBack)
		callBack(true);
}

function setJsonFromComponentFormFiled(jsonObject,$formField,fieldIdName){
	var componentFieldName = fieldIdName.split(".")[0];
	var fieldNameOfcomponentField = fieldIdName.split(".")[1];
	var componentField = jsonObject[componentFieldName];
	if(componentField&&typeof componentField =="object"){
		componentField[fieldNameOfcomponentField] = $formField.val();
	}else{
		componentField = {};
		componentField[fieldNameOfcomponentField] = $formField.val();
		jsonObject[componentFieldName] = componentField;
	}
}

function getFormJSON($form)
{
	var result = {};
	$form.find("input[type='text'],input[type='number'],textarea,select").each(function(){
		var propName = $(this).attr("id");
		if($(this).attr("submit")!="false")
		{
			var propName = $(this).attr("id");
			if(propName){
				if(propName.indexOf(".")>=0){
					setJsonFromComponentFormFiled(result,$(this),propName);
				}else{
					result[propName] = $(this).val();
				}
			}
		}
	});
	/*$form.find("textarea").each(function(){
		var propName = $(this).attr("id");
		if($(this).attr("submit")!="false")
			result[$(this).attr("id")] = $(this).val();
	});*/
	return result;
}

function getPropValue(data,propName){
	if(propName.indexOf(".")<0){
		return data[propName];
	}else{
		var componentFieldName = propName.split(".")[0];
		var fieldNameOfcomponentField = propName.split(".")[1];
		var componentField = data[componentFieldName];
		if(componentField&&typeof componentField =="object"){
			return componentField[fieldNameOfcomponentField];
		}
	}
}

function fillUpForm($form,data){
	$form.find("input[type='text'],input[type='number'],input[type='hidden'],textarea,select").each(function(){
		if($(this).attr("name")!="path"){
			var propName = $(this).attr("id");
			var isRichText = $(this).attr("richText");
			if(propName)
			{
				if(isRichText=="true"){
					$(this).val(htmlspecialchars(getPropValue(data,propName)));
					
				}else{
					$(this).val(getPropValue(data,propName));
					console.log($(this).val());
				}
			}
		}
	});
	/*$form.find("textarea").each(function(){
		var propName = $(this).attr("id");
		$(this).val(data[propName]);
	});*/
}
function cleanUpForm($form){
	$form.find("input[type='text'],input[type='number'],input[type='hidden'],textarea,select").each(function(){
		if($(this).attr("name")!="path"){
			if($(this).attr("type")=="number")
				$(this).val("0");
			else
				$(this).val("");
		}
	});
	/*$form.find("input[type='hidden']").each(function(){
		if($(this).attr("name")!="path")
			$(this).val("");
	});
	$form.find("textarea").each(function(){
		if($(this).attr("name")!="path")
			$(this).val("");
	});*/
}

function getImageShowBtn(basePath,img){
	var HTML = "<div style='position:relative;'><a href='#' id='showImg'>查看</a><img style='position: absolute;left:30px;top:20px;max-width: 200px;z-index:999' id='showImgImg'></img></div>";
	var $el = $(HTML);
	$el.find("#showImg").hover(function() {
		$el.find("#showImgImg").show().attr("src","../"+basePath+"/"+img.path+"/"+img.fileName); // 移入
	}, function() {
		$("#showImgImg",$el).hide(); // 移入
	});
	
	return $el;
}

function getContentShowBtn(content){
	var detail = "无";
	if (content)
	{
		content=content.replace(/[\r\n]/g,"<br/>");
		content=content.replace(/[ ]/g,"&nbsp;");
		content=content.replace(/[　]/g,"&nbsp;");
		detail = $("<a>查看</a>").popover(
		{
			placement: "left",
			trigger: "hover",
			title: "点击查看",
//			content: $("<div>" + value.introduce + "</div>").text().substr(0, 250) + " ...",
			container: "body"
		}).click(function()
		{
			$("#contentModal .modal-body").html(content);
			$("#contentModal").modal("show");
		});
	}
	
	return detail;
}

function htmlspecialchars(str) {
	str = str.replaceAll(/&/g, "&amp;");
	str = str.replaceAll(/</g, "&lt;");
	str = str.replaceAll(/>/g, "&gt;");
	str = str.replaceAll(/'/g, "&quot;");
	return str;
}

function fromDict(dictName,value){
	var arr = APP_DICT[dictName];
	var result = "";
	if(arr){
		var len=arr.length;
		for(var i=0;i<len;i++){
			var item = arr[i];
			if(value==item["value"])
			{
				result = item["msg"];
				break;
			}
		}
	}
	return result;
}

function genDictSelect($select,dictName,allOption){
	var options = APP_DICT[dictName];
	if(options){
		$select.empty();
		if(allOption){
			$select.append($("<option value='"+allOption.value+"'>"+allOption.msg+"</option>"));
		}
		var len = options.length;
		for(var i=0;i<len;i++){
			var option = options[i];
			$select.append($("<option value='"+option.value+"'>"+option.msg+"</option>"));
		}
	}
}

function querySelectData($select,option){
	var url = option.url;
	var valueFiled = option.valueField;
	var msgField = option.msgField;
	var allOption = option.allOption;
	$select.empty();
	if(allOption){
		$select.append($("<option value='"+allOption.value+"'>"+allOption.msg+"</option>"));
	}
	$.ajax(
			{
				type: "GET",
				url: url,
				contentType: "text/plain; charset=UTF-8",
				dataType: "json",
				success: function(response)
				{
					if (response.code == ResponseCode.Success)
					{
						
						$.each(response.data, function(index, row){
							$select.append($("<option value='"+row[valueFiled]+"'>"+row[msgField]+"</option>"));
						});
					}else{alert("下拉框数据获取错误");}
				},
				error: function(xhr, status, msg)
				{alert("下拉框数据获取错误");}
			}
	);
}
