package com.ffyc.site.service;

import java.util.List;

import com.ffyc.site.common.image.ImageInfo;
import com.ffyc.site.common.page.PaginationData;
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

public interface SiteService {

	void insertImageInfo(ImageInfo imageInfo);

	WelcomeInfo getWelcomeInfo();

	void updateWelcomInfo(String showTime, String backgroundId, String companyLogoId, String footContent);

	IndexInfo getIndexInfo();

	void updateIndexInfo(String contentText, String copyrightText, String addressText, String titleText);

	Intro getIntro();

	AdminInfo getAdminInfo(String adminName);

	List<AdminInfo> getAdminInfoList();

	void updateAdminInfo(AdminInfo info);

	PaginationData<LinkInfo> getLinkInfoList(int page);

	void updateLinkInfo(LinkInfo info);

	void insertLinkInfo(LinkInfo info);

	void deleteLinkInfo(String id);

	//PaginationData<RichArticleInfo> getRichArticleInfoVOList(int page, String articleType, int pageSize);

	PaginationData<RichArticleInfo> getRichArticleInfoList(int page, String articleType, int pageSize,String isBalad);

	void updateRichArticleInfo(RichArticleInfo info);

	void insertRichArticleInfo(RichArticleInfo info);

	void deleteRichArticleInfo(String id);

	PaginationData<RichArticleInfoVO> getRichArticleInfoListVO(int page, String articleType, int pageSize,String isBalad,String orderType);

	void addRichArticleInfoReadCount(String id);
	
	RichArticleInfo getRichArticleInfo(String id);
	
	
	PaginationData<FeedBackInfo> getFeedBackInfoList(int page, String status, int pageSize);

	void updateFeedBackInfo(String id);

	void insertFeedBackInfo(FeedBackInfo info);

	PaginationData<TeamScore> getTeamScoreList(int page, int pageSize);

	void updateTeamScore(TeamScore info);

	void insertTeamScore(TeamScore info);

	void deleteTeamScore(String id);

	PaginationData<MatchSchedule> getMatchScheduleList(int page, int pageSize);

	void updateMatchSchedule(MatchSchedule info);

	void insertMatchSchedule(MatchSchedule info);

	void deleteMatchSchedule(String id);

	List<RichArticleInfoVO> getAllBaladRicheArticleListVO(String articleType);

	MatchSchedule getNextMatchSchedule();

	MatchSchedule getLastMatchSchedule();

	void fixArticleData();


	
}
