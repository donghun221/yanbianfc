<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String rootPath=request.getContextPath();
%>
<script type="text/javascript">
var menu=<%=(String)session.getAttribute("menu")%>;
$(document).ready(function(){
	initMenu();
});

/**
 * 初始化菜单
 */
function initMenu()
{
	$("#menu tbody").empty();
	$.each(menu, function(index, value){
		var link=$("<a class='item'>"+value.name+"</a>").click(function(){
			$("#mainFrame").load("<%=rootPath%>"+value.uri, null, function(){
				$("#modalContainer").empty();
			});
			$(this).addClass("active").siblings().removeClass("active");
		});
		$("#menu").append(link);
		if (index==0)
		{
			link.click();
		}
	});
}
</script>

<style>
#menu a.item {
	background: #3A3A3A;
	border: #3A3A3A;
	color: #FFF;
	display: block;
	padding: 1em;
}
#menu a.item:hover{
	background: #ddd;
	color: #666;
	text-decoration: none;
}
#menu a.item.active{
	background: #2D89EF;
	color: #FFF
}
</style>

<div id="menu" style=" background: #3A3A3A;margin-top: 55px;">
</div>