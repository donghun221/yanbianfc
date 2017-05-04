package com.ffyc.site.common.page;

import java.util.List;

/**
 * 分页数据
 * 
 * @param <T> 分页数据的数据泛型
 * @author 宋翔
 * @date 2013-11-16 下午7:33:54
 */
public class PaginationData<T>
{
    /** 分页数据 */
    private PageCalc pageData;

    /** 业务数据 */
    private List<T> data;

    public PaginationData(List<T> data, PageCalc pageData)
    {
        this.pageData = pageData;
        this.data = data;
    }

    public PageCalc getPageData()
    {
        return pageData;
    }

    public void setPageData(PageCalc pageData)
    {
        this.pageData = pageData;
    }

    public List<T> getData()
    {
        return data;
    }

    public void setData(List<T> data)
    {
        this.data = data;
    }

}
