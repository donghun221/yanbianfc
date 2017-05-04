
<%@page import="com.peernet.mobile.server.admin.common.cst.AttachDir"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.nationsky.pub.utils.GsonUtils"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String root=request.getContextPath();
%>
<link rel="stylesheet" href="<%=root%>/lib/font-awesome-4.1.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=root%>/lib/bootstrap-multiselect-master/css/bootstrap-multiselect.css">
<script type="text/javascript">
var EnvConfig={
		ROOT: "<%=root%>/admin/",
		MANAGE_URL: "<%=root%>/admin/adminInfo",
		UPDATE_URL: "<%=root%>/admin/updateAdminInfo"
	};
</script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-wysiwyg.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-multiselect-master/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-paginator-master/build/bootstrap-paginator.min.js"></script>
<script type="text/javascript" src="<%=root%>/js/admin/password.js"></script>
<h2>修改密码</h2>


<div class="panel panel-success" id="editor">
	<div class="panel-heading">
		<h3 class="panel-title" id="editorTitle">修改管理员密码</h3>
	</div>
	<div class="panel-body">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label for="edit_showtime" class="col-lg-2 control-label">输入原密码</label>
				<div class="col-lg-3">
					<input type="password" class="form-control input-lg" id="old_password" required="required" value="" />
				</div>
			</div>
			<div class="form-group">
				<label for="edit_showtime" class="col-lg-2 control-label">输入新密码</label>
				<div class="col-lg-3">
					<input type="password" class="form-control input-lg" id="new_password" required="required" value="" />
				</div>
			</div>			
			<div class="form-group">
				<label for="edit_showtime" class="col-lg-2 control-label">确认新密码</label>
				<div class="col-lg-3">
					<input type="password" class="form-control input-lg" id="confirm_password" required="required" value="" />
					<input type="hidden"  id="id" required="required" value="" />
					<input type="hidden"  id="adminName" required="required" value="" />
					<input type="hidden"  id="adminPassword" required="required" value="" />
				</div>
			</div>		

			
			<div class="form-group">
				<div class="col-lg-4 col-lg-offset-2">
					<button type="button" class="btn btn-primary" id="modify"><i class="glyphicon glyphicon-floppy-disk"></i> 修改</button>
				</div>
			</div>
		</form>
	</div>
</div>

