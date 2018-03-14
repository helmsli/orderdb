package com.xinwei.orderDb.domain;

public class StepJumpDef {
	//单值
	static public final int Category_Single = 0;
	//区间
	static  public final int Category_Range = 1;
	//离散数值
	static 	public final int Category_discrete = 2;
	
	static public final int Notify_need = 1;
	
	
	private int category;
	
	private int startResult;
	
	private int endResult;
	
	//下一步
	private String nextStep;
	//notify
	private int isNotify=0;
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getStartResult() {
		return startResult;
	}
	public void setStartResult(int startResult) {
		this.startResult = startResult;
	}
	public int getEndResult() {
		return endResult;
	}
	public void setEndResult(int endResult) {
		this.endResult = endResult;
	}
	public String getNextStep() {
		return nextStep;
	}
	public void setNextStep(String nextStep) {
		this.nextStep = nextStep;
	}
	public int getIsNotify() {
		return isNotify;
	}
	public void setIsNotify(int isNotify) {
		this.isNotify = isNotify;
	}
	@Override
	public String toString() {
		return "StepJumpDef [category=" + category + ", startResult=" + startResult + ", endResult=" + endResult
				+ ", nextStep=" + nextStep + ", isNotify=" + isNotify + "]";
	}
	
	
}
