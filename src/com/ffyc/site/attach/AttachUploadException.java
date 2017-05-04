package com.ffyc.site.attach;

import com.peernet.mobile.server.admin.common.message.exception.CustomException;

/**
 * 附件上传异常
 * 
 * @author 宋翔
 * @date 2014-7-25 下午12:36:43
 */
public class AttachUploadException extends CustomException
{
    private static final long serialVersionUID = 2793424079096882049L;

    public AttachUploadException(String message)
    {
        super(message);
    }
}
