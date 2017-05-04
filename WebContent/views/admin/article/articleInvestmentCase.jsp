
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
		ATTACH_PATH:"<%=AttachDir.ARTICLEINFO%>",
		MANAGE_ITEM_URL:"<%=root%>/admin/articleInfo",
		LIST_URL: function(page){
			return "<%=root%>/admin/articleInfo?articleType=invst_case&page="+page;
		},
		ARTICLE_TYPE:"invst_case",
		MANAGE_ITEM_DELETE_URL:function(id){
			return "<%=root%>/admin/articleInfo/"+id;
		}
	};
</script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-wysiwyg.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-multiselect-master/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-paginator-master/build/bootstrap-paginator.min.js"></script>
<script type="text/javascript" src="<%=root%>/js/admin/article.js"></script>
<h2>投资案例</h2>
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
					<input type="text" style="display:none" name="articleType" id="articleType"/>
					
					<div class="form-group">
						<label class="col-lg-2 control-label">名称</label>
						<div class="col-lg-10">
							<input type="text"  length="9" id="articleName" class="form-control" required="required" inputName="名称" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">序号</label>
						<div class="col-lg-10">
							<input type="number" value="0" style="width:70px;" min="0" id="orderNum" class="form-control" required="required" inputName="序号" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">内容</label>
						<div class="col-lg-10">
							
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-12">
							<div class="btn-toolbar" data-role="editor-toolbar" data-target="#contentText">
								<div class="btn-group">
									<button type="button" class="btn btn-default" data-edit="justifyleft" title="" data-original-title="左对齐"><i class="fa fa-align-left"></i></button>
									<button type="button" class="btn btn-default" class="btn" data-edit="justifycenter" title="" data-original-title="居中"><i class="fa fa-align-center"></i></button>
									<button type="button" class="btn btn-default" class="btn" data-edit="justifyright" title="" data-original-title="右对齐"><i class="fa fa-align-right"></i></button>
									<button type="button" class="btn btn-default" class="btn" data-edit="justifyfull" title="" data-original-title="分散对齐"><i class="fa fa-align-justify"></i></button>
								</div>
								<div class="btn-group">
									<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="" data-original-title="字体"><i class="fa fa-font"></i><b class="caret"></b></button>
									<ul class="dropdown-menu">
										<li><a data-edit="fontName 微软雅黑" style="font-family:'微软雅黑'">微软雅黑</a></li>
										<li><a data-edit="fontName 宋体" style="font-family:'宋体'">宋体</a></li>
										<li><a data-edit="fontName 幼圆" style="font-family:'幼圆'">幼圆</a></li>
										<li><a data-edit="fontName 黑体" style="font-family:'黑体'">黑体</a></li>
									</ul>
								</div>
								<div class="btn-group">
									<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="" data-original-title="尺寸"><i class="fa fa-text-height"></i><b class="caret"></b></button>
									<ul class="dropdown-menu">
										<li><a data-edit="fontSize 5">大</a></li>
										<li><a data-edit="fontSize 3">中</a></li>
										<li><a data-edit="fontSize 1">小</a></li>
									</ul>
								</div>
								<div class="btn-group">
									<button type="button" class="btn btn-default" data-edit="bold" title="" data-original-title="粗体"><i class="fa fa-bold"></i></button>
									<button type="button" class="btn btn-default" data-edit="italic" title="" data-original-title="斜体"><i class="fa fa-italic"></i></button>
									<button type="button" class="btn btn-default" data-edit="strikethrough" title="" data-original-title="删除线"><i class="fa fa-strikethrough"></i></button>
									<button type="button" class="btn btn-default" data-edit="underline" title="" data-original-title="下划线"><i class="fa fa-underline"></i></button>
								</div>
								<!-- <div class="btn-group">
									<button type="button" class="btn btn-default" data-edit="insertunorderedlist" title="" data-original-title="无序列表"><i class="fa fa-list-ul"></i></button>
									<button type="button" class="btn btn-default" data-edit="insertorderedlist" title="" data-original-title="有序列表"><i class="fa fa-list-ol"></i></button>
									<button type="button" class="btn btn-default" data-edit="outdent" title="" data-original-title="反缩进"><i class="fa fa-indent"></i></button>
									<button type="button" class="btn btn-default" data-edit="indent" title="" data-original-title="缩进"><i class="fa fa-outdent"></i></button>
								</div> -->
								<div class="btn-group">
									<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="" data-original-title="超链接"><i class="fa fa-link"></i></button>
									<div class="dropdown-menu" role="menu">
										<div class="input-group input-group-sm">
											<input type="text" class="form-control" placeholder="URL" data-edit="createLink">
											<div class="input-group-btn">
												<button class="btn btn-success" type="button">添加</button>
											</div>
										</div>
									</div>
								</div>
								<div class="btn-group">
									<button type="button" class="btn btn-default" data-edit="undo" title="" data-original-title="撤销"><i class="fa fa-undo"></i></button>
									<button type="button" class="btn btn-default" data-edit="redo" title="" data-original-title="重做"><i class="fa fa-repeat"></i></button>
								</div>
								
							</div>
							<div id="contentText" class="rtx-editor form-control" contenteditable="true"></div>
							<div class="form-group contentEditor" id="imageEditor">
								<br/>
									<div class="col-lg-6">
										<div id="addImageBtnsDiv">
										</div>
										<button type="button" id="addImageItem" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> 添加图片</button>
									</div>
							</div>
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