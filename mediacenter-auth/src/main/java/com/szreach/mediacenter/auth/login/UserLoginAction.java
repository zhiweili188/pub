package com.szreach.mediacenter.auth.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("prototype")
public class UserLoginAction {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/login.do")
	public ModelAndView  index(Model model) {
		return new ModelAndView("/index");     
	}
}
