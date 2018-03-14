package com.xinwei.userOrders.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xinwei.userOrders.domain.UserOrders;

@Mapper
public interface UserOrdersMapper {

	@Select("select order_id as orderId, catetory, owner_key as ownerKey, current_status as currentStatus, "
			+ "create_time as createTime from user_orders where owner_key=#{ownerKey} "
			+ "and create_time=#{createTime} and order_id=#{orderId}")
	UserOrders selectUserOrdersById(@Param("ownerKey") String ownerKey, @Param("createTime") Date createTime,
			@Param("orderId") String orderId);

	@Select("select order_id as orderId, catetory, owner_key as ownerKey, current_status as currentStatus, "
			+ "create_time as createTime from user_orders where owner_key=#{ownerKey} "
			+ "and create_time=#{createTime} and current_status=#{currentStatus}")
	UserOrders selectUserOrdersByStatus(@Param("ownerKey") String ownerKey, @Param("createTime") Date createTime,
			@Param("currentStatus") int currentStatus);

	@Update("update user_orders set current_status=#{currentStatus} where owner_key=#{ownerKey} "
			+ "and create_time=#{createTime} and order_id=#{orderId}")
	int updateUserOrdersStatus(@Param("currentStatus") int currentStatus, @Param("ownerKey") String ownerKey,
			@Param("createTime") Date createTime, @Param("orderId") String orderId);

	@Insert("insert into user_orders(order_id, catetory, owner_key, current_status, create_time) "
			+ "values (#{orderId}, #{catetory}, #{ownerKey}, #{currentStatus}, #{createTime})")
	int insertUserOrders(UserOrders userOrders);

	@Delete("delete from user_orders where order_id=#{orderId}")
	int deleteUserOrders(String orderId);

}
