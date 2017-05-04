package com.peernet.mobile.server.admin.common.message;

import com.ffyc.site.common.page.PaginationData;
import com.peernet.mobile.server.admin.common.message.cst.ResponseCode;

/**
 * 返回给前端的请求结果消息对象
 * 
 * @author 宋翔
 * @date 2014-7-24 下午3:26:42
 */
public class SuccessResponseMessage extends ResponseMessage
{
    public SuccessResponseMessage(Object data)
    {
        if(data instanceof PaginationData<?>)
        {
            this.data = ((PaginationData<?>)data).getData();
            this.extraData = ((PaginationData<?>)data).getPageData();
        }
        else
        {
            this.data = data;
        }

        this.code = ResponseCode.SUCCESS;
        this.message = "Process success";
    }

    public SuccessResponseMessage(String message, Object data)
    {
        this.code = ResponseCode.SUCCESS;
        this.message = message;
        if(data instanceof PaginationData<?>)
        {
            this.data = ((PaginationData<?>)data).getData();
            this.extraData = ((PaginationData<?>)data).getPageData();
        }
        else
        {
            this.data = data;
        }
    }

    public SuccessResponseMessage()
    {
        this.code = ResponseCode.SUCCESS;
        this.message = "Process success";
    }
}
