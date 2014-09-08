package com.szreach.mediacenter.auth.role.bean;

import com.szreach.mediacenter.common.base.PageBean;

public class RoleBean extends PageBean {
	private String id;
	private String roleName;
	private String createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
