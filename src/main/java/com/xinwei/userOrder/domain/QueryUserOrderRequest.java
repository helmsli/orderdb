package com.xinwei.userOrder.domain;

import java.util.Calendar;
import java.util.Date;

public class QueryUserOrderRequest extends QueryPageRequest {
	public static final int STATUS_NULL = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1578752993197351755L;
	private Date startCreateTime;
	private Date endCreateTime = Calendar.getInstance().getTime();
	private String userId;
	private String category;
	private int status = STATUS_NULL;

	public Date getStartCreateTime() {
		return startCreateTime;
	}
	

	public void setStartCreateTime(Date startCreateTime) {
		this.startCreateTime = formatDate(startCreateTime);

	}

	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = formatDate(endCreateTime);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userid) {
		this.userId = userid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	protected Date formatDate(Date sourceDate) {
		if (sourceDate != null) {
			Calendar calenCreate = Calendar.getInstance();
			calenCreate.setTime(sourceDate);
			calenCreate.set(Calendar.MILLISECOND, 0);
			return calenCreate.getTime();
		}
		return sourceDate;
	}

	@Override
	public String toString() {
		return "QueryUserOrderRequest [startCreateTime=" + startCreateTime + ", endCreateTime=" + endCreateTime
				+ ", userId=" + userId + ", category=" + category + ", status=" + status + "]";
	}

}
