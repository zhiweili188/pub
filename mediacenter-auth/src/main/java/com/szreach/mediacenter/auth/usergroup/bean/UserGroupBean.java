package com.szreach.mediacenter.auth.usergroup.bean;

import com.szreach.mediacenter.common.base.PageBean;

public class UserGroupBean extends PageBean {
	private String id;
	private String userGroupName;
	private String createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserGroupName() {
		return userGroupName;
	}
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
