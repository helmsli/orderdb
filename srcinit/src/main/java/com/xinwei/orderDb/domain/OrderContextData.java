package com.xinwei.orderDb.domain;

import java.io.Serializable;

/**
 * Model class of order_context__data.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class OrderContextData implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 分区ID. */
	private String partitionId;

	/** 订单编号. */
	private String orderId;

	/** 订单上下文数据关键字. */
	private String dataKey;

	/** 订单步骤ID. */
	private String stepId;

	/** 流程ID. */
	private String flowId;

	/** 订单上下文数据. */
	private String contextData;

	/**
	 * Constructor.
	 */
	public OrderContextData() {
	}

	/**
	 * Set the 分区ID.
	 * 
	 * @param partitionId
	 *            分区ID
	 */
	public void setPartitionId(String partitionId) {
		this.partitionId = partitionId;
	}

	/**
	 * Get the 分区ID.
	 * 
	 * @return 分区ID
	 */
	public String getPartitionId() {
		return this.partitionId;
	}

	/**
	 * Set the 订单编号.
	 * 
	 * @param orderId
	 *            订单编号
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
		this.partitionId = orderId.substring(orderId.length() - 7, orderId.length() - 4);
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
	 * Set the 订单上下文数据关键字.
	 * 
	 * @param dataKey
	 *            订单上下文数据关键字
	 */
	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	/**
	 * Get the 订单上下文数据关键字.
	 * 
	 * @return 订单上下文数据关键字
	 */
	public String getDataKey() {
		return this.dataKey;
	}

	/**
	 * Set the 订单步骤ID.
	 * 
	 * @param stepId
	 *            订单步骤ID
	 */
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	/**
	 * Get the 订单步骤ID.
	 * 
	 * @return 订单步骤ID
	 */
	public String getStepId() {
		return this.stepId;
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

	/**
	 * Set the 订单上下文数据.
	 * 
	 * @param contextData
	 *            订单上下文数据
	 */
	public void setContextData(String contextData) {
		this.contextData = contextData;
	}

	/**
	 * Get the 订单上下文数据.
	 * 
	 * @return 订单上下文数据
	 */
	public String getContextData() {
		return this.contextData;
	}

	@Override
	public String toString() {
		return "OrderContextData [partitionId=" + partitionId + ", orderId=" + orderId + ", dataKey=" + dataKey
				+ ", stepId=" + stepId + ", flowId=" + flowId + ", contextData=" + contextData + "]";
	}

}