/**
 * Copyright (c) @2015-4-15. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.kv.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.common.base.BaseService;
import com.szreach.mediacenter.kv.bean.UserIdentity;
import com.szreach.mediacenter.kv.service.UserIdentityService;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-15
 * @Version: 1.0
 */
@Controller
@RequestMapping("/ui")
@Scope("prototype")
public class UserIdentityAction extends BaseAction<UserIdentity> {

	@Autowired
	private UserIdentityService userIdentityService;
	@Override
	protected BaseService<UserIdentity> getService() {
		return userIdentityService;
	}
	
	@RequestMapping(value="/json.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String  all(UserIdentity query, Model model) {
		
		List<UserIdentity> list = userIdentityService.getAll(query);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
}
