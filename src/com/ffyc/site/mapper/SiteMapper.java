package com.ffyc.site.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.site.common.image.ImageInfo;
import com.ffyc.site.model.AdminInfo;
import com.ffyc.site.model.FeedBackInfo;
import com.ffyc.site.model.IndexInfo;
import com.ffyc.site.model.Intro;
import com.ffyc.site.model.LinkInfo;
import com.ffyc.site.model.MatchSchedule;
import com.ffyc.site.model.RichArticleInfo;
import com.ffyc.site.model.RichArticleInfoVO;
import com.ffyc.site.model.TeamScore;
import com.ffyc.site.model.WelcomeInfo;

public interface SiteMapper {
	
	public void insertImage(ImageInfo imageInfo);

	public WelcomeInfo getWelcomeInfo();
	
	public void updateWelcomeInfo(@Param("showTime") String showTime,@Param("backgroundId") String backgroundId,@Param("companyLogoId") String companyLogoId,@Param("footContent") String footContent);

	public IndexInfo getIndexInfo();
	
	public void updateIndexInfo(@Param("contentText") String contentText,@Param("copyrightText") String copyrightText,@Param("addressText") String addressText,@Param("titleText") String titleText);
	
	public Intro getIntro();
	
	
	public AdminInfo getAdminInfo(@Param("adminName") String adminName);
	
	public List<AdminInfo> getAdminInfoList();
	
	public void updateAdminInfo(AdminInfo info);
	
	
	////////
	
	public List<LinkInfo> getLinkInfoList(@Param("startRow") int startRow,@Param("count") int count);
	
	public int getLinkInfoListCount();
	
	public void insertLinkInfo(LinkInfo info);
	
	public void updateLinkInfo(LinkInfo info);
	
	public void deleteLinkInfo(@Param("id") String id);
	
	
	/****图文文章*****/
	
	public List<RichArticleInfoVO> getRichArticleInfoVOList(@Param("startRow") int startRow,@Param("count") int count,@Param("articleType") String articleType,@Param("isBalad") String isBalad,@Param("orderType") String orderType);
	
	public List<RichArticleInfo> getRichArticleInfoList(@Param("startRow") int startRow,@Param("count") int count,@Param("articleType") String articleType,@Param("isBalad") String isBalad);
	
	public int getRichArticleInfoListCount(@Param("articleType") String articleType,@Param("isBalad") String isBalad);
	
	public void insertRichArticleInfo(RichArticleInfo info);
	
	public void updateRichArticleInfo(RichArticleInfo info);
	public void deleteRichArticleInfo(@Param("id") String id);
	
	public void addRichArticleInfoReadCount(@Param("id") String id);
	
	public RichArticleInfo getRichArticleInfo(@Param("id") String id);
	
	/****球队积分*****/
	
	public List<TeamScore> getTeamScoreList(@Param("startRow") int startRow,@Param("count") int count);
	
	public int getTeamScoreListCount();
	
	public void insertTeamScore(TeamScore info);
	
	public void updateTeamScore(TeamScore info);
	
	public void deleteTeamScore(@Param("id") String id);
	
	
	
	/*****赛程******/
	
	public List<MatchSchedule> getMatchScheduleList(@Param("startRow") int startRow,@Param("count") int count);
	
	public int getMatchScheduleListCount();
	
	public void insertMatchSchedule(MatchSchedule info);
	
	public void updateMatchSchedule(MatchSchedule info);
	
	public void deleteMatchSchedule(@Param("id") String id);
	
	public List<FeedBackInfo> getFeedBackInfoList(@Param("startRow") int startRow,@Param("count") int count,@Param("status") String status);
	
	public int getFeedBackInfoListCount(@Param("status") String status);
	
	public void insertFeedBackInfo(FeedBackInfo info);
	
	public void updateFeedBackInfo(@Param("id") String id);
	public MatchSchedule getNextMatchSchedule();
	public MatchSchedule getLastMatchSchedule();
	
	
}
