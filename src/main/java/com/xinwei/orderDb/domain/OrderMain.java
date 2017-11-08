package com.xinwei.orderDb.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * Model class of order_main.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class OrderMain implements Serializable {

	public static final String Step_start = "__start";
	public static final String Step_end = "__end";

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

	/** 分区ID. */
	private String partitionId;

	/** 订单编号. */
	private String orderId;

	/** 订单类型. */
	private String catetory;

	/** parent_order_id. */
	private String parentOrderId;

	/** 父订单类型. */
	private String parentOrderCategory;

	/** 订单业务关键字. */
	private String ownerKey;

	/** 当前步骤. */
	private String currentStep;

	/** 当前步骤状态. */
	private Integer currentStatus;

	/** 最新更新时间. */
	
	private Date updateTime;

	/** 订单是否结束. */
	private Integer isFinished;

	/** 流程ID. */
	private String flowId;

	/** 创建时间. */
	
	private Date creatTime;

	/**
	 * Constructor.
	 */
	public OrderMain() {
	}
	
	public static String getDbId(String orderId) {
		return orderId.substring(orderId.length() - 4, orderId.length());
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
		if(!StringUtils.isEmpty(orderId)&&orderId.length()>=7)
		{
			this.partitionId = orderId.substring(orderId.length() - 7, orderId.length() - 4);
		}
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
	 * Set the parent_order_id.
	 * 
	 * @param parentOrderId
	 *            parent_order_id
	 */
	public void setParentOrderId(String parentOrderId) {
		this.parentOrderId = parentOrderId;
	}

	/**
	 * Get the parent_order_id.
	 * 
	 * @return parent_order_id
	 */
	public String getParentOrderId() {
		return this.parentOrderId;
	}

	/**
	 * Set the 父订单类型.
	 * 
	 * @param parentOrderCategory
	 *            父订单类型
	 */
	public void setParentOrderCategory(String parentOrderCategory) {
		this.parentOrderCategory = parentOrderCategory;
	}

	/**
	 * Get the 父订单类型.
	 * 
	 * @return 父订单类型
	 */
	public String getParentOrderCategory() {
		return this.parentOrderCategory;
	}

	/**
	 * Set the 订单业务关键字.
	 * 
	 * @param ownerKey
	 *            订单业务关键字
	 */
	public void setOwnerKey(String ownerKey) {
		this.ownerKey = ownerKey;
	}

	/**
	 * Get the 订单业务关键字.
	 * 
	 * @return 订单业务关键字
	 */
	public String getOwnerKey() {
		return this.ownerKey;
	}

	/**
	 * Set the 当前步骤.
	 * 
	 * @param currentStep
	 *            当前步骤
	 */
	public void setCurrentStep(String currentStep) {
		this.currentStep = currentStep;
	}

	/**
	 * Get the 当前步骤.
	 * 
	 * @return 当前步骤
	 */
	public String getCurrentStep() {
		return this.currentStep;
	}

	/**
	 * Set the 当前步骤状态.
	 * 
	 * @param currentStatus
	 *            当前步骤状态
	 */
	public void setCurrentStatus(Integer currentStatus) {
		this.currentStatus = currentStatus;
	}

	/**
	 * Get the 当前步骤状态.
	 * 
	 * @return 当前步骤状态
	 */
	public Integer getCurrentStatus() {
		return this.currentStatus;
	}

	/**
	 * Set the 最新更新时间.
	 * 
	 * @param updateTime
	 *            最新更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * Get the 最新更新时间.
	 * 
	 * @return 最新更新时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * Set the 订单是否结束.
	 * 
	 * @param isFinished
	 *            订单是否结束
	 */
	public void setIsFinished(Integer isFinished) {
		this.isFinished = isFinished;
	}

	/**
	 * Get the 订单是否结束.
	 * 
	 * @return 订单是否结束
	 */
	public Integer getIsFinished() {
		return this.isFinished;
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
	 * Set the 创建时间.
	 * 
	 * @param creatTime
	 *            创建时间
	 */
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	/**
	 * Get the 创建时间.
	 * 
	 * @return 创建时间
	 */
	public Date getCreatTime() {
		return this.creatTime;
	}

	@Override
	public String toString() {
		return "OrderMain [partitionId=" + partitionId + ", orderId=" + orderId + ", catetory=" + catetory
				+ ", parentOrderId=" + parentOrderId + ", parentOrderCategory=" + parentOrderCategory + ", ownerKey="
				+ ownerKey + ", currentStep=" + currentStep + ", currentStatus=" + currentStatus + ", updateTime="
				+ updateTime + ", isFinished=" + isFinished + ", flowId=" + flowId + ", creatTime=" + creatTime + "]";
	}

}
