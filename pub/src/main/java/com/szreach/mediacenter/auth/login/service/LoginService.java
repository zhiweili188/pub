/**
 * Copyright (c) @2015-3-25. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.login.service;

import com.szreach.mediacenter.auth.login.bean.LoginUser;
import com.szreach.mediacenter.common.base.BaseService;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-25
 * @Version: 1.0
 */
public interface LoginService extends BaseService {
	public int checkLogin(LoginUser user, LoginUser loginUser);
}
