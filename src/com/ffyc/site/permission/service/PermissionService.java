package com.ffyc.site.permission.service;

import java.util.List;

import com.ffyc.site.permission.model.UserMenu;
import com.nationsky.pub.framework.exception.ApplicationException;

/**
 * 权限管理服务
 * 
 * @author 宋翔
 * @date 2014-4-13 下午3:50:34
 */
public interface PermissionService
{

    /**
     * 登录
     * 
     * @param username 用户名
     * @param password 密码
     * @return 登录信息
     * @throws ApplicationException 登录校验失败，抛出该异常
     */
    public List<UserMenu> login(String username, String password) throws ApplicationException;

}
