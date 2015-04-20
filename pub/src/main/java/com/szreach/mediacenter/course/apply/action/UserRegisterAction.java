/**
 * Copyright (c) @2015-4-15. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.course.apply.action;

import java.util.Locale;

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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.szreach.mediacenter.auth.login.bean.LoginUser;
import com.szreach.mediacenter.auth.login.service.LoginUserService;
import com.szreach.mediacenter.common.annotation.RepeatSubmitValidate;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.common.base.BaseService;
import com.szreach.mediacenter.course.apply.bean.UserRegister;
import com.szreach.mediacenter.course.apply.service.UserRegisterService;
import com.szreach.mediacenter.st.Key;
import com.szreach.mediacenter.st.M;
import com.szreach.mediacenter.st.ReturnCode;
import com.szreach.mediacenter.st.ReturnObject;

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
	@Autowired
	private LoginUserService loginUserService;
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
		model.addAttribute(Key.DISPLAY_MESSAGE, M.USER_REGISTER_SUCCESS);
		return new ModelAndView("success");     
	} 
	
	@RequestMapping(value="/validate.do")
	@ResponseBody
	public ReturnObject  validate(UserRegister bean, Model model) {
		ReturnObject returnObject = null;
		returnObject = new ReturnObject(ReturnCode.SUCCESS);     
		String msg = null;
		if(StringUtils.hasText(bean.getUserName())) {
			LoginUser loginUser = loginUserService.getByUserName(bean.getUserName());
			if(loginUser != null) {
				msg = getMessageSource().getMessage(M.USER_NAME_EXIST, null, M.LOCALE);
				returnObject.setCode(ReturnCode.ERROR);
			}
		}
		if(StringUtils.hasText(bean.getIdCardNo())) {
			UserRegister reg = userRegisterService.getByIdCardNo(bean.getIdCardNo());
			if(reg != null) {
				msg = getMessageSource().getMessage(M.USER_IDCARD_EXIST, null, M.LOCALE);
				returnObject.setCode(ReturnCode.ERROR);
			}
		}
		if(StringUtils.hasText(bean.getEmail())) {
			UserRegister reg = userRegisterService.getByEmail(bean.getEmail());
			if(reg != null) {
				msg = getMessageSource().getMessage(M.USER_EAMIL_EXIST, null, M.LOCALE);
				returnObject.setCode(ReturnCode.ERROR);
			}
		}
		
		returnObject.setMessage(msg);
		
		return returnObject;
	}
}
