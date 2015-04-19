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
public class ReturnObject {

	private Integer code;
	private String message;
	private String returnToUrl;
	
	public String getReturnToUrl() {
		return returnToUrl;
	}
	public void setReturnToUrl(String returnToUrl) {
		this.returnToUrl = returnToUrl;
	}
	/**
	 * @param code
	 * @param message
	 */
	public ReturnObject(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	public ReturnObject(Integer code) {
		this.code = code;
		this.message = null;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
