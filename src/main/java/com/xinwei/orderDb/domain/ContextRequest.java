package com.xinwei.orderDb.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ContextRequest  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6175100741560152989L;

	private List<String> contextKeys;

	private OrderMain orderMain;

	private Map<String, String> contextDatas;

	public List<String> getContextKeys() {
		return contextKeys;
	}

	public void setContextKeys(List<String> contextKeys) {
		this.contextKeys = contextKeys;
	}

	public Map<String, String> getContextDatas() {
		return contextDatas;
	}

	public void setContextDatas(Map<String, String> contextDatas) {
		this.contextDatas = contextDatas;
	}

	public OrderMain getOrderMain() {
		return orderMain;
	}

	public void setOrderMain(OrderMain orderMain) {
		this.orderMain = orderMain;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
