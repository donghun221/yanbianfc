package com.ffyc.site.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.site.common.image.ImageInfo;
import com.ffyc.site.common.page.PageCalc;
import com.ffyc.site.common.page.PaginationData;
import com.ffyc.site.mapper.SiteMapper;
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

@Service("siteService")
public class SiteServiceImpl implements SiteService {
	

	@Autowired
	private SiteMapper siteMapper;
	

	@Override
	public void insertImageInfo(ImageInfo imageInfo) {
		this.siteMapper.insertImage(imageInfo);
	}
	@Override
	public WelcomeInfo getWelcomeInfo() {
		return this.siteMapper.getWelcomeInfo();
	}
	@Override
	public void updateWelcomInfo(String showTime,String backgroundId,String companyLogoId,String footContent){
		this.siteMapper.updateWelcomeInfo(showTime, backgroundId, companyLogoId,footContent);
	}
	
	@Override
	public IndexInfo getIndexInfo() {
		return this.siteMapper.getIndexInfo();
	}
	@Override
	public void updateIndexInfo(String contentText, String copyrightText,
			String addressText, String titleText) {
		this.siteMapper.updateIndexInfo(contentText, copyrightText, addressText, titleText);
		
	}
	@Override
	public Intro getIntro() {
		return this.siteMapper.getIntro();
	}
	@Override
	public AdminInfo getAdminInfo(String adminName) {
		return this.siteMapper.getAdminInfo(adminName);
	}
	@Override
	public List<AdminInfo> getAdminInfoList() { 
		return this.siteMapper.getAdminInfoList();
	}
	@Override
	public void updateAdminInfo(AdminInfo info) {
		this.siteMapper.updateAdminInfo(info);
	}
	
	//////////////////////
	
	@Override
	public PaginationData<LinkInfo> getLinkInfoList(int page) {
		PageCalc pageCalc = new PageCalc(this.siteMapper.getLinkInfoListCount());
	    List<LinkInfo> list = this.siteMapper.getLinkInfoList(pageCalc.getStart(page), pageCalc.getPageSize());
	    return new PaginationData<LinkInfo>(list, pageCalc);
	}
	@Override
	public void updateLinkInfo(LinkInfo info) {
		this.siteMapper.updateLinkInfo(info);
	}
	@Override
	public void insertLinkInfo(LinkInfo info) {
		this.siteMapper.insertLinkInfo(info);
	}
	@Override
	public void deleteLinkInfo(String id) {
		this.siteMapper.deleteLinkInfo(id);
	}
	
	
	
	
	
	
	
	
	/****图文文章*****/
	
	@Override
	public PaginationData<RichArticleInfo> getRichArticleInfoList(int page,String articleType,int pageSize,String isBalad) {
		PageCalc pageCalc = new PageCalc(this.siteMapper.getRichArticleInfoListCount(articleType,isBalad),pageSize);
	    List<RichArticleInfo> list = this.siteMapper.getRichArticleInfoList(pageCalc.getStart(page), pageCalc.getPageSize(),articleType,isBalad);
	    return new PaginationData<RichArticleInfo>(list, pageCalc);
	}
	@Override
	public void updateRichArticleInfo(RichArticleInfo info) {
		this.siteMapper.updateRichArticleInfo(info);
	}
	@Override
	public void insertRichArticleInfo(RichArticleInfo info) {
		this.siteMapper.insertRichArticleInfo(info);
	}
	@Override
	public void deleteRichArticleInfo(String id) {
		this.siteMapper.deleteRichArticleInfo(id);
	}
	
	@Override
	public PaginationData<RichArticleInfoVO> getRichArticleInfoListVO(int page,String articleType,int pageSize,String isBalad,String orderType) {
		PageCalc pageCalc = new PageCalc(this.siteMapper.getRichArticleInfoListCount(articleType,isBalad),pageSize);
	    List<RichArticleInfoVO> list = this.siteMapper.getRichArticleInfoVOList(pageCalc.getStart(page), pageCalc.getPageSize(),articleType,isBalad,orderType);
	    return new PaginationData<RichArticleInfoVO>(list, pageCalc);
	}
	
	@Override
	public void addRichArticleInfoReadCount(String id) {
		this.siteMapper.addRichArticleInfoReadCount(id);
	}
	
	
	@Override
	public RichArticleInfo getRichArticleInfo(String id) {

		this.addRichArticleInfoReadCount(id);
		
		return this.siteMapper.getRichArticleInfo(id);
	}
	
	/**
	 * 查询首推
	 */
	@Override
	public List<RichArticleInfoVO> getAllBaladRicheArticleListVO(String articleType){
		PaginationData<RichArticleInfoVO> result = this.getRichArticleInfoListVO(1, articleType, 10, "1",null);
		return result.getData();
	}
	
	/****球队积分*****/
	
