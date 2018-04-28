package com.xinwei.userOrder.service;

import java.text.ParseException;

import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.userOrder.domain.QueryUserOrderRequest;
import com.xinwei.userOrder.domain.UserOrder;

public interface UserOrderDbService {
	
	/**
	 * 按照用户查询所有用户
	 * @param queryUserOrderRequest
	 * @return
	 */
	public ProcessResult selOrdersByUser(QueryUserOrderRequest queryUserOrderRequest);
	
	
	/**
	 * 按照用户查询所有用户
	 * @param queryUserOrderRequest
	 * @return
	 */
	public ProcessResult selOrdersByUserOrderIdAsc(QueryUserOrderRequest queryUserOrderRequest);
	
	
	/**
	 * 按照userid,orderId查询订单信息
	 * @param queryUserOrderRequest
	 * @return
	 */
	public ProcessResult selByUserOrderId(UserOrder userOrder);
	
	
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
	 * @throws ParseException 
	 */
	public ProcessResult updateUserOrderStatus(UserOrder userOrder);
	
//	/**
//	 * @param userId
//	 * @return
//	 * 跟距userId判定管理员或者代理
//	 */
//	public ProcessResult verifyIsAdmin(String userId);
//	
//
//	/**
//	 * @param userId
//	 * @return
//	 * 跟距userId判定管理员或者代理
//	 */
//	public ProcessResult verifyIsAgent(String userId);
}
