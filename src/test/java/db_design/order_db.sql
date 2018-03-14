SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Indexes */

DROP INDEX index_order_flow ON order_flow;
DROP INDEX index_status ON user_orders;
DROP INDEX index_id ON user_orders;



/* Drop Tables */

DROP TABLE IF EXISTS order_childs;
DROP TABLE IF EXISTS order_context__data;
DROP TABLE IF EXISTS order_flow;
DROP TABLE IF EXISTS order_flow_def;
DROP TABLE IF EXISTS order_flow_stepDef;
DROP TABLE IF EXISTS order_main;
DROP TABLE IF EXISTS order_running;
DROP TABLE IF EXISTS user_orders;




/* Create Tables */

-- 该订单派生出来的子订单信息
CREATE TABLE order_childs
(
	-- 对各种订单进行分类，以区别一个应用中不同类型的订单:例如：买家购买订单，买家退货订单
	catetory varchar(32) COMMENT '对各种订单进行分类，以区别一个应用中不同类型的订单:例如：买家购买订单，买家退货订单',
	-- 后四位UUUU是按照用户或者别的主键进行分库的规则；
	-- 后5位到后7位XXX是按照一定的规则进行分区的；
	-- yyyyyyyXXXUUUU
	-- 其中yyyyyyy是变长的。
	order_id varchar(128) COMMENT '后四位UUUU是按照用户或者别的主键进行分库的规则；
后5位到后7位XXX是按照一定的规则进行分区的；
yyyyyyyXXXUUUU
其中yyyyyyy是变长的。',
	child_category varchar(32),
	child_orderId varchar(128),
	create_time datetime
) ENGINE = InnoDB COMMENT = '该订单派生出来的子订单信息';


CREATE TABLE order_context__data
(
	-- 按照创建时间加步骤id分区
	partition_id varchar(16) COMMENT '按照创建时间加步骤id分区',
	-- 后四位UUUU是按照用户或者别的主键进行分库的规则；
	-- 后5位到后7位XXX是按照一定的规则进行分区的；
	-- yyyyyyyXXXUUUU
	-- 其中yyyyyyy是变长的。
	order_id varchar(128) COMMENT '后四位UUUU是按照用户或者别的主键进行分库的规则；
后5位到后7位XXX是按照一定的规则进行分区的；
yyyyyyyXXXUUUU
其中yyyyyyy是变长的。',
catetory varchar(32),
data_key varchar(64),
	step_id varchar(32),
	-- 标识一个订单中的具体流程
	flow_id varchar(128) COMMENT '标识一个订单中的具体流程',
	-- 在order_context_data表中，如果flowid为空，则数据保存在data表中，如果flowid不为空，则数据保存到order_flow中
	context_data blob COMMENT '在order_context_data表中，如果flowid为空，则数据保存在data表中，如果flowid不为空，则数据保存到order_flow中'
) ENGINE = InnoDB;


-- 按照订单ID和业务关键字分区
-- 按照用户分库：按照XXX分区，一个分区支持40万的数据，一个订单包括20步，一个分区支持
CREATE TABLE order_flow
(
	-- 按照创建时间加步骤id分区
	partition_id varchar(16) COMMENT '按照创建时间加步骤id分区',
	-- 后四位UUUU是按照用户或者别的主键进行分库的规则；
	-- 后5位到后7位XXX是按照一定的规则进行分区的；
	-- yyyyyyyXXXUUUU
	-- 其中yyyyyyy是变长的。
	order_id varchar(128) COMMENT '后四位UUUU是按照用户或者别的主键进行分库的规则；
后5位到后7位XXX是按照一定的规则进行分区的；
yyyyyyyXXXUUUU
其中yyyyyyy是变长的。',
	-- 订单归属关键字，比如买家订单，则需要保存买家的用户ID
catetory varchar(32),	
owner_key varchar(128) COMMENT '订单归属关键字，比如买家订单，则需要保存买家的用户ID',
	step_id varchar(32),
	-- 标识一个订单中的具体流程
	flow_id varchar(128) COMMENT '标识一个订单中的具体流程',
	create_time datetime,
	update_time datetime,
	data_key varchar(64),
	-- 在order_context_data表中，如果flowid为空，则数据保存在data表中，如果flowid不为空，则数据保存到order_flow中
	context_data blob COMMENT '在order_context_data表中，如果flowid为空，则数据保存在data表中，如果flowid不为空，则数据保存到order_flow中',
	-- 任务失败后重做信息，保存json信息
	retry_times varchar(1024) COMMENT '任务失败后重做信息，保存json信息',
	current_status int,
	-- 错误和描述
	ret_code varchar(128) COMMENT '错误和描述',
	-- 错误和描述
	ret_msg varchar(128) COMMENT '错误和描述'
) ENGINE = InnoDB COMMENT = '按照订单ID和业务关键字分区
按照用户分库：按照XXX分区，一个分区支持40万的数据，一个订单包括20步，一个分区支持';


