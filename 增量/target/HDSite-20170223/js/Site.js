var SiteConf = {};
SiteConf.AROOT = "/admin";
SiteConf.prefix = location.protocol + '//' + location.hostname + (location.port == '80' ? '' : ':' + location.port);
var CF = {};

CF._tpl_footer="<div class='footer'>"
			+"<div class='footDiv'>"
				+"<p>$${copyrightText}<br/>联系我们 电话:${zipCode} 地址:${address}</p>"
				+"<a href='index.html'><img src='images/logo_small.jpg'></a>"
			+"</div>"
		+"</div>";
CF.initFooter = function(){
	$.get(SiteConf.AROOT+"/contactInfo",null,function(resp){
       		var data = resp.data;
       		var html = juicer(CF._tpl_footer,data);
       		$("body").append($(html));
       		
    },"json");
};

CF.initFirstPageInfo = function(pageCode){
	$.get(SiteConf.AROOT+"/firstPageInfo/"+pageCode,null,function(resp){
   		var data = resp.data;
   		CF.rendTpl("tpl_headText",data);
   		
	},"json");
}

CF.queryArticleInfo = function(articleType,callBack){
	$.get(SiteConf.AROOT+"/articleInfo?page=-1&articleType="+articleType,null,function(resp){
   		var data = resp;
   		if(callBack){
   			callBack(data);
   		}
	},"json");
}

CF.rendTpl = function(tplElId,data,$appendTargetEl){
	var $tplEl = $("#"+tplElId);
	var tpl = juicer($tplEl.html());
	var html = tpl.render(data);
	if(!$appendTargetEl)
	{
//		$appendTargetEl = $("#"+tplElId).parent();
		$tplEl.before($(html));
		
	}else{
		$appendTargetEl.append($(html));
	}
}

CF.getImgURL = function(imgInfo){
	if(!imgInfo)
		return "";
	return "/staticcontent/"+imgInfo.path+"/"+imgInfo.fileName+"?"+CF._getTimeStr(imgInfo.lastModTime);
}

CF._getTimeStr = function(str){
	str = str.replace(new RegExp(/(-)/g),'');
	str = str.replace(new RegExp(/(:)/g),'');
	str = str.replace(new RegExp(/( )/g),'');
	str = str.replace(new RegExp(/(\.)/g),'');
	return str;
};

CF.initMenu = function(){
	
	$.get(SiteConf.AROOT+"/articleInfoVOAll",null,function(resp){
		var data = resp.data;
		if(data.about_us.length>0)
			CF.rendTpl("tpl_menu_about_us",{data:data.about_us});
		if(data.business_areas.length>0)
			CF.rendTpl("tpl_menu_business_areas",{data:data.business_areas});
		if(data.invst_case.length>0)
			CF.rendTpl("tpl_menu_investment_case",{data:data.invst_case});
		if(data.invst_relation.length>0)
			CF.rendTpl("tpl_menu_investor_relations",{data:data.invst_relation});
		
		
	},"json");
}

CF.jumpMenu = function(){
	var tarMenuId = CF._getQueryString("menu");
	if(tarMenuId){
		$("#"+tarMenuId).trigger("click");
	}
}


CF._getQueryString = function(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
} 

CF.fixPage = function(){
	setTimeout(function(){
		var $menuList = $(".aboutMenu");
		var menuHeight = $menuList.height();
		var leftHeight = menuHeight+120;
		
		var $right = $(".aContent:visible");
		var rightHeight = $right.height();
		if(rightHeight<=leftHeight){
			$right.css("height",leftHeight+"px");
		}else{
			$right.css("height","100%")
		}
	},1500);
	
}

CF._menuHTML = 

	"<li><a style='border-radius: 4px 0px 0px 4px;width: 60px;' href='index.html'>首页</a></li>" +
	"<li><a  href='about_us.html'>关于我们</a>" +
	"  <ul class='subMenu'>" + 
	"    <script id='tpl_menu_about_us' type='text/template'>" + 
	"      {@each data as p,index}" + 
	"        <li><a href='about_us.html{@if index!=0}?menu=${p.id}{@/if}'>${p.articleName}</a></li>" + 
	"      {@/each}" + 
	"    </script>" + 
	"  </ul>" + 
	"</li>" + 
	"<li><a  href='investment_team.html'>投资团队</a>" + 
	"  <ul class='subMenu'>" + 
	"    <li><a href='investment_team.html'>创始合伙人</a></li>" + 
	"    <li><a href='investment_team.html?menu=t2'>管理团队</a></li>" + 
	"    <li><a href='investment_team.html?menu=t3'>员工风采</a></li>" + 
	"  </ul>" + 
	"</li>" + 
	"<li><a href='business_areas.html'>业务领域</a>" + 
	"  <ul class='subMenu'>" + 
	"    <script id='tpl_menu_business_areas' type='text/template'>" + 
	"      {@each data as p,index}" + 
	"        <li><a href='business_areas.html{@if index!=0}?menu=${p.id}{@/if}'>${p.articleName}</a></li>" + 
	"      {@/each}" + 
	"    </script>" + 
	"  </ul>" + 
	"</li>" + 
	"<li><a href='investment_case.html'>投资案例</a>" + 
	"  <ul class='subMenu'>" + 
	"    <script id='tpl_menu_investment_case' type='text/template'>" + 
	"      {@each data as p,index}" + 
	"        <li><a href='investment_case.html{@if index!=0}?menu=${p.id}{@/if}'>${p.articleName}</a></li>" + 
	"      {@/each}" + 
	"    </script>" + 
	"  </ul>" + 
	"</li>" + 
	"<li><a href='investor_relations.html'>投资者关系</a>" + 
	"  <ul class='subMenu'>" + 
	"    <script id='tpl_menu_investor_relations' type='text/template'>" + 
	"      {@each data as p,index}" + 
	"        <li><a href='investor_relations.html{@if index!=0}?menu=${p.id}{@/if}'>${p.articleName}</a></li>" + 
	"      {@/each}" + 
	"    </script>" + 
	"  </ul>" + 
	"</li>" + 
	"<li><a style='width: 110px;' href='infomation_center.html'>资讯中心</a>" + 
	"  <ul class='subMenu'>" + 
	"    <li><a href='infomation_center.html'>行业资讯</a></li>" + 
	"    <li><a href='infomation_center.html?menu=in2'>弘道资讯</a></li>" + 
	"    <li><a href='infomation_center.html?menu=in3'>招贤纳士</a></li>" + 
	"    <li><a href='infomation_center.html?menu=in4'>联系我们</a></li>" + 
	"  </ul>" + 
	"</li>";



{
	juicer.register('imgFormater', CF.getImgURL); //注册自定义函数
}