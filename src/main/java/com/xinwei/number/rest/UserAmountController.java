package com.xinwei.number.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.number.domain.StatCounter;
import com.xinwei.number.service.UserAmountService;
import com.xinwei.userOrder.controller.rest.ControllerUtils;


@RestController
@RequestMapping("/userStatCounter")
public class UserAmountController {
	private static final Logger logger = LoggerFactory.getLogger(UserAmountController.class);

	@Autowired
	private UserAmountService userAmountService;

	/**
	 * 插入用户计数器
	 * retCode = 0     成功
	 * retCode = -1  失败
	 * @param userId
	 * @param amountId
	 * @return
	 */
	@PostMapping("{category}/initToZero")
	public ProcessResult initToZero(@PathVariable("category") String category, @RequestBody StatCounter statCounter) {
		ProcessResult result = new ProcessResult();
		try {
			
			boolean insert = userAmountService.insert(statCounter.getUserId(), statCounter.getAmountId());
			if (!insert) {
				result.setRetCode(-1);
			} else {
				result.setRetCode(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ControllerUtils.getFromResponse(e, -1, result);
		}
	
		return result;
	}

	/**
	 * 获取用户计数器的值
	 * retCode = 0     成功
	 * retCode = -1  失败
	 * @param userId
	 * @param amountId
	 * @return
	 */
	@GetMapping("{category}/{userId}/{amountId}/getAmount")
	public ProcessResult getAmount(@PathVariable("category") String category,@PathVariable("userId") String userId, @PathVariable("amountId") String amountId) {
		ProcessResult result = new ProcessResult();
		try {
			long amount = userAmountService.getAmount(userId, amountId);
			
			result.setRetCode(0);			
			result.setResponseInfo(String.valueOf(amount));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ControllerUtils.getFromResponse(e, -1, result);
		}
		
		return result;
	}

	/**
	 * 用户计数器统计一次
	 * retCode = 0     成功
	 * retCode = -1  失败
	 * @param userId
	 * @param amountId
	 * @param ownerKey
	 * @return
	 */
	@PostMapping("{category}/plusOne")
	public ProcessResult plusOne(@PathVariable("category") String category,@RequestBody StatCounter statCounter) {
		ProcessResult result = new ProcessResult();
		try {
			int ret = userAmountService.addOne(statCounter.getUserId(), statCounter.getAmountId(), statCounter.getOwnerKey());
			result.setRetCode(ret);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ControllerUtils.getFromResponse(e, UserAmountService.Error_Exception, result);
		}
	
		return result;
	}

	/**
	 * 用户计数器统计addtAmount次
	 * retCode = 0     成功
	 * retCode = -1  失败
	 * @param userId
	 * @param amountId
	 * @param addtAmount
	 * @param ownerKey
	 * @return
	 */
	@PostMapping("{category}/plusGreaterOne")
	public ProcessResult plusGreaterOne(@PathVariable("category") String category,@RequestBody StatCounter statCounter) {
		ProcessResult result=null;
		try {
			result = userAmountService.add(statCounter.getUserId(), statCounter.getAmountId(), statCounter.getAmount(), statCounter.getOwnerKey());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ControllerUtils.getFromResponse(e, -1, result);
		}
		
		return result;
	}

	/**
	 * 用户计数器统计 减少1次
	 * retCode = 0     成功
	 * retCode = -1  失败
	 * @param userId
	 * @param amountId
	 * @param ownerKey
	 * @return
	 */
	@PostMapping("{category}/substractOne")
	public ProcessResult substractOne(@PathVariable("category") String category,@RequestBody StatCounter statCounter) {
		ProcessResult result = new ProcessResult();
		try {
			boolean ret = userAmountService.substractOne(statCounter.getUserId(), statCounter.getAmountId(), statCounter.getOwnerKey());
			if (!ret) {
				result.setRetCode(-1);
			} else {
				result.setRetCode(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ControllerUtils.getFromResponse(e, -1, result);
		}
		logger.info("substractOne end...result=" + result);
		return result;
	}

	/**
	 * 用户计数器统计减少substractAmount次
	 * retCode = 0     成功
	 * retCode = -1  失败
	 * @param userId
	 * @param amountId
	 * @param substractAmount
	 * @param ownerKey
	 * @return
	 */
	@PostMapping("{category}/substractGreaterOne")
	public ProcessResult substract(@PathVariable("category") String category,@RequestBody StatCounter statCounter) {
		ProcessResult result=null;
		try {
			result = userAmountService.substract(statCounter.getUserId(), statCounter.getAmountId(), statCounter.getAmount(), statCounter.getOwnerKey());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ControllerUtils.getFromResponse(e, -1, result);
		}
		
		return result;
	}
}
