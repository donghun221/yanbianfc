<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% String root=request.getContextPath(); %>

<link rel="stylesheet" href="<%=root%>/lib/bootstrap3.0.2/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=root%>/lib/terebentina-sco.js-b2e291b/css/scojs.css">
<link rel="stylesheet" href="<%=root%>/lib/silviomoreto-bootstrap-select-9656fcf/bootstrap-select.min.css">
<link rel="stylesheet" href="<%=root%>/lib/iCheck-master/skins/square/blue.css">
<link rel="stylesheet" href="<%=root%>/lib/bootstrap-switch-master/dist/css/bootstrap3/bootstrap-switch.min.css">
<link rel="stylesheet" href="<%=root%>/lib/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="<%=root%>/css/common.css">

<script type="text/javascript" src="<%=root%>/lib/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=root%>/lib/jquery.scrollto.js"></script>
<script type="text/javascript" src="<%=root%>/lib/terebentina-sco.js-b2e291b/js/sco.message.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap3.0.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=root%>/lib/silviomoreto-bootstrap-select-9656fcf/bootstrap-select.min.js"></script>
<script type="text/javascript" src="<%=root%>/lib/iCheck-master/icheck.min.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-switch-master/dist/js/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=root%>/lib/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=root%>/js/dict.js"></script>
<script type="text/javascript" src="<%=root%>/js/common.js"></script>

<!-- Ajax请求错误弹出框 -->
<div class="modal fade" id="ajaxErrorModal" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">请求发生异常</h4>
			</div>
			<div class="modal-body">
				<h5 class="alert alert-danger"></h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
			</div>
		</div>
	</div>
</div>
<!-- Ajax请求错误弹出框 -->

<!-- 数据请求返回错误消息弹出框 -->
<div class="modal fade" id="bizErrorModal" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<h5 class="alert alert-warning"></h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
			</div>
		</div>
	</div>
</div>
<!-- 数据请求返回错误消息弹出框 -->