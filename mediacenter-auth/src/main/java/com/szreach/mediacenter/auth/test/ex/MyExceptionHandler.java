/**
 * Copyright (c) @2014-12-29. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.test.ex;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-12-29
 * @Version: 1.0
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		if(ex instanceof BusinessException) {
			return new ModelAndView("error-business", model);
		} else if (ex instanceof ParameterException) {
			return new ModelAndView("error-parameter", model);
		} else {
			return new ModelAndView("error", model);
		}
	}

}
