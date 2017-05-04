<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title></title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script src="js/jquery.js" language="javascript" type="text/javascript"></script>
	<script language="javascript" type="text/javascript" src="js/ss.js"></script>
	<script type="text/javascript">

	</script>
</head>
<body>
	<div class="header">
		<div class="headDiv">
			<a href="index.html"><img class="logo" src="images/logo.jpg"/></a>
			<ul class="menu">
				<li><a href="index.html">首页</a></li>
				<li><a href="about_us.html">关于我们</a>
					<ul class="subMenu">
						<li><a href="about_us.html">公司概况</a></li>
						<li><a href="about_us.html">企业文化</a></li>
						<li><a href="about_us.html">总裁致辞</a></li>
					</ul>
				</li>
				<li><a href="investment_team.html">投资团队</a>
					<ul class="subMenu">
						<li><a href="investment_team.html">创始合伙人</a></li>
						<li><a href="investment_team.html">管理团队</a></li>
						<li><a href="investment_team.html">员工风采</a></li>
					</ul>
				</li>
				<li><a href="business_areas.html">业务领域</a>
					<ul class="subMenu">
						<li><a href="business_areas.html">美元基金</a></li>
						<li><a href="business_areas.html">股权投资</a></li>
						<li><a href="business_areas.html">并购重组</a></li>
						<li><a href="business_areas.html">金控平台</a></li>
					</ul>
				</li>
				<li><a href="investment_case.html">投资案例</a>
					<ul class="subMenu">
						<li><a href="investment_case.html">美元基金</a></li>
						<li><a href="investment_case.html">股权投资</a></li>
						<li><a href="investment_case.html">并购重组</a></li>
						<li><a href="investment_case.html">金控平台</a></li>
						<li><a href="investment_case.html">其他</a></li>
					</ul>
				</li>
				<li><a href="investor_relations.html">投资者关系</a>
					<ul class="subMenu">
						<li><a href="investor_relations.html">主要LP</a></li>
						<li><a href="investor_relations.html">投资俱乐部</a></li>
						<li><a href="investor_relations.html">服务中心</a></li>
					</ul>
				</li>
				<li><a href="infomation_center.html">资讯中心</a>
					<ul class="subMenu">
						<li><a href="infomation_center.html">行业资讯</a></li>
						<li><a href="infomation_center.html">弘道资讯</a></li>
						<li><a href="infomation_center.html">招贤纳士</a></li>
						<li><a href="infomation_center.html">联系我们</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
    <div class="indexCenter">
        <div class="indexTitleDiv">
            <div class="indexTitle">
                <h1>文案文案 文案文案</h1>
                <p>文案文案 文案文案</p>
            </div>
        </div>

        <div class="content">
            <div class="indexContentLeft" id="indexContent">
                <p><span>业务领域</span>&nbsp;&nbsp;Business Areas</p>
                <ul class="indexMenu">
                    <li><a href="#bContent1">美元基金</a></li>
                    <li><a href="#bContent2">股权投资</a></li>
                    <li><a href="#bContent3">并购重组</a></li>
                    <li><a href="#bContent4">金控平台</a></li>
                </ul>
                <div class="indexLeftContent">
                    <div id="bContent1">
                        <a href="#"><img src="images/business_1.jpg"/>
                        <p>美元基金文字简介美元基金文字简介美元基金文字简介美元基金文字简介美元基金文字简介美元基金文字简介美元基金文字简介美元基金文字简介美元基金文字简介美元基金文字简介美元基金文字简介美元基金文字简介美元基金文字简介美元基金文字简介美元基金文字简介基金文字简介美元基金文字简介美元基金文字简介美元基金文字简介美元基金文字简介</p></a>
                    </div>
                    <div id="bContent2">
                        <a href="#"><img src="images/business_1.jpg"/>
                        <p>股权投资文字简介股权投资文字简介股权投资文字简介股权投资文字简介</p></a>
                    </div>
                    <div id="bContent3">
                        <a href="#"><img src="images/business_1.jpg"/>
                        <p>并购重组文字简介并购重组文字简介并购重组文字简介并购重组文字简介并购重组文字简介并购重组文字简介并购重组文字简介</p></a>
                    </div>
                    <div id="bContent4">
                        <a href="#"><img src="images/business_1.jpg"/>
                        <p>金控平台文字简介金控平台文字简介金控平台文字简介金控平台文字简介金控平台文字简介金控平台文字简介金控平台文字简介金控平台文字简介金控平台文字简介金控平台文字简介金控平台文字简介金控平台文字简介金控平台文字简介</p></a>
                    </div>
                </div>
            </div>
            <script language="javascript">
                $(document).ready(function () {
                    var lis=$(".indexMenu li a");
                    lis[0].className='isClick';
                });

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
                $("#indexContent").tabs();
            </script>
            <div class="indexContentCenter">
                <p><span>投资案例</span>&nbsp;&nbsp;Investment Case</p>
                <div id="indexSubContent_case">
                    <a href="#"><img src="images/case_1.jpg"/>
                    <p>投资案例的文字说明投资案例的文字说明投资案例的文字说明投资案例的文字说明投资案例的文字说明</p></a>
                </div>
            </div>
            <div class="indexContentRight">
                <p><span>投资团队</span>&nbsp;&nbsp;Investment Team</p>
                <div id="indexSubContent_team">
                    <a href="#"><img src="images/team_1.jpg"/>
                    <p>投资团队的文字说明投资团队的文字说明投资团队的文字说明投资团队的文字说明投资团队的文字说明投资团队的文字说明投资团队的文字说明投资团队的文字说明投资团队的文字说明</p></a>
                </div>
            </div>
            <div class="warp"></div>
            <div class="indexContentBottom">
                <p><span>合作伙伴</span>&nbsp;&nbsp;Cooperative Partner</p>
                <div class="banner">
                    <img class="bannerBtnL prev" src="images/arrow_left.jpg" width="15" height="26" />
                    <img class="bannerBtnR next" src="images/arrow_right.jpg" width="15" height="26" />
                    <div class="bannerList">
                        <ul>
                            <li><a href="www.baidu.com" target="_blank"><img src="images/partner_1.jpg" width="120" height="50" /></a></li>
                            <li><a href="www.baidu.com" target="_blank"><img src="images/partner_2.jpg" width="120" height="50" /></a></li>
                            <li><a href="www.baidu.com" target="_blank"><img src="images/partner_1.jpg" width="120" height="50" /></a></li>
                            <li><a href="www.baidu.com" target="_blank"><img src="images/partner_2.jpg" width="120" height="50" /></a></li>
                            <li><a href="www.baidu.com" target="_blank"><img src="images/partner_1.jpg" width="120" height="50" /></a></li>
                            <li><a href="www.baidu.com" target="_blank"><img src="images/partner_2.jpg" width="120" height="50" /></a></li>
                        </ul>
                    </div>

                </div>
                <script language="javascript">
                    jQuery(".banner").slide({titCell:"",mainCell:".bannerList ul",autoPage:false,effect:"leftLoop",autoPlay:false,vis:5});
                </script>
            </div>
        </div>
    </div>
	<div class="footer">
		<div class="footDiv">
			<p>版权所有 弘道天瑞资本 京ICP备00000000号 Copyright &copy;2014 hdtr.com All Right Reserved<br/>联系我们  电话:010-87654321  地址:北京市xx区xx路xx号</p>
			<a href="index.html"><img src="images/logo_small.jpg"></a>
		</div>
	</div>
</body>
</html>
