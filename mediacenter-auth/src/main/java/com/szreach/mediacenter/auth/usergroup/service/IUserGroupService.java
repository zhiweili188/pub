package com.szreach.mediacenter.auth.usergroup.service;

import java.util.List;

import com.szreach.mediacenter.auth.usergroup.bean.UserGroupBean;

public interface IUserGroupService {
	public int count();
	public void insertUserGroup(UserGroupBean bean);
	public void updateUserGroup(UserGroupBean bean);
	public void delete(String id);
	public UserGroupBean getUserGroupByID(String userGroupId);
	
	public List<UserGroupBean> queryUserGroup(UserGroupBean query);
}
