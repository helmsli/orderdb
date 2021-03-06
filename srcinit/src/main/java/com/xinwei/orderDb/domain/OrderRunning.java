package com.xinwei.orderDb.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Model class of order_running.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class OrderRunning implements Serializable {

	// 订单初始化状态
	public static final int STATUS_initial = 0;

	// 订单运行状态
	public static final int STATUS_running = 1;

	// 订单挂起状态
	public static final int STATUS_suspend = 2;

	// 订单终止状态（不继续执行）
	public static final int STATUS_terminate = 3;

	// 订单正常结束状态
	public static final int STATUS_ending = 4;

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 订单类型. */
	private String catetory;

	/** 当前分区id. */
	private String partitionId;

	/** 订单编号. */
	private String orderId;

	/** 创建时间. */
	private Timestamp createTime;

	/** 流程ID. */
	private String flowId;

	/**
	 * Constructor.
	 */
	public OrderRunning() {
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

	public String getPartitionId() {
		return partitionId;
	}

	public void setPartitionId(String partitionId) {
		this.partitionId = partitionId;
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

	/**
	 * Set the 流程ID.
	 * 
	 * @param flowId
	 *            流程ID
	 */
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	/**
	 * Get the 流程ID.
	 * 
	 * @return 流程ID
	 */
	public String getFlowId() {
		return this.flowId;
	}

	@Override
	public String toString() {
		return "OrderRunning [catetory=" + catetory + ", partitionId=" + partitionId + ", orderId=" + orderId
				+ ", createTime=" + createTime + ", flowId=" + flowId + "]";
	}

}
