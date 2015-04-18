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
import com.szreach.mediacenter.kv.bean.UserTitle;
import com.szreach.mediacenter.kv.dao.UserTitleDao;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-15
 * @Version: 1.0
 */
@Service("userTitleService")
@Scope("prototype")
@Transactional
public class UserTitleServiceImpl extends AbstractBaseServiceImpl<UserTitle> implements UserTitleService {
	@Autowired
	private UserTitleDao userTitleDao;
	@Override
	public BaseDao<UserTitle> getBaseDao() {
		return userTitleDao;
	}
}
