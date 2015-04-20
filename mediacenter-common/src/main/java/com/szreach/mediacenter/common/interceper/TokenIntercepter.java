package com.szreach.mediacenter.common.interceper;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.szreach.mediacenter.common.annotation.RepeatSubmitValidate;

public class TokenIntercepter extends HandlerInterceptorAdapter {
	private Logger log = LoggerFactory.getLogger(getClass());
	 @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        if (handler instanceof HandlerMethod) {
	        	if(request.getSession(false) ==null) {
	        		return true;
	        	}
	            HandlerMethod handlerMethod = (HandlerMethod) handler;
	            Method method = handlerMethod.getMethod();
	            RepeatSubmitValidate annotation = method.getAnnotation(RepeatSubmitValidate.class);
	            if (annotation != null) {
	                boolean needSaveSession = annotation.create();
	                if (needSaveSession) {
	                    request.getSession(false).setAttribute("token", UUID.randomUUID().toString());
	                }
	                boolean needRemoveSession = annotation.destroy();
	                if (needRemoveSession) {
	                    if (isRepeatSubmit(request)) {
	                    	log.debug("repeat submit occord");
	                        return false;
	                    }
	                    request.getSession(false).removeAttribute("token");
	                }
	            }
	            return true;
	        } else {
	            return super.preHandle(request, response, handler);
	        }
	    }
	 
	    private boolean isRepeatSubmit(HttpServletRequest request) {
	        String serverToken = (String) request.getSession(false).getAttribute("token");
	        if (serverToken == null) {
	            return true;
	        }
	        String clinetToken = request.getParameter("token");
	        if (clinetToken == null) {
	            return true;
	        }
	        if (!serverToken.equals(clinetToken)) {
	            return true;
	        }
	        return false;
	    }
}
