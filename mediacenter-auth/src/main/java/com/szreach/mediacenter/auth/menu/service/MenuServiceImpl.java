/**
 * Copyright (c) @2014-3-7. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.menu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.szreach.mediacenter.auth.menu.bean.MenuBean;
import com.szreach.mediacenter.auth.menu.dao.MenuDao;
import com.szreach.mediacenter.common.base.AbstractBaseServiceImpl;
import com.szreach.mediacenter.common.base.PageBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-3-7
 * @Version: 1.0
 */
@Service("menuService")
@Scope("prototype")
@Transactional
public class MenuServiceImpl  extends AbstractBaseServiceImpl<MenuBean> implements MenuService{
	@Autowired
	private MenuDao menuDao;
	
	@Override
	public int count(MenuBean query) {
		return menuDao.countAll(query);
	}
	
	@Override
	public void insert(MenuBean menu) {
		//String code = this.createCode(menu);
		//menu.setMenuCode(code);
		//menu.setMenuLevel(code.length()/2);
		menu.setMenuStatus(0);
		
		menuDao.insert(menu);
	}
	
	@Override
	public void update(MenuBean menu) {
		//String code = this.createCode(menu);
		//menu.setMenuCode(code);
		//menu.setMenuLevel(code.length()/2);
		//menu.setMenuStatus(0);
		
		menuDao.update(menu);
		
	}
	
	public void delete(Integer menuId){
			
			menuDao.delete(menuId);
	}

	@Override
	public MenuBean getByID(Integer menuId) {
		MenuBean menu = menuDao.get(menuId);
		return menu;
	}
	
	@Override
	public List<MenuBean> query(MenuBean query, PageBean page) {
		if(page != null) {
			
			int total = menuDao.countAll(query);
			page.setTotal(total);
		}
		List<MenuBean> list = menuDao.query(query, page);
		return list;
	}
	
	public List<MenuBean> queryTree() {
		List<MenuBean> list = menuDao.queryTree();
		if(list == null || list.size()==0) return null;
		List<MenuBean> treeList = new ArrayList<MenuBean>();
		
		MenuBean parentMenu = null;
		MenuBean childMenu = null;
		Integer id = -1;
		for(MenuBean menu: list ) {
			//父id相同，取子菜单的数据
			if(id.equals(menu.getId())) {
				if(menu.getChildId() != null) {
					
					childMenu = new MenuBean();
					childMenu.setId(menu.getChildId());
					childMenu.setText(menu.getChildMenuName());
					parentMenu.getChildren().add(childMenu);
				}
			} else {
				if(menu.getChildId() != null) {
					
					parentMenu = new MenuBean();
					parentMenu.setId(menu.getId());
					parentMenu.setText(menu.getMenuName());
					parentMenu.setChildren( new ArrayList<MenuBean>());
					treeList.add(parentMenu);
					
					childMenu = new MenuBean();
					childMenu.setId(menu.getChildId());
					childMenu.setText(menu.getChildMenuName());
					parentMenu.getChildren().add(childMenu);
				}
				
				id=menu.getId();
			}
		}
		return treeList;
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
			
			 List<MenuBean> list = this.query(query, null);
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
			
			MenuBean parentMenu = menuDao.get(menu.getParentID());
			if(parentMenu != null) {
				MenuBean query = new MenuBean();
				query.setMenuLevel(parentMenu.getMenuLevel() + 1);
				
				 List<MenuBean> list = this.query(query, null);
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
