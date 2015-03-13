/**
 * Copyright (c) @2014-12-31. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.function.action;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-12-31
 * @Version: 1.0
 */
public class TimeBasedAccessInterceptor extends HandlerInterceptorAdapter {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		logger.debug("afterCompletion");
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("afterConcurrentHandlingStarted");
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.debug("postHandle");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("preHandle");
		 Calendar cal = Calendar.getInstance();
	        int hour = cal.get(Calendar.HOUR_OF_DAY);
	        if (openingTime <= hour && hour < closingTime) {
	            return true;
	        }
	        response.sendRedirect("http://www.baidu.com");
	        return false;
	}
	
	private int openingTime;
    private int closingTime;

    public void setOpeningTime(int openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(int closingTime) {
        this.closingTime = closingTime;
    }

}
