/**
 * Copyright (c) @2015-4-14. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.course.apply.bean;

import java.util.List;

import com.szreach.mediacenter.common.base.Persistentable;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-14
 * @Version: 1.0
 */
public class Course extends Persistentable {
	private String tid;
	private String courseName;
	private String courseTypeId;
	private String courseTypeName;
	private String courseMethod;
	private String courseAddress;
	private String hostEntity;
	private String contactPerson;
	private String contactTel;
	private String applyStartTime;
	private String applyEndTime;
	private int applyQuota;
	private int remainApplyQuota;
	private int applyMethod;
	private String createUserId;
	private String createTime;
	
	private List<TrainingRoomOrderBean> detail;
	
	private boolean open = false;//是否在报名时间内
	private boolean applied = false;//是否已报名

	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseTypeId() {
		return courseTypeId;
	}
	public void setCourseTypeId(String courseTypeId) {
		this.courseTypeId = courseTypeId;
	}
	public String getCourseTypeName() {
		return courseTypeName;
	}
	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}
	public String getCourseMethod() {
		return courseMethod;
	}
	public void setCourseMethod(String courseMethod) {
		this.courseMethod = courseMethod;
	}
	public String getCourseAddress() {
		return courseAddress;
	}
	public void setCourseAddress(String courseAddress) {
		this.courseAddress = courseAddress;
	}
	public String getHostEntity() {
		return hostEntity;
	}
	public void setHostEntity(String hostEntity) {
		this.hostEntity = hostEntity;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getApplyStartTime() {
		return applyStartTime;
	}
	public void setApplyStartTime(String applyStartTime) {
		this.applyStartTime = applyStartTime;
	}
	public String getApplyEndTime() {
		return applyEndTime;
	}
	public void setApplyEndTime(String applyEndTime) {
		this.applyEndTime = applyEndTime;
	}
	public int getApplyQuota() {
		return applyQuota;
	}
	public void setApplyQuota(int applyQuota) {
		this.applyQuota = applyQuota;
	}
	public int getApplyMethod() {
		return applyMethod;
	}
	public void setApplyMethod(int applyMethod) {
		this.applyMethod = applyMethod;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public List<TrainingRoomOrderBean> getDetail() {
		return detail;
	}
	public void setDetail(List<TrainingRoomOrderBean> detail) {
		this.detail = detail;
	}
	public int getRemainApplyQuota() {
		return remainApplyQuota;
	}
	public void setRemainApplyQuota(int remainApplyQuota) {
		this.remainApplyQuota = remainApplyQuota;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isApplied() {
		return applied;
	}
	public void setApplied(boolean applied) {
		this.applied = applied;
	}
	
}
