/**
 * Copyright (c) @2014-6-9. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.course.apply.bean;

import com.szreach.mediacenter.common.base.Persistentable;

/**
 * @Description:培训室预约
 * @author lizhiwei
 * @Date: 2014-6-9
 * @Version: 1.0
 */
public class TrainingRoomOrderBean extends Persistentable {
	private String tid;	
	private String startTime;
	private String endTime;
	private String classRoomId;
	private String courseName;
	private String teacherId;
	private String teacherName;
	private Integer status;//0：待审核 ，1：已审核，2：审核未通过
	private String reason;
	private String courseType;//课程类型ID
	private String secondCourseType;//课程类型ID
	
	private String subjectId;//学科
	private String createUserId;
	private String createTime;
	
	private String classRoomName;
	
	private String equipName;//教具名称
	private Integer quantity;//借用数量
	private String userId;//学生ID
	
	private Integer signFlg;//是否已经签到
	private Integer cntStudent;//上这个培训的学生总数
	
	private Integer canModify = 0;
	
	private String courseId;//课程
	

	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getClassRoomId() {
		return classRoomId;
	}
	public void setClassRoomId(String classRoomId) {
		this.classRoomId = classRoomId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getClassRoomName() {
		return classRoomName;
	}
	public void setClassRoomName(String classRoomName) {
		this.classRoomName = classRoomName;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getSignFlg() {
		return signFlg;
	}
	public void setSignFlg(Integer signFlg) {
		this.signFlg = signFlg;
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
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public Integer getCntStudent() {
		return cntStudent;
	}
	public void setCntStudent(Integer cntStudent) {
		this.cntStudent = cntStudent;
	}
	public String getSecondCourseType() {
		return secondCourseType;
	}
	public void setSecondCourseType(String secondCourseType) {
		this.secondCourseType = secondCourseType;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getCanModify() {
		return canModify;
	}
	public void setCanModify(Integer canModify) {
		this.canModify = canModify;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}	
	
}
