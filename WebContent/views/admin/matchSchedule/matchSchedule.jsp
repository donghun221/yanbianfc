
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
<link rel="stylesheet" href="<%=root%>/lib/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css">
<script type="text/javascript">
var EnvConfig={
		ROOT: "<%=root%>/admin/",
		ATTACH_UPLOAD_URL:"<%=root%>/admin/attach/single?path=upimg",
		ATTACH_PATH:"<%=AttachDir.ATTACH_BASE_DIR%>",
		MANAGE_ITEM_URL:"<%=root%>/admin/matchSchedule",
		LIST_URL: function(page){
			return "<%=root%>/admin/matchSchedule?page="+page;
		},
		MANAGE_ITEM_DELETE_URL:function(id){
			return "<%=root%>/admin/matchSchedule/"+id;
		}
	};
</script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-wysiwyg.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-multiselect-master/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-paginator-master/build/bootstrap-paginator.min.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=root%>/js/admin/matchSchedule.js"></script>
<h2>赛程</h2>
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
			<th>赛程名称</th>
			<th>对手</th>
			<th>时间</th>
			<th>地点</th>
			<th>主客场</th>
			<th>比赛结果</th>
			<th>比分</th>
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
						<label class="col-lg-2 control-label">对手</label>
						<div class="col-lg-4">
							<select class="form-control selectpicker"  required="required" inputName="对手信息" data-live-search="true" id="teamInfo.id" style="width: 12em">
							</select>
						</div>
						<label class="col-lg-2 control-label">赛程名称</label>
						<div class="col-lg-4">
							<input type="text"  id="matchName" class="form-control" required="required" inputName="赛程名称"/>
						</div>
					</div>
					
					<div class="form-group">
						
						<label class="col-lg-2 control-label">时间</label>
						<div class="col-lg-6">
							<div class="input-group date matchTime" data-date="" data-date-format="yyyy-mm-dd HH:ii" data-link-format="yyyy-mm-dd HH:ii">
			                    <input class="form-control" size="30" id="matchTime" type="text" value="" readonly>
			                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
			                </div>
							<!-- <input type="text" style="display: none;" id="matchTime" value="" required="required" inputName="比赛时间"/><br/> -->
						</div>
						<!-- <div class="col-lg-4">
							<input type="number" min="0" id="roundTime" class="form-control" required="required" inputName="时间"/>
						</div> -->
					</div>
					
					
					<div class="form-group">
						
						<label class="col-lg-2 control-label">地点</label>
						<div class="col-lg-4">
							<input type="text"  id="matchPlace" class="form-control" required="required" inputName="地点"/>
						</div>
						
						<label class="col-lg-2 control-label">主客场</label>
						<div class="col-lg-4">
							<select class="form-control selectpicker"  required="required" inputName="主客场" data-live-search="false" id="isLocal" style="width: 12em">
								<option value="">请选择</option>
								<option value="1">主场</option>
								<option value="0">客场</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-2 control-label">比赛结果</label>
						<div class="col-lg-4">
							<select class="form-control selectpicker"  inputName="比赛结果" data-live-search="false" id="matchResult" style="width: 12em">
								<option value="">请选择</option>
								<option value="1">胜</option>
								<option value="0">败</option>
							</select>
						</div>
						<label class="col-lg-2 control-label">比分</label>
						<div class="col-lg-4">
							<input type="text" id="score" class="form-control"  inputName="比分"/>
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