/**
 * Copyright (c) @2015-4-15. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.course.apply.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.szreach.mediacenter.auth.login.bean.LoginUser;
import com.szreach.mediacenter.common.annotation.SkipLogin;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.common.base.BaseService;
import com.szreach.mediacenter.common.util.DateUtil;
import com.szreach.mediacenter.course.apply.bean.Course;
import com.szreach.mediacenter.course.apply.bean.UserCourseApply;
import com.szreach.mediacenter.course.apply.bean.UserRegister;
import com.szreach.mediacenter.course.apply.service.CourseApplyService;
import com.szreach.mediacenter.course.apply.service.UserCourseApplyService;
import com.szreach.mediacenter.course.apply.service.UserRegisterService;
import com.szreach.mediacenter.st.Key;
import com.szreach.mediacenter.st.Message;
import com.szreach.mediacenter.st.ReturnCode;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-15
 * @Version: 1.0
 */
@Controller
@RequestMapping("/crs")
@Scope("prototype")
public class CourseApplyAction extends BaseAction<Course> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CourseApplyService courseApplyService;
	
	@Autowired
	private UserCourseApplyService userCourseApplyService;
	@Autowired
	private UserRegisterService userRegisterService;
	
	@Override
	protected String getPrefix() {
		return "/course-apply";
	}
	@Override
	protected BaseService<Course> getService() {
		return courseApplyService;
	}
	
	@SkipLogin(value=true)
	@RequestMapping(value="/courselist.do")
	public ModelAndView  list(Model model, HttpSession session) {
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
		
		return new ModelAndView(getPrefix()+"/course-list");     
	} 
	
	@RequestMapping(value="/detail/{refid}.do")
	public ModelAndView  detail(@PathVariable("refid") String id, Model model, HttpSession session) {
		LoginUser loginUser = (LoginUser)session.getAttribute(Key.SESSION_LOGIN_USER);
		UserRegister userReg = userRegisterService.getByUserId(loginUser.getUserId());
		if(userReg == null) {
			model.addAttribute(Key.DISPLAY_MESSAGE, Message.ERR_USER_REGISTER_INFO_UNCOMPLETE.getMsgKey());
			return new ModelAndView("success");     
		} else {
			
		}
		Course course = courseApplyService.getByID(id);
		model.addAttribute("course", course);
		return new ModelAndView(getPrefix()+"/course-detail");     
	} 
	
	@RequestMapping(value="/apply/{refid}.do")
	public ModelAndView  apply(@PathVariable("refid") String courseId, Model model, HttpSession session) {
		LoginUser loginUser = (LoginUser)session.getAttribute(Key.SESSION_LOGIN_USER);
		if(loginUser != null) {
			int returnCode = courseApplyService.apply(courseId, loginUser.getUserId());
			if(returnCode == ReturnCode.SUCCESS) {
				model.addAttribute(Key.DISPLAY_MESSAGE, Message.COURSE_APPLY_SUCCESS.getMsgKey());
			} else {
				model.addAttribute(Key.DISPLAY_MESSAGE, Message.ERR_COURSE_QUOTO_FULL.getMsgKey());
			}
		}
		
		return new ModelAndView("success");     
	} 
	
	@RequestMapping(value="/myCourselist.do")
	public ModelAndView  myCourselist(Model model, HttpSession session) {
		LoginUser loginUser = (LoginUser)session.getAttribute(Key.SESSION_LOGIN_USER);
		UserCourseApply query = new UserCourseApply();
		query.setUserId(loginUser.getUserId());
		List<Course> list = courseApplyService.queryUserApplyCourse(query);
		model.addAttribute("courseList", list);
		
		return new ModelAndView(getPrefix()+"/mycourselist");     
	} 
}
