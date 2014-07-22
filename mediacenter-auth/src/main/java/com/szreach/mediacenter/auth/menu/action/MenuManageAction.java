/**
 * Copyright (c) @2014-3-7. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.menu.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.szreach.mediacenter.auth.menu.bean.MenuBean;
import com.szreach.mediacenter.auth.menu.service.IMenuService;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-3-7
 * @Version: 1.0
 */
@Controller
@RequestMapping("/menu")  
@Scope("prototype")
public class MenuManageAction {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("menuService")
	IMenuService menuService;
	
	@RequestMapping(value="/count.do")
	public void count(Model model) {
		int cnt = menuService.count();
		logger.debug("------------"+cnt);
		model.addAttribute("cnt", cnt);
	}
	
	@RequestMapping(value="/save.do")
	public void save(MenuBean model) {
		menuService.insertMenu(model);
		
	}
	
	@RequestMapping(value="/index.do")
	public ModelAndView  index(Model model) {
		return new ModelAndView("/menu/index");     
	}
	
	@RequestMapping(value="/init_add.do")
	public ModelAndView  initAdd(Model model) {
		return new ModelAndView("/menu/add");     
	}
	
	@RequestMapping(value="/list.do")
	@ResponseBody
	public String  list(MenuBean query, Model model) {
		List<MenuBean> list = menuService.queryMenu(query);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		json = "{Rows:"+json+",Total:91}";
		return json;
	}
}
