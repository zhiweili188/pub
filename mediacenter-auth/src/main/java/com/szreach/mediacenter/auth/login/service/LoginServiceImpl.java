/**
 * Copyright (c) @2015-3-25. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.szreach.mediacenter.auth.login.dao.LoginDao;
import com.szreach.mediacenter.auth.menu.bean.MenuBean;
import com.szreach.mediacenter.auth.st.ReturnCode;
import com.szreach.mediacenter.auth.user.bean.LoginUser;
import com.szreach.mediacenter.auth.user.dao.LoginUserDao;
import com.szreach.mediacenter.common.base.AbstractBaseServiceImpl;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-25
 * @Version: 1.0
 */
@Service("loginService")
@Scope("prototype")
@Transactional
public class LoginServiceImpl extends AbstractBaseServiceImpl implements LoginService {
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private LoginUserDao loginUserDao;
	
	@Override
	public List<MenuBean> queryMenuTree(int userId) {
		List<MenuBean> list = loginDao.queryMenuTree(userId);
		if(list == null || list.size()==0) return null;
		List<MenuBean> treeList = new ArrayList<MenuBean>();
		
		MenuBean parentMenu = null;
		MenuBean childMenu = null;
		Integer id = -1;
		for(MenuBean menu: list ) {
			//父id相同，取子菜单的数据
			if(id.equals(menu.getId())) {
					
					childMenu = new MenuBean();
					childMenu.setId(menu.getChildId());
					childMenu.setMenuName(menu.getChildMenuName());
					childMenu.setMenuAction(menu.getChildMenuAction());
					childMenu.setMenuIcon(menu.getChildMenuIcon());
					parentMenu.getChildren().add(childMenu);
			} else {
					
					parentMenu = new MenuBean();
					parentMenu.setId(menu.getId());
					parentMenu.setMenuName(menu.getMenuName());
					parentMenu.setMenuIcon(menu.getMenuIcon());
					
					parentMenu.setChildren( new ArrayList<MenuBean>());
					treeList.add(parentMenu);
					
					childMenu = new MenuBean();
					childMenu.setId(menu.getChildId());
					childMenu.setMenuName(menu.getChildMenuName());
					childMenu.setMenuAction(menu.getChildMenuAction());
					childMenu.setMenuIcon(menu.getChildMenuIcon());
					parentMenu.getChildren().add(childMenu);
				
					id=menu.getId();
			}
		}
		list = null;
		return treeList;
	}

	public int checkLogin(LoginUser user, LoginUser loginUser) {
		int result = ReturnCode.SUCCESS;
		//1.检查用户名是否存在
		if(loginUser == null) {
			result = ReturnCode.USERNAME_PASSW_ERROR;
		} else {
			//2.检查密码是否正确
			if( ! loginUser.getPassword().equals(user.getPassword())) {
				result = ReturnCode.USERNAME_PASSW_ERROR;
			} else {
				
			}
		}
		
		return result;
	}
}
