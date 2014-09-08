package com.szreach.mediacenter.auth.role.dao;

import java.util.List;

import com.szreach.mediacenter.auth.role.bean.RoleBean;

public interface RoleDao {
	public void insert(RoleBean bean);
	public RoleBean getByID(String id);
	public void update(RoleBean bean);
	
	public int count();
	
	public List<RoleBean> query(RoleBean query);
	
	public void delete(String id);
}
