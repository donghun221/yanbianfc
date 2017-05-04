package com.peernet.mobile.server.admin.common.message;

import java.util.Map;

/**
 * 返回给前端的请求结果消息对象
 * 
 * @author 宋翔
 * @date 2014-7-24 下午3:26:42
 */
public class ResponseMessage
{
    /** 请求结果编码 */
    protected String code;

    /** 请求结果描述 */
    protected String message;

    /** 返回的主要数据 */
    protected Object data;

    /** 返回的其他数据 */
    protected Map<String, Object> extraDataMap;

    protected Object extraData;

    public ResponseMessage(String code, String data)
    {
        this.code = code;
        this.data = data;
    }

    public ResponseMessage(String code, String data, String message)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseMessage()
    {
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public Map<String, Object> getExtraDataMap()
    {
        return extraDataMap;
    }

    public void setExtraDataMap(Map<String, Object> extraDataMap)
    {
        this.extraDataMap = extraDataMap;
    }

    public Object getExtraData()
    {
        return extraData;
    }

    public void setExtraData(Object extraData)
    {
        this.extraData = extraData;
    }

}
