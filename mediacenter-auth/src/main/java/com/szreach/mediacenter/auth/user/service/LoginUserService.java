/**
 * Copyright (c) @2015-3-11. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.user.service;

import java.util.List;

import com.szreach.mediacenter.auth.user.bean.LoginUser;
import com.szreach.mediacenter.common.base.BaseService;
import com.szreach.mediacenter.common.base.PageBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-11
 * @Version: 1.0
 */
public interface LoginUserService extends BaseService {
	public void insertLoginUser(LoginUser user);
	public LoginUser getLoginUserByID(Integer loginUserId);
	public void updateLoginUser(LoginUser user);
	
	public int count();
	
	public List<LoginUser> query(LoginUser query);
	public List<LoginUser> query(LoginUser query, PageBean page);
	
	public void delete(Integer loginUserId);
}
