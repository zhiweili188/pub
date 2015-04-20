/**
 * Copyright (c) @2014-3-7. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.szreach.mediacenter.auth.role.bean.Privilege;
import com.szreach.mediacenter.auth.role.bean.Role;
import com.szreach.mediacenter.common.base.BaseDao;
import com.szreach.mediacenter.common.base.PageBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-3-7
 * @Version: 1.0
 */
public interface RoleDao  extends BaseDao<Role>{
	//public int countAll(@Param("query") Role query);
	//public List<Role> query(@Param("query") Role query, @Param("page")  PageBean page);
	public void insertPrivilege(@Param("roleId") int roleId, @Param("list") List<Integer> menuIdList);
	public List<Privilege> getPrivilege(int roleId);
	public void deletePrivilege(int roleId);
	public List<Role> getChoosableRoles(int userId);
}
