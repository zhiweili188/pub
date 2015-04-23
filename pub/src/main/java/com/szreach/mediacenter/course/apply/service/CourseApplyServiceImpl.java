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
import com.szreach.mediacenter.common.util.DateUtil;
import com.szreach.mediacenter.course.apply.bean.Course;
import com.szreach.mediacenter.course.apply.bean.UserCourseApply;
import com.szreach.mediacenter.course.apply.dao.CourseApplyDao;
import com.szreach.mediacenter.course.apply.dao.UserCourseApplyDao;
import com.szreach.mediacenter.st.ReturnCode;

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
	@Autowired UserCourseApplyDao userCourseApplyDao;

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
	
	public int apply(String courseId, String userId) {
		int returnCode = ReturnCode.SUCCESS;
		
		Course course = getByID(courseId);
		if(course.getRemainApplyQuota() > 0) {
			//还有剩余名额,剩余名额减1
			courseApplyDao.decRemainQuota(courseId);
			
			//保存申请记录
			UserCourseApply ucApply = new UserCourseApply();
			ucApply.setStatus(0);
			ucApply.setApplyTime(DateUtil.getCurrentDateTimeStr());
			ucApply.setCourseId(courseId);
			ucApply.setUserId(userId);
			userCourseApplyDao.insert(ucApply);
			
		} else {
			//没有名额了
			returnCode = ReturnCode.ERR_COURSE_QUOTO_FULL;
		}
		return returnCode;
	}
	
	@Override
	public List<Course> queryUserApplyCourse(UserCourseApply query) {
		List<Course> list = courseApplyDao.queryUserApplyCourse(query);
		return list;
	}
}
