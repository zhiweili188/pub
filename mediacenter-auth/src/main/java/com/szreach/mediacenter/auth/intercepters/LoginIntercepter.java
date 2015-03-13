/**
 * Copyright (c) @2015-3-11. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.intercepters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-11
 * @Version: 1.0
 */
public class LoginIntercepter implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("----------------preHandle----------------");
		String requestUri = request.getServletPath();
		if(requestUri.equals("/login/index.do")) {
			return true;
		}
		boolean isLogin = false;
		if(request.getSession(false) != null) {
			isLogin = true;
		} else {
			 response.sendRedirect("/auth/redict2login.jsp");
		}
		logger.debug("user logined: " + isLogin);
		
		return isLogin;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.debug("==============执行顺序: 2、postHandle================");  

	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		logger.debug("==============执行顺序: 3、afterCompletion================");  

	}

}
