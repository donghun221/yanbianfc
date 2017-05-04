
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
		ATTACH_UPLOAD_URL:"<%=root%>/admin/attach",
		ATTACH_PATH:"<%=AttachDir.PRDINFO%>",
		MANAGE_ITEM_URL:"<%=root%>/admin/prdInfo",
		LIST_URL: function(page){
			return "<%=root%>/admin/prdInfo?page="+page;
		},
		MANAGE_ITEM_DELETE_URL:function(id){
			return "<%=root%>/admin/prdInfo/"+id;
		},
		
		MANAGE_ITEM_SUB_URL:"<%=root%>/admin/prdImageInfo",
		LIST_SUB_URL: function(prdId){
			return "<%=root%>/admin/prdImageInfo?prdId="+prdId;
		},
		MANAGE_ITEM_SUB_DELETE_URL:function(id){
			return "<%=root%>/admin/prdImageInfo/"+id;
		}
	};
</script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-wysiwyg.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-multiselect-master/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-paginator-master/build/bootstrap-paginator.min.js"></script>
<script type="text/javascript" src="<%=root%>/js/admin/prd.js"></script>
<h2>产品</h2>
<div class="well">
	<form class="form-inline" role="form">
		<div class="form-group" style="margin-left:1em">
			<button type="button" class="btn btn-success" id="insert"><i class="glyphicon glyphicon-floppy-save"></i>新增</button>
		</div>
	</form>
</div>
<table class="table table-hover" id="list">
	<thead>
		<tr>
			<th>#</th>
			<th>名称</th>
			<th>LOGO</th>
			<th>描述</th>
			<th style="width: 8em"></th>
		</tr>
	</thead>
	<tbody>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7" class="text-right">
				<div class="pull-right">
					<div class="pull-left">
						<ul id="pagination" style="margin: 0 2em"></ul>
					</div>
					<div class="input-group">
						<input type="number" min="1" class="form-control" id="pageJump" style="width: 6em" />
						<span class="input-group-addon"> / <span id="pageCount"></span></span>
						<span class="input-group-btn">
							<button type="button" id="pageButton" class="btn btn-default">转到</button>
						</span>
					</div>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<!-- 新增、编辑 -->
<div class="modal fade" id="editModal" data-backdrop="true" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top: 1em">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<input type="text" style="display:none" name="id" id="id"/>
					<div class="form-group">
						<label class="col-lg-2 control-label">名称</label>
						<div class="col-lg-10">
							<input type="text" id="name" class="form-control" required="required" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">描述</label>
						<div class="col-lg-10">
							<textarea rows="10" class="form-control" id="content" required="required"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">LOGO</label>
						<div class="col-lg-9">
							<div class="input-group" id="logoImg">
								<input type="hidden" class="form-control imgId" readonly="readonly"/>
								<input type="text" submit="false" class="form-control imgName" readonly="readonly"/>
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
				</form>
			</div>
			<div style="padding-left: 45px">
				<label class="col-lg-2 control-label">产品图片</label>
				<a id="addImg" href="#">+增加</a>
			</div>
			<div  class="modal-body" id="imgListDiv" style="padding-left: 10%;">
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" data-dismiss="modal" id="submit">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

<!-- 删除确认弹出框 -->
<div class="modal fade" id="removeModal" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<h5>确认删除这条记录吗？</h5>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" id="remove">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

<!-- 查看介绍弹出框 -->
<div class="modal fade" id="contentModal" data-backdrop="true" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top: 1em">
	<div class="modal-dialog" style="width: 60%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>