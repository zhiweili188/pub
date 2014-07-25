/**
 * Copyright (c) @2014-6-6. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.common.base;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-6-6
 * @Version: 1.0
 */
public class PageBean {
	private int currPage = 1; // 当前页码
	private int pageSize = 10; // 每页行数
	private int total; // 总行数
	private int pageCnt; // 总页数

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}

}
