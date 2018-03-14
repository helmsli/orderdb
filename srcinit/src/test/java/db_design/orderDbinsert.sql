-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.29 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 order_db 的数据库结构
DROP DATABASE IF EXISTS `order_db`;
CREATE DATABASE IF NOT EXISTS `order_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `order_db`;


-- 导出  表 order_db.order_flow_def 结构
DROP TABLE IF EXISTS `order_flow_def`;
CREATE TABLE IF NOT EXISTS `order_flow_def` (
  `catetory` varchar(5) DEFAULT NULL COMMENT '对各种订单进行分类，以区别一个应用中不同类型的订单:例如：买家购买订单，买家退货订单',
  `category_name` varchar(128) DEFAULT NULL,
  `version` varchar(32) DEFAULT NULL COMMENT '标识订单系统的版本，以用来支持后续版本升级；',
  `orderId_category` varchar(256) DEFAULT NULL COMMENT '支持的订单ID生成规则，包括以时间为规则；随机数规则；按照用户和自定义数据分区',
  `deploy_id` varchar(32) DEFAULT NULL COMMENT '主要是为了支持大数据，大并发，针对该类型的订单定义一些常量，以用来支持ngix路由；',
  `steps` varchar(1024) DEFAULT NULL COMMENT '保存步骤信息，其中以;分割步骤信息，以:分割每一个步骤中的字段，主要包括，步骤描述信息和步骤ID；例如：包含两部，第一步是购买，第二步是付款；个数如下：\nbuy:01;pay:02;',
  `finished_step` varchar(32) DEFAULT NULL COMMENT '指明该订单结束的状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单流程定义';

-- 正在导出表  order_db.order_flow_def 的数据：~0 rows (大约)
DELETE FROM `order_flow_def`;
/*!40000 ALTER TABLE `order_flow_def` DISABLE KEYS */;
INSERT INTO `order_flow_def` (`catetory`, `category_name`, `version`, `orderId_category`, `deploy_id`, `steps`, `finished_step`) VALUES
	('test', 'sales', '0.1', '{"defId":1,"dbId":"0001","dbIdLength":4,"partitionIdLength":3,"partitionStartId":100,"partitionNum":100}', '1', '10', '10;');
/*!40000 ALTER TABLE `order_flow_def` ENABLE KEYS */;


-- 导出  表 order_db.order_flow_stepdef 结构
DROP TABLE IF EXISTS `order_flow_stepdef`;
CREATE TABLE IF NOT EXISTS `order_flow_stepdef` (
  `catetory` varchar(5) DEFAULT NULL COMMENT '对各种订单进行分类，以区别一个应用中不同类型的订单:例如：买家购买订单，买家退货订单',
  `step_id` varchar(32) DEFAULT NULL,
  `step_name` varchar(128) DEFAULT NULL,
  `task_in` varchar(2048) DEFAULT NULL COMMENT '填写进入该步骤的时候需要调用的外部任务。就是个URl，即该步骤需要调用的rest的ajax请求。\n如果为null，进入该步骤不需要调用外部接口，需要等待外部程序调用触发进入下一个环节；',
  `task_out_error` varchar(1024) DEFAULT NULL COMMENT '格式如下：以；分割每一个步骤，以，分割每一个步骤中的内容；\n具体如下：\n单值的格式\n0,返回值,下一步的步骤，是否通知运维；\n离散的格式：\n2，离散数值1，离散数值2，离散数值n,下一步的步骤，是否通知运维；\n区间的格式；\n1，区间开始数值，区间结束数值，下一步的步骤，是否通知运维；\n\n',
  `task_out_succ` varchar(1024) DEFAULT NULL COMMENT '格式如下：以；分割每一个步骤，以，分割每一个步骤中的内容；\n具体如下：\n单值的格式\n0,返回值,下一步的步骤，是否通知运维；\n',
  `task_out_default` varchar(1024) DEFAULT NULL COMMENT '格式如下：以；分割每一个步骤，以，分割每一个步骤中的内容；\n单值的格式\n0,default,下一步的步骤，是否通知运维；\n',
  `run_type` varchar(32) DEFAULT NULL COMMENT '任务运行类型，定时任务，立即执行任务',
  `run_info` varchar(1024) DEFAULT NULL COMMENT '保存任务类型对应的任务信息，比如定时任务，需要保存定时任务执行的信息；立即执行任务，可以配置优先级，延迟时间等信息;是否需要后台重做',
  `retry_times` varchar(1024) DEFAULT NULL COMMENT '任务失败后重做信息，保存json信息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  order_db.order_flow_stepdef 的数据：~2 rows (大约)
DELETE FROM `order_flow_stepdef`;
/*!40000 ALTER TABLE `order_flow_stepdef` DISABLE KEYS */;
INSERT INTO `order_flow_stepdef` (`catetory`, `step_id`, `step_name`, `task_in`, `task_out_error`, `task_out_succ`, `task_out_default`, `run_type`, `run_info`, `retry_times`) VALUES
	('test', '__start', 'addOrderMain', '{"name":"first", "category":0, "url":"http://127.0.0.1:9001/orderDb","restMethod":"test","runExpress":"0/3 * * * * ?","retryExpress":"0/10 * * * * ?","maxThreadNumber" : 15,"initThreadNumber":1,"keepAliveTime":100,"queneSize":20}', ' 0,6002,0,0;2,6003,6100,0,0;', '0,0,2,0;', '0,default,3,0;', 'immediately', 'test', '0'),
	('test', '2', 'updateOrderMain', '{"name":"first", "category":0, "url":"http://127.0.0.1/orderDb/addOrderMain","restMethod":"addOrderMain","runExpress":"0/3 * * * * ?","retryExpress":"0/10 * * * * ?","maxThreadNumber" : 15,"initThreadNumber":1,"keepAliveTime":100,"queneSize":20}', '0,6002,0,0;2,6003,6100,0,0;', '0,0,3,0;', '0,0,0,0;', 'immediately', 'test', '0'),
	('test', '3', 'finnishOrderMain', '{"name":"first", "category":0, "url":"http://127.0.0.1/orderDb/addOrderMain","restMethod":"addOrderMain","runExpress":"0/3 * * * * ?","retryExpress":"0/10 * * * * ?","maxThreadNumber" : 15,"initThreadNumber":1,"keepAliveTime":100,"queneSize":20}', '0,6002,0,0;2,6003,6100,0,0;', '0,0,0,0;', '0,0,0,0;', 'immediately', 'test', '0');
/*!40000 ALTER TABLE `order_flow_stepdef` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
