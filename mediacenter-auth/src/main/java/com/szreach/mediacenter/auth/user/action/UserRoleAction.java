/**
 * Copyright (c) @2015-3-30. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.user.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.szreach.mediacenter.auth.user.bean.UserRole;
import com.szreach.mediacenter.auth.user.service.UserRoleService;
import com.szreach.mediacenter.common.base.BaseAction;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-30
 * @Version: 1.0
 */
@Controller
@RequestMapping("/userrole")
@Scope("prototype")
public class UserRoleAction  extends BaseAction  {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserRoleService userRoleService;
	
	@RequestMapping(value="/{userId}.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String getUserRole(@PathVariable("userId") int userId) {
		List<UserRole> list = userRoleService.getUserRole(userId);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
}
