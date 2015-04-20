/**
 * Copyright (c) @2015-3-30. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.szreach.mediacenter.auth.user.bean.UserRole;
import com.szreach.mediacenter.common.base.BaseDao;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-30
 * @Version: 1.0
 */
public interface UserRoleDao extends BaseDao<UserRole> {
	public void insertUserRole(@Param("userId") int userId, @Param("list") List<Integer> roleIdList);
	public List<UserRole> getUserRole(int userId);
}
