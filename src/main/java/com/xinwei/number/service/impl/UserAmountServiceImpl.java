package com.xinwei.number.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.number.mapper.UserAmountLogMapper;
import com.xinwei.number.mapper.UserAmountMapper;
import com.xinwei.number.service.UserAmountService;
import com.xinwei.number.util.RepeatControlUtil;


@Service("userAmountService")
public class UserAmountServiceImpl implements UserAmountService {
	private static final Logger logger = LoggerFactory.getLogger(UserAmountServiceImpl.class);

	@Autowired
	private UserAmountMapper userAmountMapper;

	@Autowired
	private UserAmountLogMapper userAmountLogMapper;

	@Override
	public boolean insert(String userId, String amountId) {
		int ret = userAmountMapper.insert(userId, amountId);
		logger.info("insert result=" + ret);
		return ret > 0;
	}

	@Override
	public long getAmount(String userId, String amountId) {
		long amount = userAmountMapper.selectAmount(userId, amountId);
		logger.info("get amount, amount=" + amount);
		return amount;
	}

	@Override
	@Transactional
	public boolean addOne(String userId, String amountId, String ownerKey) {

		/**
		 * useramountLog 首先向Log表中插入记录，userID，AmountID，key，如果成功了，addOne，如果失败，查询log表中是否存在，如果已经存在，返回成功
		 */
		int ret = 0;
		// 用户计数统计key
		String numberKey = RepeatControlUtil.getNumberKey(userId, amountId, ownerKey);
		try {
			//String numberkey = getNumberkey(userId, amountId, ownerKey);
			// number 已经被使用, 不能再用
			if (!RepeatControlUtil.canAddCount(numberKey)) {
				logger.info("can't operate again...");
				return true;
			}
			ret = userAmountLogMapper.insert(userId, amountId, ownerKey);
			ret = userAmountMapper.addOne(userId, amountId);
			if(ret==0)
			{
				userAmountMapper.insert(userId, amountId);
				ret = userAmountMapper.addOne(userId, amountId);
			}
			if(ret>0)
			{
				RepeatControlUtil.addCount(numberKey);
			}
		} 
		catch(RuntimeException e)
		{
			throw e;
		}
		
		catch (Exception e) {
			e.printStackTrace();
			try {
				// userAmountLogMapper.insert 重复插入出错
				long amount = userAmountLogMapper.selectCount(userId, amountId, ownerKey);
				if (amount > 0) {
					RepeatControlUtil.addCount(numberKey);
					return true;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return ret > 0;
	}

	@Override
	@Transactional
	public ProcessResult add(String userId, String amountId, long addAmount, String ownerKey) {
		ProcessResult result = new ProcessResult();
		result.setRetCode(0);
		// 用户计数统计key
		String numberKey = RepeatControlUtil.getNumberKey(userId, amountId, ownerKey);
		if (!RepeatControlUtil.canAddCount(numberKey)) {
			logger.info("can't operate again...");
			return result;
		}
		int ret = 0;
		try {
			ret = userAmountLogMapper.insert(userId, amountId, ownerKey);
			ret = userAmountMapper.add(userId, amountId, addAmount);
			if(ret==0)
			{
				userAmountMapper.insert(userId, amountId);
				ret = userAmountMapper.add(userId, amountId, addAmount);
					
			}
			if(ret>0)
			{
				RepeatControlUtil.addCount(numberKey);
			}
		} 
		catch(RuntimeException e)
		{
			throw e;
		}
		
		catch (Exception e) {
			e.printStackTrace();
			try {
				// userAmountLogMapper.insert 重复插入出错
				long amount = userAmountLogMapper.selectCount(userId, amountId, ownerKey);
				if (amount > 0) {
					RepeatControlUtil.addCount(numberKey);
					return result;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (ret <= 0) {
			result.setRetCode(-1);
		}
		logger.info("add " + addAmount + " result=" + ret);
		return result;
	}

	@Override
	@Transactional
	public boolean substractOne(String userId, String amountId, String ownerKey) {
		// 用户计数统计key
		String numberKey = RepeatControlUtil.getNumberKey(userId, amountId, ownerKey);
		if (!RepeatControlUtil.canAddCount(numberKey)) {
			logger.info("can't operate again...");
			return true;
		}
		int ret = 0;
		try {
			ret = userAmountLogMapper.insert(userId, amountId, ownerKey);
			ret = userAmountMapper.substractOne(userId, amountId);
			if(ret==0)
			{
		 		userAmountMapper.insert(userId, amountId);
		 		ret = userAmountMapper.substractOne(userId, amountId);
				    		
			}
			if(ret>0)
			{
				RepeatControlUtil.addCount(numberKey);
			}
		} 
		catch(RuntimeException e )
		{
			throw e;
		}		
		catch (Exception e) {
			e.printStackTrace();
			try {
				// userAmountLogMapper.insert 重复插入出错
				long amount = userAmountLogMapper.selectCount(userId, amountId, ownerKey);
				if (amount > 0) {
					RepeatControlUtil.addCount(numberKey);
					return true;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		logger.info("substractOne result=" + ret);
		return ret > 0;
	}

	@Override
	public ProcessResult substract(String userId, String amountId, long substractAmount, String ownerKey) {
		ProcessResult result = new ProcessResult();
		result.setRetCode(0);
		// 用户计数统计key
		String numberKey = RepeatControlUtil.getNumberKey(userId, amountId, ownerKey);
		if (!RepeatControlUtil.canAddCount(numberKey)) {
			logger.info("can't operate again...");
			return result;
		}
		int ret = 0;
		try {
			ret = userAmountLogMapper.insert(userId, amountId, ownerKey);
			
			ret = userAmountMapper.substract(userId, amountId, substractAmount);
			if(ret==0)
			{
				userAmountMapper.insert(userId, amountId);
				ret = userAmountMapper.substract(userId, amountId, substractAmount);
				
			}
			if(ret>0)
			{
				RepeatControlUtil.addCount(numberKey);
			}
		}
		catch(RuntimeException e)
		{
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				// userAmountLogMapper.insert 重复插入出错
				long amount = userAmountLogMapper.selectCount(userId, amountId, ownerKey);
				if (amount > 0) {
					RepeatControlUtil.addCount(numberKey);
					return result;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (ret <= 0) {
			result.setRetCode(-1);
		}
		logger.info("substract " + substractAmount + " result=" + ret);
		return result;
	}
}
