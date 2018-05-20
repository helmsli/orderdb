package com.xinwei.number.service;

import com.xinwei.nnl.common.domain.ProcessResult;

public interface UserAmountService {
	/**
	 * 插入计数记录
	 * @param userId
	 * @param amountId
	 * @return
	 */
	public boolean insert(String userId, String amountId);

	/**
	 * 获取用户的计数值
	 * @param userId
	 * @param amountId
	 * @return
	 */
	public long getAmount(String userId, String amountId);

	/**
	 * 给用户计数值+1
	 * @param userId
	 * @param amountId
	 * @return
	 */
	public boolean addOne(String userId, String amountId, String ownerKey);

	/**
	 * 给用户计数值+addAmount
	 * @param userId
	 * @param amountId
	 * @param addAmount
	 * @return
	 */
	public ProcessResult add(String userId, String amountId, long addAmount, String ownerKey);

	/**
	 * 给用户计数值-1
	 * @param userId
	 * @param amountId
	 * @return
	 */
	public boolean substractOne(String userId, String amountId, String ownerKey);

	/**
	 * 给用户计数值-substractAmount
	 * @param userId
	 * @param amountId
	 * @param substractAmount
	 * @return
	 */
	public ProcessResult substract(String userId, String amountId, long substractAmount, String ownerKey);
}
