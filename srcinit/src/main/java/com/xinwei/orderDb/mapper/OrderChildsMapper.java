package com.xinwei.orderDb.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xinwei.orderDb.domain.OrderChilds;

@Mapper
public interface OrderChildsMapper {

	@Insert("insert into order_childs (catetory, order_id, child_category, child_order_id, create_time)"
			+ " values (#{catetory}, #{orderId}, #{childCategory}, #{childOrderId}, #{createTime})")
	int insert(OrderChilds record);

	@Select("select * from order_childs where order_id=#{orderId} and child_order_id=#{childOrderId}")
	OrderChilds selectOrderChilds(@Param("orderId") String orderId, @Param("childOrderId") String childOrderId);

	//添加字段分区id
	
	
	
	
	@Delete("delete from order_childs where order_id=#{orderId} and child_order_id=#{childOrderId}")
	int deleteOrderChilds(@Param("orderId") String orderId, @Param("childOrderId") String childOrderId);

}