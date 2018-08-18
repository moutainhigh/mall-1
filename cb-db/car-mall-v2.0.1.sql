




#INSERT INTO `profile` (`FILE_ID`, `PROFILE_NAME`, `FILE_VALUE`, `IS_PICTURE`, `REMARKS`) VALUES ('11', 'SHARE_PATH', 'http://test.app.999shuijingqiu.com/register.html?invitationCode=', '0', '');
#INSERT INTO `profile` (`FILE_ID`, `PROFILE_NAME`, `FILE_VALUE`, `IS_PICTURE`, `REMARKS`) VALUES ('12', 'SHARE_TITLE', '云信 - 让生活更美好', '0', '');
#INSERT INTO `profile` (`FILE_ID`, `PROFILE_NAME`, `FILE_VALUE`, `IS_PICTURE`, `REMARKS`) VALUES ('13', 'SHARE_ICON', 'http://test.resource.999shuijingqiu.com/Firq1iyRsRVaVD4nxDfLlBexjoA5', '1', '');
#INSERT INTO `profile` (`FILE_ID`, `PROFILE_NAME`, `FILE_VALUE`, `IS_PICTURE`, `REMARKS`) VALUES ('14', 'SHARE_DESCRIPTION', '邀请您注册云信，成为尊贵的云信会员，体验休闲经济带来美好生活！', '0', '');
#INSERT INTO `profile` (`FILE_ID`, `PROFILE_NAME`, `FILE_VALUE`, `IS_PICTURE`, `REMARKS`) VALUES ('15', 'SHARE_SHORTMESSAGE_CONTENT', '邀请您注册云信，成为尊贵的云信会员，体验休闲经济带来美好生活！', '0', '');
##这些需要改动，上哪个环境，需要改成对应配置

update insurance_product set INSURE_PERIOD='TEN_YEAR' where INSURE_PERIOD='10年';
update insurance_product set INSURE_PERIOD='TWENTY_YEAR' where INSURE_PERIOD='20年';
update insurance_product set INSURE_PERIOD='LIFITIME' where INSURE_PERIOD='终身';
update insurance_product set PROTECTION_YEAR='TEN_YEAR' where PROTECTION_YEAR='10年';
update insurance_product set PROTECTION_YEAR='TWENTY_YEAR' where PROTECTION_YEAR='20年';
update insurance_product set PROTECTION_YEAR='LIFITIME' where PROTECTION_YEAR='终身';
###add by lxc 2018-08-10 17:58
ALTER TABLE `crystal_ball`.`rb_funds_pool_log`
ADD COLUMN `ITEM_ID` int(11) NULL COMMENT '订单详情ID/报账详情ID' AFTER `TRANSACTION_ID`;

##add  by guwenshao 2018-8-10
ALTER TABLE `order_item` add `COST_PRICE` float NOT NULL COMMENT '成本价';

#add by wangteng 2018-08-11
alter table rb_reimbursement add TAX_RATE DECIMAL(10,4) DEFAULT 0;


##add  by guwenshao 2018-8-11
INSERT INTO `profile` (`PROFILE_NAME`, `FILE_VALUE`, `IS_PICTURE`, `REMARKS`) VALUES ('MAX_LOAN_NUM', '5', '0', '最多借款次数');

##add  by tangou 2018-8-13
ALTER TABLE bank_info MODIFY COLUMN CARD_TYPE VARCHAR(60) DEFAULT NULL COMMENT '证件类型';

##add by guwenshao 2018-08-13
ALTER TABLE `order_form` add  `DELIVER_TIME` datetime DEFAULT NULL COMMENT '发货时间';
ALTER TABLE `order_form` add  `COLLECT_TIME` datetime DEFAULT NULL COMMENT '收货时间';

DROP TABLE IF EXISTS `history_record`;

