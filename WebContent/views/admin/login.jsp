<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="../public/include.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=root%>/css/signin.css" rel="stylesheet">
<title>欢迎使用</title>
<script type="text/javascript">
      $(document).ready(function(){
    	  <% if(request.getAttribute("message")!=null){%>
    	  	$.scojs_message('<%=request.getAttribute("message")%>', $.scojs_message.TYPE_ERROR);
    	  <%}%>
    	  
    	  if ($("#menu").size()>0)
    	  {
    		  location.href="<%=root%>/login";
    	  }
      });
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-6 text-right">
				<img alt="" style="height: 175px;margin-top: 9px;" src="../images/soccer/logo.png">
			</div>
			<div class="col-lg-4 text-left">
				<form class="form-signin" action="admin/login" method="POST">
					<div class="form-group">
						<input type="text" class="form-control input-lg" name="username" id="user" placeholder="用户名" required autofocus>
					</div>
					<div class="form-group">
						<input type="password" class="form-control input-lg" name="password" id="pwd" placeholder="密码" required>
					</div>
					<div class="form-group">
						<button class="btn btn-lg btn-danger btn-block" type="submit">登 录</button>
					</div>
				</form>
			</div>
		</div>
	</div> <!-- /container -->
</body>
</html>