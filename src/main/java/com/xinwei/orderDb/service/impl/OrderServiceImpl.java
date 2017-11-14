package com.xinwei.orderDb.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.nnl.common.util.JsonUtil;
import com.xinwei.orderDb.Const.OrderDbConst;
import com.xinwei.orderDb.domain.OrderContextData;
import com.xinwei.orderDb.domain.OrderFlow;
import com.xinwei.orderDb.domain.OrderFlowDef;
import com.xinwei.orderDb.domain.OrderFlowStepdef;
import com.xinwei.orderDb.domain.OrderMain;
import com.xinwei.orderDb.mapper.OrderContextDataMapper;
import com.xinwei.orderDb.mapper.OrderFlowDefMapper;
import com.xinwei.orderDb.mapper.OrderFlowMapper;
import com.xinwei.orderDb.mapper.OrderFlowStepdefMapper;
import com.xinwei.orderDb.mapper.OrderMainMapper;
import com.xinwei.orderDb.service.OrderService;
import com.xinwei.userOrders.domain.UserOrders;
import com.xinwei.userOrders.mapper.UserOrdersMapper;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMainMapper orderMainMapper;

	@Autowired
	private OrderFlowMapper orderFlowMapper;

	@Autowired
	private OrderContextDataMapper orderContextDataMapper;

	@Autowired
	private OrderFlowDefMapper orderFlowDefMapper;

	@Autowired
	private OrderFlowStepdefMapper orderFlowStepdefMapper;

	@Autowired
	private UserOrdersMapper userOrdersMapper;

	/**
	 * 创建一个不带上下文的订单
	 * 
	 * @param orderMain
	 * @return
	 */
	@Override
	@Transactional
	public ProcessResult createNewOrder(OrderMain orderMain) {
		// TODO Auto-generated method stub
		ProcessResult processResult = new ProcessResult();
		/*
		UserOrders userOrders = new UserOrders();
		userOrders.setCatetory(orderMain.getCatetory());
		userOrders.setCreateTime(new Date());
		userOrders.setCurrentStatus(orderMain.getCurrentStatus());
		userOrders.setOrderId(orderMain.getOrderId());
		userOrders.setOwnerKey(orderMain.getOwnerKey());
		userOrdersMapper.insertUserOrders(userOrders);
		 */
		//selectCountOrderMain
		int result=0;
		int record = orderMainMapper.selectCountOrderMain(orderMain.getOrderId(), orderMain.getPartitionId());
		if(record==0)
		{
			Date date = new Date(System.currentTimeMillis());
			orderMain.setCreatTime(date);
			orderMain.setIsFinished(0);
			 result = orderMainMapper.insert(orderMain);
		}
		else
		{
			Date date = new Date(System.currentTimeMillis());
			orderMain.setUpdateTime(Calendar.getInstance().getTime());
			result = orderMainMapper.update(orderMain);
		}
		if (result != 1) {
			processResult.setRetCode(OrderDbConst.RESULT_Error_DbError);
			return processResult;
		}

		processResult.setRetCode(OrderDbConst.RESULT_SUCCESS);

		return processResult;
	}

	/**
	 * 创建控制主订单的事物，确保成功全成功
	 * 
	 * @param orderMain
	 * @param orderFlows
	 * @return
	 */
	@Transactional
	public ProcessResult insertMainFlow(OrderMain orderMain, Map<String,String> contextDatas) {
		ProcessResult processResult = new ProcessResult();
		Date date = new Date(System.currentTimeMillis());

		int records = orderMainMapper.selectCountOrderMain(orderMain.getOrderId(), orderMain.getPartitionId());
		if(records==0)
		{
			orderMain.setCreatTime(date);
			orderMain.setUpdateTime(date);
			orderMainMapper.insert(orderMain);
		}
		else
		{
			orderMain.setUpdateTime(date);
			orderMainMapper.update(orderMain);
		}
		/*
		UserOrders userOrders = new UserOrders();
		userOrders.setCatetory(orderMain.getCatetory());
		userOrders.setCreateTime(date);
		userOrders.setCurrentStatus(orderMain.getCurrentStatus());
		userOrders.setOrderId(orderMain.getOrderId());
		userOrders.setOwnerKey(orderMain.getOwnerKey());
		userOrdersMapper.insertUserOrders(userOrders);
		 */
		int result1 = 0;
		int result2 = 0;

		// if (result == 1) {
		if (contextDatas != null) {
			this.putContextData(orderMain.getOrderId(), contextDatas);
		}
		// }

		return processResult;

	}

	/**
	 * 创建一个带上下文信息的订单
	 * 
	 * @param orderMain
	 * @param contextDatas
	 * @return
	 */
	@Override
	public ProcessResult createNewOrder(OrderMain orderMain, Map<String, String> contextDatas) {
		// TODO Auto-generated method stub

		/**
		 * 1.由调用者决定orderID不能重复；在调用之前加锁处理 2.在主流程表中插入数据， 3.根据步骤初始化数据，start对应的数据；
		 * 4.保存数据到start步骤中
		 * 
		 */
		ProcessResult processResult = new ProcessResult();
		Date date = new Date();
		orderMain.setCreatTime(date);
		orderMain.setIsFinished(0);
		try {
			

				processResult = insertMainFlow(orderMain, contextDatas);
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);

		}
		return processResult;
	}

	/**
	 * 修改订单
	 * 
	 * @param orderid
	 * @return
	 */
	@Override
	public ProcessResult updateMainOrder(OrderMain orderMain) {
		// TODO Auto-generated method stub
		ProcessResult processResult = new ProcessResult();

		try {
			
			int result = orderMainMapper.update(orderMain);
			//更新失败
			if (result != 1) {
				if (result == 0) {
					// 如果查询不存在，执行插入
					OrderMain queryOrderMain = orderMainMapper.selectOrderMain(orderMain.getOrderId(),
							orderMain.getPartitionId());
					if (queryOrderMain == null) {
						orderMain.setCreatTime(Calendar.getInstance().getTime());
						result = orderMainMapper.insert(orderMain);	
					}
				}
				if (result != 1) {
					processResult.setRetCode(OrderDbConst.RESULT_Error_DbError);
					return processResult;
				}
			}

			processResult.setRetCode(OrderDbConst.RESULT_SUCCESS);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);

		}
		return processResult;
	}

	/**
	 * 修改主订单的状态
	 * 
	 * 
	 * @return
	 */
	@Override
	public ProcessResult updateMainOrderStatus(String orderId, int status, String step) {
		// TODO Auto-generated method stub
		ProcessResult processResult = new ProcessResult();

		try {

			int result = orderMainMapper.updateMainOrderStatus(orderId, status, step);

			if (result != 1) {
				processResult.setRetCode(OrderDbConst.RESULT_Error_DbError);
				return processResult;
			}

			processResult.setRetCode(OrderDbConst.RESULT_SUCCESS);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);

		}
		return processResult;
	}

	/**
	 * 
	 * 
	 * @param orderFlow
	 * @return
	 */
	@Override
	public ProcessResult configOrderFlow(OrderFlow orderFlow) {
		// TODO Auto-generated method stub
		ProcessResult processResult = new ProcessResult();

		try {

			int result = orderFlowMapper.update(orderFlow);

			if (result != 1) {
				processResult.setRetCode(OrderDbConst.RESULT_Error_DbError);
				return processResult;
			}

			processResult.setRetCode(OrderDbConst.RESULT_SUCCESS);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);

		}
		return processResult;
	}

	/**
	 * 
	 * @param orderId
	 * @param contextKey
	 * @return -- 返回Map
	 */
	@Override
	public ProcessResult getContextData(String orderId, List<String> contextKeys) {
		// TODO Auto-generated method stub

		Map<String, String> resultMap = new HashMap<>();
		ProcessResult processResult = new ProcessResult();

		try {

			for (String contextKey : contextKeys) {
				OrderContextData orderContextData = orderContextDataMapper.selectByOrderIdAndDataKey(orderId,
						contextKey.trim());
				resultMap.put(contextKey, orderContextData.getContextData());

			}
			if (resultMap.size() == 0) {
				processResult.setRetCode(OrderDbConst.RESULT_Error_DbError);
				return processResult;
			}
			processResult.setResponseInfo(resultMap);
			processResult.setRetCode(OrderDbConst.RESULT_SUCCESS);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);
		}

		return processResult;
	}

	/**
	 * 
	 * @param contextDatas
	 * @return
	 */
	@Override
	// 事物控制
	public ProcessResult putContextData(String orderId, Map<String, String> contextDatas) {
		// TODO Auto-generated method stub
		ProcessResult processResult = new ProcessResult();
		int result1 = 0;
		int result2 = 0;
		try {
			long currentTime = System.currentTimeMillis();
			for (String dataKey : contextDatas.keySet()) {
				OrderContextData orderContextData = new OrderContextData();
				orderContextData.setContextData((String) contextDatas.get(dataKey));
				orderContextData.setDataKey(dataKey);
				orderContextData.setFlowId("0");
				orderContextData.setOrderId(orderId);
				orderContextData.setStepId("0");
				int records = orderContextDataMapper.selectCount(orderId, dataKey);
				if(records==0)
				{
					result1 = orderContextDataMapper.insert(orderContextData);
				}
				else
				{
					result1=orderContextDataMapper.updateOrderContextData(orderContextData);
				}
				if (result1 == 1) {
					result2++;
				}
			}
			if (result2 == contextDatas.keySet().size()) {

				processResult.setRetCode(OrderDbConst.RESULT_SUCCESS);
				return processResult;
			}

			processResult.setRetCode(OrderDbConst.RESULT_Error_DbError);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);
		}

		return processResult;
	}

	/**
	 * /**
	 * 
	 * 第一个 orderflow为老的流程信息，第二个orderflow为新的流程信息。 第一个订单必须填写状态，步骤，流程id， /**
	 * 更新主订单的时间，更新主订单当前步骤和当前流程ID和状态为orderFlow， 将上一步骤订单更新，如果没有也创建。
	 * 将下一步订单更新，如果没有创建。如果上一部有running，需要将其移走
	 * 对于订单步骤表，需要判断当前订单ID和flowID是否存在，如果存在，更新，不存在，插入；
	 */
	@Override
	@Transactional
	public ProcessResult stepJumping(OrderFlow preOrderFlow, OrderFlow nextOrderFlow, int nextOrderAutoRun,
			String orderId, int preOrderAutoRun) {
		ProcessResult processResult = new ProcessResult();
		Date date = Calendar.getInstance().getTime();
		preOrderFlow.setUpdateTime(date);
		nextOrderFlow.setUpdateTime(date);
		/*
		 * 先去更新，判断更新结果
		 */
		OrderMain orderMain = orderMainMapper.selectOrderMain(orderId, preOrderFlow.getPartitionId());
		orderMain.setUpdateTime(date);
		orderMain.setFlowId(nextOrderFlow.getFlowId());
		orderMain.setCurrentStatus(nextOrderFlow.getCurrentStatus());
		int result = orderMainMapper.update(orderMain);
		if (result != 1) {

			processResult.setRetCode(OrderDbConst.RESULT_Error_DbError);
			return processResult;
		}
		// 如果是第一步
		// 直接插入nextFlowhou 返回
		if (nextOrderFlow.getStepId().compareToIgnoreCase(OrderMain.Step_start) == 0) {
			nextOrderFlow.setUpdateTime(date);
			nextOrderFlow.setCreateTime(date);
			orderFlowMapper.insert(nextOrderFlow);
			processResult.setRetCode(OrderDbConst.RESULT_SUCCESS);

			return processResult;
		}
		preOrderFlow.setUpdateTime(Calendar.getInstance().getTime());
		int preOrderFlowResult = orderFlowMapper.updateStatus(preOrderFlow);
		int nextOrderFlowResult = orderFlowMapper.update(nextOrderFlow);
		if (preOrderFlowResult != 1) {
			preOrderFlow.setUpdateTime(date);
			preOrderFlow.setCreateTime(date);
			
			orderFlowMapper.insert(preOrderFlow);
		}
		if (nextOrderFlowResult != 1) {
			nextOrderFlow.setCreateTime(date);
			nextOrderFlow.setUpdateTime(date);
			orderFlowMapper.insert(nextOrderFlow);
		}

		if (preOrderFlow.getCurrentStatus()==(OrderFlow.STATUS_running)) {
			orderFlowMapper.deleteOrderFlow(preOrderFlow.getOrderId(), preOrderFlow.getPartitionId(),
					preOrderFlow.getStepId(), preOrderFlow.getFlowId());
		}
		processResult.setRetCode(OrderDbConst.RESULT_SUCCESS);

		return processResult;
	}

	@Override
	public ProcessResult updateStepStatus(OrderFlow orderFlow) {
		ProcessResult processResult = new ProcessResult();
		Date date = new Date(System.currentTimeMillis());
		try {
			orderFlow.setUpdateTime(date);
			int result = orderFlowMapper.updateStatus(orderFlow);
			if (result != 1) {
				processResult.setRetCode(OrderDbConst.RESULT_Error_DbError);
				return processResult;
			}
			processResult.setRetCode(OrderDbConst.RESULT_SUCCESS);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);
		}

		return processResult;
	}

	/**
	 * 根据分类查询单条orderDef
	 * 
	 * @param catetory
	 * @return
	 */
	@Override
	public ProcessResult getOrderDef(String catetory) {
		// TODO Auto-generated method stub
		ProcessResult processResult = new ProcessResult();
		try {
			OrderFlowDef orderFlowDef = orderFlowDefMapper.selectOrderFlowDefByCategory(catetory);
			if (orderFlowDef == null) {
				processResult.setRetCode(OrderDbConst.RESULT_Error_DbError);
				return processResult;
			}
			processResult.setRetCode(OrderDbConst.RESULT_SUCCESS);
			processResult.setResponseInfo(orderFlowDef);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);
		}
		return processResult;
	}

	/**
	 * 根据分类查询所有的orderStepdef
	 * 
	 * @param catetory
	 * @return
	 */
	@Override
	public ProcessResult getOrderStepDef(String catetory) {
		// TODO Auto-generated method stub
		ProcessResult processResult = new ProcessResult();
		try {
			List<OrderFlowStepdef> orderFlowStepdefs = orderFlowStepdefMapper.selectOrderFlowStepdefs(catetory);
			if (orderFlowStepdefs.size() == 0) {
				processResult.setRetCode(OrderDbConst.RESULT_Error_DbError);
				return processResult;
			}
			processResult.setRetCode(OrderDbConst.RESULT_SUCCESS);
			processResult.setResponseInfo(orderFlowStepdefs);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);
		}
		return processResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xinwei.orderDb.service.OrderService#getOrderMainFromDb(java.lang.String)
	 * 根據orderId查詢orderMain
	 */
	@Override
	public ProcessResult getOrderMainFromDb(String orderId) {
		// TODO Auto-generated method stub
		ProcessResult processResult = new ProcessResult();
		try {
			String partitionId = orderId.substring(orderId.length() - 7, orderId.length() - 4);
			OrderMain orderMain = orderMainMapper.selectOrderMain(orderId, partitionId);
			if (orderMain == null) {
				processResult.setRetCode(OrderDbConst.RESULT_Error_DbError);
				return processResult;
			}
			processResult.setRetCode(OrderDbConst.RESULT_SUCCESS);
			processResult.setResponseInfo(orderMain);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);
		}
		return processResult;
	}

	@Override
	public ProcessResult selectOrderFlow(String orderId, String partitonId, String stepId, String flowId) {
		// TODO Auto-generated method stub
		ProcessResult processResult = new ProcessResult();
		try {
			String partitionId = OrderMain.getDbId(orderId);

			OrderFlow orderFlow = orderFlowMapper.selectOrderFlow(orderId, partitionId, stepId, flowId);
			if (orderFlow == null) {
				processResult.setRetCode(OrderDbConst.RESULT_Error_DbError);
				return processResult;
			}
			processResult.setRetCode(OrderDbConst.RESULT_SUCCESS);
			processResult.setResponseInfo(orderFlow);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);
		}
		return processResult;
	}

}
