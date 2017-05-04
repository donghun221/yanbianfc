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


	<%@ include file="top.jsp"%>
    
  
    <!-- Pushy Panel - Dark -->
    
    <!-- Pushy Panel - Dark / End -->
    

    <!-- Content
    ================================================== -->
    <div class="site-content">
      <div class="container">

        <div class="row">
          <!-- Content -->
          <div class="content col-md-8">

            <!-- Featured News -->
            <div class="card card--clean">
              <header class="card__header card__header--has-filter">
                <h4>最新资讯</h4>
                <ul class="category-filter category-filter">
                  <li class="category-filter__item"><a href="index" class="category-filter__link<c:if test="${empty tt}"> category-filter__link--reset category-filter__link--active</c:if>">全部</a></li>
                  <li class="category-filter__item"><a href="index?tt=1" class="category-filter__link<c:if test="${tt eq '1'}"> category-filter__link--reset category-filter__link--active</c:if>">官方新闻</a></li>
                  <li class="category-filter__item"><a href="index?tt=2" class="category-filter__link<c:if test="${tt eq '2'}"> category-filter__link--reset category-filter__link--active</c:if>">官方公告</a></li>
                  <li class="category-filter__item"><a href="index?tt=3" class="category-filter__link<c:if test="${tt eq '3'}"> category-filter__link--reset category-filter__link--active</c:if>">精彩视频</a></li>
                  <li class="category-filter__item"><a href="index?tt=4" class="category-filter__link<c:if test="${tt eq '4'}"> category-filter__link--reset category-filter__link--active</c:if>">精彩图片</a></li>
                </ul>
              </header>
              <div class="card__content">

                <!-- Slider -->
                <div class="slick posts posts--slider-featured posts-slider posts-slider--center">

				 <c:forEach items="${allBaladList}" var="info"  varStatus="i"> 
					<div class="posts__item posts__item--category-${ph:getRichArticleCategoryCode(info.articleType)}">
	                    <a href="<%=root%>/article/${info.id}" target="_blank" class="posts__link-wrapper">
	                      <figure class="posts__thumb">
	                        <img style="object-fit:cover;height:400px;width:775px;" src="<%=root%>/${ph:getImagePath(info.articleImage)}" alt="">
	                      </figure>
	                      <div class="posts__inner">
	                        <div class="posts__cat">
	                          <span class="label posts__cat-label">${ph:getGetArticleCategoryDictMsg(info.articleType)}</span>
	                        </div>
	                        <h3 class="posts__title">${info.articleName}</h3>
	                        <div class="post-author">
	                         <%--  <figure class="post-author__avatar">
	                            <img src="<%=root%>/images/samples/avatar-1.jpg" alt="Post Author Avatar">
	                          </figure> --%>
	                          <div class="post-author__info">
	                            <!-- <h4 class="post-author__name"></h4> -->
	                            <time datetime="<fmt:formatDate value="${info.createDate}" pattern="yyyy年MM月dd日"/>" class="posts__date"><fmt:formatDate value="${info.createDate}" pattern="yyyy年MM月dd日"/></time>
	                          </div>
	                        </div>
	                      </div>
	                    </a>
                    </div>
				 </c:forEach> 
                </div>
                <!-- Slider / End -->

              </div>
            </div>
            <!-- Featured News / End -->

            <!-- Post Area 3 -->
            <div class="row">
              <div class="col-sm-6">
                <div class="card">
                  <div class="card__content">
                    <ul class="posts posts--simple-list">
                      <c:forEach items="${topAreaListLeft}" var="info"  varStatus="i"> 
	                      <li class="posts__item posts__item--category-${ph:getRichArticleCategoryCode(info.articleType)}">
	                        <figure class="posts__thumb">
	                          <a href="<%=root%>/article/${info.id}" target="_blank"><img  style="width:80px;height:80px;object-fit:cover;" src="<%=root%>/${ph:getImagePath(info.articleImage)}" alt=""></a>
	                         <!--  封面图 -->
	                        </figure>
	                        <div class="posts__inner">
	                          <div class="posts__cat">
	                            <span class="label posts__cat-label">${ph:getGetArticleCategoryDictMsg(info.articleType)}</span>
	                            <!-- 类型 -->
	                          </div>
	                          <h6 class="posts__title"><a href="<%=root%>/article/${info.id}" target="_blank">${info.articleName}</a></h6>
	                          <time datetime="${info.createDate}" class="posts__date">${info.createDate}</time>
	                        </div>
	                        <div class="posts__excerpt posts__excerpt--space-sm">
	                          ${info.descText}
	                        </div>
	                      </li>
                      </c:forEach>
                    </ul>
                  </div>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="card">
                  <div class="card__content">
                    <ul class="posts posts--simple-list">
                      <c:forEach items="${topAreaListRight}" var="info"  varStatus="i"> 
	                      <li class="posts__item posts__item--category-${ph:getRichArticleCategoryCode(info.articleType)}">
	                        <figure class="posts__thumb">
	                          <a href="<%=root%>/article/${info.id}" target="_blank"><img  style="width:80px;height:80px;object-fit:cover;" src="<%=root%>/${ph:getImagePath(info.articleImage)}" alt=""></a>
	                         <!--  封面图 -->
	                        </figure>
	                        <div class="posts__inner">
	                          <div class="posts__cat">
	                            <span class="label posts__cat-label">${ph:getGetArticleCategoryDictMsg(info.articleType)}</span>
	                            <!-- 类型 -->
	                          </div>
	                          <h6 class="posts__title"><a href="<%=root%>/article/${info.id}" target="_blank">${info.articleName}</a></h6>
	                          <time datetime="${info.createDate}" class="posts__date">${info.createDate}</time>
	                        </div>
	                        <div class="posts__excerpt posts__excerpt--space-sm">
	                          ${info.descText}
	                        </div>
	                      </li>
                      </c:forEach>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
            <!-- Post Area 3 / End -->

			<!-- Popular Moments -->
            <!-- <div class="card card--clean">
              <header class="card__header card__header--has-filter">
                <h4>精彩瞬间</h4>
                <ul class="category-filter category-filter--featured">
                  <li class="category-filter__item"><a href="#" class="category-filter__link category-filter__link--reset category-filter__link--active">全部</a></li>
                  <li class="category-filter__item"><a href="#" class="category-filter__link" data-category="posts__item--category-1">精彩视频</a></li>
                  <li class="category-filter__item"><a href="#" class="category-filter__link" data-category="posts__item--category-3">精彩图片</a></li>
                </ul>
              </header>
             </div> -->
		    <!-- Popular Moments / End -->

          </div>
          <!-- Content / End -->

          <!-- Sidebar -->
          <div id="sidebar" class="sidebar col-md-4">
            <!-- Widget: Match Announcement -->
            <aside class="widget widget--sidebar card widget-preview">
              <div class="widget__title card__header">
                <h4>下一场赛事</h4>
              </div>
              <div class="widget__content card__content">
            
                <!-- Match Preview -->
                <div class="match-preview">
                  <section class="match-preview__body">
                  <c:if test="${nextMatch == null}">
                  
                    <div class="match-preview__content" style="text-align: center">
                    	<h2>待定</h2>
                    </div>
				  </c:if>
				  <c:if test="${nextMatch != null}">
				  	<header class="match-preview__header">
	                      <time class="match-preview__date" datetime='<fmt:formatDate value="${nextMatch.matchTime}" pattern="yyyy-MM-dd"/>'>
	                      <fmt:formatDate value="${nextMatch.matchTime}" pattern="MM月dd日 E"/>
	                      </time>
	                      <h3 class="match-preview__title match-preview__title--lg">${nextMatch.matchName}</h3>
	                    </header>
	                    <div class="match-preview__content">
	            
	                      <!-- 1st Team -->
	                      <div class="match-preview__team match-preview__team--first">
	                        <figure class="match-preview__team-logo">
	                          <img src="<%=root%>/images/soccer/logos/yanbianfude/yanbianfude_buy_tickets.png" alt="">
	                        </figure>
	                        <h5 class="match-preview__team-name">延边富德</h5>
	                        <div class="match-preview__team-info">
	                        	<c:if test="${nextMatch.isLocal eq '1'}">
	                        		主场
	                        	</c:if>
	                        	<c:if test="${nextMatch.isLocal eq '0'}">
	                        		客场
	                        	</c:if>
	                        </div>
	                      </div>
	                      <!-- 1st Team / End -->
	            
	                      <div class="match-preview__vs">
	                        <div class="match-preview__conj">VS</div>
	                        <div class="match-preview__match-info">
	                          <time class="match-preview__match-time" datetime='<fmt:formatDate value="${nextMatch.matchTime}" pattern="yyyy-MM-dd HH:mm"/>'>
								<fmt:formatDate value="${nextMatch.matchTime}" pattern="HH:mm"/>
							 </time>
	                          <div class="match-preview__match-place">${nextMatch.matchPlace}</div>
	                        </div>
	                      </div>
	            
	                      <!-- 2nd Team -->
	                      <div class="match-preview__team match-preview__team--second">
	                        <figure class="match-preview__team-logo">
	                          <img src="<%=root%>/${ph:getImagePath(nextMatch.teamInfo.logoImg)}" alt="">
	                        </figure>
	                        <h5 class="match-preview__team-name">${nextMatch.teamInfo.name}</h5>
	                        <div class="match-preview__team-info">
	                        	<c:if test="${nextMatch.isLocal eq '0'}">
	                        		主场
	                        	</c:if>
	                        	<c:if test="${nextMatch.isLocal eq '1'}">
	                        		客场
	                        	</c:if>
	                        </div>
	                      </div>
	                      <!-- 2nd Team / End -->
	            
	                    </div>
	                  </section>
	                  <div class="countdown__content">
	                    <div class="countdown-counter" data-date='<fmt:formatDate value="${nextMatch.matchTime}" pattern="yyyy-MM-dd HH:mm"/>:00'></div>
	                  </div>
	                  <!-- <div class="match-preview__action match-preview__action--ticket">
	                    <a href="#" class="btn btn-primary-inverse btn-lg btn-block">购买球票</a>
	                  </div> -->
	                </div>
	                <!-- Match Preview / End -->
				  </c:if>
            
              </div>
            </aside>
            <!-- Widget: Match Announcement / End -->
            
            <!-- Widget: Standings -->
            <aside class="widget card widget--sidebar widget-standings">
              <div class="widget__title card__header card__header--has-btn">
                <h4>中超积分榜 2017</h4>
                <a  href="<%=root%>/teamScore" target="_blank" class="btn btn-default btn-outline btn-xs card-header__button">查看全部</a>
              </div>
              <div class="widget__content card__content">
                <div class="table-responsive">
                  <table class="table table-hover table-standings">
                    <thead>
                      <tr>
                        <th>排名</th>
                        <th>胜</th>
                        <th>平</th>
                        <th>负</th>
                        <th>积分</th>
                      </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${indexRankingList}" var="team"  varStatus="i"> 
                    		
	                      <tr>
	                        <td>
	                          <div class="team-meta">
	                            <figure class="team-meta__logo">
	                              <img style="height:100%;" src="<%=root%>/${ph:getImagePath(team.logoImg)}" alt="">
	                            </figure>
	                            <div class="team-meta__info">
	                              <h6 class="team-meta__name">${team.name}</h6>
	                              <!-- <span class="team-meta__place">Bebop Institute</span> -->
	                            </div>
	                          </div>
	                        </td>
	                        <td>${team.win}</td>
	                        <td>${team.draw}</td>
	                        <td>${team.lose}</td>
	                        <td>${team.totalScore}</td>
	                      </tr>
                    	</c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </aside>
            <!-- Widget: Standings / End -->
            
            <!-- Widget: Game Result -->
            <aside class="widget card widget--sidebar widget-game-result">
              <div class="widget__title card__header card__header--has-btn">
                <h4>上一轮赛程</h4>
                <!-- <a href="#" class="btn btn-default btn-outline btn-xs card-header__button">详情</a> -->
              </div>
              <div class="widget__content card__content">
                <!-- Game Score -->
                <div class="widget-game-result__section">
                  <div class="widget-game-result__section-inner">
                    <header class="widget-game-result__header">
                      <h3 class="widget-game-result__title">${lastMatch.matchName}</h3>
                      <time class="widget-game-result__date" datetime="2016-03-24">
                      	<fmt:formatDate value="${lastMatch.matchTime}" pattern="MM月dd日 E"/>
                      </time>
                    </header>
            
                    <div class="widget-game-result__main">
                      <!-- 1st Team -->
                      <div class="widget-game-result__team widget-game-result__team--first">
                        <figure class="widget-game-result__team-logo">
                          <a href="#"><img src="<%=root%>/images/soccer/logos/yanbianfude/yanbianfude_buy_tickets.png" alt=""></a>
                        </figure>
                        <div class="widget-game-result__team-info">
                          <h5 class="widget-game-result__team-name">延边富德</h5>
                          <div class="widget-game-result__team-desc">
                          	<c:if test="${lastMatch.isLocal eq '1'}">
                        		主场
                        	</c:if>
                        	<c:if test="${lastMatch.isLocal eq '0'}">
                        		客场
                        	</c:if>
                          </div>
                        </div>
                      </div>
                      <!-- 1st Team / End -->
            
                      <div class="widget-game-result__score-wrap">
                        <div class="widget-game-result__score">
                          <span class="widget-game-result__score-result widget-game-result__score-result">${lastMatch.score}</span>
                        </div>
                        <div class="widget-game-result__score-label">比分</div>
                      </div>
            
                      <!-- 2nd Team -->
                      <div class="widget-game-result__team widget-game-result__team--second">
                        <figure class="widget-game-result__team-logo">
                          <a href="#"><img src="<%=root%>/${ph:getImagePath(lastMatch.teamInfo.logoImg)}" alt=""></a>
                        </figure>
                        <div class="widget-game-result__team-info">
                          <h5 class="widget-game-result__team-name">${lastMatch.teamInfo.name}</h5>
                          <div class="widget-game-result__team-desc">
                          	<c:if test="${lastMatch.isLocal eq '0'}">
                        		主场
                        	</c:if>
                        	<c:if test="${lastMatch.isLocal eq '1'}">
                        		客场
                        	</c:if>
                          </div>
                        </div>
                      </div>
                      <!-- 2nd Team / End -->
                    </div>
                  </div>
                </div>
              </div>
            </aside>
            <!-- Widget: Game Result / End -->
            
          </div>
          <!-- Sidebar / End -->
        </div>

      </div>
    </div>

    <!-- Content / End -->
	<%@ include file="bottomInclude.jsp"%>
  	<script type="text/javascript">
  		$("#menuIndex").addClass("active");
  	</script>
  </body>
  </html>
  
