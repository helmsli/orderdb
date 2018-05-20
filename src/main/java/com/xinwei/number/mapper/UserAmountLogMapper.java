package com.xinwei.number.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserAmountLogMapper {
	@Insert("insert into useramountlog(userId,amountId,ownerKey) values(#{userId},#{amountId},#{ownerKey})")
	public int insert(@Param("userId") String userId, @Param("amountId") String amountId,
			@Param("ownerKey") String ownerKey);

	@Select("select count(*) from useramountlog where userId=#{userId} and amountId=#{amountId} and ownerKey =#{ownerKey}")
	public long selectCount(@Param("userId") String userId, @Param("amountId") String amountId,
			@Param("ownerKey") String ownerKey);

}
