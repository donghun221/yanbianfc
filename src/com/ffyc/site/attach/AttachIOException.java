package com.ffyc.site.attach;

import com.peernet.mobile.server.admin.common.message.exception.CustomException;

/**
 * 附件保存错误失败
 * 
 * @author 宋翔
 * @date 2014-7-24 下午5:19:57
 */
public class AttachIOException extends CustomException
{
    private static final long serialVersionUID = -1444621781122128102L;

    public AttachIOException(String message)
    {
        super(message);
    }
}
