package com.peernet.mobile.server.admin.common.utils;

import java.util.Arrays;
import java.util.List;

import com.nationsky.pub.framework.exception.GenericException;
import com.nationsky.pub.utils.StringUtils;

/**
 * 字符串相关工具
 * 
 * @author 宋翔
 * @date 2013-12-17 下午1:50:08
 */
public class StringUtil
{
    /**
     * 将逗号分隔的字符串转换成String列表
     * 
     * @param stringSet 逗号分隔的字符串
     * @return String列表
     */
    public static final List<String> splitToList(String stringSet)
    {
        try
        {
            return Arrays.asList(StringUtils.split(stringSet, ","));
        }
        catch(GenericException e)
        {
            return null;
        }
    }

    /**
     * 判断两个字符串是否相同
     * 
     * @param str1
     * @param str2
     * @return true，如果相同或两个都为null
     */
    public static boolean isEqual(String str1, String str2)
    {
        if(str1 != null)
        {
            return str1.equals(str2);
        }

        return str1 == str2;
    }
    
    public static boolean isEmpty(String str){
    	if(str==null)
    		return true;
    	return str.trim().length()==0;
    }
}