-- 订单流程定义
CREATE TABLE order_flow_def
(
	-- 对各种订单进行分类，以区别一个应用中不同类型的订单:例如：买家购买订单，买家退货订单
	catetory varchar(32) COMMENT '对各种订单进行分类，以区别一个应用中不同类型的订单:例如：买家购买订单，买家退货订单',
	category_name varchar(128),
	-- 标识订单系统的版本，以用来支持后续版本升级；
	version varchar(32) COMMENT '标识订单系统的版本，以用来支持后续版本升级；',
	-- 支持的订单ID生成规则，包括以时间为规则；随机数规则；按照用户和自定义数据分区
	orderId_category varchar(256) COMMENT '支持的订单ID生成规则，包括以时间为规则；随机数规则；按照用户和自定义数据分区',
	-- 主要是为了支持大数据，大并发，针对该类型的订单定义一些常量，以用来支持ngix路由；
	deploy_id varchar(32) COMMENT '主要是为了支持大数据，大并发，针对该类型的订单定义一些常量，以用来支持ngix路由；',
	-- 保存步骤信息，其中以;分割步骤信息，以:分割每一个步骤中的字段，主要包括，步骤描述信息和步骤ID；例如：包含两部，第一步是购买，第二步是付款；个数如下：
	-- buy:01;pay:02;
	steps varchar(1024) COMMENT '保存步骤信息，其中以;分割步骤信息，以:分割每一个步骤中的字段，主要包括，步骤描述信息和步骤ID；例如：包含两部，第一步是购买，第二步是付款；个数如下：
buy:01;pay:02;',
	-- 指明该订单结束的状态
	finished_step varchar(32) COMMENT '指明该订单结束的状态'
) ENGINE = InnoDB COMMENT = '订单流程定义';


