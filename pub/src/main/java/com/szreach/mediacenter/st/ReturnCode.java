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
	public static final int ERR_COURSE_QUOTO_FULL = 20001;//课程名额已报满
}
