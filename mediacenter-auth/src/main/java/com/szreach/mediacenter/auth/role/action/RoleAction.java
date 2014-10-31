package com.szreach.mediacenter.auth.role.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.szreach.mediacenter.auth.role.bean.RoleBean;
import com.szreach.mediacenter.auth.role.service.IRoleService;

@Controller
@RequestMapping("/role")  
@Scope("prototype")
public class RoleAction {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("roleService")
	IRoleService roleService;
	
	@RequestMapping(value="/save.do")
	public void save(RoleBean model) {
		if("".equals(model.getId())){
			
			roleService.insertRole(model);
		} else {
			
			roleService.updateRole(model);
		}
	}
	
	@RequestMapping(value="/index.do")
	public ModelAndView  index(Model model) {
		return new ModelAndView("/role/index");     
	}
	
	@RequestMapping(value="/init_add.do")
	public ModelAndView  initAdd(Model model) {
		return new ModelAndView("/role/add");     
	}
	@RequestMapping(value="/init_update.do")
	public ModelAndView  initUpdate(RoleBean param, Model model) {
		RoleBean bean = roleService.getRoleByID(param.getId());
		model.addAttribute("bean", bean);
		return new ModelAndView("/role/add");     
	}
	
	@RequestMapping(value="/list.do")
	@ResponseBody
	public String  list(RoleBean query, Model model) {
		List<RoleBean> list = roleService.queryRole(query);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		json = "{Rows:"+json+",Total:"+query.getTotal()+",currPage:"+query.getCurrPage()+"}";
		return json;
	}
	
	@RequestMapping(value="/del.do")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String selectId = request.getParameter("id");
		roleService.delete(selectId);
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
}
