package com.ffyc.site.common.page;

import com.peernet.mobile.server.admin.common.cst.ConfigProperty;

/**
 * 分页计算结果
 * 
 * @author SongXiang
 * @date 2013-3-20 上午10:06:59
 */
public class PageCalc
{
    /** 记录总数 */
    private int totalCount;

    /** 每页数量 */
    private int pageSize;

    /** 页码总数 */
    private int pageCount;

    public PageCalc(int totalCount, int pageSize)
    {
        this.totalCount = totalCount;
        this.pageSize = pageSize;

        // 用出发计算出页码数量，如果有零头，则加一页，至少有1页
        this.pageCount = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
        this.pageCount = this.pageCount == 0 ? 1 : this.pageCount;
    }

    public PageCalc(int totalCount)
    {
        this.totalCount = totalCount;
        this.pageSize = ConfigProperty.LIST_PAGE_SIZE;

        // 用出发计算出页码数量，如果有零头，则加一页，至少有1页
        this.pageCount = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
    }

    /**
     * 根据页码计算记录起始位置
     * 
     * @param page 页码
     * @return 记录起始位置
     */
    public int getStart(int page)
    {
        return (page * this.getPageSize()) - this.getPageSize();
    }

    /**
     * 根据页码计算记录结束位置
     * 
     * @param page 页码
     * @return 记录结束位置
     */
    public int getEnd(int page)
    {
        return page * this.getPageSize();
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public int getPageSize()
    {
        return pageSize;
    }
}
