package com.xinwei.userOrder.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xinwei.userOrder.domain.UserOrder;

@Mapper
public interface UserOrderMapper {
	
	
	@Insert("insert into userorder(createTime,userId,orderId,category,status,orderDataType,orderData,updateTime) values(#{createTime},#{userId},#{orderId},#{category},#{status},#{orderDataType},#{orderDataByte,jdbcType=BLOB},#{updateTime})")
	public int insertUserOrder(UserOrder userOrder);
	
	@Update("update userorder set category=#{category},status=#{status},orderDataType=#{orderDataType},orderData=#{orderDataByte,jdbcType=BLOB},updateTime=#{updateTime} where createTime=#{createTime, jdbcType=TIMESTAMP} and userId=#{userId} and category=#{category} and orderId=#{orderId}")
	public int updateUserOrder(UserOrder userOrder);
	
	@Update("update userorder set status=#{status},updateTime=#{updateTime} where createTime=#{createTime, jdbcType=TIMESTAMP}  and userId=#{userId} and category=#{category} and orderId=#{orderId}")
	public int updateUserOrderStatus(UserOrder userOrder);
	
	@Delete("delete from  userorder  where createTime=#{createTime, jdbcType=TIMESTAMP} and userId=#{userId} and category=#{category} and orderId=#{orderId}")
	public int delUserOrder(UserOrder userOrder);
	
	@Select("select createTime,userId,orderId,category,status,orderDataType,orderData as orderDataByte,updateTime from  userorder  where createTime=#{createTime, jdbcType=TIMESTAMP} and userId=#{userId} and category=#{category} and orderId=#{orderId}")
	public UserOrder selectUserOrderById(UserOrder userOrder);
	
	//@Select("select createTime,userId,orderId,category,status,orderDataType,orderData as orderDataByte,updateTime from  userorder  where createTime=#{createTime, jdbcType=TIMESTAMP} and category=#{category} and orderId=#{orderId}")
	//public UserOrder selectByCategoryAndOrderId(UserOrder userOrder);
	
//	@Select("select createTime,userId,orderId,category,status,orderDataType,orderData as orderDataByte,updateTime from  userorder  where createTime=#{createTime, jdbcType=TIMESTAMP} and userId=#{userId} and category=#{category}")
//	public UserOrder selectFamilyUserOrder(UserOrder userOrder);
	
	@Select("select createTime,userId,orderId,category,status,orderDataType,orderData as orderDataByte,updateTime from  userorder  where createTime between #{startCreateTime} and #{endCreateTime} and category=#{category}")
	public List<UserOrder> selectAllByCategory(@Param("category") String category,@Param("startCreateTime") Date startCreateTime,@Param("endCreateTime") Date endCreateTime);
	
	@Select("select count(*) from userorder where createTime=#{createTime, jdbcType=TIMESTAMP} and userId=#{userId} and category=#{category} and orderId=#{orderId}")
	public int selectCountById(UserOrder userOrder);
	
	@Select("select createTime,userId,orderId,category,status,orderDataType,orderData as orderDataByte,updateTime from  userorder  where createTime between #{startCreateTime} and #{endCreateTime} and userId=#{userId} and category=#{category} order by createTime desc,userid desc,category desc,updateTime desc")
	//@Results({@Result(column = "addr_id", property = "address.addrId")  
	//})
	public List<UserOrder> selOrdersByUser(@Param("category") String category,@Param("startCreateTime") Date startCreateTime,@Param("endCreateTime") Date endCreateTime,@Param("userId") String userid);
	
	
	@Select("select createTime,userId,orderId,category,status,orderDataType,orderData as orderDataByte,updateTime from  userorder  where createTime between #{startCreateTime} and #{endCreateTime} and userId=#{userId} and category=#{category} order by createTime desc,userid desc,category desc,orderId asc")
	//@Results({@Result(column = "addr_id", property = "address.addrId")  
	//})
	public List<UserOrder> selOrdersByUserOrderIdAsc(@Param("category") String category,@Param("startCreateTime") Date startCreateTime,@Param("endCreateTime") Date endCreateTime,@Param("userId") String userid);
	
	
	@Select("select createTime,userId,orderId,category,status,orderDataType,orderData as orderDataByte,updateTime from  userorder  where createTime between #{startCreateTime} and #{endCreateTime} and userId=#{userId} and category=#{category} and status = #{status} order by createTime desc,userid desc,category desc,updateTime desc")
	public List<UserOrder> selOrderByUserStatus(@Param("category") String category,@Param("startCreateTime") Date startCreateTime,@Param("endCreateTime") Date endCreateTime,@Param("userId") String userid,@Param("status") int status);
	
	@Select("select createTime,userId,orderId,category,status,orderDataType,orderData as orderDataByte,updateTime from  userorder  where createTime between #{startCreateTime} and #{endCreateTime}  and category=#{category} and status = #{status} order by createTime desc,userid desc,category desc,updateTime desc")
	public List<UserOrder> selOrderByCategoryAndStatus(@Param("category") String category,@Param("startCreateTime") Date startCreateTime,@Param("endCreateTime") Date endCreateTime,@Param("status") int status);
	
}
