/**
 * Copyright (c) @2014-3-7. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.menu.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.szreach.mediacenter.auth.menu.bean.MenuBean;
import com.szreach.mediacenter.auth.menu.service.MenuService;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.common.base.BaseService;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-3-7
 * @Version: 1.0
 */
@Controller
@RequestMapping("/menu")  
@Scope("prototype")
public class MenuManageAction extends BaseAction<MenuBean> {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("menuService")
	MenuService menuService;
	
	@RequestMapping(value="/count.do")
	public void count(MenuBean query, Model model) {
		int cnt = menuService.count(query);
		logger.debug("------------"+cnt);
		model.addAttribute("cnt", cnt);
	}
	
	@RequestMapping(value="/save.do", method=RequestMethod.POST)
	@ResponseBody
	public String save(MenuBean model, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
		
		if( file != null) {
			
	        String path = request.getSession().getServletContext().getRealPath("upload/menuicons");  
	        String fileName = file.getOriginalFilename();  
	        logger.debug(path);  
	        File targetFile = new File(path, fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	  
	        //保存  
	        try {  
	            file.transferTo(targetFile);  
	            //设置菜单的图标路径
	            StringBuffer sbRealPath = new StringBuffer("upload/menuicons/");  
	            sbRealPath.append(fileName);
	            model.setMenuIcon(sbRealPath.toString());
	        } catch (Exception e) {  
	            logger.error("上传图标文件失败", e);
	        }  
		}
		
		if(model.getId() == null){
			
			menuService.insert(model);
		} else {
			
			menuService.update(model);
		}
		return "success";
	}
	
/*	@RequestMapping(value="/index.do")
	public ModelAndView  index(Model model) {
		return new ModelAndView("/menu/index");     
	}*/
	
	/*@RequestMapping(value="/id{id}.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public MenuBean getLoginUserById(@PathVariable("id") int id) {
		MenuBean bean = menuService.getMenuByID(id);
		return bean;
	}*/
	
/*	@RequestMapping(value="/list.do")
	@ResponseBody
	public String  list(MenuBean query, @RequestParam("page") int pageNumber,  @RequestParam("rows") int pageSize,  Model model) {
		PageBean page = new PageBean();
		page.setCurrPage(pageNumber);
		page.setPageSize(pageSize);
		
		List<MenuBean> list = menuService.queryMenu(query, page);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		json = "{\"rows\":"+json+",\"total\":"+page.getTotal()+"}";
		return json;
	}*/
	@RequestMapping(value="/combox.do")
	@ResponseBody
	public String  list(MenuBean query,Model model) {
		
		List<MenuBean> list = menuService.query(query, null);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
	
	/*@RequestMapping(value="/del.do",  method = RequestMethod.POST)
	public void delete(@RequestParam("id") int id) {
		menuService.delete(id);
	}*/
	
	@RequestMapping(value="/tree.do")
	@ResponseBody
	public String  list() {
		
		List<MenuBean> list = menuService.queryTree();
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}

	@Override
	public BaseService<MenuBean> getService() {
		return menuService;
	}

	@Override
	protected String getPrefix() {
		// TODO Auto-generated method stub
		return "/menu";
	}
	
}
