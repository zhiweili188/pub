/**
 * Copyright (c) @2015-3-11. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-11
 * @Version: 1.0
 */
@Controller
@RequestMapping("/login")  
@Scope("prototype")
public class LoginAction {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/index.do")
	public ModelAndView doLogin(Model model) {
		logger.debug("----doLogin--------");
		return new ModelAndView("/index");     
	}
}
