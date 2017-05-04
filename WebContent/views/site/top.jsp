<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Header
    ================================================== -->
  
    <!-- Header Mobile -->
    <div class="header-mobile clearfix" id="header-mobile">
      <div class="header-mobile__logo">
        <a href="index"><img src="<%=root%>/images/soccer/logo.png" srcset="<%=root%>/images/soccer/logo@2x.png 2x" alt="Alchemists" class="header-mobile__logo-img"></a>
      </div>
      <div class="header-mobile__inner">
        <a id="header-mobile__toggle" class="burger-menu-icon"><span class="burger-menu-icon__line"></span></a>
        <span class="header-mobile__search-icon" id="header-mobile__search-icon"></span>
      </div>
    </div>
  
    <!-- Header Desktop -->
    <header class="header">
  
      <!-- Header Top Bar -->
      <div class="header__top-bar clearfix">
        <div class="container">
  
          <!-- Account Navigation -->
          <ul class="nav-account">
          <li class="nav-account__item"><a href="#" data-toggle="modal" data-target="#modal-login-register-tabs"></a></li>
           <!--  <li class="nav-account__item"><a href="#" data-toggle="modal" data-target="#modal-login-register-tabs">我的账号</a></li>
            <li class="nav-account__item"><a href="#">语言: <span class="highlight">中文</span></a>
              <ul class="main-nav__sub">
                <li><a href="#">中文</a></li>
              </ul>
            </li>
            <li class="nav-account__item nav-account__item--logout"><a href="_soccer_shop-login.html">注销</a></li> -->
          </ul>
          <!-- Account Navigation / End -->
  
        </div>
      </div>
      <!-- Header Top Bar / End -->
  
      <!-- Header Secondary -->
      <div class="header__secondary">
        <div class="container">
          <!-- Header Search Form -->
          <div class="header-search-form">
            <!-- <form action="#" id="mobile-search-form" class="search-form">
              <input type="text" class="form-control header-mobile__search-control" value="" placeholder="请输入搜索关键词。。。">
              <button type="submit" class="header-mobile__search-submit"><i class="fa fa-search"></i></button>
            </form> -->
          </div>
          <!-- Header Search Form / End -->
          <ul class="info-block info-block--header">
            <li class="info-block__item info-block__item--contact-secondary">
		      <img src="<%=root%>/images/soccer/social_media_logo/email_logo.png" srcset="<%=root%>/images/soccer/social_media_logo/email_logo.png" alt="Alchemists" class="df-icon df-icon--soccer-ball">
              <h6 class="info-block__heading">官方邮箱</h6>
              <a class="info-block__link" href="mailto:ybzqjlb@163.com">ybzqjlb@163.com</a>
            </li>
            <li class="info-block__item info-block__item--contact-secondary">
		      <img src="<%=root%>/images/soccer/social_media_logo/wechat_logo.png" srcset="<%=root%>/images/soccer/social_media_logo/wechat_logo.png" alt="Alchemists" class="df-icon df-icon--soccer-ball">
              <h6 class="info-block__heading">官方微信</h6>
              <a class="info-block__link" href="#">ybzqjlb</a>
            </li>
            <li class="info-block__item info-block__item--contact-secondary">
		      <img src="<%=root%>/images/soccer/social_media_logo/weibo_logo.png" srcset="<%=root%>/images/soccer/social_media_logo/weibo_logo.png" alt="Alchemists" class="df-icon df-icon--soccer-ball">
              <h6 class="info-block__heading">官方微博</h6>
              <a class="info-block__link" href="http://weibo.com/yanbianfc" target="_blank">yanbianfc</a>
            </li>
          </ul>
        </div>
      </div>
      <!-- Header Secondary / End -->
  
      <!-- Header Primary -->
      <div class="header__primary">
        <div class="container">
          <div class="header__primary-inner">
  
            <!-- Header Logo -->
            <div class="header-logo">
              <a href="index"><img src="<%=root%>/images/soccer/logo.png" srcset="<%=root%>/images/soccer/logo@2x.png 2x" alt="Alchemists" class="header-logo__img"></a>
            </div>
            <!-- Header Logo / End -->
  
            <!-- Main Navigation -->
            <nav class="main-nav clearfix">
              <ul class="main-nav__list">
                <li class="" id="menuIndex"><a href="<%=root%>/index">首页</a></li>
                <li class="" id="menuNewsCenter"><a href="#">新闻中心</a>
                  <ul class="main-nav__sub">
                    <li><a href="<%=root%>/articleList?articleType=1">官方新闻</a></li>
                    <li><a href="<%=root%>/articleList?articleType=2">官方公告</a></li>
                  </ul>
                </li>
                <li class="" id="menuClub"><a href="#">俱乐部</a>
                  <ul class="main-nav__sub">
                    <li><a href="<%=root%>/teamOverview">俱乐部简介</a></li>
                    <li><a href="<%=root%>/teamRoster">俱乐部事记</a></li>
                    <li><a href="<%=root%>/teamCoach">教练组</a></li>
                    <li><a href="<%=root%>/teamPlayer">球员</a></li>
                    <li><a href="<%=root%>/teamContactUs">联系方式</a></li>
                  </ul>
                </li>
                <li class=""  id="menuSchedule"><a href="<%=root%>/teamSchedule">赛程</a>
                </li>
                <li class=""  id="menuScore"><a href="<%=root%>/teamScore">积分榜</a></li>
                <li class=""  id="menuHighLight"><a href="#">精彩瞬间</a>
                  <ul class="main-nav__sub">
                    <li><a href="<%=root%>/articleList?articleType=4">精彩图片</a></li>
                    <li><a href="<%=root%>/articleList?articleType=3">精彩视频</a></li>
                  </ul>
                </li>
                <li class=""><a href="https://shop58435933.taobao.com/" target="_blank">球迷商城</a>
                </li>
              </ul>
  
              <!-- Pushy Panel Toggle -->
              <!-- <a href="#" class="pushy-panel__toggle">
                <span class="pushy-panel__line"></span>
              </a> -->
              <!-- Pushy Panel Toggle / Eng -->
            </nav>
            <!-- Main Navigation / End -->
          </div>
        </div>
      </div>
      <!-- Header Primary / End -->
  
    </header>
    <!-- Header / End -->