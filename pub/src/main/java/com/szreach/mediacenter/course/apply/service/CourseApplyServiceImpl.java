/**
 * Copyright (c) @2015-4-15. All Rights Reserved.
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
import com.szreach.mediacenter.course.apply.bean.Course;
import com.szreach.mediacenter.course.apply.dao.CourseApplyDao;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-15
 * @Version: 1.0
 */
@Service("courseApplyService")
@Scope("prototype")
@Transactional
public class CourseApplyServiceImpl extends AbstractBaseServiceImpl<Course> implements CourseApplyService {

	@Autowired
	private CourseApplyDao courseApplyDao;

	@Override
	public BaseDao<Course> getBaseDao() {
		return courseApplyDao;
	}
	
	@Override
	public List<Course> getAll(Course query) {
		List<Course> list = courseApplyDao.query(query);
		return list;
	}

	@Override
	public Course getByID(String id) {
		Course course = courseApplyDao.get(id);
		return course;
	}
	
}
