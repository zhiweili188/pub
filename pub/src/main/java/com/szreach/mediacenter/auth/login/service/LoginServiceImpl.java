/**
 * Copyright (c) @2015-3-25. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szreach.mediacenter.auth.login.bean.LoginUser;
import com.szreach.mediacenter.auth.login.dao.LoginDao;
import com.szreach.mediacenter.auth.login.dao.LoginUserDao;
import com.szreach.mediacenter.common.base.AbstractBaseServiceImpl;
import com.szreach.mediacenter.st.ReturnCode;

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
