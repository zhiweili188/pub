/**
 * Copyright (c) @2014-3-7. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.menu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		if("".equals(model.getId())){
			
			menuService.insertMenu(model);
		} else {
			
			menuService.updateMenu(model);
		}
	}
	
	@RequestMapping(value="/index.do")
	public ModelAndView  index(Model model) {
		return new ModelAndView("/menu/index");     
	}
	
	@RequestMapping(value="/init_add.do")
	public ModelAndView  initAdd(Model model) {
		return new ModelAndView("/menu/add");     
	}
	@RequestMapping(value="/init_update.do")
	public ModelAndView  initUpdate(MenuBean param, Model model) {
		MenuBean bean = menuService.getMenuByID(param.getId());
		model.addAttribute("menu", bean);
		return new ModelAndView("/menu/add");     
	}
	
	@RequestMapping(value="/list.do")
	@ResponseBody
	public String  list(MenuBean query, Model model) {
		List<MenuBean> list = menuService.queryMenu(query);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		json = "{Rows:"+json+",Total:"+query.getTotal()+",currPage:"+query.getCurrPage()+"}";
		return json;
	}
	
	@RequestMapping(value="/del.do")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String selectId = request.getParameter("id");
		menuService.delete(selectId);
		PrintWriter out;
		try {
			out = response.getWriter();
			if (out != null) {
				out.print("success");
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		String en = URLEncoder.encode("眼界.ppt", "iso8859-1");
		System.out.println(en);
	}
}
