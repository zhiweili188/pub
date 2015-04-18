/**
 * Copyright (c) @2015-4-15. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.kv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szreach.mediacenter.common.base.AbstractBaseServiceImpl;
import com.szreach.mediacenter.common.base.BaseDao;
import com.szreach.mediacenter.kv.bean.UserTitleType;
import com.szreach.mediacenter.kv.dao.UserTitleTypeDao;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-15
 * @Version: 1.0
 */
@Service("userTitleTypeService")
@Scope("prototype")
@Transactional
public class UserTitleTypeServiceImpl extends AbstractBaseServiceImpl<UserTitleType> implements UserTitleTypeService {

	@Autowired
	private UserTitleTypeDao userTitleTypeDao;
	@Override
	public BaseDao<UserTitleType> getBaseDao() {
		return userTitleTypeDao;
	}
}
