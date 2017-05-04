$(document).ready(function(){
	CF.initMenu();
	CF.initFooter();
	initLinkInfo();
	CF.initFirstPageInfo("index");
	initPortal();
	
	function initLinkInfo(){
		$.get(SiteConf.AROOT+"/linkInfo?page=-1",null,function(resp){
       		var data = resp;
       		CF.rendTpl("tpl_linkInfo",data,$("#linkInfoListUL"));
       		var vis = data.data.length;
       		if(vis>5)
       			vis=5;
       		$(".banner").slide({titCell:"",mainCell:".bannerList ul",autoPage:false,effect:"leftLoop",autoPlay:false,vis:vis});
		},"json");
		
	}
	function initPortal(){
		$.get(SiteConf.AROOT+"/portalInfo?portalType=left&page=-1",null,function(resp){
       		var data = resp;
       		CF.rendTpl("tpl_portalLeftTab",data);
       		CF.rendTpl("tpl_portalLeftContent",data);
       		initComps();
       		$.get(SiteConf.AROOT+"/portalInfo?portalType=right&page=-1",null,function(resp){
           		var data = resp;
           		CF.rendTpl("tpl_portalRightContent",data);
           		$("#indexContent").tabs();
    		},"json");
		},"json");
		
	}
	
	function initComps(){
		 var lis=$(".indexMenu li a");
		    
		 lis[0].className='isClick';

		    $(".indexMenu li a").click(function () {
		        var lis=$(".indexMenu li a");

		        for (var i=0; i<lis.length; i++){
		            var lisa=lis[i];
		            if (lisa == this){
		                lisa.className='isClick';
		            } else {
		                lisa.className='';
		            }
		        }
		    });
		   
	}
	
	
	
});

