/**
 * Copyright (c) @2014-3-7. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.menu.dao;

import java.util.List;

import com.szreach.mediacenter.auth.menu.bean.MenuBean;
import com.szreach.mediacenter.common.base.BaseDao;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-3-7
 * @Version: 1.0
 */
public interface MenuDao  extends BaseDao<MenuBean>{
	//public int countAll(@Param("query") MenuBean query);
	//public List<MenuBean> query(@Param("query") MenuBean query, @Param("page")  PageBean page);
	public List<MenuBean> queryTree();
	
}
