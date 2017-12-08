USE `order_db`;

-- 正在导出表  order_db.order_flow_def 的数据：~0 rows (大约)
DELETE FROM `order_flow_def` where catetory = "coursebuyer";
/*!40000 ALTER TABLE `order_flow_def` ENABLE KEYS */;
INSERT INTO `order_flow_def` (`catetory`, `category_name`, `version`, `orderId_category`, `deploy_id`, `steps`, `finished_step`) VALUES
	('coursebuyer', '学员购买课程', '0.1', '{"defId":1,"dbId":"0002","dbIdLength":4,"partitionIdLength":3,"partitionStartId":100,"partitionNum":10}', '1', '10', '10;');


DELETE FROM `order_flow_stepdef` where catetory="coursebuyer";
/*!40000 ALTER TABLE `order_flow_stepdef` DISABLE KEYS */;
INSERT INTO `order_flow_stepdef` (`catetory`, `step_id`, `step_name`, `task_in`, `task_out_error`, `task_out_succ`, `task_out_default`, `run_type`, `run_info`, `retry_times`) VALUES
	('coursebuyer', '__start', '等待付款', '{"name":"等待付款", "category":2}', '0,1,__start,0;', '0,0,upCourseClass,0;', '0,default,__start,0;', 'immediately', 'test', '3'),
	('coursebuyer', '__end', '流程结束', '', '0,6002,0,0;', '0,0,0,0;', '0,0,0,0;', 'immediately', 'test', '0');

commit;