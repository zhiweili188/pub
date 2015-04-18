/**
 * Copyright (c) @2015-3-11. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.login.service;

import java.util.List;

import com.szreach.mediacenter.auth.login.bean.LoginUser;
import com.szreach.mediacenter.common.base.BaseService;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-11
 * @Version: 1.0
 */
public interface LoginUserService extends BaseService<LoginUser> {
	//public void insertLoginUser(LoginUser user);
	//public LoginUser getLoginUserByID(Integer loginUserId);
	public LoginUser getByUserName(String userName);
	
	//public void updateLoginUser(LoginUser user);
	public void updatePwd(LoginUser user);

}
