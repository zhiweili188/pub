/**
 * Copyright (c) @2015-4-22. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.st;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-22
 * @Version: 1.0
 */
public enum Message {
	/**可通用*/
	SUCCESS(ReturnCode.SUCCESS,"m.success"),
	ERROR(ReturnCode.ERROR,"m.error"),
	

	/**登录 10xxx*/
	ERR_USERNAME_PASSW(ReturnCode.ERR_USERNAME_PASSW,"m.username_pwd_error"),
	
	/**课程申请 20xxx*/
	COURSE_APPLY_SUCCESS(ReturnCode.COURSE_APPLY_SUCCESS,"m.course_apply_success"),
	ERR_COURSE_QUOTO_FULL(ReturnCode.ERR_COURSE_QUOTO_FULL, "m.course_quoto_full"),
	
	/**用户注册 30xxx*/
	USER_REGISTER_SUCCESS(ReturnCode.USER_REGISTER_SUCCESS,"m.user_register_success"),
	ERR_USER_NAME_EXIST(ReturnCode.ERR_USER_NAME_EXIST,"m.user_name_exist"),
	ERR_USER_IDCARD_EXIST(ReturnCode.ERR_USER_IDCARD_EXIST,"m.user_idcard_exist"),
	ERR_USER_EAMIL_EXIST(ReturnCode.ERR_USER_EAMIL_EXIST,"m.user_email_exist"),
	USER_MODIFY_SUCCESS(ReturnCode.USER_MODIFY_SUCCESS,"m.user_modify_success"),
	USER_MODIFY_PASSWD_SUCCESS(ReturnCode.USER_MODIFY_PASSWD_SUCCESS,"m.user_modify_passwd_success"),
	ERR_OLD_PASSWD_WRONG(ReturnCode.ERR_OLD_PASSWD_WRONG,"m.old_passwd_wrong"),
	;
	public static void main(String[] args) {
		

		Message[] m = Message.values();
		for(Message me : m) {
			System.out.println(me);
		}
	}
	
	private static Map<Integer, String> messageMap = new HashMap<Integer, String>();
	static {
		Message[] m = Message.values();
		for(Message me : m) {
			messageMap.put(me.getId(), me.getMsgKey());
		}
	}
	
	public static String getById(Integer id) {
		return messageMap.get(id);
	}
	
	private int id;
	private String msgKey;
	/**
	 * @param id
	 * @param name
	 */
	private Message(int id, String msgKey) {
		this.id = id;
		this.msgKey = msgKey;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getMsgKey() {
		return msgKey;
	}
	/**
	 * @param name the name to set
	 */
	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}
	@Override
	public String toString() {
		return this.getId()+":"+this.getMsgKey();
	}
	
}
