package com.xinwei.orderDb.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xinwei.orderDb.domain.OrderMain;

@Mapper
public interface OrderMainMapper {
	// 增删改差
	@Insert("insert into order_main (partition_id, order_id, catetory,"
			+ "      parent_order_id, parent_order_category, owner_key,"
			+ "      current_step, current_status, update_time, is_finished, flow_id, creat_time)"
			+ "    values (#{partitionId}, #{orderId}, #{catetory},"
			+ "      #{parentOrderId}, #{parentOrderCategory}, #{ownerKey},"
			+ "      #{currentStep}, #{currentStatus}, #{updateTime}," + " #{isFinished}, #{flowId}, #{creatTime})")
	int insert(OrderMain record);

	@Update("UPDATE order_main SET catetory=#{catetory}, parent_order_id=#{parentOrderId},"
			+ "parent_order_category=#{parentOrderCategory},"
			+ "owner_key=#{ownerKey}, current_step=#{currentStep}, current_status=#{currentStatus},"
			+ "update_time=#{updateTime}, is_finished=#{isFinished} , flow_id = #{flowId} WHERE order_id=#{orderId} and partition_id=#{partitionId}")
	int update(OrderMain record);

	@Update("UPDATE order_main SET current_step=#{step}, current_status=#{status} WHERE order_id=#{orderId} and partition_id=#{partitionId}")
	int updateMainOrderStatus(@Param("orderId") String orderId, @Param("status") int status,
			@Param("step") String step);

	@Select("select partition_id as partitionId, order_id as orderId, catetory,parent_order_id as parentOrderId, parent_order_category as parentOrderCategory, "
			+ "owner_key as ownerKey,current_step as currentStep, current_status as currentStatus, "
			+ "update_time as updateTime, is_finished as isFinished, flow_id as flowId, creat_time as creatTime"
			+ " from order_main WHERE order_id=#{orderId} and partition_id=#{partitionId}")
	OrderMain selectOrderMain(@Param("orderId") String orderId, @Param("partitionId") String partitionId);
	
	@Select("select count(*)  from order_main WHERE order_id=#{orderId} and partition_id=#{partitionId}")
	public int selectCountOrderMain(@Param("orderId") String orderId, @Param("partitionId") String partitionId);
	
}