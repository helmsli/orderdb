package com.xinwei.userOrder.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.orderDb.Const.UserOrderConst;
import com.xinwei.userOrder.domain.QueryUserOrderRequest;
import com.xinwei.userOrder.domain.UserOrder;
import com.xinwei.userOrder.mapper.UserOrderMapper;
import com.xinwei.userOrder.service.UserOrderDbService;

@Service("userOrderDbService")
public class UserOrderDbServiceImpl implements UserOrderDbService {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserOrderMapper userOrderMapper;

	protected ProcessResult getDefaultErrorResult() {
		ProcessResult processResult = new ProcessResult();
		processResult.setRetCode(UserOrderConst.RESULT_FAILURE);
		return processResult;
	}

	
	@Override
	public ProcessResult selOrdersByUserOrderIdAsc(QueryUserOrderRequest queryUserOrderRequest) {
		ProcessResult processResult = getDefaultErrorResult();
		PageHelper.startPage(queryUserOrderRequest.getPageNum(), queryUserOrderRequest.getPageSize());
		List<UserOrder> list =null;
		if(!StringUtils.isEmpty(queryUserOrderRequest.getUserId()))
		{
			list = userOrderMapper.selOrdersByUserOrderIdAsc(queryUserOrderRequest.getCategory(),
				queryUserOrderRequest.getStartCreateTime(), queryUserOrderRequest.getEndCreateTime(),
				queryUserOrderRequest.getUserId());
		}
		
		PageInfo pageInfo = new PageInfo(list);
		processResult.setResponseInfo(pageInfo);
		processResult.setRetCode(UserOrderConst.RESULT_SUCCESS);
		
		return processResult;
	}

	
	@Override
	public ProcessResult selOrdersByUser(QueryUserOrderRequest queryUserOrderRequest) {
		ProcessResult processResult = getDefaultErrorResult();
		PageHelper.startPage(queryUserOrderRequest.getPageNum(), queryUserOrderRequest.getPageSize());
		List<UserOrder> list =null;
		if(!StringUtils.isEmpty(queryUserOrderRequest.getUserId()))
		{
			list = userOrderMapper.selOrdersByUser(queryUserOrderRequest.getCategory(),
				queryUserOrderRequest.getStartCreateTime(), queryUserOrderRequest.getEndCreateTime(),
				queryUserOrderRequest.getUserId());
		}
		else
		{
			list = userOrderMapper.selectAllByCategory(queryUserOrderRequest.getCategory(),
				queryUserOrderRequest.getStartCreateTime(), queryUserOrderRequest.getEndCreateTime());
		}
		PageInfo pageInfo = new PageInfo(list);
		processResult.setResponseInfo(pageInfo);
		processResult.setRetCode(UserOrderConst.RESULT_SUCCESS);
		
		return processResult;
	}

	@Override
	public ProcessResult selOrderByUserStatus(QueryUserOrderRequest queryUserOrderRequest) {
		// TODO Auto-generated method stub
		ProcessResult processResult = getDefaultErrorResult();

		//
		PageHelper.startPage(queryUserOrderRequest.getPageNum(), queryUserOrderRequest.getPageSize());

		List<UserOrder> list = null;
		if(!StringUtils.isEmpty(queryUserOrderRequest.getUserId()))
		{
			list = userOrderMapper.selOrderByUserStatus(queryUserOrderRequest.getCategory(),
				queryUserOrderRequest.getStartCreateTime(), queryUserOrderRequest.getEndCreateTime(),
				queryUserOrderRequest.getUserId(), queryUserOrderRequest.getStatus());
		}
		else
		{
			list = userOrderMapper.selOrderByCategoryAndStatus(queryUserOrderRequest.getCategory(),
				queryUserOrderRequest.getStartCreateTime(), queryUserOrderRequest.getEndCreateTime(),queryUserOrderRequest.getStatus());
		}
		PageInfo pageInfo = new PageInfo(list);
		// Page page = (Page) list;
		// return "PageInfo: " + JSON.toJSONString(pageInfo) + ", Page: " +
		// JSON.toJSONString(page);
		processResult.setResponseInfo(pageInfo);
		processResult.setRetCode(UserOrderConst.RESULT_SUCCESS);
		return processResult;
	}

