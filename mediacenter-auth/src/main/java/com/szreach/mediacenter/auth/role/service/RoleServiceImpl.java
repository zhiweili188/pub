/**
 * Copyright (c) @2015-3-23. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.role.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.szreach.mediacenter.auth.role.bean.Privilege;
import com.szreach.mediacenter.auth.role.bean.Role;
import com.szreach.mediacenter.auth.role.dao.RoleDao;
import com.szreach.mediacenter.common.base.AbstractBaseServiceImpl;
import com.szreach.mediacenter.common.base.PageBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-23
 * @Version: 1.0
 */
@Service("roleService")
@Scope("prototype")
@Transactional
public class RoleServiceImpl  extends AbstractBaseServiceImpl<Role>  implements RoleService {

	@Autowired
	private RoleDao roleDao;
	@Override
	public void insert(Role role) {
		role.setRoleStatus(0);
		roleDao.insert(role);

		savePrivileges(role);
	}

	/**
	 * @param role
	 */
	private void savePrivileges(Role role) {
		if( !StringUtils.isEmpty(role.getPrivileges())) {
			 List<Integer> menuIdList = new ArrayList<Integer>();
				String[] ids = role.getPrivileges().split(",");
				for(String id : ids) {
					menuIdList.add(Integer.valueOf(id));
				}
				roleDao.insertPrivilege(role.getId(), menuIdList);
		}
	}

	@Override
	public Role getByID(Integer roleId) {
		 List<Privilege> privileges = roleDao.getPrivilege(roleId);
		 StringBuffer buf = new StringBuffer();
		 for(Privilege p : privileges) {
			 buf.append(p.getMenuId()).append(",");
		 }
		 if(buf.length() > 0) {
			 
			 buf.deleteCharAt(buf.length()-1);
		 }
		 
		 Role role = roleDao.get(roleId);
		 role.setPrivileges(buf.toString());
		 
		return role;
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
		roleDao.deletePrivilege(role.getId());
		savePrivileges(role);
	}

	@Override
	public int count(Role query) {
		return roleDao.countAll(query);
	}

	@Override
	public List<Role> query(Role query, PageBean page) {
		int total = roleDao.countAll( query);
		page.setTotal(total);
		return roleDao.query(query, page);
	}
	@Override
	public List<Role> getAll(Role query) {
		return roleDao.query(query, null);
	}
	
	

	@Override
	public void delete(Integer roleId) {
		roleDao.delete(roleId);

	}

	@Override
	public List<Role> getChoosableRoles(int userId) {
		return roleDao.getChoosableRoles(userId);
	}

}
