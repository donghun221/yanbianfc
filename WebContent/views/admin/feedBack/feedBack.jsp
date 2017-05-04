
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
		MANAGE_ITEM_URL:"<%=root%>/admin/feedBackInfo",
		LIST_URL: function(page){
			return "<%=root%>/admin/feedBackInfo?page="+page;
		},
		MANAGE_ITEM_DELETE_URL:function(id){
			return "<%=root%>/admin/feedBackInfo/"+id;
		}
	};
</script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-wysiwyg.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-multiselect-master/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-paginator-master/build/bootstrap-paginator.min.js"></script>
<script type="text/javascript" src="<%=root%>/js/admin/feedBack.js"></script>
<h2>留言管理</h2>
<div class="well">
	<form class="form-inline" role="form">
		<div class="form-group" style="margin-left:1em">
			<label>已读:  </label>
			<select class="form-control selectpicker" data-live-search="false" name="status" style="width: 6em">
				<option value="1" data-id="-1">否</option>
				<option value="0" data-id="-1">是</option>
			</select>
		</div>
		<div class="form-group" style="margin-left:1em">
			<button type="button" class="btn btn-primary" id="queryButton"><i class="glyphicon glyphicon-search"></i> 查询</button>
		</div>
	</form>
</div>
<table class="table table-hover" id="list">
	<thead>
		<tr>
			<th>#</th>
			<th>姓名</th>
			<th>email</th>
			<th>日期</th>
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
						<label class="col-lg-2 control-label">姓名</label>
						<div class="col-lg-10">
							<input type="text" id="userName" readOnly="readOnly" class="form-control" required="required" inputName="名称" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">email</label>
						<div class="col-lg-10">
							<input type="text" id="email" readOnly="readOnly" class="form-control" required="required" inputName="网址"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">日期</label>
						<div class="col-lg-10">
							<input type="text" id="createDate" readOnly="readOnly" class="form-control" required="required" inputName="日期"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">留言</label>
						<div class="col-lg-10">
							<textarea rows="3" readOnly="readOnly" class="form-control" id="contentText" required="required" inputName="留言"></textarea>
						</div>
					</div>
				</form>
			</div>
				
			<div class="modal-footer">
				<span style="color:red" id="errorMsgSpan"></span>
				<button type="button" class="btn btn-success" id="submit">标记为已读</button>
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