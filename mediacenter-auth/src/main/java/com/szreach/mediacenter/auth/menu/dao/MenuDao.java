/**
 * Copyright (c) @2014-3-7. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.menu.dao;

import java.util.List;

import com.szreach.mediacenter.auth.menu.bean.MenuBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-3-7
 * @Version: 1.0
 */
public interface MenuDao {

	public void insertMenu(MenuBean menu);
	public MenuBean getMenuByID(String menuId);
	public void updateMenu(MenuBean menu);
	
	public int count();
	
	public List<MenuBean> queryMenu(MenuBean query);
}
