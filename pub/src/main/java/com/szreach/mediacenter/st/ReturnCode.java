/**
 * Copyright (c) @2015-3-31. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.st;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-31
 * @Version: 1.0
 */
public interface ReturnCode {
	/**可通用*/
	public static final int SUCCESS = 0;
	public static final int ERROR = 1;
	/**登录 10xxx*/
	public static final int ERR_USERNAME_PASSW = 10001;//用户名或密码错误
	/**课程申请 20xxx*/
	public static final int COURSE_APPLY_SUCCESS = 20000;//报名成功
	public static final int ERR_COURSE_QUOTO_FULL = 20001;//课程名额已报满
	
	/**用户注册 30xxx*/
	public static final int USER_REGISTER_SUCCESS = 30000;//注册成功
	public static final int ERR_USER_NAME_EXIST = 30001;//用户名存在
	public static final int ERR_USER_IDCARD_EXIST = 30002;//身份证已注册
	public static final int ERR_USER_EAMIL_EXIST = 30003;//邮箱已注册
	public static final int USER_MODIFY_SUCCESS = 30004;//修改注册信息成功
	public static final int USER_MODIFY_PASSWD_SUCCESS = 30005;//修改密码成功
	public static final int ERR_OLD_PASSWD_WRONG = 30006;//原密码不正确
}
