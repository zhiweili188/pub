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
	
	  // 起始记录index
    private int beginIndex;
    // 末尾记录index
    private int endIndex;

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
		
		pageCnt = this.total  / pageSize ;
		int diff = this.total % this.pageSize;
		if(diff > 0) {
			this.pageCnt += 1;
		}
        if (this.currPage > pageCnt) {
        	this.currPage = pageCnt;
        }
        if (this.currPage < 1) {
        	this.currPage = 1;
        }
        beginIndex = (this.currPage - 1) * this.pageSize;
        endIndex = beginIndex + this.pageSize - 1;
        if (endIndex > this.total ) {
            endIndex = this.total ;
        }
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}
