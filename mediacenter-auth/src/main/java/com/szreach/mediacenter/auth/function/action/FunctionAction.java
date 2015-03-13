/**
 * Copyright (c) @2014-12-30. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.function.action;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szreach.mediacenter.auth.function.bean.FunctionBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-12-30
 * @Version: 1.0
 */
@Controller
@RequestMapping("/func")
@Scope("prototype")
public class FunctionAction {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/index")
	public String index() {
		return "/function/index";
	}
	
	@RequestMapping(value="/imp")
	public void importData() {
		
	}
	
	@RequestMapping(value="/setup.do", method=RequestMethod.GET)
	public void setupForm(@RequestParam("id") String id) {
		logger.debug("get param id:"+id);
	}
	
	@RequestMapping(value="/getParam.do", method=RequestMethod.GET)
	@ResponseBody
	public String getParam(@RequestParam Map<String,String> map) {
		logger.debug("get param map:");
		
		Set<Map.Entry<String, String>> set = map.entrySet();
		Iterator<Map.Entry<String, String>> it = set.iterator();
		while(it.hasNext()) {
			Map.Entry<String, String> e = it.next();
			logger.debug(e.getKey() +"="+e.getValue() );
		}
		
		return "success";
	}
	
	@RequestMapping(value="/receive.do", method=RequestMethod.POST)
	@ResponseBody
	public String receiveRequesetBody(@RequestBody String body) {
		logger.debug("receive request msg :"+body );
		return "success";
	}
	
	@RequestMapping(value="/receive2.do", method=RequestMethod.POST)
	public ResponseEntity<String> receive(HttpEntity<byte[]> req) {
		HttpHeaders headers = req.getHeaders();
		String myheader = headers.getFirst("myheader");
		logger.debug("myheader="+myheader);
		
		byte[] body = req.getBody();
		logger.debug("body length:"+body.length);
		
		HttpHeaders responseHeader = new HttpHeaders();
		responseHeader.add("MyResponseHeader", "1111111111");
		return new ResponseEntity<String>("hello world", responseHeader, HttpStatus.OK);
	}
	
	@ModelAttribute
	public void addAttr(Model model) {
		logger.debug("------");
		model.addAttribute("key1", "value1");
	}
	
	@RequestMapping("/{id}/addattr.do")
	public void addAttr(@ModelAttribute FunctionBean bean, Model model) {
		logger.debug("---addAttr---");
		logger.debug("---addAttr---"+bean.getId());
		logger.debug("---addAttr---"+ model.containsAttribute("key1"));
		logger.debug("---addAttr---"+ model.containsAttribute("key2"));
		logger.debug("---addAttr---"+ model.containsAttribute("functionBean"));
		
	}
}
