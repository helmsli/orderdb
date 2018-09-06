package com.xinwei.number.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xinwei.number.domain.StatCounter;

@Mapper
public interface UserAmountMapper {
	@Insert("insert into useramount(userId,amountId,amount) values(#{userId},#{amountId},0)")
	public int insert(@Param("userId") String userId, @Param("amountId") String amountId);

	@Select("select amount from useramount where userId=#{userId} and amountId=#{amountId}")
	public long selectAmount(@Param("userId") String userId, @Param("amountId") String amountId);

	@Select("select count(*) from useramount where userId=#{userId} and amountId=#{amountId}")
	public int countAmount(@Param("userId") String userId, @Param("amountId") String amountId);

	
	
	@Select("select userId,amountId,amount from useramount where userId=#{userId}")
	public List<StatCounter> selectUserAmountList(@Param("userId") String userId);

	@Update("update useramount set amount=amount+1 where userId=#{userId} and amountId=#{amountId} and amount>=0")
	public int addOne(@Param("userId") String userId, @Param("amountId") String amountId);

	@Update("update useramount set amount = amount + #{addAmount} "
			+ "where userId=#{userId} and amountId=#{amountId}")
	public int add(@Param("userId") String userId, @Param("amountId") String amountId,@Param("addAmount") long addAmount);

	@Update("update useramount set amount=amount-1 where userId=#{userId} and amountId=#{amountId} and amount>0")
	public int substractOne(@Param("userId") String userId, @Param("amountId") String amountId);

	@Update("update useramount set amount = amount - #{substractAmount} "
			+ "where userId=#{userId} and amountId=#{amountId}  and amount>=#{substractAmount}")
	public int substract(@Param("userId") String userId, @Param("amountId") String amountId,
			@Param("substractAmount") long substractAmount);

}
