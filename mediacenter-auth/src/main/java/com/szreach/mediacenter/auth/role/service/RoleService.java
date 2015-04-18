/**
 * Copyright (c) @2015-3-23. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.role.service;

import java.util.List;

import com.szreach.mediacenter.auth.role.bean.Role;
import com.szreach.mediacenter.common.base.BaseService;
import com.szreach.mediacenter.common.base.PageBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-23
 * @Version: 1.0
 */
public interface RoleService extends BaseService<Role> {
	//public void insertRole(Role role);
	//public Role getRoleByID(Integer roleId);
	//public void updateRole(Role role);
	
	//public int count(Role query);
	
	//public List<Role> query(Role query, PageBean page);
	//public List<Role> getAll(Role query);
	
	public List<Role> getChoosableRoles(int userId);
	
	//public void delete(Integer roleId);
}
