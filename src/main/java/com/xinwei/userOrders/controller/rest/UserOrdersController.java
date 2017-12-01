package com.xinwei.userOrders.controller.rest;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.userOrders.domain.UserOrders;
import com.xinwei.userOrders.service.UserOrdersService;

@RestController
@RequestMapping("/userOrders")
public class UserOrdersController {

	@Autowired
	UserOrdersService userOrdersService;

	@RequestMapping(method = RequestMethod.POST, value = "{dbId}/{orderId}/selectUserOrdersById")
	public ProcessResult selectUserOrdersById(@RequestBody UserOrders userOrders) {
		ProcessResult processResult = userOrdersService.selectUserOrdersById(userOrders.getOwnerKey(),
				userOrders.getCreateTime(), userOrders.getOrderId());

		return processResult;
	}

	@RequestMapping(method = RequestMethod.POST, value = "{dbId}/{orderId}/selectUserOrdersByStatus")
	public ProcessResult selectUserOrdersByStatus(@RequestBody UserOrders userOrders) {
		ProcessResult processResult = userOrdersService.selectUserOrdersByStatus(userOrders.getOwnerKey(),
				userOrders.getCreateTime(), userOrders.getCurrentStatus());

		return processResult;
	}

	@RequestMapping(method = RequestMethod.POST, value = "{dbId}/{orderId}/insertUserOrders")
	public ProcessResult insertUserOrders(@RequestBody UserOrders userOrders) {
		ProcessResult processResult = userOrdersService.insertUserOrders(userOrders);

		return processResult;
	}

	@RequestMapping(method = RequestMethod.POST, value = "{dbId}/{orderId}/updateUserOrdersStatus")
	public ProcessResult updateUserOrdersStatus(@RequestBody UserOrders userOrders) {
		ProcessResult processResult = userOrdersService.updateUserOrdersStatus(userOrders.getCurrentStatus(),
				userOrders.getOwnerKey(), userOrders.getCreateTime(), userOrders.getOrderId());

		return processResult;
	}

	@RequestMapping(method = RequestMethod.POST, value = "{dbId}/{orderId}/deleteUserOrders")
	public ProcessResult deleteUserOrders(@PathVariable String orderId) {
		ProcessResult processResult = userOrdersService.deleteUserOrders(orderId);

		return processResult;
	}
	protected void saveExceptionToResult(ProcessResult processResult,Exception e)
	{
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		String errorStr = errors.toString();
		if(!StringUtils.isEmpty(errorStr))
		{
			processResult.setRetMsg(errorStr.substring(0,1000));
		}
		return ;
	}

}
