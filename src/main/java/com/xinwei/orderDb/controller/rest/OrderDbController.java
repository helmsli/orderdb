package com.xinwei.orderDb.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinwei.nnl.common.domain.JsonRequest;
import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.nnl.common.util.JsonUtil;
import com.xinwei.orderDb.Const.OrderDbConst;
import com.xinwei.orderDb.domain.OrderFlow;
import com.xinwei.orderDb.domain.OrderMain;
import com.xinwei.orderDb.domain.OrderMainContext;
import com.xinwei.orderDb.domain.StepJumpingRequest;
import com.xinwei.orderDb.service.OrderService;

@RestController
@RequestMapping("/orderDb")
public class OrderDbController {

	@Autowired
	private OrderService orderService;

	/**
	 * 新增订单
	 * 
	 * @param dbId
	 *            数据库分库id
	 * @param orderId
	 * @param orderMain
	 * @param contextDatas
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{dbId}/{orderId}/addOrderMain")
	public ProcessResult addOrderMain(@PathVariable String dbId, @PathVariable String orderId,
			@RequestBody OrderMainContext orderMainContext) {
		ProcessResult processResult = new ProcessResult();
		try {

			if (orderMainContext.getContextDatas() == null) {
				processResult = orderService.createNewOrder(orderMainContext);
				return processResult;
			}
			Map<String, String> contextDatas = orderMainContext.getContextDatas();
			processResult = orderService.createNewOrder(orderMainContext, contextDatas);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);

		}
		return processResult;
	}

	/**
	 * 
	 * 用于执行步骤过程中，步骤没有发生跳转，仅仅更新步骤运行的结果
	 * 
	 * @param dbId
	 * @param orderId
	 * @param orderFlow
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{dbId}/{orderId}/configOrderFlow")
	public ProcessResult configOrderFlow(@PathVariable String dbId, @PathVariable String orderId,
			@RequestBody OrderFlow orderFlow) {
		ProcessResult processResult = new ProcessResult();
		try {

			processResult = orderService.configOrderFlow(orderFlow);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);

		}
		return processResult;
	}

	/**
	 * 更新订单状态，用于将订单从步骤A跳转到步骤B
	 * 
	 * @param dbId
	 * @param orderId
	 * @param flowJumpingInfo
	 *            -- 第一个 orderflow为老的流程信息，第二个orderflow为新的流程信息。 第一个订单必须填写状态，步骤，流程id，
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{dbId}/{orderId}/stepjumping")
	public ProcessResult stepJumping(@PathVariable String dbId, @PathVariable String orderId,
			@RequestBody StepJumpingRequest stepJumpingRequest) {
		ProcessResult processResult = new ProcessResult();
		try {
			/**
			 * 更新主订单的时间，更新主订单当前步骤和当前流程ID和状态为orderFlow， 将上一步骤订单更新，如果没有也创建。
			 * 将下一步订单更新，如果没有创建。如果上一部有running，需要将其移走
			 * 对于订单步骤表，需要判断当前订单ID和flowID是否存在，如果存在，更新，不存在，插入；
			 */
			OrderFlow preOrderFlow = stepJumpingRequest.getPreOrderFlow();
			OrderFlow nextOrderFlow = stepJumpingRequest.getNextOrderFlow();
			int nextOrderAutoRun = stepJumpingRequest.getNextOrderAutoRun();
			int preOrderAutoRun = stepJumpingRequest.getPreOrderAutoRun();

			processResult = orderService.stepJumping(preOrderFlow, nextOrderFlow, nextOrderAutoRun, orderId,
					preOrderAutoRun);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);

		}
		return processResult;
	}

	/**
	 * 用于挂起，重新启动，
	 * 
	 * @param dbId
	 * @param orderId
	 * @param orderFlow
	 *            --其中仅仅flowID，和状态id起作用。
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{dbId}/{orderId}/updateStepStatus")
	public ProcessResult updateStepStatus(@PathVariable String dbId, @PathVariable String orderId,
			@RequestBody OrderFlow orderFlow) {
		ProcessResult processResult = new ProcessResult();
		try {
			/**
			 * 更新主订单的时间，更新主订单当前步骤和当前流程ID和状态为orderFlow，如果主订单的flowid一样在修改主订单 更新子订单的状态和时间。
			 */

			processResult = orderService.updateStepStatus(orderFlow);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);

		}
		return processResult;
	}

	/**
	 * 更新订单
	 * 
	 * @param dbId
	 * @param orderId
	 * @param orderMain
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{dbId}/{orderId}/updateMainOrder")
	public ProcessResult updateMainOrder(@PathVariable String dbId, @PathVariable String orderId,
			@RequestBody OrderMain orderMain) {
		ProcessResult processResult = new ProcessResult();
		try {
			processResult = orderService.updateMainOrder(orderMain);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);

		}
		return processResult;
	}

	/**
	 * 获取上下文
	 * 
	 * @param dbId
	 * @param orderId
	 * @param contextKeys
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{dbId}/{orderId}/getContextData")
	public ProcessResult getContextData(@PathVariable String dbId, @PathVariable String orderId,
			@RequestBody JsonRequest jsonRequest) {
		ProcessResult processResult = new ProcessResult();
		try {
			String jsonString = jsonRequest.getJsonString();
			Map<String, List<String>> jsonMap = JsonUtil.fromJson(jsonString);
			List<String> contextKeys = jsonMap.get("contextKeys");
			processResult = orderService.getContextData(orderId, contextKeys);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);
		}
		return processResult;
	}

	/**
	 * 新增订单上下文
	 * 
	 * @param dbId
	 * @param orderId
	 * @param contextDatas
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{dbId}/{orderId}/putContextData")
	public ProcessResult putContextData(@PathVariable String dbId, @PathVariable String orderId,
			@RequestBody OrderMainContext orderMainContext) {
		ProcessResult processResult = new ProcessResult();
		try {

			processResult = orderService.putContextData(orderId, orderMainContext.getContextDatas());

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
	 * @param category
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "{dbId}/{orderId}/{category}/{ownerKey}/getOrderDef")
	public ProcessResult getOrderDef(@PathVariable String category) {
		ProcessResult processResult = orderService.getOrderDef(category);

		return processResult;
	}

	/**
	 * 根据分类查询所有的orderStepdef
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "{dbId}/{orderId}/{category}/{ownerKey}/getOrderStepDef")
	public ProcessResult getOrderStepDef(@PathVariable String category) {

		ProcessResult processResult = orderService.getOrderStepDef(category);
		String orderStepDefs = JsonUtil.toJson(processResult.getResponseInfo());
		processResult.setResponseInfo(orderStepDefs);
		return processResult;
	}

}
