package com.peernet.mobile.server.admin.common.message.cst;

/**
 * 返回消息的编码
 * 
 * @author 宋翔
 * @date 2014-7-24 下午3:31:29
 */
public class ResponseCode
{
    /** 成功 */
    public static final String SUCCESS = "0x00";

    /** 失败 */
    public static final String FAILURE = "0x99";

    /** App版本有重复 */
    public static final String DUPLICATE_APPVERSION = "0x01";

    /** 用户名有重复 */
    public static final String DUPLICATE_USERNAME = "0x02";
}
