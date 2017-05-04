
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
		ATTACH_UPLOAD_URL:"<%=root%>/admin/attach/single?path=upimg",
		ATTACH_PATH:"<%=AttachDir.ATTACH_BASE_DIR%>",
		MANAGE_ITEM_URL:"<%=root%>/admin/teamScore",
		LIST_URL: function(page){
			return "<%=root%>/admin/teamScore?page="+page;
		},
		MANAGE_ITEM_DELETE_URL:function(id){
			return "<%=root%>/admin/teamScore/"+id;
		}
	};
</script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-wysiwyg.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-multiselect-master/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-paginator-master/build/bootstrap-paginator.min.js"></script>
<script type="text/javascript" src="<%=root%>/js/admin/teamScore.js"></script>
<h2>球队积分</h2>
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
			<th>排名</th>
			<th>队名</th>
			<th>队标</th>
			<th>场次</th>
			<th>胜</th>
			<th>平</th>
			<th>负</th>
			<th>进</th>
			<th>失</th>
			<th>净胜</th>
			<th>积分</th>
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
			<input type="hidden" id="pointOffset"/>
			<input type="hidden" id="totalScore"/>
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<input type="text" style="display:none" name="id" id="id"/>
					<div class="form-group">
						<label class="col-lg-2 control-label">队名</label>
						<div class="col-lg-10">
							<input type="text" id="name" class="form-control" required="required" inputName="队名" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-2 control-label">队标</label>
						<div class="col-lg-9">
							<div class="input-group" id="logoImg">
								<input type="hidden" class="form-control imgId" readonly="readonly"/>
								<input type="text" submit="false" class="form-control imgName" readonly="readonly" required="required" inputName="图片"/>
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
						<label class="col-lg-2 control-label">场次</label>
						<div class="col-lg-4">
							<input type="number" min="0" id="roundTime" class="form-control" required="required" inputName="场次"/>
						</div>
						<label class="col-lg-2 control-label">胜</label>
						<div class="col-lg-4">
							<input type="number" min="0" id="win" class="form-control" required="required" inputName="胜"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-2 control-label">平</label>
						<div class="col-lg-4">
							<input type="number" min="0" id="draw" class="form-control" required="required" inputName="平"/>
						</div>
						<label class="col-lg-2 control-label">负</label>
						<div class="col-lg-4">
							<input type="number" min="0" id="lose" class="form-control" required="required" inputName="负"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-2 control-label">进球</label>
						<div class="col-lg-4">
							<input type="number" min="0" id="pointWin" class="form-control" required="required" inputName="进球"/>
						</div>
						<label class="col-lg-2 control-label">失球</label>
						<div class="col-lg-4">
							<input type="number" min="0" id="pointLose" class="form-control" required="required" inputName="失球"/>
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