/**
 * Copyright (c) @2015-3-11. All Rights Reserved.
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

import com.szreach.mediacenter.auth.login.bean.LoginUser;
import com.szreach.mediacenter.auth.login.dao.LoginUserDao;
import com.szreach.mediacenter.common.base.AbstractBaseServiceImpl;
import com.szreach.mediacenter.common.base.PageBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-11
 * @Version: 1.0
 */
@Service("loginUserService")
@Scope("prototype")
@Transactional
public class LoginUserServiceImpl extends AbstractBaseServiceImpl<LoginUser>  implements LoginUserService {

	@Autowired
	private LoginUserDao loginUserDao;

	@Override
	public LoginUser getByID(Integer loginUserId) {
		return loginUserDao.get(loginUserId);
	}
	
	public LoginUser getByUserName(String userName) {
		return loginUserDao.getByUserName(userName);
	}

	
	public void updatePwd(LoginUser user) {
		loginUserDao.updatePwd(user);
	}



}
