/**
 * Copyright (c) @2015-3-25. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.login.dao;

import java.util.List;

import com.szreach.mediacenter.auth.menu.bean.MenuBean;
import com.szreach.mediacenter.common.base.BaseDao;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-25
 * @Version: 1.0
 */
public interface LoginDao   extends BaseDao{
	public List<MenuBean> queryMenuTree(int userId);
}
