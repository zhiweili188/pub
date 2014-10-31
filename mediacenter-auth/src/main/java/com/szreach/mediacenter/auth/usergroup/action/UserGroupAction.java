package com.szreach.mediacenter.auth.usergroup.action;

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
import com.szreach.mediacenter.auth.usergroup.bean.UserGroupBean;
import com.szreach.mediacenter.auth.usergroup.service.IUserGroupService;

@Controller
@RequestMapping("/usergroup")  
@Scope("prototype")
public class UserGroupAction {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("userGroupService")
	IUserGroupService userGroupService;
	
	@RequestMapping(value="/save.do")
	public void save(UserGroupBean model) {
		if("".equals(model.getId())){
			
			userGroupService.insertUserGroup(model);
		} else {
			
			userGroupService.updateUserGroup(model);
		}
	}
	
	@RequestMapping(value="/index.do")
	public ModelAndView  index(Model model) {
		return new ModelAndView("/usergroup/index");     
	}
	
	@RequestMapping(value="/init_add.do")
	public ModelAndView  initAdd(Model model) {
		return new ModelAndView("/usergroup/add");     
	}
	@RequestMapping(value="/init_update.do")
	public ModelAndView  initUpdate(UserGroupBean param, Model model) {
		UserGroupBean bean = userGroupService.getUserGroupByID(param.getId());
		model.addAttribute("bean", bean);
		return new ModelAndView("/usergroup/add");     
	}
	
	@RequestMapping(value="/list.do")
	@ResponseBody
	public String  list(UserGroupBean query, Model model) {
		List<UserGroupBean> list = userGroupService.queryUserGroup(query);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		json = "{Rows:"+json+",Total:"+query.getTotal()+",currPage:"+query.getCurrPage()+"}";
		return json;
	}
	
	@RequestMapping(value="/del.do")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String selectId = request.getParameter("id");
		userGroupService.delete(selectId);
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
