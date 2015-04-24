/**
 * Copyright (c) @2015-4-24. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.login.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.szreach.mediacenter.auth.login.bean.UserActivate;
import com.szreach.mediacenter.auth.login.service.UserActivateService;
import com.szreach.mediacenter.common.annotation.SkipLogin;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.st.Key;
import com.szreach.mediacenter.st.Message;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-24
 * @Version: 1.0
 */
@Controller
@RequestMapping("/valid")  
@Scope("prototype")
public class UserActivateAction extends BaseAction<UserActivate> {

	@Autowired
	private UserActivateService userActivateService;
	
	@SkipLogin(value=true)
	@RequestMapping(value="/activate.do")
	public ModelAndView  activateUser(UserActivate param, Model model) {
		int result = userActivateService.activateUser(param);
		model.addAttribute(Key.DISPLAY_MESSAGE, Message.getById(result));
		return new ModelAndView("success");     
	} 
}
