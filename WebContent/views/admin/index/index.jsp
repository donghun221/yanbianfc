
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
		MANAGE_URL: "<%=root%>/admin/contactInfo",
		ATTACH_UPLOAD_URL:"<%=root%>/admin/attach",
		ATTACH_PATH:"<%=AttachDir.INDEXINFO%>"
	};
</script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-wysiwyg.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-multiselect-master/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-paginator-master/build/bootstrap-paginator.min.js"></script>
<script type="text/javascript" src="<%=root%>/js/admin/index.js"></script>
<h2>基本信息</h2>


<div class="panel panel-success" id="editor">
	<div class="panel-heading">
		<h3 class="panel-title" id="editorTitle">信息设置</h3>
	</div>
	<div class="panel-body">
		<form class="form-horizontal" role="form" id="submitForm">
			<div class="form-group">
				<label for="edit_copyrightText" class="col-lg-2 control-label">版权信息</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="copyrightText" required="required" value="" />
				</div>
			</div>
			<div class="form-group">
				<label for="edit_zipCode" class="col-lg-2 control-label">电话</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="zipCode" required="required" inputName="电话" value="" />
				</div>
			</div>
			
			<div class="form-group">
				<label for="edit_fax" class="col-lg-2 control-label">传真</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="fax" required="required" inputName="传真" value="" />
				</div>
			</div>
			<div class="form-group">
				<label for="edit_email" class="col-lg-2 control-label">邮箱</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="email" required="required" inputName="邮箱" value="" />
				</div>
			</div>
			
			<div class="form-group">
				<label for="edit_address" class="col-lg-2 control-label">地址</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" id="address" required="required" inputName="地址" value="" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-lg-2 control-label">地图图片</label>
				<div class="col-lg-4">
					<div class="input-group" id="weiboImg">
						<input type="hidden" class="form-control imgId" readonly="readonly"/>
						<input type="text" class="form-control imgName" readonly="readonly"/>
						<span class="input-group-btn">
							<button type="button" class="btn btn-default uploadBtn"><span class="glyphicon glyphicon-open"></span>上传图片</button>
						</span>
						<span style="position: relative;padding-left: 10px;">
							<a href="#" id="showImg">查看</a>
							<img style="position: absolute;left:80px;top:20px;max-width: 500px;" id="showImgImg"></img>
						</span>
					</div>
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
