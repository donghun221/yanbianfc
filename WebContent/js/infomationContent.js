$(document).ready(function(){
	CF.initMenu();
	CF.initFooter();
	CF.initFirstPageInfo("information_content");
	queryBigGuys();
	queryLeaders();
	queryEmps(1);
	initComps();
	
	
	function queryBigGuys(){
		$.get(SiteConf.AROOT+"/empInfo?page=-1&empType=big_guy",null,function(resp){
       		var data = resp;
       		CF.rendTpl("tpl_empBigGuy",data);
		},"json");
	}
	
	function queryLeaders(){
		$.get(SiteConf.AROOT+"/empInfo?page=-1&empType=leader",null,function(resp){
       		var data = resp;
       		CF.rendTpl("tpl_empLeader",data);
		},"json");
	}
	
	function queryEmps(page){
		$.get(SiteConf.AROOT+"/empInfo?page="+page+"&empType=emp&pageSize=9",null,function(resp){
       		var data = resp;
       		var html = juicer($("#tpl_empEmp").html(),data);
       		$("#teamContentList3_1").empty().html(html);
       		var pageInfo = data.extraData;
       		pageInfo.currentPage = page;
       		refreshPageBtns(pageInfo,$("#prePageBtn"),$("#nextPageBtn"));
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
	
	function pageBtnClicked($btn){
		var tarPage = $btn.attr("page");
    	if(tarPage&&tarPage!="0")
    	{
    		queryEmps(tarPage);
    	}
	}
	
	function initComps(){

	    $("#prePageBtn").click(function(){
	    	pageBtnClicked($(this));
	    });
	    
	    $("#nextPageBtn").click(function(){
	    	pageBtnClicked($(this));
	    });
		   
	}
	
	
	
});

