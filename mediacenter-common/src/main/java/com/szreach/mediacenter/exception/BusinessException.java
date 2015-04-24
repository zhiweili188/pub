/**
 * Copyright (c) @2014-12-29. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.exception;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-12-29
 * @Version: 1.0
 */
public class BusinessException extends RuntimeException {

	public BusinessException() {
		
	}
	public BusinessException(String code, String msg) {
		super(msg);
	}
}
