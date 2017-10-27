package com.xinwei.orderDb.service;

import java.util.List;
import java.util.Map;

import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.orderDb.domain.OrderFlow;
import com.xinwei.orderDb.domain.OrderMain;

public interface OrderService {
	/**
	 * 创建一个不带上下文的订单
	 * 
	 * @param orderMain
	 * @return
	 */
	public ProcessResult createNewOrder(OrderMain orderMain);

	/**
	 * 创建一个带上下文信息的订单 在orderMain中增加一条记录，同时在order_flow中增加一条记录，记录创建时间
	 * 
	 * @param orderMain
	 * @param contextDatas
	 * @return
	 */
	public ProcessResult createNewOrder(OrderMain orderMain, Map<String, String> contextDatas);

	/**
	 * 修改订单
	 * 
	 * @param orderid
	 * @return
	 */
	public ProcessResult updateMainOrder(OrderMain orderMain);

	/**
	 * 修改主订单的状态
	 * 
	 * @param orderId
	 * @param step
	 * @param status
	 * @return
	 */
	public ProcessResult updateMainOrderStatus(String orderId, int status, String step);

	/**
	 * 
	 * 
	 * @param orderFlow
	 * @return
	 */
	public ProcessResult configOrderFlow(OrderFlow orderFlow);

	/**
	 * 
	 * @param orderId
	 * @param contextKey
	 * @return -- 返回Map
	 */

	public ProcessResult getContextData(String orderId, List<String> contextKey);

	/**
	 * 
	 * @param contextDatas
	 * @return
	 */
	public ProcessResult putContextData(String orderId, Map<String, String> contextDatas);

	/**
	 * 
	 * 第一个 orderflow为老的流程信息，第二个orderflow为新的流程信息。 第一个订单必须填写状态，步骤，流程id， /**
	 * 更新主订单的时间，更新主订单当前步骤和当前流程ID和状态为orderFlow， 将上一步骤订单更新，如果没有也创建。
	 * 将下一步订单更新，如果没有创建。如果上一部有running，需要将其移走
	 * 对于订单步骤表，需要判断当前订单ID和flowID是否存在，如果存在，更新，不存在，插入；
	 * 
	 * @param preOrderFlow
	 * @param nextOrderFlow
	 * @param nextOrderAutoRun
	 * @return
	 */
	public ProcessResult stepJumping(OrderFlow preOrderFlow, OrderFlow nextOrderFlow, int nextOrderAutoRun,
			String orderId, int preOrderAutoRun);

	/**
	 * 
	 * 
	 * 更新主订单的时间，更新主订单当前步骤和当前流程ID和状态为orderFlow，如果主订单的flowid一样在修改主订单 更新子订单的状态和时间。
	 * 
	 * 
	 * @param orderId
	 * @param orderFlow
	 * @return
	 */
	public ProcessResult updateStepStatus(OrderFlow orderFlow);
	
	/**
	 * 根据分类查询单条orderDef
	 * @param catetory
	 * @return
	 */
	public ProcessResult getOrderDef(String catetory);
//	
	/**
	 * 根据分类查询所有的orderStepdef
	 * @param catetory
	 * @return
	 */
	public ProcessResult getOrderStepDef(String catetory);
}
