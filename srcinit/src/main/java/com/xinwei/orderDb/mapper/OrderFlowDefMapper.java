package com.xinwei.orderDb.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.xinwei.orderDb.domain.OrderFlowDef;

@Mapper
public interface OrderFlowDefMapper {

	@Insert(" insert into order_flow_def (catetory, category_name, version,"
			+ " orderId_category, deploy_id, steps, finished_step) values "
			+ "(#{catetory}, #{categoryName}, #{version}, #{orderidCategory}, #{deployId}, #{steps}, #{finishedstep})")
	int insert(OrderFlowDef record);

	// 增删改差
	@Select("select catetory, category_name as categoryName, version, "
			+ "orderId_category as orderidCategory, deploy_id as deployId, steps, finished_step as finishedstep "
			+ "from order_flow_def where catetory=#{category}")
	OrderFlowDef selectOrderFlowDefByCategory(String category);
}