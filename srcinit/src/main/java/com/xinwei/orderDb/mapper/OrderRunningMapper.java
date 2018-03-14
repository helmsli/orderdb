package com.xinwei.orderDb.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xinwei.orderDb.domain.OrderRunning;

@Mapper
public interface OrderRunningMapper {
	// 增删改差

	// 步骤跳转insert
	@Insert("insert into order_running (current_status, order_id, flow_id, create_time, catetory) "
			+ "values (#{currentStatus}, #{orderId}, #{flowId}, #{createTime}, #{catetory})")
	int insert(OrderRunning record);

	@Delete("delete from order_running where order_id={orderId} and flow_id#{flowId} and catetory=#{catetory})")
	int delete(@Param("orderId") String orderId, @Param("flowId") String flowId, @Param("catetory") String catetory);

}