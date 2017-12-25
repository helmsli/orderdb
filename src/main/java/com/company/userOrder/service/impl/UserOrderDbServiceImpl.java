package com.company.userOrder.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.userOrder.domain.UserOrder;
import com.company.userOrder.domain.QueryUserOrderRequest;
import com.company.userOrder.domain.UserOrder;
import com.company.userOrder.mapper.UserOrderMapper;
import com.company.userOrder.service.UserOrderDbService;
import com.company.userOrderPlatform.domain.UserOrderConst;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinwei.nnl.common.domain.ProcessResult;

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
	public ProcessResult selOrdersByUser(QueryUserOrderRequest queryUserOrderRequest) {
		// TODO Auto-generated method stub
		ProcessResult processResult = getDefaultErrorResult();
		List<UserOrder> list = null;
		PageHelper.startPage(queryUserOrderRequest.getPageNum(), queryUserOrderRequest.getPageSize());
		list = userOrderMapper.selOrdersByUser(queryUserOrderRequest.getCategory(),
				queryUserOrderRequest.getStartCreateTime(), queryUserOrderRequest.getEndCreateTime(),
				queryUserOrderRequest.getUserId());

		PageInfo pageInfo = new PageInfo(list);
		processResult.setResponseInfo(pageInfo);
		processResult.setRetCode(UserOrderConst.RESULT_SUCCESS);
		// Page page = (Page) list;
		// return "PageInfo: " + JSON.toJSONString(pageInfo) + ", Page: " +
		// JSON.toJSONString(page);
		return processResult;
	}

	@Override
	public ProcessResult selOrderByUserStatus(QueryUserOrderRequest queryUserOrderRequest) {
		// TODO Auto-generated method stub
		ProcessResult processResult = getDefaultErrorResult();

		PageHelper.startPage(queryUserOrderRequest.getPageNum(), queryUserOrderRequest.getPageSize());
		List<UserOrder> list = userOrderMapper.selOrderByUserStatus(queryUserOrderRequest.getCategory(),
				queryUserOrderRequest.getStartCreateTime(), queryUserOrderRequest.getEndCreateTime(),
				queryUserOrderRequest.getUserId(), queryUserOrderRequest.getStatus());
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
		// TODO Auto-generated method stub
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
		Calendar createTime = Calendar.getInstance();
		createTime.setTime(userOrder.getCreateTime());
		;
		UserOrder retUserOrder = null;
		if (createTime.get(Calendar.YEAR) == 1900) {
			createTime = Calendar.getInstance();
			createTime.add(Calendar.DAY_OF_MONTH, -40);
			userOrder.setCreateTime(createTime.getTime());
			 List<UserOrder> retUserOrders= userOrderMapper.selOrderByIdWithoutTime(userOrder);
			 if(retUserOrders!=null && retUserOrders.size()!=0)
			 {
				 retUserOrder=retUserOrders.get(0);
			 }
		} else {
			retUserOrder = userOrderMapper.selectUserOrderById(userOrder);

		}

		if (retUserOrder != null) {
			processResult.setResponseInfo(retUserOrder);
			processResult.setRetCode(UserOrderConst.RESULT_SUCCESS);
		} else {
			processResult.setRetCode(UserOrderConst.RESULT_Error_NotFound);
			processResult.setRetMsg("not found record");
		}
		return processResult;
	}

}
