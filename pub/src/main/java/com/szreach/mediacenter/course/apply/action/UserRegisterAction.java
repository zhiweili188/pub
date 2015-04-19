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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.szreach.mediacenter.common.annotation.RepeatSubmitValidate;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.common.base.BaseService;
import com.szreach.mediacenter.course.apply.bean.UserRegister;
import com.szreach.mediacenter.course.apply.service.UserRegisterService;
import com.szreach.mediacenter.st.M;

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
	
	@Override
	protected String getPrefix() {
		return "/course-apply";
	}
	
	@RequestMapping(value="/start-register.do")
	@RepeatSubmitValidate(create=true)
	public ModelAndView  startRegister( Model model) {
		return new ModelAndView(getPrefix()+"/register");     
	} 
	
	@RequestMapping(value="/register.do")
	@RepeatSubmitValidate(destroy=true)
	public ModelAndView  register(UserRegister bean, Model model) {
		if(bean.getId() == null){
			getService().insert(bean);
		} else {
			getService().update(bean);
		}
		model.addAttribute("msg", M.USER_REGISTER_SUCCESS);
		return new ModelAndView("success");     
	} 
}
