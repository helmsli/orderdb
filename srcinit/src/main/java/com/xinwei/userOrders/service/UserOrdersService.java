package com.xinwei.userOrders.service;

import java.util.Date;

import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.userOrders.domain.UserOrders;

/**
 * @author herui
 *
 */
public interface UserOrdersService {

	/**
	 * 根据状态查询userOrders
	 * @param ownerKey
	 * @param createTime
	 * @param currentStatus
	 * @return
	 * 
	 */
	ProcessResult selectUserOrdersByStatus(String ownerKey, Date createTime, int currentStatus);

	/**
	 * 根据orderId查询userOrders
	 * @param ownerKey
	 * @param createTime
	 * @param orderId
	 * @return
	 */
	ProcessResult selectUserOrdersById(String ownerKey, Date createTime, String orderId);

	/**
	 * 根据orderId更新userOrders的状态
	 * @param currentStatus
	 * @param ownerKey
	 * @param createTime
	 * @param orderId
	 * @return
	 */
	ProcessResult updateUserOrdersStatus(int currentStatus, String ownerKey, Date createTime, String orderId);

	/**
	 * 插入一条userOrders
	 * @param userOrders
	 * @return
	 */
	ProcessResult insertUserOrders(UserOrders userOrders);

	/**	删除一条userOrders
	 * @param orderId
	 * @return
	 */
	ProcessResult deleteUserOrders(String orderId);
}
