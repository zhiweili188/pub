/**
 * Copyright (c) @2014-6-5. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.common.util;

import java.util.UUID;

/**M类包含了一些静态方法，比如获取UUID的方法
 * @Description:
 * @author lizhiwei
 * @Date: 2014-6-5
 * @Version: 1.0
 */
public class M {
	/**
	 * 生成数据的ID
	 * @return
	 */
	public static String getID() {
		return getGUID();
	}
	/**
	 * 随机产生全局唯一GUID标识,
	 * 
	 * @return
	 */
	public static String getGUID() {
		UUID uuid = UUID.randomUUID();
		String guid = uuid.toString().replace("-", "");
		return guid;
	}
}
