/*package com.ffyc.site.service.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffyc.site.model.AdminInfo;
import com.ffyc.site.model.ArticleInfo;
import com.ffyc.site.model.CaseImageInfo;
import com.ffyc.site.model.CaseInfo;
import com.ffyc.site.model.ContactInfo;
import com.ffyc.site.model.EmpInfo;
import com.ffyc.site.model.FeedBackInfo;
import com.ffyc.site.model.FirstPageInfo;
import com.ffyc.site.model.IndexInfo;
import com.ffyc.site.model.Intro;
import com.ffyc.site.model.LinkInfo;
import com.ffyc.site.model.NewsInfo;
import com.ffyc.site.model.PortalInfo;
import com.ffyc.site.model.PrdImageInfo;
import com.ffyc.site.model.PrdInfo;
import com.ffyc.site.model.PurpInfo;
import com.ffyc.site.model.RichArticleInfo;
import com.ffyc.site.model.ShowInfo;
import com.ffyc.site.model.TimeLineInfo;
import com.ffyc.site.model.WelcomeInfo;
import com.ffyc.site.permission.model.UserMenu;
import com.ffyc.site.permission.service.PermissionService;
import com.ffyc.site.service.SiteService;
import com.nationsky.pub.framework.exception.ApplicationException;
import com.nationsky.pub.utils.GsonUtils;
import com.peernet.mobile.server.admin.common.message.FailureResponseMessage;
import com.peernet.mobile.server.admin.common.message.ResponseMessage;
import com.peernet.mobile.server.admin.common.message.SuccessResponseMessage;

@Controller
public class SiteController_back {
	
	@Autowired
    private PermissionService permissionService;
	
	@Autowired
	private SiteService siteService;


	
    *//**
     * 页面初始化（如果未登录跳到登陆页面，否则跳到管理页面）
     *//*
    @RequestMapping("/admin")
    public String init(HttpSession session)
    {
        if(session.getAttribute("loginInfo") != null)
        {
            return "/admin/main";
        }
        return "/admin/login";
    }


    *//**
     * 用户登录
     *//*
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String login(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam String username, @RequestParam String password) throws IOException
    {
        try
        {
        	List<UserMenu> userMenu = permissionService.login(username, password);
            session.setAttribute("loginInfo", username);
            session.setAttribute("username", username);
            session.setAttribute("menu", GsonUtils.toGson(userMenu));
            
            //response.sendRedirect(request.getContextPath());
            return "/admin/main";
        }
        catch(ApplicationException e)
        {
            model.addAttribute("message", e.getMessage());
            //return "/admin/login";
            return "redirect:" + "/admin";
        }
    }

    *//**
     * 退出登录
     *//*
    //@RequestMapping("/admin/logout")
    @RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        session.removeAttribute("loginInfo");

        return "redirect:" + "/admin";
    }
    
    *//**
     * 欢迎页管理
     * @param session
     * @param request
     * @param response
     * @return
     * @throws IOException
     *//*
    @RequestMapping(value = "/admin/welcomeInfoManage", method = RequestMethod.GET)
    public String welcomeInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        if(session.getAttribute("loginInfo") == null)
        {
            response.sendRedirect(request.getContextPath());
            return null;
        }
        return "/admin/welcome/welcome";
    }
    
    *//**
     * 获取欢迎页信息
     *//*
    @RequestMapping(value = "admin/welcomeInfo", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getWelcomeInfo()
    {
        return new SuccessResponseMessage(this.siteService.getWelcomeInfo());
    }
    
    *//**
     * 修改
     *//*
    @RequestMapping(value = "admin/welcomeInfo", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseMessage modifyWelcomeInfo(@RequestBody WelcomeInfo form)
    {
		try {
			this.siteService.updateWelcomInfo(form.getShowTime(), form.getBackGround().getId(), form.getCompanyLogo().getId(),form.getFootContent());
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
    }
    
    
    *//**
     * 首页管理
     * @param session
     * @param request
     * @param response
     * @return
     * @throws IOException
     *//*
    @RequestMapping(value = "/admin/indexInfoManage", method = RequestMethod.GET)
    public String indexInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        if(session.getAttribute("loginInfo") == null)
        {
            response.sendRedirect(request.getContextPath());
            return null;
        }
        return "/admin/index/index";
    }
    *//**
     * 获取首页信息
     *//*
    @RequestMapping(value = "admin/indexInfo", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getIndexInfo()
    {
        return new SuccessResponseMessage(this.siteService.getIndexInfo());
    }
    
    *//**
     * 修改首页信息
     *//*
    @RequestMapping(value = "admin/indexInfo", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseMessage modifyIndexInfo(@RequestBody IndexInfo form)
    {
		try {
			this.siteService.updateIndexInfo(form.getContentText(), form.getCopyrightText(), form.getAddressText(), form.getTitleText());
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
    }
    
    *//**
     * 公司简介管理
     * @param session
     * @param request
     * @param response
     * @return
     * @throws IOException
     *//*
    @RequestMapping(value = "/admin/introManage", method = RequestMethod.GET)
    public String introManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        if(session.getAttribute("loginInfo") == null)
        {
            response.sendRedirect(request.getContextPath());
            return null;
        }
        return "/admin/intro/intro";
    }
    *//**
     * 获取公司简介信息
     *//*
    @RequestMapping(value = "admin/intro", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getIntro()
    {
        return new SuccessResponseMessage(this.siteService.getIntro());
    }
    
    *//**
     * 修改公司简介信息
     *//*
    @RequestMapping(value = "admin/intro", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseMessage modifyIntro(@RequestBody Intro form)
    {
		try {
			this.siteService.updateIntro(form.getIntroText());
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
    }
    
    *//**
     * 获取公司TIMELINE列表
     *//*
    @RequestMapping(value = "admin/timeLineInfo", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getTimeLineList(@RequestParam int page)
    {
        return new SuccessResponseMessage(this.siteService.getTimeLineInfoList(page));
    }
    *//**
     * 创建TIMELINE
     * @param form
     * @return
     *//*
   @RequestMapping(value = "admin/timeLineInfo", method = RequestMethod.POST)
   public @ResponseBody
   ResponseMessage insertTimeLine(@RequestBody TimeLineInfo form)
   {
       try
       {
           this.siteService.insertTimeLineInfo(form);
       }
       catch(Exception e)
       {
           return new FailureResponseMessage(e);
       }
       return new SuccessResponseMessage();
   }
   *//**
    * 修改TIMELINE
    *//*
   @RequestMapping(value = "admin/timeLineInfo", method = RequestMethod.PUT)
   public @ResponseBody
   ResponseMessage modifyTimeLine(@RequestBody TimeLineInfo form)
   {
		try {
			this.siteService.updateTimeLineInfo(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
   }
   
   *//**
    * 删除TIMELINE
    * @param seq
    * @return
    *//*
   @RequestMapping(value = "admin/timeLineInfo/{id}", method = RequestMethod.DELETE)
   public @ResponseBody
   ResponseMessage removeTimeLine(@PathVariable String id)
   {
       try
       {
           this.siteService.deleteTimeLineInfo(id);
       }
       catch(Exception e)
       {
           return new FailureResponseMessage(e);
       }
       return new SuccessResponseMessage();
   }
    
    
    *//**
     * 公司愿景管理
     * @param session
     * @param request
     * @param response
     * @return
     * @throws IOException
     *//*
    @RequestMapping(value = "/admin/purpInfoManage", method = RequestMethod.GET)
    public String purpInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        if(session.getAttribute("loginInfo") == null)
        {
            response.sendRedirect(request.getContextPath());
            return null;
        }
        return "/admin/purp/purp";
    }
    *//**
     * 获取愿景
     *//*
    @RequestMapping(value = "admin/purpInfo", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getPurpInfo()
    {
        return new SuccessResponseMessage(this.siteService.getPurpInfo());
    }
    
    *//**
     * 修改公司愿景信息
     *//*
    @RequestMapping(value = "admin/purpInfo", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseMessage modifyPurpInfo(@RequestBody PurpInfo form)
    {
		try {
			this.siteService.updatePurpInfo(form.getContent(), form.getPurpImage().getId());
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
    }
    
    *//**
     * 公司风采
     *//*
    @RequestMapping(value = "/admin/showInfoManage", method = RequestMethod.GET)
    public String showInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        if(session.getAttribute("loginInfo") == null)
        {
            response.sendRedirect(request.getContextPath());
            return null;
        }
        return "/admin/show/show";
    }
    *//**
     * 获取公司风采
     *//*
    @RequestMapping(value = "admin/showInfo", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getShowInfo()
    {
        return new SuccessResponseMessage(this.siteService.getShowInfo());
    }
    
    *//**
     * 修改公司风采信息
     *//*
    @RequestMapping(value = "admin/showInfo", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseMessage modifyShowInfo(@RequestBody ShowInfo form)
    {
		try {
			this.siteService.updateShowInfo(form.getImgOne().getId(), form.getImgTwo().getId(), form.getImgThree().getId());
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
    }
    

    *//**
     * 联系信息管理
     *//*
    @RequestMapping(value = "/admin/contactInfoManage", method = RequestMethod.GET)
    public String contactInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        if(session.getAttribute("loginInfo") == null)
        {
            response.sendRedirect(request.getContextPath());
            return null;
        }
        return "/admin/contact/contact";
    }
    *//**
     * 获取联系信息
     *//*
    @RequestMapping(value = "admin/contactInfo", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getContactInfo()
    {
        return new SuccessResponseMessage(this.siteService.getContactInfo());
    }
    
    *//**
     * 修改联系信息
     *//*
    @RequestMapping(value = "admin/contactInfo", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseMessage modifycontactInfo(@RequestBody ContactInfo form)
    {
		try {
			this.siteService.updateContactInfo(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
    }
    
    *//**
     * 新闻管理
     *//*
    @RequestMapping(value = "/admin/newsInfoManage", method = RequestMethod.GET)
    public String newsInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        if(session.getAttribute("loginInfo") == null)
        {
            response.sendRedirect(request.getContextPath());
            return null;
        }
        String type = request.getParameter("type");
        request.setAttribute("type", type);
        return "/admin/news/news";
    }
    
    *//**
     * 新闻
     *//*
    @RequestMapping(value = "admin/newsInfo", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getNewsInfoList(@RequestParam(required=false,defaultValue="0") String type,@RequestParam int page,@RequestParam(required=false,defaultValue="20") int limit)
    {
        return new SuccessResponseMessage(this.siteService.getNewsInfoList(type,page,limit));
    }
    *//**
     * 创建新闻
     * @param form
     * @return
     *//*
   @RequestMapping(value = "admin/newsInfo", method = RequestMethod.POST)
   public @ResponseBody
   ResponseMessage insertNewsInfo(@RequestBody NewsInfo form)
   {
       try
       {
           this.siteService.insertNewsInfo(form);
       }catch(Exception e)
       {return new FailureResponseMessage(e);}
       return new SuccessResponseMessage();
   }
   *//**
    * 修改新闻
    *//*
   @RequestMapping(value = "admin/newsInfo", method = RequestMethod.PUT)
   public @ResponseBody
   ResponseMessage modifyNewsInfo(@RequestBody NewsInfo form)
   {
		try {
			this.siteService.updateNewsInfo(form);
		} catch (Exception e) {return new FailureResponseMessage(e);}
		return new SuccessResponseMessage();
   }
   
   *//**
    * 删除新闻
    * @param seq
    * @return
    *//*
   @RequestMapping(value = "admin/newsInfo/{id}", method = RequestMethod.DELETE)
   public @ResponseBody
   ResponseMessage removeNewsInfo(@PathVariable String id)
   {
       try
       {
           this.siteService.deleteNewsInfo(id);
       }catch(Exception e)
       {return new FailureResponseMessage(e);}
       return new SuccessResponseMessage();
   }
   
   
   
   *//**
    * 产品管理
    *//*
   @RequestMapping(value = "/admin/prdInfoManage", method = RequestMethod.GET)
   public String prdInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
   {
       if(session.getAttribute("loginInfo") == null)
       {
           response.sendRedirect(request.getContextPath());
           return null;
       }
       return "/admin/prd/prd";
   }
   
   *//**
    * 产品
    *//*
   @RequestMapping(value = "admin/prdInfo", method = RequestMethod.GET)
   public @ResponseBody
   ResponseMessage getPrdInfoList(@RequestParam int page,@RequestParam(required=false,defaultValue="20") int limit)
   {
       return new SuccessResponseMessage(this.siteService.getPrdInfoList(page,limit));
   }
   *//**
    * 创建产品
    * @param form
    * @return
    *//*
  @RequestMapping(value = "admin/prdInfo", method = RequestMethod.POST)
  public @ResponseBody
  ResponseMessage insertPrdInfo(@RequestBody PrdInfo form)
  {
      try
      {
          this.siteService.insertPrdInfo(form);
      }catch(Exception e)
      {return new FailureResponseMessage(e);}
      return new SuccessResponseMessage();
  }
  *//**
   * 修改产品
   *//*
  @RequestMapping(value = "admin/prdInfo", method = RequestMethod.PUT)
  public @ResponseBody
  ResponseMessage modifyPrdInfo(@RequestBody PrdInfo form)
  {
		try {
			this.siteService.updatePrdInfo(form);
		} catch (Exception e) {return new FailureResponseMessage(e);}
		return new SuccessResponseMessage();
  }
  
  *//**
   * 删除产品
   * @param seq
   * @return
   *//*
  @RequestMapping(value = "admin/prdInfo/{id}", method = RequestMethod.DELETE)
  public @ResponseBody
  ResponseMessage removePrdInfo(@PathVariable String id)
  {
      try
      {
          this.siteService.deletePrdInfo(id);
      }catch(Exception e)
      {return new FailureResponseMessage(e);}
      return new SuccessResponseMessage();
  }
  
  *//**
	 * 产品图片
	 *//*
	@RequestMapping(value = "admin/prdImageInfo", method = RequestMethod.GET)
	public @ResponseBody ResponseMessage getPrdImageInfoList(@RequestParam String prdId) {
		return new SuccessResponseMessage(this.siteService.getPrdImageInfoList(prdId));
	}

	*//**
	 * 创建产品图片
	 * 
	 * @param form
	 * @return
	 *//*
	@RequestMapping(value = "admin/prdImageInfo", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage insertPrdImageInfo(@RequestBody PrdImageInfo form) {
		try {
			this.siteService.insertPrdImageInfo(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage(form.getId());
	}

	*//**
	 * 修改产品图片
	 * 
	 * @param form
	 * @return
	 *//*
	@RequestMapping(value = "admin/prdImageInfo", method = RequestMethod.PUT)
	public @ResponseBody ResponseMessage updatePrdImageInfo(@RequestBody PrdImageInfo form) {
		try {
			this.siteService.updatePrdImageInfo(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage(form.getId());
	}

	*//**
	 * 删除产品图片
	 * 
	 * @param seq
	 * @return
	 *//*
	@RequestMapping(value = "admin/prdImageInfo/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseMessage removePrdImageInfo(@PathVariable String id) {
		try {
			this.siteService.deletePrdImageInfo(id);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}
  
  *//**
   * 案例管理
   *//*
  @RequestMapping(value = "/admin/caseInfoManage", method = RequestMethod.GET)
  public String caseInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
  {
      if(session.getAttribute("loginInfo") == null)
      {
          response.sendRedirect(request.getContextPath());
          return null;
      }
      return "/admin/case/case";
  }
	*//**
	 * 案例
	 *//*
	@RequestMapping(value = "admin/caseInfo", method = RequestMethod.GET)
	public @ResponseBody ResponseMessage getCaseInfoList(@RequestParam int page) {
		return new SuccessResponseMessage(this.siteService.getCaseInfoList(page));
	}

	*//**
	 * 创建案例
	 * 
	 * @param form
	 * @return
	 *//*
	@RequestMapping(value = "admin/caseInfo", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage insertCaseInfo(@RequestBody CaseInfo form) {
		try {
			this.siteService.insertCaseInfo(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage(form.getId());
	}

	*//**
	 * 修改案例
	 *//*
	@RequestMapping(value = "admin/caseInfo", method = RequestMethod.PUT)
	public @ResponseBody ResponseMessage modifyCaseInfo(@RequestBody CaseInfo form) {
		try {
			this.siteService.updateCaseInfo(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}

	*//**
	 * 删除案例
	 * 
	 * @param seq
	 * @return
	 *//*
	@RequestMapping(value = "admin/caseInfo/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseMessage removeCaseInfo(@PathVariable String id) {
		try {
			this.siteService.deleteCaseInfo(id);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}
	
	*//**
	 * 案例图片
	 *//*
	@RequestMapping(value = "admin/caseImageInfo", method = RequestMethod.GET)
	public @ResponseBody ResponseMessage getCaseImageInfoList(@RequestParam String caseId) {
		return new SuccessResponseMessage(this.siteService.getCaseImageInfoList(caseId));
	}

	*//**
	 * 创建案例图片
	 * 
	 * @param form
	 * @return
	 *//*
	@RequestMapping(value = "admin/caseImageInfo", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage insertCaseImageInfo(@RequestBody CaseImageInfo form) {
		try {
			this.siteService.insertCaseImageInfo(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage(form.getId());
	}

	*//**
	 * 修改案例图片
	 * 
	 * @param form
	 * @return
	 *//*
	@RequestMapping(value = "admin/caseImageInfo", method = RequestMethod.PUT)
	public @ResponseBody ResponseMessage updateCaseImageInfo(@RequestBody CaseImageInfo form) {
		try {
			this.siteService.updateCaseImageInfo(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage(form.getId());
	}

	*//**
	 * 删除案例图片
	 * 
	 * @param seq
	 * @return
	 *//*
	@RequestMapping(value = "admin/caseImageInfo/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseMessage removeCaseImageInfo(@PathVariable String id) {
		try {
			this.siteService.deleteCaseImageInfo(id);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}
	
	
    *//**
     * 转至管理员修改密码
     *//*
    
    @RequestMapping(value = "/admin/adminInfoManage", method = RequestMethod.GET)
    public String adminInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        if(session.getAttribute("loginInfo") == null)
        {
            response.sendRedirect(request.getContextPath());
            return null;
        }
        return "/admin/user/password";
    }
    
	*//**
	 * 获取管理员对象
	 *//*
	@RequestMapping(value = "admin/adminInfo", method = RequestMethod.GET)
	public @ResponseBody ResponseMessage getAdminInfo(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String adminName= (String)session.getAttribute("username");
		return new SuccessResponseMessage(
				this.siteService.getAdminInfo(adminName));
	}
    
	*//**
	 * 修改管理员密码
	 *//*
	@RequestMapping(value = "admin/updateAdminInfo", method = RequestMethod.PUT)
	public @ResponseBody ResponseMessage updateAdminInfo(@RequestBody AdminInfo form) {
		try {
			this.siteService.updateAdminInfo(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}
	
	
	
	
	
	
	
	
	
	  *//**
	   * 友情链接管理
	   *//*
	  @RequestMapping(value = "/admin/linkInfoManage", method = RequestMethod.GET)
	  public String linkInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
	  {
	      if(session.getAttribute("loginInfo") == null)
	      {
	          response.sendRedirect(request.getContextPath());
	          return null;
	      }
	      return "/admin/link/link";
	  }
		*//**
		 * 管理页面
		 *//*
		@RequestMapping(value = "admin/linkInfo", method = RequestMethod.GET)
		public @ResponseBody ResponseMessage getLinkInfoList(@RequestParam int page) {
			return new SuccessResponseMessage(this.siteService.getLinkInfoList(page));
		}

		*//**
		 * 创建
		 * 
		 * @param form
		 * @return
		 *//*
		@RequestMapping(value = "admin/linkInfo", method = RequestMethod.POST)
		public @ResponseBody ResponseMessage insertLinkInfo(@RequestBody LinkInfo form) {
			try {
				this.siteService.insertLinkInfo(form);
			} catch (Exception e) {
				return new FailureResponseMessage(e);
			}
			return new SuccessResponseMessage(form.getId());
		}

		*//**
		 * 修改
		 *//*
		@RequestMapping(value = "admin/linkInfo", method = RequestMethod.PUT)
		public @ResponseBody ResponseMessage modifyLinkInfo(@RequestBody LinkInfo form) {
			try {
				this.siteService.updateLinkInfo(form);
			} catch (Exception e) {
				return new FailureResponseMessage(e);
			}
			return new SuccessResponseMessage();
		}

		*//**
		 * 删除
		 * 
		 * @param seq
		 * @return
		 *//*
		@RequestMapping(value = "admin/linkInfo/{id}", method = RequestMethod.DELETE)
		public @ResponseBody ResponseMessage removeLinkInfo(@PathVariable String id) {
			try {
				this.siteService.deleteLinkInfo(id);
			} catch (Exception e) {
				return new FailureResponseMessage(e);
			}
			return new SuccessResponseMessage();
		}
		
		
		  *//**
		   * 一级页面管理
		   *//*
		  @RequestMapping(value = "/admin/firstPageInfoManage", method = RequestMethod.GET)
		  public String firstPageInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
		  {
		      if(session.getAttribute("loginInfo") == null)
		      {
		          response.sendRedirect(request.getContextPath());
		          return null;
		      }
		      return "/admin/firstPage/firstPage";
		  }
			*//**
			 * 管理页面
			 *//*
			@RequestMapping(value = "admin/firstPageInfo", method = RequestMethod.GET)
			public @ResponseBody ResponseMessage getFirstPageInfoList(@RequestParam int page) {
				return new SuccessResponseMessage(this.siteService.getFirstPageInfoList(page));
			}

			*//**
			 * 创建
			 * 
			 * @param form
			 * @return
			 *//*
			@RequestMapping(value = "admin/firstPageInfo", method = RequestMethod.POST)
			public @ResponseBody ResponseMessage insertFirstPageInfo(@RequestBody FirstPageInfo form) {
				try {
					this.siteService.insertFirstPageInfo(form);
				} catch (Exception e) {
					return new FailureResponseMessage(e);
				}
				return new SuccessResponseMessage(form.getId());
			}

			*//**
			 * 修改
			 *//*
			@RequestMapping(value = "admin/firstPageInfo", method = RequestMethod.PUT)
			public @ResponseBody ResponseMessage modifyFirstPageInfo(@RequestBody FirstPageInfo form) {
				try {
					this.siteService.updateFirstPageInfo(form);
				} catch (Exception e) {
					return new FailureResponseMessage(e);
				}
				return new SuccessResponseMessage();
			}

			*//**
			 * 删除
			 * 
			 * @param seq
			 * @return
			 *//*
			@RequestMapping(value = "admin/firstPageInfo/{id}", method = RequestMethod.DELETE)
			public @ResponseBody ResponseMessage removeFirstPageInfo(@PathVariable String id) {
				try {
					this.siteService.deleteFirstPageInfo(id);
				} catch (Exception e) {
					return new FailureResponseMessage(e);
				}
				return new SuccessResponseMessage();
			}
			
			@RequestMapping(value = "admin/firstPageInfo/{pageCode}", method = RequestMethod.GET)
			public @ResponseBody ResponseMessage getFirstPageInfoList(@PathVariable String pageCode) {
				return new SuccessResponseMessage(this.siteService.getFirstPageInfoListByCode(pageCode));
			}
			
			*//**
			   * 首推业务领域内容管理
			   *//*
			  @RequestMapping(value = "/admin/portalInfoLeftManage", method = RequestMethod.GET)
			  public String portalInfoLeftManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
			  {
			      if(session.getAttribute("loginInfo") == null)
			      {
			          response.sendRedirect(request.getContextPath());
			          return null;
			      }
			      return "/admin/portal/portalLeft";
			  }
			  
			  *//**
			   * 首推右侧内容管理
			   *//*
			  @RequestMapping(value = "/admin/portalInfoRightManage", method = RequestMethod.GET)
			  public String portalInfoRightManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
			  {
			      if(session.getAttribute("loginInfo") == null)
			      {
			          response.sendRedirect(request.getContextPath());
			          return null;
			      }
			      return "/admin/portal/portalRight";
			  }
				*//**
				 * 管理页面
				 *//*
				@RequestMapping(value = "admin/portalInfo", method = RequestMethod.GET)
				public @ResponseBody ResponseMessage getPortalInfoList(@RequestParam int page,@RequestParam(required=false) String portalType) {
					return new SuccessResponseMessage(this.siteService.getPortalInfoList(page,portalType));
				}

				*//**
				 * 创建
				 * 
				 * @param form
				 * @return
				 *//*
				@RequestMapping(value = "admin/portalInfo", method = RequestMethod.POST)
				public @ResponseBody ResponseMessage insertPortalInfo(@RequestBody PortalInfo form) {
					try {
						this.siteService.insertPortalInfo(form);
					} catch (Exception e) {
						return new FailureResponseMessage(e);
					}
					return new SuccessResponseMessage(form.getId());
				}

				*//**
				 * 修改
				 *//*
				@RequestMapping(value = "admin/portalInfo", method = RequestMethod.PUT)
				public @ResponseBody ResponseMessage modifyPortalInfo(@RequestBody PortalInfo form) {
					try {
						this.siteService.updatePortalInfo(form);
					} catch (Exception e) {
						return new FailureResponseMessage(e);
					}
					return new SuccessResponseMessage();
				}

				*//**
				 * 删除
				 * 
				 * @param seq
				 * @return
				 *//*
				@RequestMapping(value = "admin/portalInfo/{id}", method = RequestMethod.DELETE)
				public @ResponseBody ResponseMessage removePortalInfo(@PathVariable String id) {
					try {
						this.siteService.deletePortalInfo(id);
					} catch (Exception e) {
						return new FailureResponseMessage(e);
					}
					return new SuccessResponseMessage();
				}
				
				
				*//**
				   * 团队管理
				   *//*
				  @RequestMapping(value = "/admin/articleInfoManage", method = RequestMethod.GET)
				  public String articleInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
				  {
				      if(session.getAttribute("loginInfo") == null)
				      {
				          response.sendRedirect(request.getContextPath());
				          return null;
				      }
				      String result = "/admin/article/article";
				      
				      String type = request.getParameter("type");
				      if(!StringUtils.isEmpty(type))
				    	  result+=type;
				      
				      return result;
				  }
				
				*//**
				 * 查询
				 *//*
				@RequestMapping(value = "admin/articleInfo", method = RequestMethod.GET)
				public @ResponseBody ResponseMessage getArticleInfoList(@RequestParam int page,@RequestParam(required=false) String articleType) {
					return new SuccessResponseMessage(this.siteService.getArticleInfoList(page,articleType));
				}
				
				*//**
				 * 查询VO
				 *//*
				@RequestMapping(value = "admin/articleInfoVOAll", method = RequestMethod.GET)
				public @ResponseBody ResponseMessage getArticleInfoVOList() {
					return new SuccessResponseMessage(this.siteService.getArticleInfoVOAll());
				}

				*//**
				 * 创建
				 * 
				 * @param form
				 * @return
				 *//*
				@RequestMapping(value = "admin/articleInfo", method = RequestMethod.POST)
				public @ResponseBody ResponseMessage insertArticleInfo(@RequestBody ArticleInfo form) {
					try {
						this.siteService.insertArticleInfo(form);
					} catch (Exception e) {
						return new FailureResponseMessage(e);
					}
					return new SuccessResponseMessage(form.getId());
				}

				*//**
				 * 修改
				 *//*
				@RequestMapping(value = "admin/articleInfo", method = RequestMethod.PUT)
				public @ResponseBody ResponseMessage modifyArticleInfo(@RequestBody ArticleInfo form) {
					try {
						this.siteService.updateArticleInfo(form);
					} catch (Exception e) {
						return new FailureResponseMessage(e);
					}
					return new SuccessResponseMessage();
				}

				*//**
				 * 删除
				 * 
				 * @param seq
				 * @return
				 *//*
				@RequestMapping(value = "admin/articleInfo/{id}", method = RequestMethod.DELETE)
				public @ResponseBody ResponseMessage removeArticleInfo(@PathVariable String id) {
					try {
						this.siteService.deleteArticleInfo(id);
					} catch (Exception e) {
						return new FailureResponseMessage(e);
					}
					return new SuccessResponseMessage();
				}
				
				*//**
				   * 团队管理
				   *//*
				  @RequestMapping(value = "/admin/empInfoManage", method = RequestMethod.GET)
				  public String empInfoRightManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
				  {
				      if(session.getAttribute("loginInfo") == null)
				      {
				          response.sendRedirect(request.getContextPath());
				          return null;
				      }
				      String result = "/admin/emp/emp";
				      
				      String type = request.getParameter("type");
				      if(!StringUtils.isEmpty(type))
				    	  result+=type;
				      
				      return result;
				  }
				
				*//**
				 * 查询
				 *//*
				@RequestMapping(value = "admin/empInfo", method = RequestMethod.GET)
				public @ResponseBody ResponseMessage getEmpInfoList(@RequestParam int page,@RequestParam(required=false) String empType,@RequestParam(defaultValue="10") int pageSize) {
					return new SuccessResponseMessage(this.siteService.getEmpInfoList(page,empType,pageSize));
				}

				*//**
				 * 创建
				 * 
				 * @param form
				 * @return
				 *//*
				@RequestMapping(value = "admin/empInfo", method = RequestMethod.POST)
				public @ResponseBody ResponseMessage insertEmpInfo(@RequestBody EmpInfo form) {
					try {
						this.siteService.insertEmpInfo(form);
					} catch (Exception e) {
						return new FailureResponseMessage(e);
					}
					return new SuccessResponseMessage(form.getId());
				}

				*//**
				 * 修改
				 *//*
				@RequestMapping(value = "admin/empInfo", method = RequestMethod.PUT)
				public @ResponseBody ResponseMessage modifyEmpInfo(@RequestBody EmpInfo form) {
					try {
						this.siteService.updateEmpInfo(form);
					} catch (Exception e) {
						return new FailureResponseMessage(e);
					}
					return new SuccessResponseMessage();
				}

				*//**
				 * 删除
				 * 
				 * @param seq
				 * @return
				 *//*
				@RequestMapping(value = "admin/empInfo/{id}", method = RequestMethod.DELETE)
				public @ResponseBody ResponseMessage removeEmpInfo(@PathVariable String id) {
					try {
						this.siteService.deleteEmpInfo(id);
					} catch (Exception e) {
						return new FailureResponseMessage(e);
					}
					return new SuccessResponseMessage();
				}
				
				
				*//**
				   * 团队管理
				   *//*
				  @RequestMapping(value = "/admin/richArticleInfoManage", method = RequestMethod.GET)
				  public String richArticleInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
				  {
				      if(session.getAttribute("loginInfo") == null)
				      {
				          response.sendRedirect(request.getContextPath());
				          return null;
				      }
				      String result = "/admin/richArticle/richArticle";
				      
				      String type = request.getParameter("type");
				      if(!StringUtils.isEmpty(type))
				    	  result+=type;
				      
				      return result;
				  }
				
				*//**
				 * 查询
				 *//*
				@RequestMapping(value = "admin/richArticleInfo", method = RequestMethod.GET)
				public @ResponseBody ResponseMessage getRichArticleInfoList(@RequestParam int page,@RequestParam(required=false) String articleType,@RequestParam(defaultValue="10") int pageSize) {
					return new SuccessResponseMessage(this.siteService.getRichArticleInfoList(page,articleType,pageSize));
				}
				
				*//**
				 * 咨询管理页面
				 *//*
				@RequestMapping(value = "admin/richArticleInfoVO", method = RequestMethod.GET)
				public @ResponseBody ResponseMessage getRichArticleInfoVOList(@RequestParam int page,@RequestParam(required=false) String articleType,@RequestParam(defaultValue="10") int pageSize) {
					return new SuccessResponseMessage(this.siteService.getRichArticleInfoVOList(page,articleType,pageSize));
				}

				*//**
				 * 创建
				 * 
				 * @param form
				 * @return
				 *//*
				@RequestMapping(value = "admin/richArticleInfo", method = RequestMethod.POST)
				public @ResponseBody ResponseMessage insertRichArticleInfo(@RequestBody RichArticleInfo form) {
					try {
						this.siteService.insertRichArticleInfo(form);
					} catch (Exception e) {
						return new FailureResponseMessage(e);
					}
					return new SuccessResponseMessage(form.getId());
				}

				*//**
				 * 修改
				 *//*
				@RequestMapping(value = "admin/richArticleInfo", method = RequestMethod.PUT)
				public @ResponseBody ResponseMessage modifyRichArticleInfo(@RequestBody RichArticleInfo form) {
					try {
						this.siteService.updateRichArticleInfo(form);
					} catch (Exception e) {
						return new FailureResponseMessage(e);
					}
					return new SuccessResponseMessage();
				}

				*//**
				 * 删除
				 * 
				 * @param seq
				 * @return
				 *//*
				@RequestMapping(value = "admin/richArticleInfo/{id}", method = RequestMethod.DELETE)
				public @ResponseBody ResponseMessage removeRichArticleInfo(@PathVariable String id) {
					try {
						this.siteService.deleteRichArticleInfo(id);
					} catch (Exception e) {
						return new FailureResponseMessage(e);
					}
					return new SuccessResponseMessage();
				}
				
				
				
				*//**
				   * 留言管理
				   *//*
				  @RequestMapping(value = "/admin/feedBackInfoManage", method = RequestMethod.GET)
				  public String feedBackInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
				  {
				      if(session.getAttribute("loginInfo") == null)
				      {
				          response.sendRedirect(request.getContextPath());
				          return null;
				      }
				      return "/admin/feedBack/feedBack";
				  }
				  
				  *//**
					 * 查询
					 *//*
					@RequestMapping(value = "admin/feedBackInfo", method = RequestMethod.GET)
					public @ResponseBody ResponseMessage getFeedBackInfoList(@RequestParam int page,@RequestParam(required=false) String status,@RequestParam(defaultValue="10") int pageSize) {
						return new SuccessResponseMessage(this.siteService.getFeedBackInfoList(page,status,pageSize));
					}
					

					*//**
					 * 创建
					 * 
					 * @param form
					 * @return
					 *//*
					@RequestMapping(value = "admin/feedBackInfo", method = RequestMethod.POST)
					public @ResponseBody ResponseMessage insertFeedBackInfo(@RequestBody FeedBackInfo form) {
						try {
							this.siteService.insertFeedBackInfo(form);
						} catch (Exception e) {
							return new FailureResponseMessage(e);
						}
						return new SuccessResponseMessage(form.getId());
					}

					*//**
					 * 修改
					 *//*
					@RequestMapping(value = "admin/feedBackInfo", method = RequestMethod.PUT)
					public @ResponseBody ResponseMessage modifyFeedBackInfo(@RequestBody FeedBackInfo form) {
						try {
							this.siteService.updateFeedBackInfo(form.getId());
						} catch (Exception e) {
							return new FailureResponseMessage(e);
						}
						return new SuccessResponseMessage();
					}

}
*/