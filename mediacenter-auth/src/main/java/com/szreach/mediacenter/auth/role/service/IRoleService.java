package com.szreach.mediacenter.auth.role.service;

import java.util.List;

import com.szreach.mediacenter.auth.role.bean.RoleBean;

public interface IRoleService {
	public int count();
	public void insertRole(RoleBean bean);
	public void updateRole(RoleBean bean);
	public void delete(String roleId);
	public RoleBean getRoleByID(String roleId);
	
	public List<RoleBean> queryRole(RoleBean query);

}
