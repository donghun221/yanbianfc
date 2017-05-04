package com.ffyc.site.model;

import com.ffyc.site.common.image.ImageInfo;

public class WelcomeInfo {
	
	private String id;
	
	private String showTime;
	
	private ImageInfo backGround;
	
	private ImageInfo companyLogo;
	
	private String footContent;
	
	public String getFootContent() {
		return footContent;
	}

	public void setFootContent(String footContent) {
		this.footContent = footContent;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}


	public ImageInfo getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(ImageInfo companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ImageInfo getBackGround() {
		return backGround;
	}

	public void setBackGround(ImageInfo backGround) {
		this.backGround = backGround;
	}

}
