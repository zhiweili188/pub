/**
 * Copyright (c) @2014-12-31. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.function.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-12-31
 * @Version: 1.0
 */
@ControllerAdvice
public class AnnotationAdvice {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler
	public ModelAndView doEx(Exception ex) {
		logger.debug("do exception at AnnotationAdvice");
		logger.debug("AnnotationAdvice", ex);
		return null;
	}
	
	@ModelAttribute
	public void addAttr(Model model) {
		logger.debug("------");
		model.addAttribute("key2", "value2");
	}
}
