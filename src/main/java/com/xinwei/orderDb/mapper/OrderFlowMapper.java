package com.xinwei.orderDb.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xinwei.orderDb.domain.OrderFlow;

@Mapper
public interface OrderFlowMapper {
	//增删改差
	//
	//增加查询接口按时间排序
	@Insert(" insert into order_flow (partition_id, order_id, owner_key," + "step_id, flow_id, create_time,"
			+ " update_time, data_key, context_data," + " retry_times, current_status, ret_code, ret_msg,catetory)"
			+ " values (#{partitionId}, #{orderId}, #{ownerKey}," + " #{stepId}, #{flowId}, #{createTime},"
			+ " #{updateTime}, #{dataKey}, #{contextData},"
			+ " #{retryTimes}, #{currentStatus}, #{retCode}, #{retMsg},#{catetory})")
	int insert(OrderFlow record);

	@Update(" UPDATE order_flow SET owner_key=#{ownerKey}," + "create_time=#{createTime},"
			+ " update_time=#{updateTime}, data_key=#{dataKey}, context_data=#{contextData},"
			+ " retry_times=#{retryTimes}, current_status=#{currentStatus}, ret_code=#{retCode}, ret_msg=#{retMsg}"
			+ " where order_id=#{orderId} and partition_id=#{partitionId} and step_id=#{stepId} and flow_id=#{flowId} and catetory=#{catetory}")
	int update(OrderFlow orderFlow);

	@Update(" UPDATE order_flow SET"
			+ " update_time=#{updateTime},current_status=#{currentStatus}, ret_code=#{retCode}, ret_msg=#{retMsg}"
			+ " where order_id=#{orderId} and partition_id=#{partitionId} and step_id=#{stepId} and flow_id=#{flowId} and catetory=#{catetory}")
	int updateStatus(OrderFlow orderFlow);

	
	@Select("select * from order_flow where order_id=#{orderId} and partition_id=#{partitionId} and step_id=#{stepId} and flow_id=#{flowId} and catetory=#{catetory}")
	OrderFlow selectOrderFlow(@Param("catetory") String catetory,@Param("orderId") String orderId, @Param("partitionId") String partitionId,
			@Param("stepId") String stepId, @Param("flowId") String flowId);
	
	@Delete("delete from order_flow where order_id=#{orderId} and partition_id=#{partitionId} and step_id=#{stepId} and flow_id=#{flowId} and catetory=#{catetory}")
	int deleteOrderFlow(@Param("catetory") String catetory,@Param("orderId") String orderId, @Param("partitionId") String partitionId,
			@Param("stepId") String stepId, @Param("flowId") String flowId);
}