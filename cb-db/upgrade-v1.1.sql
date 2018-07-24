--add by guwenshao 2018-07-21
drop table IF EXISTS `customer_wallet`;
CREATE TABLE `customer_wallet` (
  `CUSTOMER_ID` int(11) NOT NULL COMMENT '客户钱包id',
  `AVAILABLE_BALANCE` double DEFAULT 0 COMMENT '可用余额',
  `EXPECTED_RETURN_AMOUNT` double DEFAULT 0 COMMENT '预期收益金额',
  `LOAN_QUOTA` double DEFAULT 0 COMMENT '可贷额度',
  `ARREARS_AMOUNT` double DEFAULT 0 COMMENT '欠款金额',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `customer_wallet_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户钱包表';

drop table IF EXISTS `order_loan_apply`;
CREATE TABLE `order_loan_apply` (
  `LOAN_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '贷款id',
  `LOAN_CODE` varchar(32) NOT NULL COMMENT '贷款编号',
  `CUSTOMER_ID` int(11) NOT NULL COMMENT '客户id',
  `ORDER_ID` int(11) NOT NULL COMMENT '订单id',
  `LOAN_STATE` int(11) DEFAULT NULL COMMENT '贷款状态 0:申请贷款 1:贷款通过 2:贷款失败3：取消贷款 ',
  `LOAN_PRICE` double DEFAULT NULL COMMENT '贷款金额',
  `AUDIT_REMARK` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `AUDIT_STATE` int(11) NOT NULL COMMENT '审核状态 0:待审核 1:审核通过，2：审核失败',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '申请时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`LOAN_ID`) USING BTREE,
  KEY `FK_oql18xgkuy2497dxxwiogy1o` (`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `loan_apply_ibfk_2` FOREIGN KEY (`ORDER_ID`) REFERENCES `order_form` (`ORDER_ID`),
  CONSTRAINT `loan_apply_ibfk_3` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='贷款申请表';

--add by guwenshao 2018-07-23
alter table product add `RESERVED_STORE_NUM` int(11) DEFAULT 0 COMMENT '预占的库存数';

--add by wangteng 2018-07-23
alter table customer add CARD_TYPE VARCHAR(32) NOT NULL COMMENT '证件类型';
alter table customer add CUSTOMER_CARD_NO VARCHAR(32) NOT NULL COMMENT '证件号码';
alter table customer add CARD_POSITIVE_IMG VARCHAR(255) NOT NULL COMMENT '证件证明照';
alter table customer add CARD_NEGATIVE_IMG VARCHAR(255) NOT NULL COMMENT '证件反面照';
alter table customer add BANK_CARD_IMG VARCHAR(255) NOT NULL COMMENT '银行卡图片';
alter table customer add CUSTOMER_LEVEL INT(10) DEFAULT NULL COMMENT '等级';
alter table customer add LEVEL_CODE VARCHAR(255) DEFAULT NULL COMMENT '等级编码';
alter table customer add INVITATION_CODE VARCHAR(25) DEFAULT NULL COMMENT '邀请码';
