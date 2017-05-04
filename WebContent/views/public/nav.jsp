<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="page-header"
	style="margin: 0; padding: .4em .8em; padding-top: .8em; background: #333; border: none; position: fixed; width: 100%; z-index: 999">
	<div class="pull-right" style="margin-right: 2em; color: #FFF">
		<span class="glyphicon glyphicon-user"> </span> 欢迎，<%=(String) session.getAttribute("username")%>
		<a href="logout" class="text-center" style="color: #FFF"><i
			class="glyphicon glyphicon-off"></i></a>
	</div>
	<h1 style="color: #FFF; margin-top: 0; font-size: 26px">
		网站管理
	</h1>
</div>