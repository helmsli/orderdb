package com.xinwei.orderDb.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xinwei.orderDb.domain.OrderContextData;

@Mapper
public interface OrderContextDataMapper {

	@Insert("insert into order_context__data (partition_id, order_id, data_key, step_id, flow_id, context_data,category) values "
			+ "(#{partitionId}, #{orderId}, #{dataKey}, #{stepId}, #{flowId}, #{contextDataBlob},#{category})")
	int insert(OrderContextData record);

	@Select("select partition_id as partitionId, order_id as orderId, data_key as dataKey, "
			+ "step_id as stepId, flow_id as flowId, context_data as contextDataBlob "
			+ " from order_context__data where partition_id=#{partition_id} and order_id=#{orderId} and data_key=#{dataKey} and category=#{category}")
	OrderContextData selectByOrderIdAndDataKey(@Param("partition_id") String partition_id, @Param("category") String category,@Param("orderId") String orderId, @Param("dataKey") String dataKey);

	
	
	@Select("select count(*) from order_context__data where partition_id=#{partition_id} and order_id=#{orderId} and data_key=#{dataKey} and category=#{category}")
	int selectCount(@Param("partition_id") String partition_id,@Param("category") String category,@Param("orderId") String orderId, @Param("dataKey") String dataKey);

	
	// //根据orderId和上下文关键字删除上下文数据
	@Delete("delete from order_context__data where  order_id=#{orderId} and data_key=#{dataKey}")
	int deleteOrderContextData(@Param("orderId") String orderId, @Param("dataKey") String dataKey);

	// //根据key更新上下文数据
	@Update("update order_context__data set step_id=#{stepId}, flow_id=#{flowId}, context_data=#{contextDataBlob} where partition_id=#{partitionId} and order_id=#{orderId} and data_key=#{dataKey} and category=#{category}")
	int updateOrderContextData(OrderContextData orderContextData);

}