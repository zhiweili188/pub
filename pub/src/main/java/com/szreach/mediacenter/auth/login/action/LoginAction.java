/**
 * Copyright (c) @2015-3-11. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.login.action;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.szreach.mediacenter.auth.login.bean.LoginUser;
import com.szreach.mediacenter.auth.login.service.LoginService;
import com.szreach.mediacenter.auth.login.service.LoginUserService;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.st.Key;
import com.szreach.mediacenter.st.ReturnCode;
import com.szreach.mediacenter.st.ReturnObject;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-11
 * @Version: 1.0
 */
@Controller
@RequestMapping("/login")  
@Scope("prototype")
public class LoginAction extends BaseAction {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private LoginUserService loginUserService;
	
	@RequestMapping(value="/tologin.do")
	public ModelAndView tologin() {
		return new ModelAndView("/login");     
	}
	
	@RequestMapping(value="/ajaxLogin.do")
	@ResponseBody
	public ResponseEntity<ReturnObject>  ajaxLogin(LoginUser user, Model model, HttpSession session) {
		logger.debug("----ajaxLogin--------");
		ReturnObject returnObject = null;
		LoginUser loginUser = loginUserService.getByUserName(user.getUserName());
		
		int result = loginService.checkLogin(user, loginUser);
		if(result != ReturnCode.SUCCESS) {
			returnObject =  new ReturnObject(result);     
		} else {
			session.setAttribute(Key.SESSION_LOGIN_USER, loginUser);
			returnObject = new ReturnObject(ReturnCode.SUCCESS);     
			returnObject.setReturnToUrl("/crs/courselist.do");
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);

		return new ResponseEntity<ReturnObject>(returnObject, headers, HttpStatus.OK);
	}

	@RequestMapping(value="/doLogin.do")
	public ModelAndView doLogin(LoginUser user, Model model) {
		logger.debug("----doLogin--------");
		
		LoginUser loginUser = loginUserService.getByUserName(user.getUserName());
		
		int result = loginService.checkLogin(user, loginUser);
		if(result != ReturnCode.SUCCESS) {
			model.addAttribute("msgCode", result);
			return new ModelAndView("/error");     
		} else {
			
		}
		
		return new ModelAndView("redirect:/crs/courselist.do");     
	}
	
	@RequestMapping(value="/ajaxLogout.do")
	@ResponseBody
	public ResponseEntity<ReturnObject>  ajaxLogout(LoginUser user, Model model, HttpSession session) {
		logger.debug("----ajaxLogout--------");
		ReturnObject returnObject = null;
		LoginUser loginUser = (LoginUser)session.getAttribute(Key.SESSION_LOGIN_USER);
		if(loginUser != null) {
			session.removeAttribute(Key.SESSION_LOGIN_USER);
		}
		
		returnObject = new ReturnObject(ReturnCode.SUCCESS);     
		returnObject.setReturnToUrl("/crs/courselist.do");
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);

		return new ResponseEntity<ReturnObject>(returnObject, headers, HttpStatus.OK);
	}

}
