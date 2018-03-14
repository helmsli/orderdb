
CREATE TABLE userOrder
(
	createTime datetime,
	userId varchar(128),
	-- 订单编号
	orderId varchar(128) COMMENT '订单编号',
	category varchar(64),
	status int,
	orderDataType varchar(256),
	orderData blob,
	updateTime datetime
) ENGINE = MyISAM
PARTITION BY  range (to_days(createTime))  
    SUBPARTITION BY KEY(userId)
    SUBPARTITIONS 100
 (
    PARTITION p0 VALUES  LESS THAN (to_days('20171105')),
	PARTITION p1 VALUES  LESS THAN (to_days('20171205')),
	PARTITION p2 VALUES  LESS THAN (to_days('20180105')),
	PARTITION p3 VALUES  LESS THAN (to_days('20180205')),
	PARTITION p4 VALUES  LESS THAN (to_days('20180305')),
	PARTITION p5 VALUES  LESS THAN (to_days('20180405')),
	PARTITION p6 VALUES  LESS THAN MAXVALUE 
  );
  CREATE INDEX index_userorder_id1 ON user_order(createTime ASC, userId ASC, category ASC,orderId ASC);
   CREATE INDEX index_userorder_status1 ON user_order(createTime ASC, userId ASC, category ASC,status ASC);
  
  