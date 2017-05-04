package com.ffyc.site.model;

import com.ffyc.site.common.image.ImageInfo;

public class LinkInfo {

    private String id;
    private String linkName;
    private String linkUrl;
    private String orderNum;
    private ImageInfo linkImg;
    
    
    
	public String getId() {
		return id;
	}
	public String getLinkName() {
		return linkName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public ImageInfo getLinkImg() {
		return linkImg;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public void setLinkImg(ImageInfo linkImg) {
		this.linkImg = linkImg;
	}
}
