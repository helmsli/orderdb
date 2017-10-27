package com.xinwei.orderDb.domain;

import java.io.Serializable;

public class StepRunningDef implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1703899844270222005L;
	// 保存任务类型对应的任务信息，比如定时任务，需要保存定时任务执行的信息；立即执行任务，可以配置优先级，延迟时间等信息;是否需要后台
	/**
	 * 是否定时执行
	 */
	private int isScheduler;
	/**
	 * 定时执行表达式
	 */
	private String cronExpress;
	/**
	 * 是否需要在后台自动执行
	 */
	private int isRunningInback;

	public int getIsScheduler() {
		return isScheduler;
	}

	public void setIsScheduler(int isScheduler) {
		this.isScheduler = isScheduler;
	}

	public String getCronExpress() {
		return cronExpress;
	}

	public void setCronExpress(String cronExpress) {
		this.cronExpress = cronExpress;
	}

	public int getIsRunningInback() {
		return isRunningInback;
	}

	public void setIsRunningInback(int isRunningInback) {
		this.isRunningInback = isRunningInback;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "StepRunningDef [isScheduler=" + isScheduler + ", cronExpress=" + cronExpress + ", isRunningInback="
				+ isRunningInback + "]";
	}

}
