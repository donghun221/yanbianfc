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
            <h1 class="page-heading__title">${ph:getGetArticleCategoryDictMsg(articleType)}</h1>
          </div>
        </div>
      </div>
    </div>

    <!-- Content
    ================================================== -->
    <div class="site-content">
      <div class="container">

        <div class="row">

          <!-- Content
    ================================================== -->
    <div class="site-content">
      <div class="container">

        <div class="row">
          <!-- Content -->
          <div class="content col-md-8">

            <!-- Posts List -->
            <div class="posts posts--cards posts--cards-thumb-lg post-list">

              <div class="post-list__item">
				<c:forEach items="${articleList}" var="article"  varStatus="i">
				
	                <div class="posts__item posts__item--card posts__item--category-${ph:getRichArticleCategoryCode(article.articleType)} card">
	                  <figure class="posts__thumb">
	                    <a href="<%=root%>/article/${article.id}" target="_blank"><img style="height:400px;width:775px;object-fit:cover;" src="<%=root%>/${ph:getImagePath(article.articleImage)}" alt=""></a>
	                  </figure>
	                  <div class="posts__inner card__content">
	                    <div class="posts__cat">
	                      <span class="label posts__cat-label">${ph:getGetArticleCategoryDictMsg(article.articleType)}</span>
	                    </div>
	                    <h6 class="posts__title"><a href="<%=root%>/article/${article.id}" target="_blank">${article.articleName}</a></h6>
	                    <time datetime="" class="posts__date"><fmt:formatDate value="${article.createDate}" pattern="yyyy年MM月dd日 hh:mm:ss"/></time>
	                    &nbsp;&nbsp;&nbsp;&nbsp;<span class="meta__item meta__item--views">${article.readCount}</span>
	                    <div class="posts__excerpt">
	                      ${article.descText}
	                    </div>
	                  </div>
	                </div>
	                
				</c:forEach>



              </div>

            </div>
            <!-- Posts List / End -->

            <!-- Post Pagination -->
            <nav aria-label="Page navigation" class="post-pagination text-center">
		        <ul class="pagination" id="pagination"></ul>
		    </nav>
<!--             <nav class="post-pagination text-center">
              <ul class="pagination pagination--condensed pagination--lg">
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><span>...</span></li>
                <li><a href="#">16</a></li>
              </ul>
            </nav> -->
            <!-- Post Pagination / End -->
            

          </div>
          <!-- Content / End -->

          <!-- Sidebar -->
          <div id="sidebar" class="sidebar col-md-4">
            
            <!-- Widget: Popular News -->
            <aside class="widget widget--sidebar card widget-popular-posts">
              <div class="widget__title card__header">
                <h4>热门资讯</h4>
              </div>
              <div class="widget__content card__content">
                <ul class="posts posts--simple-list">
                	<c:forEach items="${popularList}" var="article"  varStatus="i">
                		<li class="posts__item posts__item--category-${ph:getRichArticleCategoryCode(article.articleType)}">
		                    <figure class="posts__thumb">
		                      <a href="<%=root%>/article/${article.id}" target="_blank"><img style="width:80px;height:80px;object-fit:cover;" src="<%=root%>/${ph:getImagePath(article.articleImage)}" alt=""></a>
		                    </figure>
		                    <div class="posts__inner">
		                      <div class="posts__cat">
		                        <span class="label posts__cat-label">${ph:getGetArticleCategoryDictMsg(article.articleType)}</span>
		                      </div>
		                      <h6 class="posts__title"><a href="<%=root%>/article/${article.id}" target="_blank">${article.articleName}</a></h6>
		                      <time datetime="" class="posts__date"><fmt:formatDate value="${article.createDate}" pattern="yyyy年MM月dd日 hh:mm:ss"/></time>
		                    </div>
		                  </li>
                	</c:forEach>
                </ul>
              </div>
            </aside>
            <!-- Widget: Popular News / End -->
          </div>
          <!-- Sidebar / End -->
        </div>

      </div>
    </div>

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
    <script src="<%=root%>/lib/esimakin-twbs-pagination/jquery.twbsPagination.min.js" type="text/javascript"></script>
    <script type="text/javascript">
		var currentArticleType = "${articleType}";
		if(currentArticleType=="1" || currentArticleType=="2")
			$("#menuNewsCenter").addClass("active");
		else
			$("#menuHighLight").addClass("active");
		
	    $(function () {
	        var obj = $('#pagination').twbsPagination({
	            totalPages: ${page.pageCount},
	            visiblePages: 5,
	            startPage:${currentPage},
	            initiateStartPageClick: false,
	            onPageClick: function (event, page) {
	                window.location.href="<%=root%>/articleList?articleType=${articleType}&page="+page;
	            }
	        });
	    });
    </script>
  
  </body>
  </html>
  
