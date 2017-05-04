package com.peernet.mobile.server.admin.common.vo;

public class WorkBookData{
	
	private String[] headArray;
	private int[] headWithArray;
	private String title;
	private String[][] dataArray;
	public String[] getHeadArray() {
		return headArray;
	}
	public void setHeadArray(String[] headArray) {
		this.headArray = headArray;
	}
	public int[] getHeadWithArray() {
		return headWithArray;
	}
	public void setHeadWithArray(int[] headWithArray) {
		this.headWithArray = headWithArray;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[][] getDataArray() {
		return dataArray;
	}
	public void setDataArray(String[][] dataArray) {
		this.dataArray = dataArray;
	}
	
}