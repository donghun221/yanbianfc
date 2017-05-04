package com.peernet.mobile.server.admin.common.cst;

import java.io.File;

import com.nationsky.pub.Env;

/**
 * 附件保存目录名称
 * 
 * @author 宋翔
 * @date 2013-11-13 下午5:55:08
 */
public class AttachDir
{
    /** 附件文件夹 */
	public static final String ATTACH_BASE_DIR = Env.getProperty("ATTACH_BASE_DIR");
    public static final File ATTACH_DIR = new File(Env.getProperty("ATTACH_DIR")+ATTACH_BASE_DIR);
    public static final File ATTACH_ADMIN_DIR = new File(Env.getProperty("ATTACH_ADMIN_DIR")+ATTACH_BASE_DIR);

    
    
    /** BANNER相关附件目录 */
    public static final File BANNER_CONTENT = new File(ATTACH_DIR, "banner");

    public static final String WELCOMEINFO = "welcomeinfo";
    public static final String INDEXINFO = "indexinfo";
    public static final String PURPINFO = "purpinfo";
    public static final String SHOWINFO = "showinfo";
    public static final String CONTACTINFO = "contactinfo";
    public static final String NEWSINFO = "newsinfo";
    public static final String PRDINFO = "prdinfo";
    public static final String CASEINFO = "caseinfo";
    public static final String LINKINFO = "link";
    public static final String PORTALINFO = "portal";
    public static final String FIRSTPAGEINFO = "firstpageinfo";
    public static final String EMPINFO = "empinfo";
    public static final String ARTICLEINFO = "articleinfo";
    
    static
    {
        ATTACH_DIR.mkdirs();
        ATTACH_ADMIN_DIR.mkdirs();
        BANNER_CONTENT.mkdirs();
    }
}
