/**
 * Copyright (c) @2015-4-15. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.course.apply.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.szreach.mediacenter.auth.login.bean.LoginUser;
import com.szreach.mediacenter.auth.login.bean.UserActivate;
import com.szreach.mediacenter.auth.login.service.LoginUserService;
import com.szreach.mediacenter.auth.login.service.UserActivateService;
import com.szreach.mediacenter.common.annotation.RepeatSubmitValidate;
import com.szreach.mediacenter.common.annotation.SkipLogin;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.common.base.BaseService;
import com.szreach.mediacenter.common.util.CommonTools;
import com.szreach.mediacenter.course.apply.bean.UserRegister;
import com.szreach.mediacenter.course.apply.service.UserRegisterService;
import com.szreach.mediacenter.mail.service.MailService;
import com.szreach.mediacenter.st.Key;
import com.szreach.mediacenter.st.Message;
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
	@Autowired
	private MailService mailService;
	@Autowired
	private UserActivateService userActivateService;
	@Autowired
	private TaskExecutor taskExecutor;
	 @Value("${activeUrl}")
	private String activeUrl;
	
	@Override
	protected BaseService<UserRegister> getService() {
		return userRegisterService;
	}
	
	public UserActivateService getUserActivateService() {
		return userActivateService;
	}

	@Override
	protected String getPrefix() {
		return "/user";
	}
	
	public MailService getMailService() {
		return mailService;
	}

	public String getActiveUrl() {
		return activeUrl;
	}

	@SkipLogin(value=true)
	@RequestMapping(value="/start-register.do")
	@RepeatSubmitValidate(create=true)
	public ModelAndView  startRegister( Model model) {
		return new ModelAndView(getPrefix()+"/register");     
	} 
	
	@SkipLogin(value=true)
	@RequestMapping(value="/register.do")
	@RepeatSubmitValidate(destroy=true)
	public ModelAndView  register(UserRegister bean, Model model) {
			userRegisterService.saveRegisterInfo(bean);
			model.addAttribute(Key.DISPLAY_MESSAGE, Message.USER_REGISTER_SUCCESS.getMsgKey());
			
			taskExecutor.execute(new MailTask(bean));
			
			return new ModelAndView("success");     
	} 
	
	@RequestMapping(value="/modify.do")
	@RepeatSubmitValidate(destroy=true)
	public ModelAndView  modify(UserRegister bean, Model model, HttpSession session) {
		if(bean.getId() == null){
			LoginUser loginUser = (LoginUser)session.getAttribute(Key.SESSION_LOGIN_USER);
			bean.setUserId(loginUser.getUserId());
			getService().insert(bean);
			model.addAttribute(Key.DISPLAY_MESSAGE, Message.USER_MODIFY_SUCCESS.getMsgKey());
			
		} else {
			getService().update(bean);
			model.addAttribute(Key.DISPLAY_MESSAGE, Message.USER_MODIFY_SUCCESS.getMsgKey());
		}
		
		return new ModelAndView("success");     
	} 
	
	@SkipLogin(value=true)
	@RequestMapping(value="/validate.do")
	@ResponseBody
	public ReturnObject  validate(UserRegister bean, Model model) {
		ReturnObject returnObject = null;
		returnObject = new ReturnObject(ReturnCode.SUCCESS);     
		String msg = null;
		if(StringUtils.hasText(bean.getUserName())) {
			LoginUser loginUser = loginUserService.getByUserName(bean.getUserName());
			if(loginUser != null) {
				msg = getMessage(Message.ERR_USER_NAME_EXIST.getMsgKey(), null);
				returnObject.setCode(Message.ERR_USER_NAME_EXIST.getId());
			}
		}
		if(StringUtils.hasText(bean.getIdCardNo())) {
			UserRegister reg = userRegisterService.getByIdCardNo(bean.getIdCardNo());
			if(reg != null) {
				msg = getMessage(Message.ERR_USER_IDCARD_EXIST.getMsgKey());
				returnObject.setCode(Message.ERR_USER_IDCARD_EXIST.getId());
			}
		}
		if(StringUtils.hasText(bean.getEmail())) {
			UserRegister reg = userRegisterService.getByEmail(bean.getEmail());
			if(reg != null) {
				msg = getMessage(Message.ERR_USER_EAMIL_EXIST.getMsgKey());
				returnObject.setCode(Message.ERR_USER_EAMIL_EXIST.getId());
			}
		}
		
		returnObject.setMessage(msg);
		
		return returnObject;
	}
	
	@RequestMapping(value="/registerDetail.do")
	@RepeatSubmitValidate(create=true)
	public ModelAndView  getRegisterInfo(Model model, HttpSession session) {
		LoginUser loginUser = (LoginUser)session.getAttribute(Key.SESSION_LOGIN_USER);
		UserRegister userInfo = userRegisterService.getByUserId(loginUser.getUserId());
		model.addAttribute("userInfo", userInfo);
		return new ModelAndView(getPrefix()+"/userInfo");
	} 
	@RequestMapping(value="/toModifyPwd.do")
	@RepeatSubmitValidate(create=true)
	public ModelAndView  startModifyPassword(Model model, HttpSession session) {
		LoginUser loginUser = (LoginUser)session.getAttribute(Key.SESSION_LOGIN_USER);
		model.addAttribute("loginUser", loginUser);
		return new ModelAndView(getPrefix()+"/modify-passwd");
	} 
	
	@RequestMapping(value="/modifyPwd.do")
	@RepeatSubmitValidate(destroy=true)
	public ModelAndView  modifyPasswd(LoginUser user, Model model, HttpSession session, RedirectAttributes attr) {
		LoginUser loginUser = (LoginUser)session.getAttribute(Key.SESSION_LOGIN_USER);
		LoginUser entity = loginUserService.getByUserName(loginUser.getUserName());
		if(entity.getPasswd().equals(CommonTools.getMD5(user.getOldpasswd()))) {
			user.setUserId(entity.getUserId());
			loginUserService.updatePwd(user);
			model.addAttribute(Key.DISPLAY_MESSAGE, Message.USER_MODIFY_PASSWD_SUCCESS.getMsgKey());
			return new ModelAndView("success");     
		} else {
			attr.addAttribute(Key.DISPLAY_MESSAGE, Message.ERR_OLD_PASSWD_WRONG.getMsgKey());
			return new ModelAndView("redirect:toModifyPwd.do");
		}
		
	} 
	
	private class MailTask implements Runnable {
		private UserRegister bean;
		public MailTask(UserRegister reg) {
			bean = reg;
		}
		@Override
		public void run() {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(bean.getEmail());
			mailMessage.setSubject("用户激活邮件");
			Map<String, Object> data = new HashMap<String, Object>();
			
			
			UserActivate acti =  getUserActivateService().getByID(bean.getUserId());
			data.put("activeUrl", getActiveUrl()+"/valid/activate.do?userId="+bean.getUserId()+"&validCode="+acti.getValidCode());
			//这里的model是给模板页面传送数据用的，在模板页面中要用freemarker的脚本语言解析这些数据
			getMailService().send(mailMessage, "sample.ftl", data);
			
		}
		
	}
	
}
