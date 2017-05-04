package com.ffyc.site.model;

import java.sql.Timestamp;
import java.util.Date;

import com.ffyc.site.common.image.ImageInfo;

public class RichArticleInfoVO {
    
    private String id;
    private String articleName;
    private String articleType;
    private String descText;
    private Timestamp createDate;
    private String readCount;
    private String orderNum;
    private ImageInfo articleImage;
    
    
    
	public String getId() {
		return id;
	}
	public String getArticleName() {
		return articleName;
	}
	public String getArticleType() {
		return articleType;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}
	public String getDescText() {
		return descText;
	}
	public ImageInfo getArticleImage() {
		return articleImage;
	}
	public void setDescText(String descText) {
		this.descText = descText;
	}
	public void setArticleImage(ImageInfo articleImage) {
		this.articleImage = articleImage;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getReadCount() {
		return readCount;
	}
	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
    
}
