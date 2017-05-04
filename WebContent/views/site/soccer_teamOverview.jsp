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
            <h1 class="page-heading__title">俱乐部简介</h1>
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
                <h4>俱乐部简介</h4>
              </header>
              <div class="card__content" style="text-align: center;">
				<img src="<%=root%>/images/soccer/soccer_team_overview/soccer_team_overview.jpg" alt="Banner">
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
  		$("#menuClub").addClass("active");
  	</script>
  
  </body>
  </html>
  
