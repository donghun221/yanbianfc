package com.ffyc.site.model;

import java.util.Date;

public class FeedBackInfo {
    
    private String id;
    private String userName;
    private String email;
    private String contentText;
    private String status;
    private Date createDate;
	public String getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public String getEmail() {
		return email;
	}
	public String getContentText() {
		return contentText;
	}
	public String getStatus() {
		return status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setContentText(String contentText) {
		this.contentText = contentText;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
}
