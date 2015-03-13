/**
 * Copyright (c) @2015-3-11. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.user.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.szreach.mediacenter.auth.site.bean.SiteBean;
import com.szreach.mediacenter.auth.user.bean.LoginUser;
import com.szreach.mediacenter.auth.user.service.LoginUserService;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.common.base.PageBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-11
 * @Version: 1.0
 */
@Controller
@RequestMapping("/user")
@Scope("prototype")
public class UserAction  extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LoginUserService loginUserService;
	
	@RequestMapping(value="/index.do")
	public ModelAndView index() {
		return new ModelAndView("/user/index");
	}
	
	@RequestMapping(value="/list.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String  list(LoginUser query,  @RequestParam("page") int pageNumber,  @RequestParam("rows") int pageSize, Model model) {
		PageBean page = new PageBean();
		page.setCurrPage(pageNumber);
		page.setPageSize(pageSize);
		
		List<LoginUser> list = loginUserService.query(query, page);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		json = "{\"rows\":"+json+",\"total\":"+page.getTotal()+"}";
		return json;
	}
	
	@RequestMapping(value="/id{id}.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public LoginUser getLoginUserById(@PathVariable("id") int id) {
		LoginUser user = loginUserService.getLoginUserByID(id);
		return user;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public String save(LoginUser model) throws Exception {
		if(model.getId() == null){
			loginUserService.insertLoginUser(model);
		} else {
			loginUserService.updateLoginUser(model);
		}
		return "success";
	}
}
