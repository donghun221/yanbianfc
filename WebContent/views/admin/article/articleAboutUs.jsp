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
<link rel="stylesheet" href="<%=root%>/lib/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="<%=root%>/lib/kindeditor/plugins/code/prettify.css" />
<script type="text/javascript">
var EnvConfig={
		ROOT: "<%=root%>/admin/",
		ATTACH_UPLOAD_URL:"<%=root%>/admin/attach",
		ATTACH_UPLOAD_URL_KINDEDITOR:"<%=root%>/admin/attach/kindEditor",
		ATTACH_PATH:"<%=AttachDir.ARTICLEINFO%>",
		MANAGE_ITEM_URL:"<%=root%>/admin/articleInfo",
		LIST_URL: function(page){
			return "<%=root%>/admin/articleInfo?articleType=about_us&page="+page;
		},
		ARTICLE_TYPE:"about_us",
		MANAGE_ITEM_DELETE_URL:function(id){
			return "<%=root%>/admin/articleInfo/"+id;
		}
	};
</script>


<script charset="utf-8" src="<%=root%>/lib/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="<%=root%>/lib/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=root%>/lib/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-multiselect-master/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-paginator-master/build/bootstrap-paginator.min.js"></script>
<script type="text/javascript" src="<%=root%>/js/admin/article.js"></script>
<h2>关于我们</h2>
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
			<th>序号</th>
			<th style="width: 14em"></th>
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
<div class="modal fade" id="editModal" data-backdrop="true" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop=”static” style="padding-top: 1em">
	<div class="modal-dialog" style="width:1000px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<input type="text" style="display:none" name="id" id="id"/>
					<input type="text" style="display:none" name="articleType" id="articleType"/>
					
					<div class="form-group">
						<label class="col-lg-1 control-label">名称</label>
						<div class="col-lg-5">
							<input type="text" id="articleName" length="9" class="form-control" required="required" inputName="名称" />
						</div>
						<label class="col-lg-1 control-label">序号</label>
						<div class="col-lg-5">
							<input type="number" value="0" style="width:70px;" min="0" id="orderNum" class="form-control" required="required" inputName="序号" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-1 control-label">内容</label>
						<div class="col-lg-12">
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-12">
								<textarea id="contentText" name="content" style="width:100%;height:400px;">
								</textarea>
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
				<div id="contentPreView"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>