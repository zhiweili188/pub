package com.szreach.mediacenter.auth.usergroup.dao;

import java.util.List;

import com.szreach.mediacenter.auth.usergroup.bean.UserGroupBean;

public interface UserGroupDao {
	public void insert(UserGroupBean bean);
	public UserGroupBean getByID(String id);
	public void update(UserGroupBean bean);
	
	public int count();
	
	public List<UserGroupBean> query(UserGroupBean query);
	
	public void delete(String id);
}
