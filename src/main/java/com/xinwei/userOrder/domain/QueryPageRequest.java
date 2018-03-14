package com.xinwei.userOrder.domain;

import java.io.Serializable;

public class QueryPageRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9141778837342759847L;
	private int pageNum;
	private int pageSize;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "QueryPageRequest [pageNum=" + pageNum + ", pageSize=" + pageSize + "]";
	}

}