CREATE TABLE order_flow_stepDef
(
	-- 对各种订单进行分类，以区别一个应用中不同类型的订单:例如：买家购买订单，买家退货订单
	catetory varchar(32) COMMENT '对各种订单进行分类，以区别一个应用中不同类型的订单:例如：买家购买订单，买家退货订单',
	step_id varchar(32),
	step_name varchar(128),
	-- 填写进入该步骤的时候需要调用的外部任务。就是个URl，即该步骤需要调用的rest的ajax请求。
	-- 如果为null，进入该步骤不需要调用外部接口，需要等待外部程序调用触发进入下一个环节；
	task_in varchar(2048) COMMENT '填写进入该步骤的时候需要调用的外部任务。就是个URl，即该步骤需要调用的rest的ajax请求。
如果为null，进入该步骤不需要调用外部接口，需要等待外部程序调用触发进入下一个环节；',
	-- 定义执行完任务失败后根据返回数值控制状态跳转，这个字段需要填写表格，以；分割行，以，分割列
	-- 格式如下：
	-- 错误码支持区间配置；支持离散的；
	-- 类型(0-单值，1-区间，2-离散数值)返回数值错误码,步骤,是否通知运维(0--不通知，其余通知）;
	-- 
	task_out_error varchar(1024) COMMENT '定义执行完任务失败后根据返回数值控制状态跳转，这个字段需要填写表格，以；分割行，以，分割列
格式如下：
错误码支持区间配置；支持离散的；
类型(0-单值，1-区间，2-离散数值)返回数值错误码,步骤,是否通知运维(0--不通知，其余通知）;
',
	-- 定义执行完任务成功后根据返回数值控制状态跳转
	-- 格式如下：
	-- 
	-- 0,步骤;
	-- 
	task_out_succ varchar(1024) COMMENT '定义执行完任务成功后根据返回数值控制状态跳转
格式如下：

0,步骤;
',
	-- 注释参见task_out_error
	task_out_default varchar(1024) COMMENT '注释参见task_out_error',
	-- 任务运行类型，定时任务，立即执行任务
	run_type varchar(32) COMMENT '任务运行类型，定时任务，立即执行任务',
	-- 保存任务类型对应的任务信息，比如定时任务，需要保存定时任务执行的信息；立即执行任务，可以配置优先级，延迟时间等信息;是否需要后台重做
	run_info varchar(1024) COMMENT '保存任务类型对应的任务信息，比如定时任务，需要保存定时任务执行的信息；立即执行任务，可以配置优先级，延迟时间等信息;是否需要后台重做',
	-- 任务失败后重做信息，保存json信息
	retry_times varchar(1024) COMMENT '任务失败后重做信息，保存json信息'
) ENGINE = InnoDB;


-- 订单主业务表
-- 
-- 1.读写操作按照业务层调用者进行分开标识路由
-- 
-- 2.工程名路由
-- 
-- 3.按照{dbId}分库路
CREATE TABLE order_main
(
	-- 按照创建时间加步骤id分区
	partition_id varchar(16) COMMENT '按照创建时间加步骤id分区',
	-- 后四位UUUU是按照用户或者别的主键进行分库的规则；
	-- 后5位到后7位XXX是按照一定的规则进行分区的；
	-- yyyyyyyXXXUUUU
	-- 其中yyyyyyy是变长的。
	order_id varchar(128) COMMENT '后四位UUUU是按照用户或者别的主键进行分库的规则；
后5位到后7位XXX是按照一定的规则进行分区的；
yyyyyyyXXXUUUU
其中yyyyyyy是变长的。',
	-- 对各种订单进行分类，以区别一个应用中不同类型的订单:例如：买家购买订单，买家退货订单
	catetory varchar(32) COMMENT '对各种订单进行分类，以区别一个应用中不同类型的订单:例如：买家购买订单，买家退货订单',
	-- 关联的父订单ID
	parent_order_id varchar(128) COMMENT '关联的父订单ID',
	parent_order_category varchar(32),
	-- 订单归属关键字，比如买家订单，则需要保存买家的用户ID
	owner_key varchar(128) COMMENT '订单归属关键字，比如买家订单，则需要保存买家的用户ID',
	current_step varchar(32),
	current_status int,
	update_time datetime,
	-- 0--没有结束
	-- 1--结束
	is_finished int COMMENT '0--没有结束
1--结束',
	-- 标识一个订单中的具体流程
	flow_id varchar(128) COMMENT '标识一个订单中的具体流程',
	creat_time datetime
) ENGINE = InnoDB COMMENT = '订单主业务表

1.读写操作按照业务层调用者进行分开标识路由

2.工程名路由

3.按照{dbId}分库路';


CREATE TABLE order_running
(
	-- 对各种订单进行分类，以区别一个应用中不同类型的订单:例如：买家购买订单，买家退货订单
	catetory varchar(32) COMMENT '对各种订单进行分类，以区别一个应用中不同类型的订单:例如：买家购买订单，买家退货订单',
	-- 按照创建时间加步骤id分区
	partition_id varchar(16) COMMENT '按照创建时间加步骤id分区',
	-- 后四位UUUU是按照用户或者别的主键进行分库的规则；
	-- 后5位到后7位XXX是按照一定的规则进行分区的；
	-- yyyyyyyXXXUUUU
	-- 其中yyyyyyy是变长的。
	order_id varchar(128) COMMENT '后四位UUUU是按照用户或者别的主键进行分库的规则；
后5位到后7位XXX是按照一定的规则进行分区的；
yyyyyyyXXXUUUU
其中yyyyyyy是变长的。',
	create_time datetime,
	-- 标识一个订单中的具体流程
	flow_id varchar(128) COMMENT '标识一个订单中的具体流程'
) ENGINE = InnoDB;


-- ownerKey和创建时间 分区  ownerKey就是用户信息关键字 
-- 按照时间，ownerKey，状态索引， 
-- 
CREATE TABLE user_orders
(
	-- 后四位UUUU是按照用户或者别的主键进行分库的规则；
	-- 后5位到后7位XXX是按照一定的规则进行分区的；
	-- yyyyyyyXXXUUUU
	-- 其中yyyyyyy是变长的。
	order_id varchar(128) COMMENT '后四位UUUU是按照用户或者别的主键进行分库的规则；
后5位到后7位XXX是按照一定的规则进行分区的；
yyyyyyyXXXUUUU
其中yyyyyyy是变长的。',
	-- 对各种订单进行分类，以区别一个应用中不同类型的订单:例如：买家购买订单，买家退货订单
	catetory varchar(32) COMMENT '对各种订单进行分类，以区别一个应用中不同类型的订单:例如：买家购买订单，买家退货订单',
	-- 订单归属关键字，比如买家订单，则需要保存买家的用户ID
	owner_key varchar(128) COMMENT '订单归属关键字，比如买家订单，则需要保存买家的用户ID',
	current_status int,
	create_time datetime
) ENGINE = InnoDB COMMENT = 'ownerKey和创建时间 分区  ownerKey就是用户信息关键字 
按照时间，ownerKey，状态索引， 
';



/* Create Indexes */

CREATE INDEX index_order_flow ON order_flow (order_id ASC, partition_id ASC, step_id ASC, flow_id ASC);
CREATE INDEX index_status ON user_orders (create_time ASC, owner_key ASC, current_status ASC);
CREATE INDEX index_id ON user_orders (order_id ASC, current_status ASC, create_time ASC);



alter table order_context__data add category varchar(32) ;
alter table order_flow add catetory varchar(32) ;