	@Override
	public PaginationData<TeamScore> getTeamScoreList(int page,int pageSize) {
		PageCalc pageCalc = new PageCalc(this.siteMapper.getTeamScoreListCount(),pageSize);
	    List<TeamScore> list = this.siteMapper.getTeamScoreList(pageCalc.getStart(page), pageCalc.getPageSize());
	    return new PaginationData<TeamScore>(list, pageCalc);
	}
	@Override
	public void updateTeamScore(TeamScore info) {
		this.calcScoreAndPoints(info);
		this.siteMapper.updateTeamScore(info);
	}
	@Override
	public void insertTeamScore(TeamScore info) {
		this.calcScoreAndPoints(info);
		this.siteMapper.insertTeamScore(info);
	}
	@Override
	public void deleteTeamScore(String id) {
		this.siteMapper.deleteTeamScore(id);
	}
	
	/**
	 * 计算积分、净胜
	 * @param info
	 */
	private void calcScoreAndPoints(TeamScore info){
		info.setPointOffset((Integer.parseInt(info.getPointWin())-Integer.parseInt(info.getPointLose()))+"");//净胜球
		info.setTotalScore((Integer.parseInt(info.getWin())*3+Integer.parseInt(info.getDraw())*1)+"");
	}
	
	
	
	/****赛程*****/
	
	@Override
	public PaginationData<MatchSchedule> getMatchScheduleList(int page,int pageSize) {
		PageCalc pageCalc = new PageCalc(this.siteMapper.getMatchScheduleListCount(),pageSize);
	    List<MatchSchedule> list = this.siteMapper.getMatchScheduleList(pageCalc.getStart(page), pageCalc.getPageSize());
	    return new PaginationData<MatchSchedule>(list, pageCalc);
	}
	@Override
	public void updateMatchSchedule(MatchSchedule info) {
		this.siteMapper.updateMatchSchedule(info);
	}
	@Override
	public void insertMatchSchedule(MatchSchedule info) {
		this.siteMapper.insertMatchSchedule(info);
	}
	@Override
	public void deleteMatchSchedule(String id) {
		this.siteMapper.deleteMatchSchedule(id);
	}
	@Override
	public MatchSchedule getNextMatchSchedule(){
		return this.siteMapper.getNextMatchSchedule();
	}
	@Override
	public MatchSchedule getLastMatchSchedule(){
		return this.siteMapper.getLastMatchSchedule();
	}
	
	
	@Override
	public PaginationData<FeedBackInfo> getFeedBackInfoList(int page,String status,int pageSize) {
		PageCalc pageCalc = new PageCalc(this.siteMapper.getFeedBackInfoListCount(status),pageSize);
	    List<FeedBackInfo> list = this.siteMapper.getFeedBackInfoList(pageCalc.getStart(page), pageCalc.getPageSize(),status);
	    return new PaginationData<FeedBackInfo>(list, pageCalc);
	}
	@Override
	public void updateFeedBackInfo(String id) {
		this.siteMapper.updateFeedBackInfo(id);
	}
	@Override
	public void insertFeedBackInfo(FeedBackInfo info) {
		this.siteMapper.insertFeedBackInfo(info);
	}
	
	@Override
	public void fixArticleData(){
		//  /editor/attached/image/20170311/20170311095392539253.jpg
		Pattern patternUrl = Pattern.compile("/editor/attached/image/\\d{8}/\\d{19,20}\\.(jpg|png|JPG|PNG)");
		List<RichArticleInfo> list = this.siteMapper.getRichArticleInfoList(1, 500, null, null);
		int len = list.size();
		for(int i=0;i<len;i++){
			RichArticleInfo a = list.get(i);
			if(a.getArticleImage()==null){
				Matcher matcherUrl = patternUrl.matcher(a.getContentText());
				if(matcherUrl.find()){
					String firstImageURL = matcherUrl.group();
					ImageInfo imgInfo = this.genImageInfo(firstImageURL);
					if(imgInfo!=null){
						a.setArticleImage(imgInfo);
					}else{
						System.out.println("couldnt create a image info");
					}
				}else{
					System.out.println("cant find any image");
				}
			}
			
			String contentTextNew = a.getContentText();
			contentTextNew = contentTextNew.replaceAll("/editor/attached/image/", "/staticcontent/");
			a.setContentText(contentTextNew);
			this.siteMapper.updateRichArticleInfo(a);
		}
	}
	
	
	
	private ImageInfo genImageInfo(String url){
		String shortUrl = url.replaceAll("/editor/attached/image/", "");
		String path = shortUrl.split("/")[0];
		String fileName = shortUrl.split("/")[1];
		ImageInfo img = new ImageInfo();
		img.setFileName(fileName);
		img.setPath(path);
		this.siteMapper.insertImage(img);
		return img;
		
	}
	
	public static void main(String[]a){
		//Pattern pattern = Pattern.compile("editor/attached/image/[0-9]\\d{8}/[0-9]\\d{19,21}\\.(jpg|png|JPG|PNG)");
		//System.out.println(pattern.matcher("editor/attached/image/20170311/20170311095392539253.jpg").find());
		Pattern pattern = Pattern.compile("editor/attached/image/\\d{8}/\\d{19,20}\\.(jpg|png|JPG|PNG)");
		Matcher matcher = pattern.matcher("sa 电风扇 dfs/editor/attached/image/20170311/20170311095392539253.jpg\"?/>");
		if(matcher.find()){
			String matchString = matcher.group();
			System.out.println(matchString);
		}
		
		
	}
}
