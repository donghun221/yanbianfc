package com.ffyc.site.model;

import com.ffyc.site.common.image.ImageInfo;

public class TeamScore {
	
	private String id;
	private String name;
	private ImageInfo logoImg;
	private String roundTime;
	private String win;
	private String draw;
	private String lose;
	private String pointLose;
	private String pointWin;
	private String totalScore;
	private String pointOffset;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ImageInfo getLogoImg() {
		return logoImg;
	}
	public void setLogoImg(ImageInfo logoImg) {
		this.logoImg = logoImg;
	}
	public String getRoundTime() {
		return roundTime;
	}
	public void setRoundTime(String roundTime) {
		this.roundTime = roundTime;
	}
	public String getWin() {
		return win;
	}
	public void setWin(String win) {
		this.win = win;
	}
	public String getDraw() {
		return draw;
	}
	public void setDraw(String draw) {
		this.draw = draw;
	}
	public String getLose() {
		return lose;
	}
	public void setLose(String lose) {
		this.lose = lose;
	}
	public String getPointLose() {
		return pointLose;
	}
	public void setPointLose(String pointLose) {
		this.pointLose = pointLose;
	}
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
	public String getPointOffset() {
		return pointOffset;
	}
	public void setPointOffset(String pointOffset) {
		this.pointOffset = pointOffset;
	}
	public String getPointWin() {
		return pointWin;
	}
	public void setPointWin(String pointWin) {
		this.pointWin = pointWin;
	}

}