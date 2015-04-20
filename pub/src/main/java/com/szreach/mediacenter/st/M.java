package com.szreach.mediacenter.st;

import java.util.Locale;

public interface M {
	public static final Locale LOCALE = Locale.SIMPLIFIED_CHINESE;
	
	public static final String USER_REGISTER_SUCCESS= "m.user_register_success";//用户注册成功
	public static final String USER_NAME_EXIST= "m.user_name_exist";//用户名存在
	public static final String USER_IDCARD_EXIST= "m.user_idcard_exist";//身份证存在
	public static final String USER_EAMIL_EXIST= "m.user_email_exist";//邮箱存在
	/**课程申请*/
	public static final String COURSE_APPLY_SUCCESS= "m.course_apply_success";//课程报名成功
	public static final String COURSE_QUOTO_FULL= "m.course_quoto_full";//课程名额已报满
}
