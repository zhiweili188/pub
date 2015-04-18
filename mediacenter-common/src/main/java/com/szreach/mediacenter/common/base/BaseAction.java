/**
 * Copyright (c) @2014-3-7. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.common.base;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-3-7
 * @Version: 1.0
 */
public abstract class BaseAction<T extends Persistentable> {
	protected  BaseService<T> getService() {
		return null;
	}
	protected  String getPrefix() {
		return null;
	}
	protected  final String INDEX=getPrefix()+"/index";
	
	@RequestMapping(value="/index.do")
	public ModelAndView  index(Model model) {
		return new ModelAndView(INDEX);     
	}

	@RequestMapping(value="/list.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public String  list(T query,  @RequestParam("page") int pageNumber,  @RequestParam("rows") int pageSize, Model model) {
		PageBean page = new PageBean();
		page.setCurrPage(pageNumber);
		page.setPageSize(pageSize);
		
		List<T> list = getService().query(query, page);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		json = "{\"rows\":"+json+",\"total\":"+page.getTotal()+"}";
		return json;
	}
	
	@RequestMapping(value="/id{id}.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public T getById(@PathVariable("id") int id) {
		T bean =  getService().getByID(id);
		return bean;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public String save(T model) throws Exception {
		if(model.getId() == null){
			getService().insert(model);
		} else {
			getService().update(model);
		}
		return "success";
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public void delete(@RequestParam("id") int id) {
		 getService().delete(id);
	}
	
	
	
}
