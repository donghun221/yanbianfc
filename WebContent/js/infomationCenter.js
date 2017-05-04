$(document).ready(function(){
	CF.initMenu();
	CF.initFooter();
	CF.initFirstPageInfo("info_center");
	queryHYZX(1);
	queryHDZX(1);
	queryZXNS(1);
	queryContact();
	//queryLeaders();
	//queryEmps(1);
	initComps();
	CF.jumpMenu();
	var _data = {};
	
	
	function queryContact(){
		$.get(SiteConf.AROOT+"/contactInfo",null,function(resp){
       		var data = resp.data;
       		CF.rendTpl("tpl_contact",data);
		},"json");
	}
	
	function queryHYZX(page){
		$.get(SiteConf.AROOT+"/richArticleInfo?page="+page+"&articleType=hyzx&pageSize=5",null,function(resp){
       		var data = resp;
       		var html = juicer($("#tpl_hyzx").html(),data);
       		$("#hyzx").empty().html(html);
       		var pageInfo = data.extraData;
       		pageInfo.currentPage = page;
       		refreshPageBtns(pageInfo,$("#prePageBtnHYZX"),$("#nextPageBtnHYZX"));
       		_data.hyzx = data.data;
		},"json");
	}
	
	function queryHDZX(page){
		$.get(SiteConf.AROOT+"/richArticleInfo?page="+page+"&articleType=hdzx&pageSize=5",null,function(resp){
       		var data = resp;
       		var html = juicer($("#tpl_hdzx").html(),data);
       		$("#hdzx").empty().html(html);
       		var pageInfo = data.extraData;
       		pageInfo.currentPage = page;
       		refreshPageBtns(pageInfo,$("#prePageBtnHDZX"),$("#nextPageBtnHDZX"));
       		_data.hdzx = data.data;
		},"json");
	}
	
	function queryZXNS(page){
		$.get(SiteConf.AROOT+"/richArticleInfo?page="+page+"&articleType=zxns&pageSize=5",null,function(resp){
       		var data = resp;
       		var html = juicer($("#tpl_zxns").html(),data);
       		$("#zxns").empty().html(html);
       		var pageInfo = data.extraData;
       		pageInfo.currentPage = page;
       		refreshPageBtns(pageInfo,$("#prePageBtnZXNS"),$("#nextPageBtnZXNS"));
       		_data.zxns = data.data;
		},"json");
	}
	
	function refreshPageBtns(pageInfo,$prePageBtn,$nextPageBtn){
		if(pageInfo.pageCount<=1){
			$prePageBtn.attr("page","0");
			$nextPageBtn.attr("page","0");
		}else if(pageInfo.pageCount>pageInfo.currentPage){
			var currP = parseInt(pageInfo.currentPage);
			$prePageBtn.attr("page",currP-1);
			$nextPageBtn.attr("page",currP+1);
		}if(pageInfo.pageCount==pageInfo.currentPage){
			var currP = parseInt(pageInfo.currentPage);
			$prePageBtn.attr("page",currP-1);
			$nextPageBtn.attr("page","0");
		}
	}
	
	function pageBtnClicked($btn,queryFunc){
		var tarPage = $btn.attr("page");
    	if(tarPage&&tarPage!="0")
    	{
    		queryFunc(tarPage);
    	}
	}
	
	function initComps(){

	    $("#prePageBtnHYZX").click(function(){
	    	pageBtnClicked($(this),queryHYZX);
	    });
	    
	    $("#nextPageBtnHYZX").click(function(){
	    	pageBtnClicked($(this),queryHYZX);
	    });
		   
	    $("#prePageBtnHDZX").click(function(){
	    	pageBtnClicked($(this),queryHDZX);
	    });
	    
	    $("#nextPageBtnHDZX").click(function(){
	    	pageBtnClicked($(this),queryHDZX);
	    });
	    
	    $("#prePageBtnZXNS").click(function(){
	    	pageBtnClicked($(this),queryZXNS);
	    });
	    
	    $("#nextPageBtnZXNS").click(function(){
	    	pageBtnClicked($(this),queryZXNS);
	    });
		   
	}
	
	window.showRich = function(dataType,index){
		
		var $inContent = $("#inContentDetail");
		var info = _data[dataType][index];
		if(info){
			
			$("h1",$inContent).text(info.articleName);
			$("#inContentDetailHTML",$inContent).html(info.contentText);
		}
		
		var $preEl = $("#"+dataType).parent().hide();
		$("#inContentDetailCloseBtn").data("preEl",$preEl);
		$inContent.show();
	}
	
	$("#inContentDetailCloseBtn").click(function(){
		$(this).data("preEl").show();
		$("#inContentDetail").hide();
	});
	
	$("#submitFeedBackBtn").click(function(){
		submitFeedBack();
	});
	
	var regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
	function submitFeedBack(){
		
		var $fbUserName = $("#fbUserName");
		var $fbEmail = $("#fbEmail");
		var $fbContentText = $("#fbContentText");
		
		var userName = $fbUserName.val();
		var email = $fbEmail.val();
		var contentText = $fbContentText.val();
		
		if(!userName){
			alert("请输入姓名");
			return;
		}
		if(!email){
			alert("请输入邮箱");
			return;
		}
		if(!regEmail.test(email)){
			alert("请输入正确邮箱地址");
			return;
		}
		if(!contentText){
			alert("请输入留言内容");
			return;
		}
		
		if(contentText.length<10){
			alert("留言内容少于10字");
			return;
		}
		
		var form = {};
		form.userName = userName;
		form.email = email;
		form.contentText = contentText;
		form.status = "1";
		
		$.ajax(
				{
					type: "POST",
					url: SiteConf.AROOT+"/feedBackInfo",
					contentType: "text/plain; charset=UTF-8",
					dataType: "json",
					data:JSON.stringify(form),
					success: function(response)
					{
						alert("提交成功");
						$fbUserName.val("");
						$fbEmail.val("");
						$fbContentText.val("");
					},
					error: function(xhr, status, msg)
					{
						alert("提交成功");
						$fbUserName.val("");
						$fbEmail.val("");
						$fbContentText.val("");
					}
				});
	}
	
});

