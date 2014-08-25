/**
 * Copyright (c) @2014-3-7. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.szreach.mediacenter.auth.menu.bean.MenuBean;
import com.szreach.mediacenter.auth.menu.dao.MenuDao;
import com.szreach.mediacenter.common.util.M;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-3-7
 * @Version: 1.0
 */
@Service("menuService")
@Scope("prototype")
public class MenuService implements IMenuService{
	@Autowired
	MenuDao menuDao;
	public int count() {
		return menuDao.count();
	}
	
	public void insertMenu(MenuBean menu) {
		String code = this.createCode(menu);
		menu.setMenuCode(code);
		menu.setMenuLevel(code.length()/2);
		menu.setMenuStatus(0);
		
		String id =M.getID();
		menu.setId(id);
		
		menuDao.insertMenu(menu);
	}
	
	@Override
	public void updateMenu(MenuBean menu) {
		String code = this.createCode(menu);
		menu.setMenuCode(code);
		menu.setMenuLevel(code.length()/2);
		menu.setMenuStatus(0);
		
		menuDao.updateMenu(menu);
		
	}
	
	public void delete(String menuId){
		if(menuId != null && !"".equals(menuId)) {
			String[] ids = menuId.split(",");
			for(String id: ids) {
				
				menuDao.delete(id);
			}
		}
	}

	public MenuBean getMenuByID(String menuId) {
		MenuBean menu = menuDao.getMenuByID(menuId);
		return menu;
	}
	
	public List<MenuBean> queryMenu(MenuBean query) {
		int total = menuDao.count();
		query.setTotal(total);
		List<MenuBean> list = menuDao.queryMenu(query);
		return list;
	}
	
	/**
	 * 生成编码，第一级是00-99，第二级是0000-9999，最多5级，已经足够多了
	 * @param menu
	 * @param con
	 * @return
	 */
	private String createCode(MenuBean menu) {
		String code = null;
		//如果没有选上级学科，把该学科当作是第一级学科
		//code从00--99中选择最小的，并且没有被占用的
		if( StringUtils.isEmpty(menu.getParentID())) {
			
			MenuBean query = new MenuBean();
			query.setMenuLevel(1);
			
			 List<MenuBean> list = this.queryMenu(query);
			 if(list ==null || list.size()==0) {
				 code = "00";//第一个添加的学科
			 } else {
				 int tmpCode =Integer.parseInt( list.get(0).getMenuCode());
				 int nextCode = 0;;
				 for(int i=1; i<list.size(); i++) {
					 nextCode = Integer.parseInt(list.get(i).getMenuCode());
					 if( nextCode > tmpCode) {
						 tmpCode = nextCode;
					 }
				 }
				 tmpCode++;
				 
				 if(tmpCode < 10) {
					 code = "0"+tmpCode;
				 } else {
					 code = ""+tmpCode;
				 }
			 }
		} else {
			//如果选择了上级学科，则code是上级学科的code+从00--99中选择最小的，并且没有被占用的
			//先查询上级学科
			
			MenuBean parentMenu = menuDao.getMenuByID(menu.getParentID());
			if(parentMenu != null) {
				MenuBean query = new MenuBean();
				query.setMenuLevel(parentMenu.getMenuLevel() + 1);
				
				 List<MenuBean> list = this.queryMenu(query);
				 if(list ==null || list.size()==0) {
					 code = parentMenu.getMenuCode();
					// for(int i=0; i<parentSubject.getTreeLevel(); i++) {
						 code += "00";
					// }
				 } else {
					 int codeLength = parentMenu.getMenuLevel() * 2;
					 int tmpCode =Integer.parseInt( list.get(0).getMenuCode());
					 int nextCode = 0;;
					 boolean found = false;
					 int i=0;
					 for(int k=0; k<100; k++) {
						 for(i=0; i<list.size(); i++) {
							 nextCode = Integer.parseInt(list.get(i).getMenuCode().substring(codeLength));
							 if( k == nextCode) {//k已经被用了
								 if( nextCode > tmpCode) {
									 tmpCode = nextCode;
								 }
								break;
							 }
							 if(i == list.size()-1) {
								 found = true;
								 break;
							 }
						 }
						 
						 if(found) {
							 tmpCode = k;
							 break;
						 }
					 }
					 
					 if(found) {
						 code = parentMenu.getMenuCode();
					 }
					
					 if(tmpCode < 10) {
						 code += "0"+tmpCode;
					 } else {
						 code += ""+tmpCode;
					 }
				 }
			}
		}
		
		return code;
	}
}
