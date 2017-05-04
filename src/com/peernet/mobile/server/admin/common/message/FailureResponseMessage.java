package com.peernet.mobile.server.admin.common.message;

import com.peernet.mobile.server.admin.common.message.cst.ResponseCode;
import com.peernet.mobile.server.admin.common.message.exception.CustomException;

/**
 * 返回给前端的请求结果消息对象
 * 
 * @author 宋翔
 * @date 2014-7-24 下午3:26:42
 */
public class FailureResponseMessage extends ResponseMessage
{
    private StackTraceElement[] stackTrace;

    public FailureResponseMessage(Object data)
    {
        this.code = ResponseCode.FAILURE;
        this.data = data;
        this.message = "Process success";
    }

    public FailureResponseMessage(String message, Object data)
    {
        this.code = ResponseCode.FAILURE;
        this.message = message;
        this.data = data;
    }

    public FailureResponseMessage(Exception e)
    {
        this.code = ResponseCode.FAILURE;
        this.message = e.getMessage();

        // 在这个构造其中，只有当异常不是自定义异常时，将异常堆栈抛到前端，如果调用时愿意使用Setter、Getter也可以避开此处
        if(!(e instanceof CustomException))
        {
            this.stackTrace = e.getStackTrace();
        }
        else
        {
            this.code = ((CustomException)e).getCode();
        }
    }

    public StackTraceElement[] getStackTrace()
    {
        return stackTrace;
    }

    public void setStackTrace(StackTraceElement[] stackTrace)
    {
        this.stackTrace = stackTrace;
    }

}
