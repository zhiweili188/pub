/**
 * Copyright (c) @2015-4-16. All Rights Reserved.
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
import com.szreach.mediacenter.common.annotation.SkipLogin;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.common.base.BaseService;
import com.szreach.mediacenter.kv.bean.Education;
import com.szreach.mediacenter.kv.service.EducationService;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-16
 * @Version: 1.0
 */
@Controller
@RequestMapping("/edu")
@Scope("prototype")
public class EducationAction extends BaseAction<Education> {
	@Autowired
	private EducationService educationService;
	@Override
	protected BaseService<Education> getService() {
		return educationService;
	}
	
	@SkipLogin(value=true)
	@RequestMapping(value="/json.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String  all(Education query, Model model) {
		
		List<Education> list = educationService.getAll(query);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
}
