package com.ffyc.site.service.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffyc.site.common.page.PaginationData;
import com.ffyc.site.model.AdminInfo;
import com.ffyc.site.model.FeedBackInfo;
import com.ffyc.site.model.IndexInfo;
import com.ffyc.site.model.LinkInfo;
import com.ffyc.site.model.MatchSchedule;
import com.ffyc.site.model.RichArticleInfo;
import com.ffyc.site.model.RichArticleInfoVO;
import com.ffyc.site.model.TeamScore;
import com.ffyc.site.permission.model.UserMenu;
import com.ffyc.site.permission.service.PermissionService;
import com.ffyc.site.service.SiteService;
import com.nationsky.pub.framework.exception.ApplicationException;
import com.nationsky.pub.utils.GsonUtils;
import com.peernet.mobile.server.admin.common.message.FailureResponseMessage;
import com.peernet.mobile.server.admin.common.message.ResponseMessage;
import com.peernet.mobile.server.admin.common.message.SuccessResponseMessage;

@Controller
public class SiteController {

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private SiteService siteService;

	/**
	 * 页面初始化（如果未登录跳到登陆页面，否则跳到管理页面）
	 */
	@RequestMapping("/admin")
	public String init(HttpSession session) {
		if (session.getAttribute("loginInfo") != null) {
			return "/admin/main";
		}
		return "/admin/login";
	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String login(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam String username, @RequestParam String password) throws IOException {
		try {
			List<UserMenu> userMenu = permissionService.login(username, password);
			session.setAttribute("loginInfo", username);
			session.setAttribute("username", username);
			session.setAttribute("menu", GsonUtils.toGson(userMenu));

			// response.sendRedirect(request.getContextPath());
			return "/admin/main";
		} catch (ApplicationException e) {
			model.addAttribute("message", e.getMessage());
			// return "/admin/login";
			return "redirect:" + "/admin";
		}
	}

	/**
	 * 退出登录
	 */
	// @RequestMapping("/admin/logout")
	@RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		session.removeAttribute("loginInfo");

		return "redirect:" + "/admin";
	}

	/**
	 * 首页管理
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin/indexInfoManage", method = RequestMethod.GET)
	public String indexInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (session.getAttribute("loginInfo") == null) {
			response.sendRedirect(request.getContextPath());
			return null;
		}
		return "/admin/index/index";
	}

	/**
	 * 获取首页信息
	 */
	@RequestMapping(value = "admin/indexInfo", method = RequestMethod.GET)
	public @ResponseBody ResponseMessage getIndexInfo() {
		return new SuccessResponseMessage(this.siteService.getIndexInfo());
	}

	/**
	 * 修改首页信息
	 */
	@RequestMapping(value = "admin/indexInfo", method = RequestMethod.PUT)
	public @ResponseBody ResponseMessage modifyIndexInfo(@RequestBody IndexInfo form) {
		try {
			this.siteService.updateIndexInfo(form.getContentText(), form.getCopyrightText(), form.getAddressText(),
					form.getTitleText());
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}

	/**
	 * 转至管理员修改密码
	 */

	@RequestMapping(value = "/admin/adminInfoManage", method = RequestMethod.GET)
	public String adminInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (session.getAttribute("loginInfo") == null) {
			response.sendRedirect(request.getContextPath());
			return null;
		}
		return "/admin/user/password";
	}

	/**
	 * 获取管理员对象
	 */
	@RequestMapping(value = "admin/adminInfo", method = RequestMethod.GET)
	public @ResponseBody ResponseMessage getAdminInfo(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		String adminName = (String) session.getAttribute("username");
		return new SuccessResponseMessage(this.siteService.getAdminInfo(adminName));
	}

