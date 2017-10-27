package com.xinwei.userOrders.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.orderDb.Const.OrderDbConst;
import com.xinwei.userOrders.domain.UserOrders;
import com.xinwei.userOrders.mapper.UserOrdersMapper;
import com.xinwei.userOrders.service.UserOrdersService;

@Service
public class UserOrdersServiceImpl implements UserOrdersService {

	@Autowired
	UserOrdersMapper userOrdersMapper;

	@Override
	public ProcessResult selectUserOrdersByStatus(String ownerKey, Date createTime, int currentStatus) {
		// TODO Auto-generated method stub
		ProcessResult processResult = new ProcessResult();

		try {
			UserOrders userOrders = userOrdersMapper.selectUserOrdersByStatus(ownerKey, createTime, currentStatus);
			processResult.setRetCode(OrderDbConst.RESULT_SUCCESS);
			processResult.setResponseInfo(userOrders);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);

		}
		return processResult;
	}

	@Override
	public ProcessResult selectUserOrdersById(String ownerKey, Date createTime, String orderId) {
		// TODO Auto-generated method stub
		ProcessResult processResult = new ProcessResult();

		try {
			UserOrders userOrders = userOrdersMapper.selectUserOrdersById(ownerKey, createTime, orderId);
			processResult.setRetCode(OrderDbConst.RESULT_SUCCESS);
			processResult.setResponseInfo(userOrders);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processResult.setRetCode(OrderDbConst.RESULT_HandleException);

		}
		return processResult;
	}

	@Override
	public ProcessResult updateUserOrdersStatus(int currentStatus, String ownerKey, Date createTime, String orderId) {
		// TODO Auto-generated method stub
		ProcessResult processResult = new ProcessResult();

		try {
			int result = userOrdersMapper.updateUserOrdersStatus(currentStatus, ownerKey, createTime, orderId);
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

	@Override
	public ProcessResult insertUserOrders(UserOrders userOrders) {
		// TODO Auto-generated method stub
		ProcessResult processResult = new ProcessResult();

		try {
			int result = userOrdersMapper.insertUserOrders(userOrders);
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

	@Override
	public ProcessResult deleteUserOrders(String orderId) {
		// TODO Auto-generated method stub
		ProcessResult processResult = new ProcessResult();

		try {
			int result = userOrdersMapper.deleteUserOrders(orderId);
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

}
