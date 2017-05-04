<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../public/include.jsp"%>
<title>中银e服务后台管理系统-错误页面</title>
</head>
<body>
	<%@ include file="../public/nav.jsp"%>
	<div class="container main">
		<div class="col-sm-3"><%@ include file="../public/menu.jsp"%></div>
		<div class="col-sm-9">
			<div class="hero">
				<h1>错误</h1>
				<p><%=request.getAttribute("errmsg")%></p>
			</div>
		</div>
	</div>
</body>
</html>