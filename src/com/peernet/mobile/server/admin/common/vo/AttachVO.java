package com.peernet.mobile.server.admin.common.vo;

/**
 * 附件VO
 * 
 * @author 宋翔
 * @date 2014-2-15 下午1:24:36
 */
public class AttachVO
{
    private String id;

    private String name;
    
    private String url;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
