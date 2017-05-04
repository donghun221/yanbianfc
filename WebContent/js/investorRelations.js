$(document).ready(function(){
	CF.initMenu();
	CF.initFooter();
	CF.initFirstPageInfo("invst_relation");
	CF.queryArticleInfo("invst_relation",function(data){
		CF.rendTpl("tpl_articleName",data);
		CF.rendTpl("tpl_articleContent",data);
		initComps();
		CF.jumpMenu();
		CF.fixPage();
	});
	
	
	function initComps(){

	    $(".aboutMenu li a").click(function () {
	    	//debugger;
	    	var $thiz = $(this);
	    	$(".isClick").removeClass("isClick");
	    	$thiz.addClass("isClick");
	        var $aContentDiv = $("#aContentDiv"+$(this).attr("index"));
	        $aContentDiv.show();
	        $aContentDiv.siblings().hide();
	        CF.fixPage();
	    });
		   
	}
	
	
	
});

