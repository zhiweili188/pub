/**
 * Copyright (c) @2015-4-20. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.course.apply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szreach.mediacenter.common.base.AbstractBaseServiceImpl;
import com.szreach.mediacenter.common.base.BaseDao;
import com.szreach.mediacenter.course.apply.bean.UserCourseApply;
import com.szreach.mediacenter.course.apply.dao.UserCourseApplyDao;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-20
 * @Version: 1.0
 */
@Service("userCourseApplyService")
@Scope("prototype")
@Transactional
public class UserCourseApplyServiceImpl extends AbstractBaseServiceImpl<UserCourseApply> implements UserCourseApplyService {

	@Autowired
	private UserCourseApplyDao userCourseApplyDao;

	@Override
	public List<UserCourseApply> getAll(UserCourseApply query) {
		List<UserCourseApply> list = userCourseApplyDao.query(query);
		return list;
	}

	@Override
	public BaseDao<UserCourseApply> getBaseDao() {
		return userCourseApplyDao;
	}
	
}
