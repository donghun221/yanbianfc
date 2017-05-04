package com.ffyc.site.permission.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.site.mapper.SiteMapper;
import com.ffyc.site.model.AdminInfo;
import com.ffyc.site.permission.model.UserMenu;
import com.nationsky.pub.framework.exception.ApplicationException;
import com.peernet.mobile.server.admin.common.cst.ConfigProperty;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService
{

	@Autowired
	private SiteMapper siteMapper;
	
    @Override
    public List<UserMenu> login(String username, String password) throws ApplicationException
    {
    	List<UserMenu> userMenu = new ArrayList<UserMenu>();
    	//if(username.equals(ConfigProperty.ADMIN_USERNAME)&&password.equals(ConfigProperty.ADMIN_USERNAME))
        if(!username.equals(ConfigProperty.ADMIN_USERNAME)){
        	throw new ApplicationException("用户名错误");
        }
        AdminInfo info = siteMapper.getAdminInfo(username);
        if(info==null){
        	throw new ApplicationException("用户名错误");
        }
        if(!info.getAdminPassword().equals(password)){
        	throw new ApplicationException("密码错误");
        }
        
        userMenu.add(new UserMenu("图文文章管理", "richArticleInfoManage"));
        userMenu.add(new UserMenu("球队积分维护", "teamScoreManage"));
        userMenu.add(new UserMenu("赛程维护", "matchScheduleManage"));
        userMenu.add(new UserMenu("友情链接", "linkInfoManage"));
        //userMenu.add(new UserMenu("留言管理", "feedBackInfoManage"));
        //userMenu.add(new UserMenu("Banner管理", BannerRouters.ROOT + BannerRouters.BANNER_CONTENT_VIEW));
        //userMenu.add(new UserMenu("欢迎页管理", "welcomeInfoManage"));
//        userMenu.add(new UserMenu("公司简介管理", "introManage"));
//        userMenu.add(new UserMenu("公司愿景管理", "purpInfoManage"));
//        userMenu.add(new UserMenu("公司风采管理", "showInfoManage"));
//        userMenu.add(new UserMenu("公司新闻咨询管理", "newsInfoManage?type=0"));
        //userMenu.add(new UserMenu("公司新闻列表管理", "newsInfoManage?type=1"));
//        userMenu.add(new UserMenu("产品管理", "prdInfoManage"));
//        userMenu.add(new UserMenu("案例管理", "caseInfoManage"));
//        userMenu.add(new UserMenu("联系信息管理", "contactInfoManage"));
        userMenu.add(new UserMenu("修改密码", "adminInfoManage"));

        return userMenu;
    }
}
