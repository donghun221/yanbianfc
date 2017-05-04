<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://ybfd.com/functions" prefix="ph" %> 
<% String root=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zxx">
<head>

  <!-- Basic Page Needs
  ================================================== -->
  <title>延边富德足球俱乐部</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="延边富德足球俱乐部">
  <meta name="author" content="郑帅">
  <meta name="keywords" content="延边富德足球俱乐部 足球俱乐部 延边 延边足球  足球 中超 延边富德 延边队">

	<%@ include file="headInclude.jsp"%>
  

</head>

<body class="template-soccer">

  <div class="site-wrapper clearfix">
    <div class="site-overlay"></div>

    <!-- Header
    ================================================== -->
  	<%@ include file="top.jsp"%>
    
    <!-- Page Heading
    ================================================== -->
    <div class="page-heading">
      <div class="container">
        <div class="row">
          <div class="col-md-10 col-md-offset-1">
            <h1 class="page-heading__title">赛程</h1>
          </div>
        </div>
      </div>
    </div>

    <!-- Content
    ================================================== -->
    <div class="site-content">
      <div class="container">

        <div class="row">

          <!-- Content -->
          <div class="content col-md-12">

            <!-- Game Scoreboard -->
            <div class="card">
              <header class="card__header card__header--has-btn">
                <h4>赛程</h4>
              </header>
              <div class="card__content">
				<div class="table-responsive">
	              <table class="table table-hover team-schedule team-schedule--full">
	                <thead>
	                  <tr>
	                    <th class="team-schedule__date">日期</th>
	                    <th class="team-schedule__versus">对手信息</th>
	                    <th class="team-schedule__status">主客场</th>
	                    <th class="team-schedule__time">时间</th>
	                    <th class="team-schedule__compet">赛事</th>
	                    <th class="team-schedule__venue">地点</th>
	                    <th class="team-schedule__tickets">比分</th>
	                  </tr>
	                </thead>
	                <tbody>
	                	<c:forEach items="${matchScheduleList}" var="match"  varStatus="i"> 
	                	
		                  <tr>
		                    <td class="team-schedule__date"><fmt:formatDate value="${match.matchTime}" pattern="yyyy年MM月dd日 E"/></td>
		                    <td class="team-schedule__versus">
		                      <div class="team-meta">
		                        <figure class="team-meta__logo">
		                          <img style="height:100%" src="<%=root%>/${ph:getImagePath(match.teamInfo.logoImg)}" alt="">
		                        </figure>
		                        <div class="team-meta__info">
		                          <h6 class="team-meta__name">${match.teamInfo.name}</h6>
		                          <span class="team-meta__place"></span>
		                        </div>
		                      </div>
		                    </td>
		                    <td class="team-schedule__status">
		                    	<c:if test="${match.isLocal eq '1'}">
                        			主场
	                        	</c:if>
	                        	<c:if test="${match.isLocal eq '0'}">
	                        		客场
	                        	</c:if>
		                    </td>
		                    <td class="team-schedule__time"><fmt:formatDate value="${match.matchTime}" pattern="HH:mm"/></td>
		                    <td class="team-schedule__compet">${match.matchName}</td>
		                    <td class="team-schedule__venue">${match.matchPlace}</td>
		                    <td class="team-schedule__tickets">${match.score}</td>
		                  </tr>
	                	
	                	</c:forEach>
	                </tbody>
	              </table>
           		 </div>
              </div>
            </div>
            <!-- Game Scoreboard / End -->

          </div>
          <!-- Content / End -->

          <!-- Sidebar -->
          <!-- <div class="sidebar col-md-4">
			
            

          </div> -->
          <!-- Sidebar / End -->
        </div>
      </div>
    </div>
    <!-- Content / End -->

    <!-- Footer
    ================================================== -->
    <%@ include file="bottomInclude.jsp"%>
    <!-- Footer / End -->
    
    <!-- Login/Register Tabs Modal -->
    <!-- Login/Register Tabs Modal / End -->
    <script type="text/javascript">
  		$("#menuSchedule").addClass("active");
  	</script>
  
  </body>
  </html>
  
