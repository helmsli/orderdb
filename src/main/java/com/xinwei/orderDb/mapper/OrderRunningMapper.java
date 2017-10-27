package com.xinwei.orderDb.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.xinwei.orderDb.domain.OrderRunning;
@Mapper
public interface OrderRunningMapper {
	//增删改差
	
	//步骤跳转insert
	@Insert("insert into order_running (current_status, order_id, flow_id, create_time, catetory) "
			+ "values (#{currentStatus}, #{orderId}, #{flowId}, #{createTime}, #{catetory})")
    int insert(OrderRunning record);

}