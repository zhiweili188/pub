/**
 * Copyright (c) @2014-3-7. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.menu.service;

import java.util.List;

import com.szreach.mediacenter.auth.menu.bean.MenuBean;
import com.szreach.mediacenter.common.base.BaseService;
import com.szreach.mediacenter.common.base.PageBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-3-7
 * @Version: 1.0
 */
public interface MenuService  extends BaseService<MenuBean> {

	//public int count(MenuBean query);
	//public void insertMenu(MenuBean menu);
	//public void updateMenu(MenuBean menu);
	//public void delete(Integer menuId);
	//public MenuBean getMenuByID(Integer menuId);
	
	//public List<MenuBean> queryMenu(MenuBean query,  PageBean page);
	
	public List<MenuBean> queryTree();
}
