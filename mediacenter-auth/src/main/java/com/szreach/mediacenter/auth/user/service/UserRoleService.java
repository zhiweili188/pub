/**
 * Copyright (c) @2015-3-30. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.user.service;

import java.util.List;

import com.szreach.mediacenter.auth.user.bean.UserRole;
import com.szreach.mediacenter.common.base.BaseService;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-30
 * @Version: 1.0
 */
public interface UserRoleService extends BaseService {
	public List<UserRole> getUserRole(int userId);
}
