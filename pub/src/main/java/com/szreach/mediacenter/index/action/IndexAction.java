/**
 * Copyright (c) @2015-4-21. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.index.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.szreach.mediacenter.auth.login.bean.LoginUser;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.common.util.DateUtil;
import com.szreach.mediacenter.course.apply.bean.Course;
import com.szreach.mediacenter.course.apply.bean.UserCourseApply;
import com.szreach.mediacenter.course.apply.service.CourseApplyService;
import com.szreach.mediacenter.course.apply.service.UserCourseApplyService;
import com.szreach.mediacenter.st.Key;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-21
 * @Version: 1.0
 */
@Controller
@Scope("prototype")
public class IndexAction extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CourseApplyService courseApplyService;
	
	@Autowired
	private UserCourseApplyService userCourseApplyService;
	
	@RequestMapping(value="/toIndex.do")
	public ModelAndView toIndex(Model model, HttpSession session) {
		Course query = new Course();
		query.setApplyStartTime(DateUtil.formatDateTimeMinute(DateUtil.getCurrentDateTime()));
		List<Course> list = courseApplyService.getAll(query);
		model.addAttribute("courseList", list);
		
		LoginUser loginUser = (LoginUser)session.getAttribute(Key.SESSION_LOGIN_USER);
		if(loginUser != null) {
			//查询当前用户申请的课程
			UserCourseApply applyQuery = new UserCourseApply();
			applyQuery.setUserId(loginUser.getUserId());
			List<UserCourseApply> applyList = userCourseApplyService.getAll(applyQuery);
			model.addAttribute("courseApplyList", applyList);
			for(Course c : list) {
				for(UserCourseApply a : applyList) {
					if(c.getTid().equals(a.getCourseId())) {
						c.setApplied(true);
						break;
					}
				}
			}
		}
		//根据设置的报名时间，设置报名标志
		Date now = DateUtil.getCurrentDateTime();
		for(Course c : list) {
			Date start = DateUtil.toDateTime(c.getApplyStartTime()+":00");
			Date end = DateUtil.toDateTime(c.getApplyEndTime()+":00");
			if(now.after(start) && now.before(end)) {
				c.setOpen(true);
			}
		}
		
		return new ModelAndView("/index");     
	}
}
