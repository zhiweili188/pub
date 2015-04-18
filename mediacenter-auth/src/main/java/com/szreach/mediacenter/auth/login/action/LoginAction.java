/**
 * Copyright (c) @2015-3-11. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.login.action;

import java.util.List;

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

import com.szreach.mediacenter.auth.login.service.LoginService;
import com.szreach.mediacenter.auth.menu.bean.MenuBean;
import com.szreach.mediacenter.auth.st.Key;
import com.szreach.mediacenter.auth.st.ReturnCode;
import com.szreach.mediacenter.auth.st.ReturnObject;
import com.szreach.mediacenter.auth.user.bean.LoginUser;
import com.szreach.mediacenter.auth.user.service.LoginUserService;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.common.base.BaseService;

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
		}
		
		returnObject = new ReturnObject(ReturnCode.SUCCESS);     
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);

		return new ResponseEntity<ReturnObject>(returnObject, headers, HttpStatus.OK);
	}
	@RequestMapping(value="/main.do")
	public ModelAndView index( Model model, HttpSession session) {
		logger.debug("----index--------");
		
		LoginUser loginUser = (LoginUser)session.getAttribute(Key.SESSION_LOGIN_USER);
		
		if(loginUser == null) {
			return new ModelAndView("redirect:/login/tologin.do");     
		} else {
			List<MenuBean> treeList = loginService.queryMenuTree(loginUser.getId());
			model.addAttribute("menuList", treeList);
		}
		
		return new ModelAndView("/index");     
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
			List<MenuBean> treeList = loginService.queryMenuTree(loginUser.getId());
			model.addAttribute("menuList", treeList);
		}
		
		return new ModelAndView("/index");     
	}

}
