package com.xinwei.orderDb.service;

import com.xinwei.nnl.common.domain.JsonRequest;
import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.orderDb.domain.OrderFlow;
import com.xinwei.orderDb.domain.OrderMain;
import com.xinwei.orderDb.domain.OrderMainContext;
import com.xinwei.userOrders.domain.UserOrders;

public interface OrderDbService {

	/**
	 * url:http://127.0.0.1:8088/orderDb/{dbId}/{orderId}/addOrderMain 
	 * 新增ordermain，
	 * 同步到user_orders 若上下文map不为空，同步到orderFlow
	 * 
	 * @param url
	 * @param orderMain
	 * @param contextDatas
	 * @return
	 */
	public ProcessResult insertNewOrderMain(String url, OrderMainContext orderMainContext);

	/**
	 * url:http://127.0.0.1:8088/orderDb/{dbId}/{orderId}/configOrderFlow
	 * 用于执行步骤过程中，步骤没有发生跳转，仅仅更新步骤运行的结果
	 * 
	 * @param url
	 * @param orderFlow
	 * @return
	 */
	public ProcessResult updateOrderFlowStatus(String url, OrderFlow orderFlow);

	/**
	 * 更新订单状态，用于将订单从步骤A跳转到步骤B
	 * @param url  http://127.0.0.1:8088/orderDb/{dbId}/{orderId}/stepjumping
	 * @param nextorderFlow
	 * @param preOrderFlow
	 * @param nextOrderAutoRun
	 * @param preOrderAutoRun
	 * @return
	 */
	public ProcessResult updateOrderFlowStepJumping(String url, OrderFlow nextOrderFlow, OrderFlow preOrderFlow,
			int nextOrderAutoRun, int preOrderAutoRun);

	/**
	 * 用于挂起，重新启动，同步主订单更新时间
	 * @param url http://127.0.0.1:8088/orderDb/{dbId}/{orderId}/updateStepStatus
	 * @param orderFlow
	 * @return
	 */
	public ProcessResult suspendOrRestartOrderFlow(String url, OrderFlow orderFlow);

	/**
	 * 更新主订单
	 * @param url http://127.0.0.1:8088/orderDb/{dbId}/{orderId}/updateMainOrder
	 * @param orderMain
	 * @return
	 */
	public ProcessResult updateOrderMain(String url, OrderMain orderMain);

	/**
	 * 获取订单上下文信息
	 * @param url  http://127.0.0.1:8088/orderDb/{dbId}/{orderId}/getContextData
	 * @param contextKeys
	 * @return
	 */
	public ProcessResult getOrderContextData(String url, JsonRequest jsonRequest);

	/**
	 * 新增订单上下文信息
	 * @param url http://127.0.0.1:8088/orderDb/{dbId}/{orderId}/putContextData
	 * @param contextDatas
	 * @return
	 */
	public ProcessResult putOrderContextData(String url, OrderMainContext orderMainContext);

	/**
	 * 根据分类查询单条orderDef
	 * @param url http://127.0.0.1:8088/orderDb/{dbId}/{orderId}/{category}/{ownerKey}/getOrderDef
	 * @return
	 */
	public ProcessResult selectOrderDefByCategory(String url);

	/**
	 * 根据分类查询所有的orderStepdef
	 * @param url http://127.0.0.1:8088/orderDb/{dbId}/{orderId}/{category}/{ownerKey}/getOrderStepDef
	 * @return
	 */
	public ProcessResult selectOrderStepDefsByCategory(String url);
	
	
	/**
	 * 根据orderId查询userOrders
	 * @param url http://127.0.0.1:8088/userOrders/{dbId}/{orderId}/selectUserOrdersById
	 * @param userOrders
	 * @return
	 */
	public ProcessResult selectUserOrdersByOrderId(String url, UserOrders userOrders);
	
	/**
	 * 根据状态查询userOrders
	 * @param url http://127.0.0.1:8088/userOrders/{dbId}/{orderId}/selectUserOrdersByStatus
	 * @param userOrders
	 * @return
	 */
	public ProcessResult selectUserOrdersByOrderStatus(String url, UserOrders userOrders);
	
	/**
	 * 根据orderId更新userOrders的状态
	 * @param url http://127.0.0.1:8088/userOrders/{dbId}/{orderId}/updateUserOrdersStatus
	 * @param userOrders
	 * @return
	 */
	public ProcessResult updateUserOrdersStatus(String url, UserOrders userOrders);
	
	/**
	 * 插入一条userOrders
	 * @param url http://127.0.0.1:8088/userOrders/{dbId}/{orderId}/insertUserOrders
	 * @param userOrders
	 * @return
	 */
	public ProcessResult insertUserOrders(String url, UserOrders userOrders);
	
	/**
	 * 删除一条userOrders
	 * @param url http://127.0.0.1:8088/userOrders/{dbId}/{orderId}/deleteUserOrders
	 * @return
	 */
	public ProcessResult deleteUserOrders(String url);
}