	@Override
	public ProcessResult configureUserOrder(UserOrder userOrder) {
		ProcessResult processResult = getDefaultErrorResult();
		int rNumbers = userOrderMapper.selectCountById(userOrder);
		if (rNumbers > 0) {
			log.error("rnumber>0:" + userOrder.toString());
			int updateNumbers = userOrderMapper.updateUserOrder(userOrder);
			if (updateNumbers == 1) {
				log.error("rnumber>0:" + updateNumbers);

				processResult.setRetCode(UserOrderConst.RESULT_SUCCESS);
			}
		} else {
			int insertNumber = userOrderMapper.insertUserOrder(userOrder);
			log.error("insertNumber>0:" + insertNumber + userOrder.toString());

			processResult.setRetCode(UserOrderConst.RESULT_SUCCESS);
		}
		return processResult;
	}

	
	@Override
	public ProcessResult delUserOrder(UserOrder userOrder) {
		// TODO Auto-generated method stub
		ProcessResult processResult = getDefaultErrorResult();
		userOrderMapper.delUserOrder(userOrder);
		processResult.setRetCode(UserOrderConst.RESULT_SUCCESS);
		return processResult;
	}

	@Override
	public ProcessResult updateUserOrderStatus(UserOrder userOrder) {
		// TODO Auto-generated method stub
		ProcessResult processResult = getDefaultErrorResult();
		int updateNumbers = userOrderMapper.updateUserOrderStatus(userOrder);
		if (updateNumbers == 1) {
			processResult.setRetCode(UserOrderConst.RESULT_SUCCESS);
		}
		return processResult;

	}

	@Override
	public ProcessResult selByUserOrderId(UserOrder userOrder) {
		// TODO Auto-generated method stub
		ProcessResult processResult = getDefaultErrorResult();

		UserOrder retUserOrder = userOrderMapper.selectUserOrderById(userOrder);
		if (retUserOrder != null) {
			processResult.setResponseInfo(retUserOrder);
			processResult.setRetCode(UserOrderConst.RESULT_SUCCESS);
		} else {
			processResult.setRetCode(UserOrderConst.RESULT_Error_NotFound);
			processResult.setRetMsg("not found record");
		}
		return processResult;
	}

//	@Override
//	public ProcessResult verifyIsAdmin(String userId) {
//		// TODO Auto-generated method stub
//		ProcessResult processResult = getDefaultErrorResult();
//		UserOrder scratchUserOrder = userOrderMapper.selectScratchAgentOrAdmin(userId);
//		
//		if (scratchUserOrder != null && scratchUserOrder.getOrderDataType() != null
//				&& scratchUserOrder.getCategory().equals(OrderDbConst.SCARTCH_CATEGORY_AGENT)) {
//			processResult.setRetCode(UserOrderConst.RESULT_SUCCESS);
//			return processResult;
//		}
//
//		processResult.setRetCode(UserOrderConst.RESULT_Error_NotFound);
//		processResult.setRetMsg("not found record");
//
//		return processResult;
//	}
//
//	@Override
//	public ProcessResult verifyIsAgent(String userId) {
//		// TODO Auto-generated method stub
//		ProcessResult processResult = getDefaultErrorResult();
//		UserOrder scratchUserOrder = userOrderMapper.selectScratchAgentOrAdmin(userId);
//		if (scratchUserOrder != null && scratchUserOrder.getOrderDataType() != null
//				&& scratchUserOrder.getCategory().equals(OrderDbConst.SCARTCH_CATEGORY_ADMIN)) {
//			processResult.setRetCode(UserOrderConst.RESULT_SUCCESS);
//			return processResult;
//		}
//		processResult.setRetCode(UserOrderConst.RESULT_Error_NotFound);
//		processResult.setRetMsg("not found record");
//
//		return processResult;
//	}
	
	

}
