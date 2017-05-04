package com.peernet.mobile.server.admin.common.message.exception;

import com.peernet.mobile.server.admin.common.message.cst.ResponseCode;

/**
 * 自定义异常
 * 
 * @author 宋翔
 * @date 2014-7-24 下午4:00:08
 */
public abstract class CustomException extends Exception
{
    private static final long serialVersionUID = -8486072460354624317L;

    /** 消息中带的返回编码 */
    protected String code = ResponseCode.FAILURE;

    protected CustomException(String message)
    {
        super(message);
    }

    public String getCode()
    {
        return code;
    }
}