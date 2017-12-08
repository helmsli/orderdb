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
CREATE DATABASE IF NOT EXISTS `order_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `order_db`;

-- 正在导出表  order_db.order_flow_def 的数据：~0 rows (大约)
DELETE FROM `order_flow_def` where catetory = "tcoursepub";
/*!40000 ALTER TABLE `order_flow_def` DISABLE KEYS */;
INSERT INTO `order_flow_def` (`catetory`, `category_name`, `version`, `orderId_category`, `deploy_id`, `steps`, `finished_step`) VALUES
	('tcoursepub', '老师发布新课程', '0.1', '{"defId":1,"dbId":"0001","dbIdLength":4,"partitionIdLength":3,"partitionStartId":100,"partitionNum":10}', '1', '10', '10;');
/*!40000 ALTER TABLE `order_flow_def` ENABLE KEYS */;
INSERT INTO `order_flow_def` (`catetory`, `category_name`, `version`, `orderId_category`, `deploy_id`, `steps`, `finished_step`) VALUES
	('coursebuyer', '学员购买课程', '0.1', '{"defId":1,"dbId":"0002","dbIdLength":4,"partitionIdLength":3,"partitionStartId":100,"partitionNum":10}', '1', '10', '10;');


-- 导出  表 order_db.order_flow_stepdef 结构
-- 正在导出表  order_db.order_flow_stepdef 的数据：~2 rows (大约)
DELETE FROM `order_flow_stepdef` where catetory="tcoursepub";
/*!40000 ALTER TABLE `order_flow_stepdef` DISABLE KEYS */;
INSERT INTO `order_flow_stepdef` (`catetory`, `step_id`, `step_name`, `task_in`, `task_out_error`, `task_out_succ`, `task_out_default`, `run_type`, `run_info`, `retry_times`) VALUES
	('coursebuyer', '__start', '等待付款', '{"name":"等待付款", "category":2}', '0,1,__start,0;', '0,0,upCourseClass,0;', '0,default,__start,0;', 'immediately', 'test', '3'),
	('coursebuyer', '__end', '流程结束', '', '0,6002,0,0;', '0,0,0,0;', '0,0,0,0;', 'immediately', 'test', '0');
/*!40000 ALTER TABLE `order_flow_stepdef` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
INSERT INTO `order_flow_stepdef` (`catetory`, `step_id`, `step_name`, `task_in`, `task_out_error`, `task_out_succ`, `task_out_default`, `run_type`, `run_info`, `retry_times`) VALUES
	('tcoursepub', '__start', '放入教师发布课程库', '{"name":"发布后立即执行", "category":1, "url":"http://127.0.0.1:9200/courseTeacherManager","restMethod":"publishCourse","runExpress":"0/3 * * * * ?","retryExpress":"0/10 * * * * ?","maxThreadNumber" : 15,"initThreadNumber":1,"keepAliveTime":100,"queneSize":20}', '0,1,__start,0;', '0,0,upCourseClass,0;', '0,default,__start,0;', 'immediately', 'test', '3'),
	('tcoursepub', 'upCourseClass', '更新教师Courseclass信息', '{"name":"发布后立即执行", "category":1, "url":"http://127.0.0.1:9200/courseTeacherManager","restMethod":"confTeacherClass","runExpress":"0/3 * * * * ?","retryExpress":"0/10 * * * * ?","maxThreadNumber" : 15,"initThreadNumber":1,"keepAliveTime":100,"queneSize":20}', '0,1,__start,0;', '0,0,updateCourse,0;', '0,default,upCourseClass,0;', 'immediately', 'test', '3'),
	('tcoursepub', 'updateCourse', '发布课程到学员库', '{"name":"发布后立即执行", "category":1, "url":"http://127.0.0.1:9200/courseTeacherManager","restMethod":"confTeacherCourse","runExpress":"0/3 * * * * ?","retryExpress":"0/10 * * * * ?","maxThreadNumber" : 15,"initThreadNumber":1,"keepAliveTime":100,"queneSize":20}', '0,1,updateUserOrder,0;', '0,0,__end,0;', '0,default,updateCourse,0;', 'immediately', 'test', '0'),
	('tcoursepub', '__end', '流程结束', '', '0,6002,0,0;', '0,0,0,0;', '0,0,0,0;', 'immediately', 'test', '0');

commit;