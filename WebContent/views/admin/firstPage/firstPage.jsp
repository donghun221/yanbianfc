
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
		ATTACH_PATH:"<%=AttachDir.FIRSTPAGEINFO%>",
		MANAGE_ITEM_URL:"<%=root%>/admin/firstPageInfo",
		LIST_URL: function(page){
			return "<%=root%>/admin/firstPageInfo?page="+page;
		},
		MANAGE_ITEM_DELETE_URL:function(id){
			return "<%=root%>/admin/firstPageInfo/"+id;
		}
	};
</script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-wysiwyg.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-multiselect-master/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-paginator-master/build/bootstrap-paginator.min.js"></script>
<script type="text/javascript" src="<%=root%>/js/admin/firstPage.js"></script>
<h2>页面标题</h2>
<div class="well" style="display:none;">
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
			<th>页面名称</th>
			<th>主标题</th>
			<th style="width: 8em"></th>
		</tr>
	</thead>
	<tbody>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7" class="text-right" style="display:none;">
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
					<input type="text" style="display:none" name="pageCode" id="pageCode"/>
					<input type="text" style="display:none" name="titleText" id="titleText"/>
					
					<div class="form-group">
						<label class="col-lg-2 control-label">页面名称</label>
						<div class="col-lg-10">
							<input type="text" readOnly="readOnly" id="pageName" class="form-control" required="required" inputName="页面名称" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">主标题</label>
						<div class="col-lg-10">
							<input type="text" id="headText" class="form-control" required="required" inputName="主标题"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-2 control-label">副标题</label>
						<div class="col-lg-10">
							<input type="text" id="subText" class="form-control" required="required" inputName="副标题"/>
						</div>
					</div>
					
				</form>
			</div>
				
			<div class="modal-footer">
				<span style="color:red" id="errorMsgSpan"></span>
				<button type="button" class="btn btn-success" id="submit">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal" id="closeBtn">取消</button>
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