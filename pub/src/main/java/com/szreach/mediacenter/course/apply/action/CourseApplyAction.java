/**
 * Copyright (c) @2015-4-15. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.course.apply.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.common.base.BaseService;
import com.szreach.mediacenter.course.apply.bean.Course;
import com.szreach.mediacenter.course.apply.service.CourseApplyService;

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
	@Override
	protected String getPrefix() {
		return "/course-apply";
	}
	@Override
	protected BaseService<Course> getService() {
		return courseApplyService;
	}
	
	
	@RequestMapping(value="/courselist.do")
	public ModelAndView  list(Model model) {
		List<Course> list = courseApplyService.getAll(null);
		model.addAttribute("courseList", list);
		return new ModelAndView(getPrefix()+"/course-list");     
	} 
	
	@RequestMapping(value="/detail/{refid}.do")
	public ModelAndView  detail(@PathVariable("refid") String id, Model model) {
		Course course = courseApplyService.getByID(id);
		model.addAttribute("course", course);
		return new ModelAndView(getPrefix()+"/course-detail");     
	} 
	
	@RequestMapping(value="/start-register/{refid}.do")
	public ModelAndView  startRegister(@PathVariable("refid") String id, Model model) {
		Course course = courseApplyService.getByID(id);
		model.addAttribute("course", course);
		return new ModelAndView(getPrefix()+"/register");     
	} 
}
