
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
		MANAGE_URL: "<%=root%>/admin/intro",
		LIST_URL: function(page){
			return "<%=root%>/admin/timeLineInfo?page="+page;
		},
		MANAGE_ITEM_URL:"<%=root%>/admin/timeLineInfo",
		MANAGE_ITEM_DELETE_URL:function(id){
			return "<%=root%>/admin/timeLineInfo/"+id;
		}
	};
</script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-wysiwyg.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-multiselect-master/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-paginator-master/build/bootstrap-paginator.min.js"></script>
<script type="text/javascript" src="<%=root%>/js/admin/intro.js"></script>
<h2>历程</h2>
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
			<th>时间</th>
			<th>内容</th>
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
						<label class="col-lg-2 control-label">时间点</label>
						<div class="col-lg-8">
							<input type="text" id="date" class="form-control" required="required" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">内容</label>
						<div class="col-lg-8">
							<input type="text" id="content" class="form-control" required="required" />
						</div>
					</div>
				</form>
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
<h2>公司简介</h2>
<div class="panel panel-success" id="editor">
	<div class="panel-heading">
		<h3 class="panel-title" id="editorTitle">公司简介</h3>
	</div>
	<div class="panel-body">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label for="edit_title" class="col-lg-2 control-label">文本简介</label>
				<div class="col-lg-6">
					<textarea rows="10" class="form-control" id="edit_introText" required="required"></textarea>
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
