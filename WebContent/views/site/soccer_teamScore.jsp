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
            <h1 class="page-heading__title">积分榜</h1>
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
                <h4>积分榜</h4>
              </header>
              <div class="card__content">
				<div class="table-responsive">
					<table class="table table-hover table-standings table-standings--full table-standings--full-soccer">
		                <thead>
		                  <tr>
		                    <th class="team-standings__pos">排名</th>
		                    <th class="team-standings__team">俱乐部</th>
		                    <th class="team-standings__played">轮</th>
		                    <th class="team-standings__win">胜</th>
		                    <th class="team-standings__lose">负</th>
		                    <th class="team-standings__drawn">平</th>
		                    <th class="team-standings__goals-for">进球</th>
		                    <th class="team-standings__goals-against">丢球</th>
		                    <th class="team-standings__goals-diff">进胜球</th>
		                    <th class="team-standings__total-points">积分</th>
		                  </tr>
		                </thead>
		                <tbody>
			                <c:forEach items="${teamScoreList}" var="team"  varStatus="i"> 
		                		<tr>
				                    <td class="team-standings__pos">${i.index+1}</td>
				                    <td class="team-standings__team">
				                      <div class="team-meta">
				                        <figure class="team-meta__logo">
				                          <img style="height:100%" src="<%=root%>/${ph:getImagePath(team.logoImg)}" alt="">
				                        </figure>
				                        <div class="team-meta__info">
				                          <h6 class="team-meta__name">${team.name}</h6>
				                          <span class="team-meta__place"></span>
				                        </div>
				                      </div>
				                    </td>
				                    <td class="team-standings__played">${team.roundTime}</td>
				                    <td class="team-standings__win">${team.win}</td>
				                    <td class="team-standings__lose">${team.lose}</td>
				                    <td class="team-standings__drawn">${team.draw}</td>
				                    <td class="team-standings__goals-for">${team.pointWin}</td>
				                    <td class="team-standings__goals-against">${team.pointLose}</td>
				                    <td class="team-standings__goals-diff">${team.pointOffset}</td>
				                    <td class="team-standings__total-points">${team.totalScore}</td>
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
  		$("#menuScore").addClass("active");
  	</script>
  
  </body>
  </html>
  
