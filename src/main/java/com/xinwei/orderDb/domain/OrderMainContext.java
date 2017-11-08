package com.xinwei.orderDb.domain;

import java.util.Map;

public class OrderMainContext extends OrderMain {
	private static final long serialVersionUID = -2486359166067173166L;

	/**
	 * 保存map的json
	 */
	private String contextDatasJson;

	/**
	 * 保存map对象
	 */
	private Map<String, String> contextDatas;

	public String getContextDatasJson() {
		return contextDatasJson;
	}

	public void setContextDatasJson(String contextDatasJson) {
		this.contextDatasJson = contextDatasJson;
	}

	public Map<String, String> getContextDatas() {
		return contextDatas;
	}

	public void setContextDatas(Map<String, String> contextDatas) {
		this.contextDatas = contextDatas;
	}

	@Override
	public String toString() {
		return "OrderMainContext [contextDatasJson=" + contextDatasJson + ", contextDatas=" + contextDatas + "]" + super.toString();
	}

	
}
