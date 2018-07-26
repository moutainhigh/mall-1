##add by guwenshao 2018-07-21
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
) ENGINE=InnoDB  COMMENT='客户钱包表';

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
) ENGINE=InnoDB COMMENT='贷款申请表';

##add by guwenshao 2018-07-23
alter table product add `RESERVED_STORE_NUM` int(11) DEFAULT 0 COMMENT '预占的库存数';

##add by wangteng 2018-07-23
alter table customer add CARD_TYPE VARCHAR(32) NOT NULL COMMENT '证件类型';
alter table customer add CUSTOMER_CARD_NO VARCHAR(32) NOT NULL COMMENT '证件号码';
alter table customer add CARD_POSITIVE_IMG VARCHAR(255) NOT NULL COMMENT '证件证明照';
alter table customer add CARD_NEGATIVE_IMG VARCHAR(255) NOT NULL COMMENT '证件反面照';
alter table customer add BANK_CARD_IMG VARCHAR(255) NOT NULL COMMENT '银行卡图片';
alter table customer add CUSTOMER_LEVEL INT(10) DEFAULT 1  COMMENT '等级';
alter table customer add LEVEL_CODE VARCHAR(255) DEFAULT NULL COMMENT '等级编码';
alter table customer add INVITATION_CODE VARCHAR(25) DEFAULT NULL COMMENT '邀请码';

##add by likang 2018-07-24
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `ATTACH_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OBJECT_TYPE` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '业务对象类型',
  `OBJECT_ID` int(11) NOT NULL COMMENT '业务对象ID',
  `BUSINESS_SCENARIO` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '业务应用场景编码',
  `INPUT_ID` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件名ID',
  `FILE_PATH` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '访问路径',
  `FILE_NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '附件名称',
  `FILE_TYPE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件类型',
  `FILE_SIZE` int(11) DEFAULT NULL COMMENT '文件大小',
  `FS_GUID` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件存储系统标识',
  `CREATE_TIME` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '上传时间',
  `STAFF_ID` int(11) DEFAULT NULL COMMENT '上传人ID',
  `STAFF_NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上传人',
  `STATE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '状态',
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ATTACH_ID`)
) ENGINE=InnoDB;

###add by guwenshao 2018-07-25
drop table IF EXISTS `customer_trading_record`;
CREATE TABLE `customer_trading_record` (
  `TRADE_RECORD_ID` int(11) NOT NULL COMMENT '交易流水id',
  `CUSTOMER_ID` int(11) NOT NULL COMMENT '客户id',
  `BUSINESS_TYPE` int(11) NOT NULL COMMENT '业务类型:0余额，1:贷款预期收益 2:贷款额度 3:贷款金额',
  `OPERATION_TYPE` int(11) NOT NULL COMMENT '操作类型:0增加，1减少',
  `AMOUNT` double DEFAULT 0 COMMENT '操作余额',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`TRADE_RECORD_ID`) USING BTREE,
  CONSTRAINT `customer_trading_record_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`)
) ENGINE=InnoDB COMMENT='客户交易流水表';

##add by pengcong 2018-07-25
DROP TABLE IF EXISTS `floor_brand`;
CREATE TABLE `floor_brand`  (
  `BRAND_ID` int(11) NOT NULL,
  `FLOOR_ID` int(11) NOT NULL,
  `SORT_ORDER` int(11) NOT NULL,
  PRIMARY KEY (`BRAND_ID`, `FLOOR_ID`) USING BTREE,
  INDEX `FK_7tcdsbs43j61a2b9rc46ow49k`(`FLOOR_ID`) USING BTREE,
  CONSTRAINT `floor_brand_ibfk_1` FOREIGN KEY (`FLOOR_ID`) REFERENCES `home_floor` (`FLOOR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `floor_brand_ibfk_2` FOREIGN KEY (`BRAND_ID`) REFERENCES `brand` (`BRAND_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB;


ALTER TABLE home_floor add BRAND_AMOUNT int(11)  default (0) not null;
ALTER TABLE category ADD COLUMN `LOWEST_PRICE` decimal(5, 2) default (0) NOT NULL COMMENT '最低价格';
ALTER TABLE category ADD COLUMN `HIGHEST_PRICE` decimal(5, 2) default (0) NOT NULL COMMENT '最高价格';
ALTER TABLE commodity ADD COLUMN `EXPLAIN_CONTENT` VARCHAR(4098)   COMMENT '商品说明内容';
##add by wangteng 2018-07-25
alter table customer add POLICY bit(1) DEFAULT 0 COMMENT '是否买过保单';

