/**
 * Copyright (c) @2014-3-7. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.menu.service;

import java.util.List;

import com.szreach.mediacenter.auth.menu.bean.MenuBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-3-7
 * @Version: 1.0
 */
public interface IMenuService {

	public int count();
	public void insertMenu(MenuBean menu);
	public MenuBean getMenuByID(String menuId);
	
	public List<MenuBean> queryMenu(MenuBean query);
}
