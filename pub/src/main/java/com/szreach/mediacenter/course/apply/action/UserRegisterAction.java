/**
 * Copyright (c) @2015-4-15. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.course.apply.action;

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
import com.szreach.mediacenter.course.apply.bean.UserRegister;
import com.szreach.mediacenter.course.apply.service.UserRegisterService;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-15
 * @Version: 1.0
 */
@Controller
@RequestMapping("/usrreg")
@Scope("prototype")
public class UserRegisterAction extends BaseAction<UserRegister> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserRegisterService userRegisterService;
	@Override
	protected BaseService<UserRegister> getService() {
		return userRegisterService;
	}
	
	@RequestMapping(value="/register.do")
	public ModelAndView  startRegister(UserRegister bean, Model model) {
		if(bean.getId() == null){
			getService().insert(bean);
		} else {
			getService().update(bean);
		}
		model.addAttribute("redirectUrl", "/crs/courselist.do");
		return new ModelAndView("success");     
	} 
}
