/**
 * Copyright (c) @2015-2-3. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.site.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.szreach.mediacenter.auth.menu.bean.MenuBean;
import com.szreach.mediacenter.auth.site.bean.SiteBean;
import com.szreach.mediacenter.auth.site.service.SiteService;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.common.base.PageBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-2-3
 * @Version: 1.0
 */
@Controller
@RequestMapping("/site")
@Scope("prototype")
public class SiteAction extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping("/save.do")
	@ResponseBody
	public String save(SiteBean site) throws Exception {
		if(site.getId() == null){
			siteService.insertSite(site);
		} else {
			siteService.updateSite(site);
		}
		return "success";
	}
	
	@RequestMapping(value="/index.do")
	public ModelAndView index() {
		return new ModelAndView("/site/index");
	}
	
	@RequestMapping(value="/init_add.do")
	public ModelAndView  initAdd(Model model) {
		return new ModelAndView("/site/add");     
	}
	
	@RequestMapping(value="/init_update.do")
	public ModelAndView  initUpdate(SiteBean param, Model model) {
		SiteBean bean = siteService.getSiteByID(param.getId());
		model.addAttribute("site", bean);
		return new ModelAndView("/site/add");     
	}
	
	@RequestMapping(value="/list.do")
	@ResponseBody
	public String  list(SiteBean query, PageBean page, Model model) {
		List<SiteBean> list = siteService.query(query, page);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		json = "{Rows:"+json+",Total:"+page.getTotal()+"}";
		return json;
	}
	
	@RequestMapping(value="/del.do",  method = RequestMethod.POST)
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String selectId = request.getParameter("id");
		siteService.delete(Integer.valueOf(selectId));
	}
}
