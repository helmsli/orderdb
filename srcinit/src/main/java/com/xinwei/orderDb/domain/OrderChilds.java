package com.xinwei.orderDb.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Model class of order_childs.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class OrderChilds implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 订单类型. */
	private String catetory;

	/** 订单编号. */
	private String orderId;

	/** 子订单类型. */
	private String childCategory;

	/** 子订单ID. */
	private String childOrderid;

	/** 创建时间. */
	private Timestamp createTime;

	/**
	 * Constructor.
	 */
	public OrderChilds() {
	}

	/**
	 * Set the 订单类型.
	 * 
	 * @param catetory
	 *            订单类型
	 */
	public void setCatetory(String catetory) {
		this.catetory = catetory;
	}

	/**
	 * Get the 订单类型.
	 * 
	 * @return 订单类型
	 */
	public String getCatetory() {
		return this.catetory;
	}

	/**
	 * Set the 订单编号.
	 * 
	 * @param orderId
	 *            订单编号
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * Get the 订单编号.
	 * 
	 * @return 订单编号
	 */
	public String getOrderId() {
		return this.orderId;
	}

	/**
	 * Set the 子订单类型.
	 * 
	 * @param childCategory
	 *            子订单类型
	 */
	public void setChildCategory(String childCategory) {
		this.childCategory = childCategory;
	}

	/**
	 * Get the 子订单类型.
	 * 
	 * @return 子订单类型
	 */
	public String getChildCategory() {
		return this.childCategory;
	}

	/**
	 * Set the 子订单ID.
	 * 
	 * @param childOrderid
	 *            子订单ID
	 */
	public void setChildOrderid(String childOrderid) {
		this.childOrderid = childOrderid;
	}

	/**
	 * Get the 子订单ID.
	 * 
	 * @return 子订单ID
	 */
	public String getChildOrderid() {
		return this.childOrderid;
	}

	/**
	 * Set the 创建时间.
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * Get the 创建时间.
	 * 
	 * @return 创建时间
	 */
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	@Override
	public String toString() {
		return "OrderChilds [catetory=" + catetory + ", orderId=" + orderId + ", childCategory=" + childCategory
				+ ", childOrderid=" + childOrderid + ", createTime=" + createTime + "]";
	}

}
