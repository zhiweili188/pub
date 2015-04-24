/**
 * Copyright (c) @2014-12-29. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.test.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.szreach.mediacenter.auth.test.service.TestService;
import com.szreach.mediacenter.exception.BusinessException;
import com.szreach.mediacenter.exception.ParameterException;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-12-29
 * @Version: 1.0
 */
@Controller
public class TestController {
	 @Resource  
	    private TestService testService;  
	    
	 @RequestMapping(value="/init.do")
	 public ModelAndView init(){
		 return new ModelAndView("/test-ex/ex");
	 }
	    @RequestMapping(value = "/controller.do", method = RequestMethod.GET)  
	    public void controller(HttpServletResponse response, Integer id) throws Exception {  
	        switch(id) {  
	        case 1:  
	            throw new BusinessException("10", "controller10");  
	        case 2:  
	            throw new BusinessException("20", "controller20");  
	        case 3:  
	            throw new BusinessException("30", "controller30");  
	        case 4:  
	            throw new BusinessException("40", "controller40");  
	        case 5:  
	            throw new BusinessException("50", "controller50");  
	        default:  
	            throw new ParameterException("Controller Parameter Error");  
	        }  
	    }  
	      
	    @RequestMapping(value = "/service.do", method = RequestMethod.GET)  
	    public void service(HttpServletResponse response, Integer id) throws Exception {  
	        testService.exception(id);  
	    }  
	      
	    @RequestMapping(value = "/dao.do", method = RequestMethod.GET)  
	    public void dao(HttpServletResponse response, Integer id) throws Exception {  
	        testService.dao(id);  
	    }  
	    
	    /** 基于@ExceptionHandler异常处理 */  
	   
	    public String exception(HttpServletRequest request, Exception ex) {
	    	request.setAttribute("ex", ex);  
	          
	        // 根据不同错误转向不同页面  
	        if(ex instanceof BusinessException) {  
	            return "error-business";  
	        }else if(ex instanceof ParameterException) {  
	            return "error-parameter";  
	        } else {  
	            return "error";  
	        }  
	    }
	    @ExceptionHandler  
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
