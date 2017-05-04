<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../public/include.jsp"%>
<title>网站后台管理</title>
</head>
<body>
	<%@ include file="../public/nav.jsp"%>
	<div class="container main" style="width: 100%; margin-left: 0; padding-left: 0">
		<div class="center-block">
			<div class="col-lg-2" id="menuFrame" style="margin-left: 0; margin-top:0px; padding-left: 0; padding-right: 0; background: #3A3A3A; position: fixed; height: 100%; overflow-y: auto"><%@ include file="../public/menu.jsp"%></div>
			<div class="col-lg-10" id="mainFrame" style="margin-left: 16.666666666666664%; margin-top: 55px">
				<h3><span class="glyphicon glyphicon-hand-left"></span> 请在左侧选择一个功能来开始您的工作！</h3>
			</div>
		</div>
	</div>
</body>
</html>