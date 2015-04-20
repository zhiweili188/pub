/**
 * Copyright (c) @2015-4-20. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.course.apply.bean;

import com.szreach.mediacenter.common.base.Persistentable;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-20
 * @Version: 1.0
 */
public class UserCourseApply extends Persistentable {

	private String userId;
	private String courseId;
	private String applyTime;
	private Integer status;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
