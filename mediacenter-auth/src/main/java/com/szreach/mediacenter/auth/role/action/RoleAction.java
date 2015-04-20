/**
 * Copyright (c) @2015-3-23. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.role.action;

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
import com.szreach.mediacenter.auth.role.bean.Role;
import com.szreach.mediacenter.auth.role.service.RoleService;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.common.base.BaseService;
import com.szreach.mediacenter.common.base.PageBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-23
 * @Version: 1.0
 */
@Controller
@RequestMapping("/role")
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RoleService roleService;
	
	/*@RequestMapping(value="/index.do")
	public ModelAndView index() {
		return new ModelAndView("/role/index");
	}*/
	
	/*@RequestMapping(value="/list.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String  list(Role query,  @RequestParam("page") int pageNumber,  @RequestParam("rows") int pageSize, Model model) {
		PageBean page = new PageBean();
		page.setCurrPage(pageNumber);
		page.setPageSize(pageSize);
		
		List<Role> list = roleService.query(query, page);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		json = "{\"rows\":"+json+",\"total\":"+page.getTotal()+"}";
		return json;
	}*/
	
	@RequestMapping(value="/all.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String  all(Role query, Model model) {
		
		List<Role> list = roleService.getAll(query);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
	@RequestMapping(value="/choose{userId}.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String  getChoosableRoles(@PathVariable("userId") int userId) {
		
		List<Role> list = roleService.getChoosableRoles(userId);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
	
	/*@RequestMapping(value="/id{id}.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Role getRoleById(@PathVariable("id") int id) {
		Role role = roleService.getRoleByID(id);
		return role;
	}*/
	
	/*@RequestMapping("/save.do")
	@ResponseBody
	public String save(Role model) throws Exception {
		if(model.getId() == null){
			roleService.insertRole(model);
		} else {
			roleService.updateRole(model);
		}
		return "success";
	}*/
	
	/*@RequestMapping("/delete.do")
	@ResponseBody
	public String delete(@RequestParam("ids") int id) throws Exception {
		roleService.delete(id);
		return "success";
	}
*/
	/* (non-Javadoc)
	 * @see com.szreach.mediacenter.common.base.BaseAction#getService()
	 */
	@Override
	public BaseService getService() {
		// TODO Auto-generated method stub
		return roleService;
	}

	/* (non-Javadoc)
	 * @see com.szreach.mediacenter.common.base.BaseAction#getPrefix()
	 */
	@Override
	protected String getPrefix() {
		// TODO Auto-generated method stub
		return "/role";
	}
}
