/**
 * Copyright (c) @2015-3-11. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.login.dao;

import java.util.List;

import com.szreach.mediacenter.auth.login.bean.LoginUser;
import com.szreach.mediacenter.common.base.BaseDao;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-11
 * @Version: 1.0
 */
public interface LoginUserDao extends BaseDao<LoginUser> {

	//public int countAll(@Param("query") LoginUser query);
	//public List<LoginUser> query(@Param("query") LoginUser query, @Param("page")  PageBean page);
	public void updatePwd(LoginUser user);
	public void batchDel(List<Integer> idList);
	public LoginUser getByUserName(String userName);
	public void updateActivateStatus(LoginUser user);
}
