/**
 * Copyright (c) @2015-4-24. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.login.service;

import com.szreach.mediacenter.auth.login.bean.UserActivate;
import com.szreach.mediacenter.common.base.BaseService;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-24
 * @Version: 1.0
 */
public interface UserActivateService extends BaseService<UserActivate> {

	public int activateUser(UserActivate param);
	public UserActivate getByID(String userId);
}
