/**
 * Copyright (c) @2015-4-2. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.department.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.szreach.mediacenter.auth.department.bean.Department;
import com.szreach.mediacenter.auth.department.service.DepartmentService;
import com.szreach.mediacenter.auth.menu.bean.MenuBean;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.common.base.BaseService;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-2
 * @Version: 1.0
 */
@Controller
@RequestMapping("/dpt")
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private DepartmentService departmentService;
	
	@Override
	protected BaseService<Department> getService() {
		return departmentService;
	}
	
	@Override
	protected String getPrefix() {
		return "/department";
	}
	
	@RequestMapping(value="/combox.do")
	@ResponseBody
	public String  list(Department query,Model model) {
		
		List<Department> list = departmentService.query(query, null);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
	
}
