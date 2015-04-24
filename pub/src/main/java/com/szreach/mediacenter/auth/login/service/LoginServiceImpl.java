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
import com.szreach.mediacenter.common.util.CommonTools;
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
		//检查用户名是否存在
		if(loginUser == null) {
			result = ReturnCode.ERR_USERNAME_PASSW;
		} else {
			
			if(  loginUser.getActivateStatus()!= null && loginUser.getActivateStatus() != 1) {
				//用户未激活
				result = ReturnCode.ERR_USER_NOT_ACTIVATE;
			} else if( ! loginUser.getPasswd().equals(CommonTools.getMD5(user.getPasswd()))) {
				//检查密码是否正确
				result = ReturnCode.ERR_USERNAME_PASSW;
			} else {
				
			}
		}
		
		return result;
	}
}
