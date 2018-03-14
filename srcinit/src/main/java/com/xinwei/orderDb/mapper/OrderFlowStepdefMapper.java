package com.xinwei.orderDb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.xinwei.orderDb.domain.OrderFlowStepdef;

@Mapper
public interface OrderFlowStepdefMapper {
	// 增删改差
	@Insert(" insert into order_flow_stepdef (catetory, step_id, step_name,"
			+ "      task_in, task_out_error, task_out_succ," + "      task_out_default, run_type, run_info,"
			+ "      retry_times)" + "    values (#{catetory}, #{stepId}, #{stepName},"
			+ "      #{taskIn}, #{taskOutError}, #{taskOutSucc}," + "      #{taskOutDefault}, #{runType}, #{runInfo},"
			+ "      #{retryTimes})")
	int insert(OrderFlowStepdef record);

	@Select("select catetory, step_id as stepId, step_name as stepName, task_in as taskIn, task_out_error as taskOutError, "
			+ "task_out_succ as taskOutSucc,task_out_default as taskOutDefault, run_type as runType, "
			+ "run_info as runInfo, retry_times as retryTimes from order_flow_stepdef where catetory=#{catetory}")
	List<OrderFlowStepdef> selectOrderFlowStepdefs(String catetory);
}