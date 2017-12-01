package com.company.userOrder.service;

import java.util.Date;
import java.util.List;

import com.company.userOrder.domain.UserOrder;
import com.company.userOrder.domain.QueryUserOrderRequest;

import com.xinwei.nnl.common.domain.ProcessResult;

public interface UserOrderDbService {
	
	/**
	 * 按照用户查询所有用户
	 * @param queryUserOrderRequest
	 * @return
	 */
	public ProcessResult selOrdersByUser(QueryUserOrderRequest queryUserOrderRequest);
	/**
	 * 按照用户的状态查询订单信息
	 * @param queryUserOrderRequest
	 * @return
	 */
	public ProcessResult selOrderByUserStatus(QueryUserOrderRequest queryUserOrderRequest);
	/**
	 * 
	 * @param userOrder
	 * @return
	 */
	public ProcessResult configureUserOrder(UserOrder userOrder);

	/**
	 * 
	 * @param userOrder
	 * @return
	 */
	public ProcessResult delUserOrder(UserOrder userOrder);

	/**
	 * 
	 * @param userOrder
	 * @return
	 */
	public ProcessResult updateUserOrderStatus(UserOrder userOrder);
}
