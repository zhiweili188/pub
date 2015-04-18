/**
 * Copyright (c) @2015-4-16. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.kv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szreach.mediacenter.common.base.AbstractBaseServiceImpl;
import com.szreach.mediacenter.common.base.BaseDao;
import com.szreach.mediacenter.kv.bean.Education;
import com.szreach.mediacenter.kv.dao.EducationDao;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-16
 * @Version: 1.0
 */
@Service("educationService")
@Scope("prototype")
@Transactional
public class EducationServiceImpl extends AbstractBaseServiceImpl<Education> implements EducationService {
	@Autowired
	private EducationDao educationDao;
	@Override
	public BaseDao<Education> getBaseDao() {
		return educationDao;
	}
}
