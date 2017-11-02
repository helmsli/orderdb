package com.xinwei.orderDb.domain;

import java.io.Serializable;

public class StepJumpingRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 926549509560885912L;
	private OrderFlow preOrderFlow;
	private OrderFlow nextOrderFlow;

	private int preOrderAutoRun;
	/**
	 * 指明是否需要后台自动运行 nextOrderFlow 0no 1yes
	 */
	private int nextOrderAutoRun;

	public OrderFlow getPreOrderFlow() {
		return preOrderFlow;
	}

	public void setPreOrderFlow(OrderFlow preOrderFlow) {
		this.preOrderFlow = preOrderFlow;
	}

	public OrderFlow getNextOrderFlow() {
		return nextOrderFlow;
	}

	public void setNextOrderFlow(OrderFlow nextOrderFlow) {
		this.nextOrderFlow = nextOrderFlow;
	}

	public int getPreOrderAutoRun() {
		return preOrderAutoRun;
	}

	public void setPreOrderAutoRun(int preOrderAutoRun) {
		this.preOrderAutoRun = preOrderAutoRun;
	}

	public int getNextOrderAutoRun() {
		return nextOrderAutoRun;
	}

	public void setNextOrderAutoRun(int nextOrderAutoRun) {
		this.nextOrderAutoRun = nextOrderAutoRun;
	}

	@Override
	public String toString() {
		return "StepJumpingRequest [preOrderFlow=" + preOrderFlow + ", nextOrderFlow=" + nextOrderFlow
				+ ", preOrderAutoRun=" + preOrderAutoRun + ", nextOrderAutoRun=" + nextOrderAutoRun + "]";
	}

}
