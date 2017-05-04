package com.peernet.mobile.server.admin.common.cst;

import com.nationsky.pub.Env;

/**
 * 配置变量
 * 
 * @author 宋翔
 * @date 2013-11-19 下午7:04:53
 */
public class ConfigProperty
{
    public static final int LIST_PAGE_SIZE = Integer.parseInt(Env.getProperty("LIST_PAGE_SIZE"));

    public static final String ADMIN_USERNAME = Env.getProperty("ADMIN_USERNAME");

    public static final String ADMIN_PASSWORD = Env.getProperty("ADMIN_USERNAME");


}
