package com.xinwei.userOrder.controller.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.nnl.common.util.JsonUtil;
import com.xinwei.orderDb.Const.UserOrderConst;
import com.xinwei.userOrder.domain.QueryUserOrderRequest;
import com.xinwei.userOrder.domain.UserOrder;
import com.xinwei.userOrder.service.UserOrderDbService;


@RestController
@RequestMapping("/userOrderDb")
public class UserOrderController {

	@Resource(name = "userOrderDbService")
	private UserOrderDbService userOrderDbService;

	/**
	 * 从数据库查询用户一段时间内所有状态的订单
	 * 
	 * @param category
	 * @param userid
	 * @param queryUserOrderRequest
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{category}/{userid}/queryUserOrder")
	public ProcessResult queryUserAllOrder(@PathVariable String category, @PathVariable String userid,
			@RequestBody QueryUserOrderRequest queryUserOrderRequest) {
		ProcessResult processResult = new ProcessResult();
		processResult.setRetCode(UserOrderConst.RESULT_FAILURE);
		try {
			queryUserOrderRequest.setCategory(category);
			if (queryUserOrderRequest.getStatus() == QueryUserOrderRequest.STATUS_NULL) {
				processResult = userOrderDbService.selOrdersByUser(queryUserOrderRequest);
			} else {
				processResult = userOrderDbService.selOrderByUserStatus(queryUserOrderRequest);

			}

//			 toJsonSimpleProcessResult(processResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ControllerUtils.getFromResponse(e, UserOrderConst.RESULT_FAILURE, null);

		}
		return processResult;
	}
	/**
	 * 查询没有时间变化的固定的数据
	 * @param category
	 * @param userid
	 * @param queryUserOrderRequest
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{category}/{userid}/queryUserOrderConst")
	public ProcessResult queryUserAllOrderConst(@PathVariable String category, @PathVariable String userid,
			@RequestBody QueryUserOrderRequest queryUserOrderRequest) {
		ProcessResult processResult = new ProcessResult();
		processResult.setRetCode(UserOrderConst.RESULT_FAILURE);
		try {
			queryUserOrderRequest.setEndCreateTime(UserOrder.getConstCreateDate());
			queryUserOrderRequest.setStartCreateTime(UserOrder.getConstCreateDate());
			queryUserOrderRequest.setCategory(category);
			if (queryUserOrderRequest.getStatus() == QueryUserOrderRequest.STATUS_NULL) {
				processResult = userOrderDbService.selOrdersByUser(queryUserOrderRequest);
			} else {
				processResult = userOrderDbService.selOrderByUserStatus(queryUserOrderRequest);

			}

//			 toJsonSimpleProcessResult(processResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ControllerUtils.getFromResponse(e, UserOrderConst.RESULT_FAILURE, null);

		}
		return processResult;
	}

	
	/**
	 * 从数据库查询用户一段时间内所有状态的订单
	 * 
	 * @param category
	 * @param userid
	 * @param queryUserOrderRequest
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{category}/{userid}/queryUserOrderidAsc")
	public ProcessResult queryUserAllOrderIdAsc(@PathVariable String category, @PathVariable String userid,
			@RequestBody QueryUserOrderRequest queryUserOrderRequest) {
		ProcessResult processResult = new ProcessResult();
		processResult.setRetCode(UserOrderConst.RESULT_FAILURE);
		try {
			queryUserOrderRequest.setCategory(category);
			if (queryUserOrderRequest.getStatus() == QueryUserOrderRequest.STATUS_NULL) {
				processResult = userOrderDbService.selOrdersByUserOrderIdAsc(queryUserOrderRequest);
			}
//			 toJsonSimpleProcessResult(processResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ControllerUtils.getFromResponse(e, UserOrderConst.RESULT_FAILURE, null);

		}
		return processResult;
	}
	
	/**
	 * 按照状态从数据库查询用户一段时间内的订单
	 * 
	 * @param category
	 * @param userid
	 * @param queryUserOrderRequest
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{category}/{userid}/queryUserOrderByStatus")
	public ProcessResult queryUserOrderByStatus(@PathVariable String category, @PathVariable String userid,
			@RequestBody QueryUserOrderRequest queryUserOrderRequest) {
		ProcessResult processResult = new ProcessResult();
		processResult.setRetCode(UserOrderConst.RESULT_FAILURE);
		try {
			queryUserOrderRequest.setCategory(category);
			processResult = userOrderDbService.selOrderByUserStatus(queryUserOrderRequest);
			// toJsonSimpleProcessResult(processResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ControllerUtils.getFromResponse(e, UserOrderConst.RESULT_FAILURE, null);

		}
		return processResult;
	}
	
	
	/**
	 * 配置用户订单，没有插入，有更新
	 * 
	 * @param category
	 * @param userid
	 * @param userOrder
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{category}/{userid}/configUserOrder")
	public ProcessResult configUserOrder(@PathVariable String category, @PathVariable String userid,
			@RequestBody UserOrder userOrder) {
		ProcessResult processResult = new ProcessResult();
		processResult.setRetCode(UserOrderConst.RESULT_FAILURE);
		try {
			processResult = userOrderDbService.configureUserOrder(userOrder);
			toJsonSimpleProcessResult(processResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ControllerUtils.getFromResponse(e, UserOrderConst.RESULT_FAILURE, null);
		}
		return processResult;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "{category}/{userid}/configUserOrderConst")
	public ProcessResult configUserOrderConst(@PathVariable String category, @PathVariable String userid,
			@RequestBody UserOrder userOrder) {
		ProcessResult processResult = new ProcessResult();
		processResult.setRetCode(UserOrderConst.RESULT_FAILURE);
		try {
			userOrder.setConstCreateTime();
			processResult = userOrderDbService.configureUserOrder(userOrder);
			toJsonSimpleProcessResult(processResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ControllerUtils.getFromResponse(e, UserOrderConst.RESULT_FAILURE, null);
		}
		return processResult;
	}
	/**
	 * 更新用户订单状态
	 * 
	 * @param category
	 * @param userid
	 * @param userOrder
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{category}/{userid}/updateUserOrderStatus")
	public ProcessResult updateUserOrderStatus(@PathVariable String category, @PathVariable String userid,
			@RequestBody UserOrder userOrder) {
		ProcessResult processResult = new ProcessResult();
		processResult.setRetCode(UserOrderConst.RESULT_FAILURE);
		try {
			processResult = userOrderDbService.updateUserOrderStatus(userOrder);
			toJsonSimpleProcessResult(processResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ControllerUtils.getFromResponse(e, UserOrderConst.RESULT_FAILURE, null);

		}
		return processResult;
	}

	/**
	 * 更新用户订单状态
	 * 
	 * @param category
	 * @param userid
	 * @param userOrder
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{category}/{userid}/updateUserOrderStatusConst")
	public ProcessResult updateUserOrderStatusConst(@PathVariable String category, @PathVariable String userid,
			@RequestBody UserOrder userOrder) {
		ProcessResult processResult = new ProcessResult();
		processResult.setRetCode(UserOrderConst.RESULT_FAILURE);
		try {
			userOrder.setConstCreateTime();
			processResult = userOrderDbService.updateUserOrderStatus(userOrder);
			toJsonSimpleProcessResult(processResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ControllerUtils.getFromResponse(e, UserOrderConst.RESULT_FAILURE, null);

		}
		return processResult;
	}

	/**
	 * 删除用户订单
	 * 
	 * @param category
	 * @param userid
	 * @param userOrder
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "{category}/{userid}/delUserOrder")
	public ProcessResult delUserOrder(@PathVariable String category, @PathVariable String userid,
			@RequestBody UserOrder userOrder) {
		ProcessResult processResult = new ProcessResult();
		processResult.setRetCode(UserOrderConst.RESULT_FAILURE);
		try {
			processResult = userOrderDbService.delUserOrder(userOrder);
			toJsonSimpleProcessResult(processResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ControllerUtils.getFromResponse(e, UserOrderConst.RESULT_FAILURE, null);

		}
		return processResult;
	}

	@RequestMapping(method = RequestMethod.POST, value = "{category}/{userid}/delUserOrderConst")
	public ProcessResult delUserOrderConst(@PathVariable String category, @PathVariable String userid,
			@RequestBody UserOrder userOrder) {
		ProcessResult processResult = new ProcessResult();
		processResult.setRetCode(UserOrderConst.RESULT_FAILURE);
		try {
			userOrder.setConstCreateTime();
			processResult = userOrderDbService.delUserOrder(userOrder);
			toJsonSimpleProcessResult(processResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ControllerUtils.getFromResponse(e, UserOrderConst.RESULT_FAILURE, null);

		}
		return processResult;
	}
	@RequestMapping(method = RequestMethod.POST, value = "{category}/{userid}/queryOneOrder")
	public ProcessResult queryOneUserOrder(@PathVariable String category, @PathVariable String userid,
			@RequestBody UserOrder userOrder) {
		ProcessResult processResult = new ProcessResult();
		processResult.setRetCode(UserOrderConst.RESULT_FAILURE);
		try {
			processResult = userOrderDbService.selByUserOrderId(userOrder);
			toJsonSimpleProcessResult(processResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ControllerUtils.getFromResponse(e, UserOrderConst.RESULT_FAILURE, null);

		}
		return processResult;
	}

	@RequestMapping(method = RequestMethod.POST, value = "{category}/{userid}/queryOneOrderConst")
	public ProcessResult queryOneUserOrderConst(@PathVariable String category, @PathVariable String userid,
			@RequestBody UserOrder userOrder) {
		ProcessResult processResult = new ProcessResult();
		processResult.setRetCode(UserOrderConst.RESULT_FAILURE);
		try {
			userOrder.setConstCreateTime();
			processResult = userOrderDbService.selByUserOrderId(userOrder);
			toJsonSimpleProcessResult(processResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ControllerUtils.getFromResponse(e, UserOrderConst.RESULT_FAILURE, null);

		}
		return processResult;
	}


	protected void toJsonSimpleProcessResult(ProcessResult processResult) {
		Object object = processResult.getResponseInfo();
		if (object != null) {
			String jsonStr = JsonUtil.toJson(object);
			processResult.setResponseInfo(jsonStr);
		}
	}
}