##add by eleven 2018-08-14
CREATE TABLE `history_record` (
  `RECORD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL,
  `SALE_PRICE` float NOT NULL,
  `COMMODITY_ID` int(11) NOT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  PRIMARY KEY (`RECORD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='浏览记录表';

DROP TABLE IF EXISTS `insurance_order_code`;
CREATE TABLE `insurance_order_code` (
  `CODE_ID` int(10) NOT NULL COMMENT '编码ID',
  `CODE_NO` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '编码',
  `USEED` int(10) DEFAULT NULL COMMENT '是否使用',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`CODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

##add by pengcong 2018-08-14
ALTER TABLE `brand` ADD COLUMN `SORT` int(11) COMMENT '热门品牌排序';

ALTER TABLE customer modify column ENABLED int(2);
ALTER TABLE `insurance_informed_matter` add  `INSURE_PEOPLE` INT(2) DEFAULT 0 COMMENT '投保人';
ALTER TABLE `insurance_informed_matter` add  `INSURED_PEOPLE` INT(2) DEFAULT 0 COMMENT '被保人';

###add by lxc 2018-08-14 15:58
ALTER TABLE `crystal_ball`.`catalog`
MODIFY COLUMN `RATIO` decimal(10, 5) NULL DEFAULT 1.00000 COMMENT '分类比例配置' AFTER `SUPPORT_ADDED_TAX`;
ALTER TABLE `crystal_ball`.`catalog`
MODIFY COLUMN `RATIO` decimal(10, 5) NULL DEFAULT NULL COMMENT '分类比例配置' AFTER `SUPPORT_ADDED_TAX`;

##add by tangou 2018-08-15
ALTER TABLE `seller` ADD COLUMN `POSITION_X` varchar(32) COMMENT '商家经度';
ALTER TABLE `seller` ADD COLUMN `POSITION_Y` varchar(32) COMMENT '商家纬度';

##add by wangteng 2018-08-15
alter table insurance_order_code add SORT int(11) DEFAULT 0;
DROP TABLE IF EXISTS `insurance_email`;
CREATE TABLE `insurance_email` (
  `INSURANCE_EMAIL_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `FROM_EMAIL` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发送邮箱',
  `RECEIVE_EMAIL` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '接收邮箱',
  `CONTEXT` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发送内容',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`insurance_email_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='保单合同邮件提醒';
ALTER TABLE `favorite` ADD COLUMN `PRODUCT_ID` int(11) COMMENT '货品id';
ALTER TABLE `history_record` ADD COLUMN `PRODUCT_ID` int(11) COMMENT '货品id';

##add by tangou 2018-08-16
ALTER TABLE `seller` ADD COLUMN `PROVINCE` varchar(32) COMMENT '商家省';
ALTER TABLE `seller` ADD COLUMN `CITY` varchar(32) COMMENT '商家市';
ALTER TABLE `seller` ADD COLUMN `DISTRICT` varchar(32) COMMENT '商家区';
##add by tangou 2018-08-16
ALTER TABLE `seller` ADD COLUMN `PROVINCE_NAME` varchar(32) COMMENT '商家省名称';
ALTER TABLE `seller` ADD COLUMN `CITY_NAME` varchar(32) COMMENT '商家市名称';
ALTER TABLE `seller` ADD COLUMN `DISTRICT_NAME` varchar(32) COMMENT '商家区名称';

##add by wangteng 2018-08-16
INSERT INTO `profile` (`PROFILE_NAME`, `FILE_VALUE`, `IS_PICTURE`, `REMARKS`) VALUES ('INSURANCE_CODE_RECEIVE_EMAIL', 'Wangt@999shuijingqiu.com', '0', '保单合同编号接收邮箱');
INSERT INTO `profile` (`PROFILE_NAME`, `FILE_VALUE`, `IS_PICTURE`, `REMARKS`) VALUES ('INSURANCE_CODE_RECEIVE_CONTEXT', '保险合同号库存不足,请尽快导入保险合同号', '0', '保单合同编号发送内容');


##add by lxc 2018-08-16 19:21
ALTER TABLE `crystal_ball`.`commodity`
MODIFY COLUMN `RATIO` decimal(10, 0) NULL DEFAULT NULL COMMENT '商品比例配置' AFTER `SETTING_CONTENT`;
ALTER TABLE `crystal_ball`.`catalog`
MODIFY COLUMN `RATIO` decimal(10, 0) NULL DEFAULT NULL COMMENT '分类比例配置' AFTER `SUPPORT_ADDED_TAX`;

##add by pengcong 2018-08-17
CREATE TABLE `floor_advert`  (
  `ADVERT_ID` int(11) NOT NULL,
  `FLOOR_ID` int(11) NOT NULL,
  `SORT_ORDER` int(11) NOT NULL,
  PRIMARY KEY (`ADVERT_ID`, `FLOOR_ID`) USING BTREE,
  INDEX `FK_7tcdsbs43j61a2b9rc46ow40k`(`FLOOR_ID`) USING BTREE,
  CONSTRAINT `floor_advert_ibfk_1` FOREIGN KEY (`FLOOR_ID`) REFERENCES `home_floor` (`FLOOR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `floor_advert_ibfk_2` FOREIGN KEY (`ADVERT_ID`) REFERENCES `advertisement` (`ADVERT_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

ALTER TABLE `home_floor` ADD COLUMN `ADVERT_AMOUNT` int(11) NOT NULL AFTER `BRAND_AMOUNT`;

##add by gws 2018-08-17
ALTER TABLE `order_form` add `PAYMENT_STATE` int(11) DEFAULT NULL COMMENT '支付状态';
ALTER TABLE `order_item` add `PRODUCT_NAME` varchar(64) DEFAULT NULL COMMENT '货品名称';
ALTER TABLE `order_item` add `PRODUCT_NO` varchar(32) DEFAULT NULL COMMENT '货品编号';
ALTER TABLE `order_item` add `MARKET_PRICE` float NOT NULL COMMENT '市场价';
ALTER TABLE `order_item` add `VOLUME` float DEFAULT NULL COMMENT '体积';
ALTER TABLE `order_item` add `WEIGHT` float DEFAULT NULL COMMENT '重量';