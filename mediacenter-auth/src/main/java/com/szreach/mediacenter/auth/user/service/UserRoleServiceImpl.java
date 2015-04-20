/**
 * Copyright (c) @2015-3-30. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szreach.mediacenter.auth.user.bean.UserRole;
import com.szreach.mediacenter.auth.user.dao.UserRoleDao;
import com.szreach.mediacenter.common.base.AbstractBaseServiceImpl;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-30
 * @Version: 1.0
 */
@Service("userRoleService")
@Scope("prototype")
@Transactional
public class UserRoleServiceImpl extends AbstractBaseServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	@Override
	public List<UserRole> getUserRole(int userId) {
		return userRoleDao.getUserRole(userId);
	}

}
