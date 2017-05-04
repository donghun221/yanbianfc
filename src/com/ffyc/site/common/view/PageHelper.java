package com.ffyc.site.common.view;

import java.util.HashMap;
import java.util.Map;

import com.ffyc.site.common.image.ImageInfo;
import com.peernet.mobile.server.admin.common.cst.AttachDir;

public class PageHelper {

	private static Map<String,String> articleDict = new HashMap<String, String>();
	static{
		articleDict.put("1", "官方新闻");
		articleDict.put("2", "官方公告");
		articleDict.put("3", "精彩视频");
		articleDict.put("4", "精彩图片");
	}
	
	public static String getRichArticleCategoryCode(String articleType){
		return articleType;
	}
	
	public static String getImagePath(ImageInfo image){
		if(image==null)
			return "";
		return AttachDir.ATTACH_BASE_DIR+"/"+image.getPath()+"/"+image.getFileName();
	}
	
	public static String getGetArticleCategoryDictMsg(String articleType){
		return articleDict.get(articleType);
	}
}