	/**
	 * 修改管理员密码
	 */
	@RequestMapping(value = "admin/updateAdminInfo", method = RequestMethod.PUT)
	public @ResponseBody ResponseMessage updateAdminInfo(@RequestBody AdminInfo form) {
		try {
			this.siteService.updateAdminInfo(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}

	/**
	 * 友情链接管理
	 */
	@RequestMapping(value = "/admin/linkInfoManage", method = RequestMethod.GET)
	public String linkInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (session.getAttribute("loginInfo") == null) {
			response.sendRedirect(request.getContextPath());
			return null;
		}
		return "/admin/link/link";
	}

	/**
	 * 管理页面
	 */
	@RequestMapping(value = "admin/linkInfo", method = RequestMethod.GET)
	public @ResponseBody ResponseMessage getLinkInfoList(@RequestParam int page) {
		return new SuccessResponseMessage(this.siteService.getLinkInfoList(page));
	}

	/**
	 * 创建
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "admin/linkInfo", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage insertLinkInfo(@RequestBody LinkInfo form) {
		try {
			this.siteService.insertLinkInfo(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage(form.getId());
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "admin/linkInfo", method = RequestMethod.PUT)
	public @ResponseBody ResponseMessage modifyLinkInfo(@RequestBody LinkInfo form) {
		try {
			this.siteService.updateLinkInfo(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}

	/**
	 * 删除
	 * 
	 * @param seq
	 * @return
	 */
	@RequestMapping(value = "admin/linkInfo/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseMessage removeLinkInfo(@PathVariable String id) {
		try {
			this.siteService.deleteLinkInfo(id);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}

	/**
	 * 留言管理
	 */
	@RequestMapping(value = "/admin/feedBackInfoManage", method = RequestMethod.GET)
	public String feedBackInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (session.getAttribute("loginInfo") == null) {
			response.sendRedirect(request.getContextPath());
			return null;
		}
		return "/admin/feedBack/feedBack";
	}

	/**
	 * 查询
	 */
	@RequestMapping(value = "admin/feedBackInfo", method = RequestMethod.GET)
	public @ResponseBody ResponseMessage getFeedBackInfoList(@RequestParam int page,
			@RequestParam(required = false) String status, @RequestParam(defaultValue = "10") int pageSize) {
		return new SuccessResponseMessage(this.siteService.getFeedBackInfoList(page, status, pageSize));
	}

	/**
	 * 创建
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "admin/feedBackInfo", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage insertFeedBackInfo(@RequestBody FeedBackInfo form) {
		try {
			this.siteService.insertFeedBackInfo(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage(form.getId());
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "admin/feedBackInfo", method = RequestMethod.PUT)
	public @ResponseBody ResponseMessage modifyFeedBackInfo(@RequestBody FeedBackInfo form) {
		try {
			this.siteService.updateFeedBackInfo(form.getId());
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}

	/*
	 * 图文内容管理
	 */
	@RequestMapping(value = "/admin/richArticleInfoManage", method = RequestMethod.GET)
	public String richArticleInfoManage(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (session.getAttribute("loginInfo") == null) {
			response.sendRedirect(request.getContextPath());
			return null;
		}
		return "/admin/richArticle/richArticle";
	}

	/**
	 * 查询
	 */
	@RequestMapping(value = "admin/richArticleInfo", method = RequestMethod.GET)
	public @ResponseBody ResponseMessage getRichArticleInfoList(@RequestParam int page,
			@RequestParam(required = false) String articleType,@RequestParam(required = false) String isBalad, @RequestParam(defaultValue = "10") int pageSize) {
		return new SuccessResponseMessage(this.siteService.getRichArticleInfoList(page, articleType, pageSize,isBalad));
	}


	/**
	 * 创建
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "admin/richArticleInfo", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage insertRichArticleInfo(@RequestBody RichArticleInfo form) {
		try {
			this.siteService.insertRichArticleInfo(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage(form.getId());
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "admin/richArticleInfo", method = RequestMethod.PUT)
	public @ResponseBody ResponseMessage modifyRichArticleInfo(@RequestBody RichArticleInfo form) {
		try {
			this.siteService.updateRichArticleInfo(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}

	/**
	 * 删除
	 * 
	 * @param seq
	 * @return
	 */
	@RequestMapping(value = "admin/richArticleInfo/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseMessage removeRichArticleInfo(@PathVariable String id) {
		try {
			this.siteService.deleteRichArticleInfo(id);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}
	
	/*
	 * 球队信息管理
	 */
	@RequestMapping(value = "/admin/teamScoreManage", method = RequestMethod.GET)
	public String TeamScoreManage(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (session.getAttribute("loginInfo") == null) {
			response.sendRedirect(request.getContextPath());
			return null;
		}
		return "/admin/teamScore/teamScore";
	}

	/**
	 * 查询
	 */
	@RequestMapping(value = "admin/teamScore", method = RequestMethod.GET)
	public @ResponseBody ResponseMessage getTeamScoreList(@RequestParam int page,
			 @RequestParam(defaultValue = "10") int pageSize) {
		return new SuccessResponseMessage(this.siteService.getTeamScoreList(page, pageSize));
	}


	/**
	 * 创建
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "admin/teamScore", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage insertTeamScore(@RequestBody TeamScore form) {
		try {
			this.siteService.insertTeamScore(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage(form.getId());
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "admin/teamScore", method = RequestMethod.PUT)
	public @ResponseBody ResponseMessage modifyTeamScore(@RequestBody TeamScore form) {
		try {
			this.siteService.updateTeamScore(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}

	/**
	 * 删除
	 * 
	 * @param seq
	 * @return
	 */
	@RequestMapping(value = "admin/teamScore/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseMessage removeTeamScore(@PathVariable String id) {
		try {
			this.siteService.deleteTeamScore(id);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}
	
	
	/*
	 * 赛程管理
	 */
	@RequestMapping(value = "/admin/matchScheduleManage", method = RequestMethod.GET)
	public String MatchScheduleManage(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (session.getAttribute("loginInfo") == null) {
			response.sendRedirect(request.getContextPath());
			return null;
		}
		return "/admin/matchSchedule/matchSchedule";
	}

	/**
	 * 查询
	 */
	@RequestMapping(value = "admin/matchSchedule", method = RequestMethod.GET)
	public @ResponseBody ResponseMessage getMatchScheduleList(@RequestParam int page,
			 @RequestParam(defaultValue = "10") int pageSize) {
		return new SuccessResponseMessage(this.siteService.getMatchScheduleList(page, pageSize));
	}


	/**
	 * 创建
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "admin/matchSchedule", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage insertMatchSchedule(@RequestBody MatchSchedule form) {
		try {
			this.siteService.insertMatchSchedule(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage(form.getId());
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "admin/matchSchedule", method = RequestMethod.PUT)
	public @ResponseBody ResponseMessage modifyMatchSchedule(@RequestBody MatchSchedule form) {
		try {
			this.siteService.updateMatchSchedule(form);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}

	/**
	 * 删除
	 * 
	 * @param seq
	 * @return
	 */
	@RequestMapping(value = "admin/matchSchedule/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseMessage removeMatchSchedule(@PathVariable String id) {
		try {
			this.siteService.deleteMatchSchedule(id);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}
	
	/**********网站用***********/
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String siteIndexDefault(Model model,HttpServletRequest request)
			throws IOException {
		//this.siteService.fixArticleData();
		return "redirect:/index";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String siteIndex(Model model,HttpServletRequest request)
			throws IOException {
		
		//查询条件
		String topAreaType = request.getParameter("tt");
		model.addAttribute("tt", topAreaType);
		
		//滚动区域
		List<RichArticleInfoVO> allBaladList = this.siteService.getAllBaladRicheArticleListVO(null);
		model.addAttribute("allBaladList", allBaladList);
		
		//8个最新
		List<RichArticleInfoVO> topAreaList = this.siteService.getRichArticleInfoListVO(1, topAreaType, 8, null,null).getData();
		if(topAreaList!=null&&topAreaList.size()>0){
			List<RichArticleInfoVO> topAreaListLeft = new ArrayList<RichArticleInfoVO>();
			List<RichArticleInfoVO> topAreaListRight = new ArrayList<RichArticleInfoVO>();
			
			int len = topAreaList.size();
			for(int i=0;i<len;i++){
				if(i%2==0)
					topAreaListLeft.add(topAreaList.get(i));
				else
					topAreaListRight.add(topAreaList.get(i));
			}
			
			model.addAttribute("topAreaListLeft", topAreaListLeft);
			model.addAttribute("topAreaListRight", topAreaListRight);
		}
		
		//赛程
		MatchSchedule nextMatch = this.siteService.getNextMatchSchedule();
		MatchSchedule lastMatch = this.siteService.getLastMatchSchedule();
		model.addAttribute("nextMatch", nextMatch);
		model.addAttribute("lastMatch", lastMatch);
		
		//前8强排名
		
		PaginationData<TeamScore> pageData = this.siteService.getTeamScoreList(1, 8);
		if(pageData!=null&&pageData.getData()!=null){
			model.addAttribute("indexRankingList", pageData.getData());
		}
		
		//友情链接
		PaginationData<LinkInfo> linkPageData = this.siteService.getLinkInfoList(1);
		if(linkPageData!=null&&linkPageData.getData()!=null){
			model.addAttribute("linkList", linkPageData.getData());
		}
		
		return "/site/soccer_index";
	}
	
	//俱乐部简介
	@RequestMapping(value = "/teamOverview", method = RequestMethod.GET)
	public String siteTeamOverview(Model model)
			throws IOException {
		
		
		
		//友情链接
		PaginationData<LinkInfo> linkPageData = this.siteService.getLinkInfoList(1);
		if(linkPageData!=null&&linkPageData.getData()!=null){
			model.addAttribute("linkList", linkPageData.getData());
		}
		
		return "/site/soccer_teamOverview";
	}
	
	//俱乐部事记
		@RequestMapping(value = "/teamRoster", method = RequestMethod.GET)
		public String siteTeamRoster(Model model)
				throws IOException {
			
			
			
			//友情链接
			PaginationData<LinkInfo> linkPageData = this.siteService.getLinkInfoList(1);
			if(linkPageData!=null&&linkPageData.getData()!=null){
				model.addAttribute("linkList", linkPageData.getData());
			}
			
			return "/site/soccer_teamRoster";
		}
		
		//教练组
		@RequestMapping(value = "/teamCoach", method = RequestMethod.GET)
		public String siteTeamCoach(Model model)
				throws IOException {
			//友情链接
			PaginationData<LinkInfo> linkPageData = this.siteService.getLinkInfoList(1);
			if(linkPageData!=null&&linkPageData.getData()!=null){
				model.addAttribute("linkList", linkPageData.getData());
			}
			
			return "/site/soccer_teamCoach";
		}
		//球员组
		@RequestMapping(value = "/teamPlayer", method = RequestMethod.GET)
		public String siteTeamPlayer(Model model)
				throws IOException {
			//友情链接
			PaginationData<LinkInfo> linkPageData = this.siteService.getLinkInfoList(1);
			if(linkPageData!=null&&linkPageData.getData()!=null){
				model.addAttribute("linkList", linkPageData.getData());
			}
			
			return "/site/soccer_teamPlayer";
		}
		
		//联系我们
		@RequestMapping(value = "/teamContactUs", method = RequestMethod.GET)
		public String siteTeamContactUs(Model model)
				throws IOException {
			//友情链接
			PaginationData<LinkInfo> linkPageData = this.siteService.getLinkInfoList(1);
			if(linkPageData!=null&&linkPageData.getData()!=null){
				model.addAttribute("linkList", linkPageData.getData());
			}
			
			return "/site/soccer_teamContactUs";
		}
		
		//赛程
		@RequestMapping(value = "/teamSchedule", method = RequestMethod.GET)
		public String siteTeamSchedule(Model model)
				throws IOException {
			
			PaginationData<MatchSchedule> pageResult = this.siteService.getMatchScheduleList(1, 100);
			if(pageResult!=null&&pageResult.getData()!=null){
				model.addAttribute("matchScheduleList", pageResult.getData());
			}
			
			//友情链接
			PaginationData<LinkInfo> linkPageData = this.siteService.getLinkInfoList(1);
			if(linkPageData!=null&&linkPageData.getData()!=null){
				model.addAttribute("linkList", linkPageData.getData());
			}
			
			return "/site/soccer_teamSchedule";
		}
		
		//赛程
		@RequestMapping(value = "/teamScore", method = RequestMethod.GET)
		public String siteTeamScore(Model model)
				throws IOException {
			
			PaginationData<TeamScore> pageResult = this.siteService.getTeamScoreList(1, 99);
			if(pageResult!=null&&pageResult.getData()!=null){
				model.addAttribute("teamScoreList", pageResult.getData());
			}
			
			//友情链接
			PaginationData<LinkInfo> linkPageData = this.siteService.getLinkInfoList(1);
			if(linkPageData!=null&&linkPageData.getData()!=null){
				model.addAttribute("linkList", linkPageData.getData());
			}
			
			return "/site/soccer_teamScore";
		}
		
		//图文文章详情
		@RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
		public String siteArticleList(Model model,@PathVariable String id)
				throws IOException {
			
			RichArticleInfo article = this.siteService.getRichArticleInfo(id);
			model.addAttribute("article", article);
			
			
			
			//友情链接
			PaginationData<LinkInfo> linkPageData = this.siteService.getLinkInfoList(1);
			if(linkPageData!=null&&linkPageData.getData()!=null){
				model.addAttribute("linkList", linkPageData.getData());
			}
			
			return "/site/soccer_article";
		}
		
	
	/**
	 * 网站用-查询图文文章VOLIST
	 * 
	 * @param seq
	 * @return
	 */
	@RequestMapping(value = "/articleList", method = RequestMethod.GET)
	public String getRichArticleInfoListVO(
			Model model,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(required = false) String articleType,
			@RequestParam(required = false) String isBalad,
			@RequestParam(defaultValue = "10") int pageSize) {
		
		//列表
		PaginationData<RichArticleInfoVO>  pageResult = this.siteService.getRichArticleInfoListVO(page, articleType, pageSize,isBalad,null);
		if(pageResult!=null&&pageResult.getData()!=null){
			model.addAttribute("articleList", pageResult.getData());
			model.addAttribute("page", pageResult.getPageData());
		}
		//热门
		PaginationData<RichArticleInfoVO>  popularResult = this.siteService.getRichArticleInfoListVO(1, null, 4,null,"popular");
		if(popularResult!=null&&popularResult.getData()!=null){
			model.addAttribute("popularList", popularResult.getData());
		}
		
		model.addAttribute("articleType", articleType);
		model.addAttribute("currentPage", page);
		
		return "/site/soccer_articleList";
	}
	
	/**
	 * 网站用-增加阅读计数
	 * 
	 * @param seq
	 * @return
	 */
	@RequestMapping(value = "admin/richArticleInfo/{id}/readCount", method = RequestMethod.PUT)
	public @ResponseBody ResponseMessage addRichArticleInfoReadCount(@PathVariable String id) {
		try {
			this.siteService.addRichArticleInfoReadCount(id);
		} catch (Exception e) {
			return new FailureResponseMessage(e);
		}
		return new SuccessResponseMessage();
	}
	
	

}
