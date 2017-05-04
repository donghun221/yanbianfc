
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
		MANAGE_URL: "<%=root%>/admin/welcomeInfo",
		ATTACH_UPLOAD_URL:"<%=root%>/admin/attach",
		ATTACH_PATH:"<%=AttachDir.WELCOMEINFO%>"
	};
</script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-wysiwyg.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-multiselect-master/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-paginator-master/build/bootstrap-paginator.min.js"></script>
<script type="text/javascript" src="<%=root%>/js/admin/welcome.js"></script>
<h2>欢迎页管理</h2>


<div class="panel panel-success" id="editor">
	<div class="panel-heading">
		<h3 class="panel-title" id="editorTitle">欢迎页管理</h3>
	</div>
	<div class="panel-body">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label for="edit_showtime" class="col-lg-2 control-label">展示时间(秒)</label>
				<div class="col-lg-3">
					<input type="text" class="form-control" id="edit_showTime" required="required" value="" />
				</div>
			</div>
			
			
			<div class="form-group">
				<label for="edit_imagebg" class="col-lg-2 control-label">背景图片</label>
				<div class="col-lg-4">
					<div class="input-group" id="backGround">
						<input type="hidden" class="form-control imgId" readonly="readonly" />
						<input type="text" class="form-control imgName" readonly="readonly" />
						<span class="input-group-btn">
							<button type="button" class="btn btn-default uploadBtn"><span class="glyphicon glyphicon-open"></span>上传图片</button>
						</span>
						<span style="position: relative;padding-left: 10px;">
							<a href="#" id="showImg">查看</a>
							<img style="position: absolute;left:80px;top:20px;max-width: 500px;z-index:999" id="showImgImg"></img>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label">logo图片</label>
				<div class="col-lg-4">
					<div class="input-group" id="companyLogo"> 
						<input type="hidden" class="form-control imgId" readonly="readonly"/>
						<input type="text" class="form-control imgName" readonly="readonly"/>
						<span class="input-group-btn">
							<button type="button" class="btn btn-default uploadBtn"><span class="glyphicon glyphicon-open"></span>上传图片</button>
						</span>
						<span style="position: relative;padding-left: 10px;">
							<a href="#" id="showImg">查看</a>
							<img style="position: absolute;left:80px;top:20px;max-width: 500px;z-index:999" id="showImgImg"></img>
						</span>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label for="edit_footContent" class="col-lg-2 control-label">页脚内容</label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="edit_footContent" required="required" value="" />
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

