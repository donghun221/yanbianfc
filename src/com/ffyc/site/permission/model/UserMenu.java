package com.ffyc.site.permission.model;

/**
 * 用户菜单
 * 
 * @author 宋翔
 * @date 2014-4-13 下午4:23:13
 */
public class UserMenu
{

    private String name;

    private String uri;

    public UserMenu(String name, String uri)
    {
        this.name = name;
        this.uri = uri;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

}
