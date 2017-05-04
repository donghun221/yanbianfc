<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <!-- Footer
    ================================================== -->
    <footer id="footer" class="footer">
    
      <!-- Footer Widgets -->
      <div class="footer-widgets">
        <div class="footer-widgets__inner">
          <div class="container">
            <div class="row">
              <div class="col-sm-6 col-md-3">
                <div class="footer-col-inner">
    
                  <!-- Footer Logo -->
                  <div class="footer-logo footer-logo--has-txt">
                    <a href="#">
                      <img src="<%=root%>/images/soccer/logo.png" srcset="<%=root%>/images/soccer/logo.png 2x" alt="Alchemists" class="footer-logo__img">
                      <div class="footer-logo__heading">
                        <h5 class="footer-logo__txt">	延边富德足球俱乐部</h5>
                        <span class="footer-logo__tagline"></span>
                      </div>
                    </a>
                  </div>
                  <!-- Footer Logo / End -->
    
                  <!-- Widget: Contact Info -->
                  <div class="widget widget--footer widget-contact-info">
                    <div class="widget__content">
                      <div class="widget-contact-info__desc">
                        <p>延边人自己的球队，梦想</p>
                      </div>
                      <div class="widget-contact-info__body info-block">
                        <div class="info-block__item">
                          <img src="<%=root%>/images/soccer/social_media_logo/email_logo.png" srcset="<%=root%>/images/soccer/social_media_logo/email_logo.png" alt="Alchemists" class="df-icon df-icon--soccer-ball">
			              <h6 class="info-block__heading">官方邮箱</h6>
			              <a class="info-block__link" href="mailto:ybzqjlb@163.com">ybzqjlb@163.com</a>
                          
                        </div>
                        <div class="info-block__item">
                         <img src="<%=root%>/images/soccer/social_media_logo/weibo_logo.png" srcset="<%=root%>/images/soccer/social_media_logo/weibo_logo.png" alt="" class="df-icon df-icon--soccer-ball">
			              <h6 class="info-block__heading">官方微博</h6>
			              <a class="info-block__link" href="http://weibo.com/yanbianfc" target="_blank">yanbianfc</a>
                        </div>
                        <div class="info-block__item">
                         <img src="<%=root%>/images/soccer/social_media_logo/wechat_logo.png" srcset="<%=root%>/images/soccer/social_media_logo/wechat_logo.png" alt="" class="df-icon df-icon--soccer-ball">
			              <h6 class="info-block__heading">官方微信</h6>
			              <a class="info-block__link" >ybzqjlb</a>
                        </div>
                        <div class="info-block__item">
                         <img src="<%=root%>/images/soccer/social_media_logo/qr_code.jpg" srcset="<%=root%>/images/soccer/social_media_logo/qr_code.jpg" alt="" class="">
                        </div>
                        <!-- <div class="info-block__item info-block__item--nopadding">
                          <ul class="social-links">
                            <li class="social-links__item">
                              <a href="#" class="social-links__link"><i class="fa fa-facebook"></i> Facebook</a>
                            </li>
                            <li class="social-links__item">
                              <a href="#" class="social-links__link"><i class="fa fa-twitter"></i> Twitter</a>
                            </li>
                            <li class="social-links__item">
                              <a href="#" class="social-links__link"><i class="fa fa-google-plus"></i> Google+</a>
                            </li>
                          </ul>
                        </div> -->
                      </div>
                    </div>
                  </div>
                  <!-- Widget: Contact Info / End -->
                </div>
              </div>
              <div class="col-sm-6 col-md-3">
                <div class="footer-col-inner">
                  <!-- Widget: Popular Posts / End -->
                  <div class="widget widget--footer widget-popular-posts">
                    <h4 class="widget__title">主赞助商</h4>
                    <div class="widget__content">
                      <ul class="posts posts--simple-list">
                      	<img src="<%=root%>/images/soccer/sponser/primary_sponser/fudebaoxian_logo.png" srcset="<%=root%>/images/soccer/sponser/primary_sponser/fudebaoxian_logo.png" alt="Alchemists" class="footer-logo__img">
                      </ul>
                    </div>
                  </div>
                  <!-- Widget: Popular Posts / End -->
                </div>
              </div>
              <div class="clearfix visible-sm"></div>
              <div class="col-sm-6 col-md-3">
                <div class="footer-col-inner">
                  <!-- Widget: Featured News / End -->
                  <div class="widget widget--footer widget-featured-posts">
                    <h4 class="widget__title">赞助商</h4>
                    <div class="widget__content">
                      <ul class="posts posts--simple-list">
                      	<img src="<%=root%>/images/soccer/sponser/primary_sponser/fudebaoxian_logo.png" srcset="<%=root%>/images/soccer/sponser/primary_sponser/fudebaoxian_logo.png" alt="Alchemists" class="footer-logo__img">
                      </ul>
                    </div>
                  </div>
                  <!-- Widget: Featured News / End -->
                </div>
              </div>
              <div class="col-sm-6 col-md-3">
                <div class="footer-col-inner">
    
                  <!-- Widget: Contact / End -->
                  <div class="widget widget--footer widget-contact">
                    <h4 class="widget__title">友情链接</h4>
                    <div class="widget__content">
                    	<c:forEach items="${linkList}" var="link"  varStatus="i"> 
	                      <ul class="posts posts--simple-list">
	                      	<a href="${link.linkUrl}" target="_blank">
		                      	<img style="width:130px;height:80px;margin-bottom:5px;" src="<%=root%>/${ph:getImagePath(link.linkImg)}" srcset="<%=root%>/${ph:getImagePath(link.linkImg)}" alt="${link.linkName}" class="footer-logo__img">
	                      	</a>
	                      </ul>
                    	</c:forEach>
                    </div>
                  </div>
                  <!-- Widget: Contact / End -->
                  
    
                </div>
              </div>
            </div>
          </div>
        </div>
    
        <!-- Sponsors -->
        <%-- <div class="container">
          <div class="sponsors">
            <h6 class="sponsors-title">网站赞助商:</h6>
            <ul class="sponsors-logos">
              <!--li class="sponsors__item">
                <a href="#"><img src="<%=root%>/images/samples/sponsor-visa.png" alt=""></a>
              </li>
              <li class="sponsors__item">
                <a href="#"><img src="<%=root%>/images/samples/sponsor-discover.png" alt=""></a>
              </li>
              <li class="sponsors__item">
                <a href="#"><img src="<%=root%>/images/samples/sponsor-paypal.png" alt=""></a>
              </li>
              <li class="sponsors__item">
                <a href="#"><img src="<%=root%>/images/samples/sponsor-skrill.png" alt=""></a>
              </li>
              <li class="sponsors__item">
                <a href="#"><img src="<%=root%>/images/samples/sponsor-westernunion.png" alt=""></a>
              </li>
              <li class="sponsors__item">
                <a href="#"><img src="<%=root%>/images/samples/sponsor-payoneer.png" alt=""></a>
              </li-->
            </ul>
          </div>
        </div> --%>
        <!-- Sponsors / End -->
    
      </div>
      <!-- Footer Widgets / End -->

    </footer>
    <!-- Footer / End -->    
  </div>

  <!-- Javascript Files
  ================================================== -->
  <!-- Core JS -->
  <script src="<%=root%>/vendor/jquery/jquery.min.js"></script>
  <script src="<%=root%>/js/core-min.js"></script>
  
  <!-- Vendor JS -->
  <script src="<%=root%>/vendor/twitter/jquery.twitter.js"></script>
  
  
  <!-- Template JS -->
  <script src="<%=root%>/js/init.js"></script>
  <script src="<%=root%>/js/custom.js"></script>