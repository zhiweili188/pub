/**
 * Copyright (c) @2015-4-24. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.login.dao;

import com.szreach.mediacenter.auth.login.bean.UserActivate;
import com.szreach.mediacenter.common.base.BaseDao;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-24
 * @Version: 1.0
 */
public interface UserActivateDao extends BaseDao<UserActivate> {
	public UserActivate getByUserId(String id);
}
