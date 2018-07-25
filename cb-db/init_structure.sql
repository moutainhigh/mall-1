/*
 Navicat Premium Data Transfer

 Source Server         : local-server
 Source Server Type    : MySQL
 Source Server Version : 100308
 Source Host           : 192.168.0.104:3306
 Source Schema         : crystal_ball

 Target Server Type    : MySQL
 Target Server Version : 100308
 File Encoding         : 65001

 Date: 25/07/2018 20:49:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for acrivity_acrivity_rule
-- ----------------------------
DROP TABLE IF EXISTS `acrivity_acrivity_rule`;
CREATE TABLE `acrivity_acrivity_rule`  (
  `RULE_ID` int(11) NOT NULL,
  `ACRIVITY_ID` int(11) NOT NULL,
  PRIMARY KEY (`ACRIVITY_ID`, `RULE_ID`) USING BTREE,
  INDEX `FK_karokmp6q864apb3pp5pc221d`(`RULE_ID`) USING BTREE,
  CONSTRAINT `acrivity_acrivity_rule_ibfk_1` FOREIGN KEY (`RULE_ID`) REFERENCES `activity_rule` (`RULE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `acrivity_acrivity_rule_ibfk_2` FOREIGN KEY (`ACRIVITY_ID`) REFERENCES `activity` (`ACTIVITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `ACTIVITY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACTIVITY_ALIAS` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ACTIVITY_CATEGORY_IDS` tinyblob NULL,
  `ACTIVITY_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ACTIVITY_NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ACTIVITY_SCOPE` int(11) NOT NULL,
  `ACTIVITY_STATE` int(11) NOT NULL,
  `ACTIVITY_TYPE` int(11) NOT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `ENABLED` bit(1) NOT NULL,
  `END_TIME` datetime(0) NULL DEFAULT NULL,
  `LIMIT_AMOUNT` bit(1) NOT NULL,
  `LIMIT_AMOUNT_SIZE` int(11) NULL DEFAULT NULL,
  `PIC_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `RULE_IDS` tinyblob NULL,
  `RULE_MUTEX` bit(1) NOT NULL,
  `START_TIME` datetime(0) NULL DEFAULT NULL,
  `SUPPORT_COUPON` bit(1) NOT NULL,
  `RULE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ACTIVITY_ID`) USING BTREE,
  INDEX `FK_jgwg2676agiy79j6x47pihnty`(`RULE_ID`) USING BTREE,
  CONSTRAINT `activity_ibfk_1` FOREIGN KEY (`RULE_ID`) REFERENCES `rule_condition` (`RULE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for activity_commodity
-- ----------------------------
DROP TABLE IF EXISTS `activity_commodity`;
CREATE TABLE `activity_commodity`  (
  `ACTIVITY_ID` int(11) NOT NULL,
  `COMMODITY_ID` int(11) NOT NULL,
  `LIMIT_AMOUNT_SIZE` int(11) NOT NULL,
  PRIMARY KEY (`ACTIVITY_ID`, `COMMODITY_ID`) USING BTREE,
  INDEX `FK_gl96xogh96xpi74thfaspd6mq`(`COMMODITY_ID`) USING BTREE,
  CONSTRAINT `activity_commodity_ibfk_1` FOREIGN KEY (`COMMODITY_ID`) REFERENCES `commodity` (`COMMODITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `activity_commodity_ibfk_2` FOREIGN KEY (`ACTIVITY_ID`) REFERENCES `activity` (`ACTIVITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for activity_rule
-- ----------------------------
DROP TABLE IF EXISTS `activity_rule`;
CREATE TABLE `activity_rule`  (
  `RULE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `RULE_KEY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `RULE_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `RULE_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `RULE_VALUE` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`RULE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for advertisement
-- ----------------------------
DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE `advertisement`  (
  `ADVERT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADVERT_CODE` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ADVERT_TITLE` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ADVERT_URL` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ADVERTISEMENT_PLACE` int(11) NOT NULL,
  `ADVERTISEMENT_TYPE` int(11) NOT NULL,
  `ADVERTISEMENT_URLTYPE` int(11) NOT NULL,
  `CLIENT_TYPES` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONTENT` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `ENABLED` bit(1) NOT NULL,
  `PIC_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TEMPLATE_PATH` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `VIDEO_PATH` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ADVERT_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `ARTICLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ARTICLE_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ARTICLE_TITLE` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `AUTHOR` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONTENT` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `ENABLED` bit(1) NOT NULL,
  `ORDER_TIME` datetime(0) NULL DEFAULT NULL,
  `ORIGIN` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PIC_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PUBLISH_DATE` date NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SHORT_TITLE` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SUMMARY` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `TITLE_COLOR` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PROGRAMA_ID` int(11) NOT NULL,
  `SUBJECT_ID` int(11) NULL DEFAULT NULL,
  `STEP_ENABLED` bit(1) NOT NULL,
  PRIMARY KEY (`ARTICLE_ID`) USING BTREE,
  INDEX `FK_9ljvs57ea3tit7riapb3oflo6`(`PROGRAMA_ID`) USING BTREE,
  INDEX `FK_pfmi5j46nwh379hs111fchjau`(`SUBJECT_ID`) USING BTREE,
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`SUBJECT_ID`) REFERENCES `special_subject` (`SUBJECT_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `article_ibfk_2` FOREIGN KEY (`PROGRAMA_ID`) REFERENCES `programa` (`PROGRAMA_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 307 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_channel
-- ----------------------------
DROP TABLE IF EXISTS `article_channel`;
CREATE TABLE `article_channel`  (
  `CHANNEL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ARTICLE_CHANNEL_TYPES` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CHANNEL_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CHANNEL_KEY` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CHANNEL_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CHANNEL_STYLE` int(11) NOT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `DESCRIPTION` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ENABLED` bit(1) NOT NULL,
  `OPERA_IMG_PATH` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SEO_DESCRIPTION` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SEO_KEY` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SEO_TITLE` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SORT_ORDER` int(11) NOT NULL,
  `TIP_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CHANNEL_POSITION` int(11) NOT NULL,
  `CHANNEL_URL` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CHANNEL_VIDEO_URL` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`CHANNEL_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_recipe_step
-- ----------------------------
DROP TABLE IF EXISTS `article_recipe_step`;
CREATE TABLE `article_recipe_step`  (
  `STEP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PIC_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `STEP_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `STEP_ORDER` int(11) NULL DEFAULT NULL,
  `STEP_WHEN` int(11) NULL DEFAULT NULL,
  `ARTICLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`STEP_ID`) USING BTREE,
  INDEX `FK_lkeo3sdkj529tgii97ob1fn7n`(`ARTICLE_ID`) USING BTREE,
  CONSTRAINT `FK_lkeo3sdkj529tgii97ob1fn7n` FOREIGN KEY (`ARTICLE_ID`) REFERENCES `article` (`ARTICLE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment`  (
  `ATTACH_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OBJECT_TYPE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '业务对象类型',
  `OBJECT_ID` int(11) NOT NULL COMMENT '业务对象ID',
  `BUSINESS_SCENARIO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '业务应用场景编码',
  `INPUT_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件名ID',
  `FILE_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '访问路径',
  `FILE_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '附件名称',
  `FILE_TYPE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  `FILE_SIZE` int(11) NULL DEFAULT NULL COMMENT '文件大小',
  `FS_GUID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件存储系统标识',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上传时间',
  `STAFF_ID` int(11) NULL DEFAULT NULL COMMENT '上传人ID',
  `STAFF_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上传人',
  `STATE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态',
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ATTACH_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for attribute
-- ----------------------------
DROP TABLE IF EXISTS `attribute`;
CREATE TABLE `attribute`  (
  `ATTRIBUTE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ATTRIBUTE_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IMAGE_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SORT_ORDER` smallint(6) NULL DEFAULT NULL,
  `GROUP_ID` int(11) NOT NULL,
  PRIMARY KEY (`ATTRIBUTE_ID`) USING BTREE,
  INDEX `FK_cv25wj6me65bqxoft8or4h7wh`(`GROUP_ID`) USING BTREE,
  CONSTRAINT `attribute_ibfk_1` FOREIGN KEY (`GROUP_ID`) REFERENCES `attribute_group` (`GROUP_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 808 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for attribute_group
-- ----------------------------
DROP TABLE IF EXISTS `attribute_group`;
CREATE TABLE `attribute_group`  (
  `GROUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `GROUP_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SHOW_AS_IMAGE` bit(1) NOT NULL,
  `COMMODITY_ID` int(11) NOT NULL,
  PRIMARY KEY (`GROUP_ID`) USING BTREE,
  INDEX `FK_e3cmdcvq45mg45ev94b3er7o1`(`COMMODITY_ID`) USING BTREE,
  CONSTRAINT `attribute_group_ibfk_1` FOREIGN KEY (`COMMODITY_ID`) REFERENCES `commodity` (`COMMODITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 435 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`  (
  `BRAND_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BRAND_EN_NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `BRAND_KEY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `BRAND_NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `BRAND_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `BRAND_TITLE` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `DESCRIPTION` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `DISPLAY` bit(1) NOT NULL,
  `ENABLED` bit(1) NOT NULL,
  `HOT` bit(1) NOT NULL,
  `PIC_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SEO_DESCRIPTION` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SEO_KEY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SEO_TITLE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `WEBSITE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CATEGORY_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`BRAND_ID`) USING BTREE,
  INDEX `FK_to44ocjngm85awc54c5dmdvoa`(`CATEGORY_ID`) USING BTREE,
  CONSTRAINT `brand_ibfk_1` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`CATEGORY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 234 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for catalog
-- ----------------------------
DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog`  (
  `CATALOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATALOG_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CATALOG_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `ENABLED` bit(1) NOT NULL,
  `LEAF` bit(1) NOT NULL,
  `REMARK` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SORT_ORDER` int(11) NOT NULL,
  `PARENT_CATALOG_ID` int(11) NOT NULL,
  `SUPPORT_ADDED_TAX` bit(1) NOT NULL,
  PRIMARY KEY (`CATALOG_ID`) USING BTREE,
  INDEX `FK_sqpor725u394w5vjfok0qfirw`(`PARENT_CATALOG_ID`) USING BTREE,
  CONSTRAINT `catalog_ibfk_1` FOREIGN KEY (`PARENT_CATALOG_ID`) REFERENCES `catalog` (`CATALOG_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 156 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for catalog_attribute
-- ----------------------------
DROP TABLE IF EXISTS `catalog_attribute`;
CREATE TABLE `catalog_attribute`  (
  `ATTRIBUTE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ATTRIBUTE_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IMAGE_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SORT_ORDER` smallint(6) NULL DEFAULT NULL,
  `GROUP_ID` int(11) NOT NULL,
  PRIMARY KEY (`ATTRIBUTE_ID`) USING BTREE,
  INDEX `FK_jy501ubwuqf6bjretoknbe7lq`(`GROUP_ID`) USING BTREE,
  CONSTRAINT `catalog_attribute_ibfk_1` FOREIGN KEY (`GROUP_ID`) REFERENCES `catalog_attribute_group` (`GROUP_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 190 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for catalog_attribute_group
-- ----------------------------
DROP TABLE IF EXISTS `catalog_attribute_group`;
CREATE TABLE `catalog_attribute_group`  (
  `GROUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `GROUP_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SHOW_AS_IMAGE` bit(1) NOT NULL,
  `CATALOG_ID` int(11) NOT NULL,
  PRIMARY KEY (`GROUP_ID`) USING BTREE,
  INDEX `FK_iw7ew8ey039vk92y4s5esenvl`(`CATALOG_ID`) USING BTREE,
  CONSTRAINT `catalog_attribute_group_ibfk_1` FOREIGN KEY (`CATALOG_ID`) REFERENCES `catalog` (`CATALOG_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `CATEGORY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY_KEY` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CATEGORY_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CATEGORY_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `DESCRIPTION` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ENABLED` bit(1) NOT NULL,
  `ICON_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `LEVEL` int(11) NOT NULL,
  `RECOMMEND` bit(1) NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SEO_DESCRIPTION` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SEO_KEY` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SEO_TITLE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SORT_ORDER` int(11) NOT NULL,
  `PARENT_CATEGORY_ID` int(11) NOT NULL,
  `LOWEST_PRICE` decimal(5, 2) NOT NULL COMMENT '最低价格',
  `HIGHEST_PRICE` decimal(5, 2) NOT NULL COMMENT '最高价格',
  PRIMARY KEY (`CATEGORY_ID`) USING BTREE,
  INDEX `FK_lw3gvrhwvg6yyyeh2gc65uwj4`(`PARENT_CATEGORY_ID`) USING BTREE,
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`PARENT_CATEGORY_ID`) REFERENCES `category` (`CATEGORY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 305 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for category_activity
-- ----------------------------
DROP TABLE IF EXISTS `category_activity`;
CREATE TABLE `category_activity`  (
  `CATEGORY_ID` int(11) NOT NULL,
  `ACTIVITY_ID` int(11) NOT NULL,
  PRIMARY KEY (`ACTIVITY_ID`, `CATEGORY_ID`) USING BTREE,
  INDEX `FK_fx2grr14qeba6fqragwqh0umc`(`CATEGORY_ID`) USING BTREE,
  CONSTRAINT `category_activity_ibfk_1` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`CATEGORY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `category_activity_ibfk_2` FOREIGN KEY (`ACTIVITY_ID`) REFERENCES `activity` (`ACTIVITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for combination
-- ----------------------------
DROP TABLE IF EXISTS `combination`;
CREATE TABLE `combination`  (
  `COMBINATION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMBINATION_TYPE` int(11) NOT NULL,
  `COMBINED_COMMODITY_ID` int(11) NOT NULL,
  `COMMODITY_ID` int(11) NOT NULL,
  PRIMARY KEY (`COMBINATION_ID`) USING BTREE,
  INDEX `FK_h4lf90uu0ow0onmdyv7p6knhb`(`COMBINED_COMMODITY_ID`) USING BTREE,
  INDEX `FK_l0dx56yhfl2taiqelx8tn4d9g`(`COMMODITY_ID`) USING BTREE,
  CONSTRAINT `combination_ibfk_1` FOREIGN KEY (`COMMODITY_ID`) REFERENCES `commodity` (`COMMODITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `combination_ibfk_2` FOREIGN KEY (`COMBINED_COMMODITY_ID`) REFERENCES `commodity` (`COMMODITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `COMMENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMMENT_TIME` datetime(0) NULL DEFAULT NULL,
  `CONTENT` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `SHARE_ID` int(11) NOT NULL,
  PRIMARY KEY (`COMMENT_ID`) USING BTREE,
  INDEX `FK_7dcqsgrex6ea8jlrvhvjk1xaa`(`CUSTOMER_ID`) USING BTREE,
  INDEX `FK_guqo8vvnnxb2r9wn9txujc6l4`(`SHARE_ID`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`SHARE_ID`) REFERENCES `share` (`SHARE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`  (
  `COMMODITY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AUDIT_REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `BARTER` bit(1) NULL DEFAULT NULL,
  `CITY` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `COMMODITY_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `COMMODITY_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `COMMODITY_PYNAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `COMMODITY_STATE` int(11) NOT NULL,
  `COMMODITY_TITLE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONTENT` varchar(4098) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `COST_PRICE` double NOT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `DEFAULT_PIC_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `DELIVERY_TYPE` int(11) NULL DEFAULT NULL,
  `DESCRIPTION` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `FORBID_AIR_CARGO` bit(1) NULL DEFAULT NULL,
  `GIVEAWAY` bit(1) NULL DEFAULT NULL,
  `MARKET_PRICE` double NULL DEFAULT NULL,
  `POPULAR` bit(1) NULL DEFAULT NULL,
  `PRE_SELL` bit(1) NULL DEFAULT NULL,
  `PROVINCE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PUBLISH_STATE` int(11) NOT NULL,
  `RECOMMEND` bit(1) NULL DEFAULT NULL,
  `SALE_NUM` int(11) NULL DEFAULT NULL,
  `SELL_PRICE` double NOT NULL,
  `SEO_DESCRIPTION` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SEO_KEY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SEO_TITLE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SHORT_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SPECIAL` bit(1) NULL DEFAULT NULL,
  `UNIT` int(11) NOT NULL,
  `VOLUME` double NULL DEFAULT NULL,
  `WEIGHT` double NULL DEFAULT NULL,
  `BRAND_ID` int(11) NOT NULL,
  `CATALOG_ID` int(11) NOT NULL,
  `SECTION_ID` int(11) NOT NULL,
  `SELLER_ID` int(11) NOT NULL,
  `PACKING_LIST` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`COMMODITY_ID`) USING BTREE,
  INDEX `FK_1pf0on6vwjljary6qnywulp3t`(`BRAND_ID`) USING BTREE,
  INDEX `FK_dwdnkeuj13wmlewaytr6cepf4`(`CATALOG_ID`) USING BTREE,
  INDEX `FK_39sgwcf0tc1g6ptjnisu52ip9`(`SECTION_ID`) USING BTREE,
  INDEX `FK_nwu9i88fak5x07l6qrd8e8ltu`(`SELLER_ID`) USING BTREE,
  CONSTRAINT `commodity_ibfk_1` FOREIGN KEY (`SELLER_ID`) REFERENCES `seller` (`SELLER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `commodity_ibfk_2` FOREIGN KEY (`BRAND_ID`) REFERENCES `brand` (`BRAND_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `commodity_ibfk_3` FOREIGN KEY (`SECTION_ID`) REFERENCES `price_section` (`SECTION_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `commodity_ibfk_4` FOREIGN KEY (`CATALOG_ID`) REFERENCES `catalog` (`CATALOG_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 402 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for commodity_category
-- ----------------------------
DROP TABLE IF EXISTS `commodity_category`;
CREATE TABLE `commodity_category`  (
  `COCA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `RECOMMEND_VALUE` int(11) NOT NULL,
  `CATEGORY_ID` int(11) NOT NULL,
  `COMMODITY_ID` int(11) NOT NULL,
  PRIMARY KEY (`COCA_ID`) USING BTREE,
  INDEX `FK_qrxhafxgc6b6cve727x5qmdqu`(`CATEGORY_ID`) USING BTREE,
  INDEX `FK_7bceg4pnb4sikqcq71j7ekyxt`(`COMMODITY_ID`) USING BTREE,
  CONSTRAINT `commodity_category_ibfk_1` FOREIGN KEY (`COMMODITY_ID`) REFERENCES `commodity` (`COMMODITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `commodity_category_ibfk_2` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`CATEGORY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 417 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for commodity_category_filter
-- ----------------------------
DROP TABLE IF EXISTS `commodity_category_filter`;
CREATE TABLE `commodity_category_filter`  (
  `COCA_ID` int(11) NOT NULL,
  `ITEM_ID` int(11) NOT NULL,
  PRIMARY KEY (`COCA_ID`, `ITEM_ID`) USING BTREE,
  INDEX `FK_saok19feftjywlfof57pep98p`(`ITEM_ID`) USING BTREE,
  CONSTRAINT `commodity_category_filter_ibfk_1` FOREIGN KEY (`COCA_ID`) REFERENCES `commodity_category` (`COCA_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `commodity_category_filter_ibfk_2` FOREIGN KEY (`ITEM_ID`) REFERENCES `filter_item` (`ITEM_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for commodity_spec
-- ----------------------------
DROP TABLE IF EXISTS `commodity_spec`;
CREATE TABLE `commodity_spec`  (
  `COMMODITY_ID` int(11) NOT NULL,
  `SPEC_ID` int(11) NOT NULL,
  `VALUE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`COMMODITY_ID`, `SPEC_ID`) USING BTREE,
  INDEX `FK_dy93l7ue3x8i3r2pix9jccvx7`(`SPEC_ID`) USING BTREE,
  CONSTRAINT `commodity_spec_ibfk_1` FOREIGN KEY (`SPEC_ID`) REFERENCES `spec` (`SPEC_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `commodity_spec_ibfk_2` FOREIGN KEY (`COMMODITY_ID`) REFERENCES `commodity` (`COMMODITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for complaint
-- ----------------------------
DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint`  (
  `COMPLAINT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `ENABLED` bit(1) NOT NULL,
  `TITLE` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CUSTOMER_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`COMPLAINT_ID`) USING BTREE,
  INDEX `FK_2us3coh3dv8ww78tfktdca9is`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `complaint_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for concent
-- ----------------------------
DROP TABLE IF EXISTS `concent`;
CREATE TABLE `concent`  (
  `CONCENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONCENT_CODE` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONCENT_NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `PAD_STATE` int(11) NOT NULL,
  `ONLINE` bit(1) NOT NULL,
  PRIMARY KEY (`CONCENT_ID`) USING BTREE,
  INDEX `FK_31dpjicgakr2n2xl559hg7yny`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `concent_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `COUPON_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COUPON_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `COUPON_STATE` int(11) NOT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `RECEIVE_STATUS` bit(1) NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `USED_AMOUNT` double NULL DEFAULT NULL,
  `USED_TIME` datetime(0) NULL DEFAULT NULL,
  `SCHEME_ID` int(11) NOT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  PRIMARY KEY (`COUPON_ID`) USING BTREE,
  INDEX `FK_68a8ea5w1igf24h7sbquff55v`(`SCHEME_ID`) USING BTREE,
  INDEX `FK_a4nq3kr5hdonaqjsq1owcnga3`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `coupon_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `coupon_ibfk_2` FOREIGN KEY (`SCHEME_ID`) REFERENCES `coupon_schema` (`SCHEMA_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for coupon_schema
-- ----------------------------
DROP TABLE IF EXISTS `coupon_schema`;
CREATE TABLE `coupon_schema`  (
  `SCHEMA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `CREATE_USER` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `DISTRIBUTION_METHOD` int(11) NOT NULL,
  `DISTRIBUTION_NUM` int(11) NOT NULL,
  `ENABLED` bit(1) NOT NULL,
  `END_TIME` datetime(0) NULL DEFAULT NULL,
  `FACE_VALUE` double NOT NULL,
  `LIMIT_COMMODITY` bit(1) NOT NULL,
  `LOWEST_CONSUME` double NOT NULL,
  `REMARK` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SCHEME_NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SCHEME_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `START_TIME` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`SCHEMA_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for coupon_schema_uses
-- ----------------------------
DROP TABLE IF EXISTS `coupon_schema_uses`;
CREATE TABLE `coupon_schema_uses`  (
  `USE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACTIVITY_ID` int(11) NULL DEFAULT NULL,
  `BRAND_ID` int(11) NULL DEFAULT NULL,
  `CATEGORY_ID` int(11) NULL DEFAULT NULL,
  `COMMODITY_ID` int(11) NULL DEFAULT NULL,
  `SCHEME_ID` int(11) NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NULL DEFAULT NULL,
  `RANK_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`USE_ID`) USING BTREE,
  INDEX `FK_9cca4k75j1x6c8qo5bv2mmu4p`(`ACTIVITY_ID`) USING BTREE,
  INDEX `FK_fe6w2byhcw1bnqth1mfybq3m8`(`BRAND_ID`) USING BTREE,
  INDEX `FK_av3b9kal363ch7ue448fxsmx3`(`CATEGORY_ID`) USING BTREE,
  INDEX `FK_9k1u00a6ekb1a3022ngkera42`(`COMMODITY_ID`) USING BTREE,
  INDEX `FK_f9g9vkoujg8y2x727r1u02j26`(`SCHEME_ID`) USING BTREE,
  INDEX `FK_dxkmvf9kvdwjshlo8ppwytkqp`(`CUSTOMER_ID`) USING BTREE,
  INDEX `FK_g2p15x0tmxj8r4csjuwpjdi1f`(`RANK_ID`) USING BTREE,
  CONSTRAINT `coupon_schema_uses_ibfk_1` FOREIGN KEY (`RANK_ID`) REFERENCES `rank` (`RANK_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `coupon_schema_uses_ibfk_2` FOREIGN KEY (`ACTIVITY_ID`) REFERENCES `activity` (`ACTIVITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `coupon_schema_uses_ibfk_3` FOREIGN KEY (`COMMODITY_ID`) REFERENCES `commodity` (`COMMODITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `coupon_schema_uses_ibfk_4` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`CATEGORY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `coupon_schema_uses_ibfk_5` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `coupon_schema_uses_ibfk_6` FOREIGN KEY (`SCHEME_ID`) REFERENCES `coupon_schema` (`SCHEMA_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `coupon_schema_uses_ibfk_7` FOREIGN KEY (`BRAND_ID`) REFERENCES `brand` (`BRAND_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `CUSTOMER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `BIRTHDAY` datetime(0) NULL DEFAULT NULL,
  `CARD_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CITY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `CUSTOMER_TYPE` int(11) NOT NULL,
  `DISTRICT` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `EMAIL` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ENABLED` bit(1) NOT NULL,
  `EXCHANGE_INTEGRAL` int(11) NULL DEFAULT NULL,
  `INTEGRAL` int(11) NULL DEFAULT NULL,
  `MAIL_CHECKED` bit(1) NULL DEFAULT NULL,
  `MOBILE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MOBILE_CHECKED` bit(1) NULL DEFAULT NULL,
  `PASSWORD` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `POST_CODE` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PROVINCE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `QQ_ACCESS_TOKEN` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `QQ_FIGURE_URL` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `QQ_NICK_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `QQ_OPEN_ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REAL_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REMARK` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SEX` bit(1) NULL DEFAULT NULL,
  `TELEPHONE` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TOTAL_INTEGRAL` int(11) NULL DEFAULT NULL,
  `RANK` int(11) NULL DEFAULT NULL,
  `EMAIL_CHECKED` bit(1) NULL DEFAULT b'0',
  `RONG_CLOUD_TOKEN` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '融云TOKEN',
  `AVATAR_URL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `RECOMMEND_CUSTOMER_ID` int(11) NULL DEFAULT NULL COMMENT '推荐人',
  `NICK_NAME` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PRAISE` int(11) NOT NULL DEFAULT 0 COMMENT '推荐人点赞数',
  `PRAISE_NUM` int(11) NOT NULL DEFAULT 0,
  `CARD_TYPE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '证件类型',
  `CUSTOMER_CARD_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '证件号码',
  `CARD_POSITIVE_IMG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '证件证明照',
  `CARD_NEGATIVE_IMG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '证件反面照',
  `BANK_CARD_IMG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '银行卡图片',
  `CUSTOMER_LEVEL` int(10) NULL DEFAULT 1 COMMENT '等级',
  `LEVEL_CODE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '等级编码',
  `INVITATION_CODE` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邀请码',
  `POLICY` bit(1) NULL DEFAULT b'0' COMMENT '是否买过保单',
  PRIMARY KEY (`CUSTOMER_ID`) USING BTREE,
  UNIQUE INDEX `UK_9lu3bh6jcw1fpj58cbvwleqsk`(`ACCOUNT_NAME`) USING BTREE,
  UNIQUE INDEX `UK_psf9ttks9snc7bs52t4fdnc4u`(`MOBILE`) USING BTREE,
  UNIQUE INDEX `UK_avvcl5e49cemeb3kwghev2icy`(`QQ_OPEN_ID`) USING BTREE,
  INDEX `FK_o8uot2r9n15qwklo108v28eae`(`RANK`) USING BTREE,
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`RANK`) REFERENCES `rank` (`RANK_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 180 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for customer_friend
-- ----------------------------
DROP TABLE IF EXISTS `customer_friend`;
CREATE TABLE `customer_friend`  (
  `CUSTOMER_ID` int(11) NOT NULL,
  `FRIEND_ID` int(11) NOT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `ALIAS_NAME` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '别名',
  `TAG` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标签',
  `PHONE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `IMAGE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '相关图片',
  `STATE` int(2) UNSIGNED ZEROFILL NULL DEFAULT 00 COMMENT '好友状态：0正常，1黑名单',
  PRIMARY KEY (`CUSTOMER_ID`, `FRIEND_ID`) USING BTREE,
  INDEX `FK_1jaiofi0qbdqf8w7kb2kjs66h`(`FRIEND_ID`) USING BTREE,
  CONSTRAINT `customer_friend_ibfk_1` FOREIGN KEY (`FRIEND_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `customer_friend_ibfk_2` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for customer_friend_request
-- ----------------------------
DROP TABLE IF EXISTS `customer_friend_request`;
CREATE TABLE `customer_friend_request`  (
  `REQUEST_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int(11) NULL DEFAULT NULL,
  `FRIEND_ID` int(11) NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `REQUEST_MESSAGE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `STATE` int(2) NULL DEFAULT NULL COMMENT '0,新请求；1,同意，2，拒绝，3，忽略',
  PRIMARY KEY (`REQUEST_ID`) USING BTREE,
  INDEX `FK_FRIEND_REQUEST_CUSTOMER_ID`(`CUSTOMER_ID`) USING BTREE,
  INDEX `FK_FRIEND_REQUEST_FRIEND_ID`(`FRIEND_ID`) USING BTREE,
  CONSTRAINT `FK_FRIEND_REQUEST_CUSTOMER_ID` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_FRIEND_REQUEST_FRIEND_ID` FOREIGN KEY (`FRIEND_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for customer_trading_record
-- ----------------------------
DROP TABLE IF EXISTS `customer_trading_record`;
CREATE TABLE `customer_trading_record`  (
  `TRADE_RECORD_ID` int(11) NOT NULL COMMENT '交易流水id',
  `CUSTOMER_ID` int(11) NOT NULL COMMENT '客户id',
  `BUSINESS_TYPE` int(11) NOT NULL COMMENT '业务类型:0余额，1:贷款预期收益 2:贷款额度 3:贷款金额',
  `OPERATION_TYPE` int(11) NOT NULL COMMENT '操作类型:0增加，1减少',
  `AMOUNT` double NULL DEFAULT 0 COMMENT '操作余额',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`TRADE_RECORD_ID`) USING BTREE,
  INDEX `customer_trading_record_ibfk_1`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `customer_trading_record_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户交易流水表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_wallet
-- ----------------------------
DROP TABLE IF EXISTS `customer_wallet`;
CREATE TABLE `customer_wallet`  (
  `CUSTOMER_ID` int(11) NOT NULL COMMENT '客户钱包id',
  `AVAILABLE_BALANCE` double NULL DEFAULT 0 COMMENT '可用余额',
  `EXPECTED_RETURN_AMOUNT` double NULL DEFAULT 0 COMMENT '预期收益金额',
  `LOAN_QUOTA` double NULL DEFAULT 0 COMMENT '可贷额度',
  `ARREARS_AMOUNT` double NULL DEFAULT 0 COMMENT '欠款金额',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `customer_wallet_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户钱包表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for delivery_address
-- ----------------------------
DROP TABLE IF EXISTS `delivery_address`;
CREATE TABLE `delivery_address`  (
  `ADDRESS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS_TYPE` int(11) NULL DEFAULT NULL,
  `CITY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONSIGNEE_ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONSIGNEE_MOBILE` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CONSIGNEE_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONSIGNEE_TELEPHONE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `DEFAULT_ADDRESS` bit(1) NOT NULL,
  `DISTRICT` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `POST_CODE` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PROVINCE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `REMARK` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  PRIMARY KEY (`ADDRESS_ID`) USING BTREE,
  INDEX `FK_gis1ig4hxyrwsro1ptnxtjlg1`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `delivery_address_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `DEVICE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `DEVICE_CODE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `DEVICE_TYPE` int(11) NOT NULL,
  `FACTORY_DATE` date NULL DEFAULT NULL,
  `VERSION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CONCENT_ID` int(11) NOT NULL,
  `ONLINE` bit(1) NOT NULL,
  `DEVICE_STATE` int(11) NULL DEFAULT NULL,
  `SELLER_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`DEVICE_ID`) USING BTREE,
  INDEX `FK_gpvaaknkbes71o9tllhland9v`(`CONCENT_ID`) USING BTREE,
  INDEX `FK_f5ij9ks0s8yvrksi91is532a8`(`SELLER_ID`) USING BTREE,
  CONSTRAINT `FK_f5ij9ks0s8yvrksi91is532a8` FOREIGN KEY (`SELLER_ID`) REFERENCES `seller` (`SELLER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `device_ibfk_1` FOREIGN KEY (`CONCENT_ID`) REFERENCES `concent` (`CONCENT_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for device_state
-- ----------------------------
DROP TABLE IF EXISTS `device_state`;
CREATE TABLE `device_state`  (
  `STATE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `TEMPERATURE` float NOT NULL,
  `WORKING` bit(1) NOT NULL,
  `DEVICE_ID` int(11) NOT NULL,
  PRIMARY KEY (`STATE_ID`) USING BTREE,
  INDEX `FK_4lq1kcuh5sfx312opl1sng2yo`(`DEVICE_ID`) USING BTREE,
  CONSTRAINT `device_state_ibfk_1` FOREIGN KEY (`DEVICE_ID`) REFERENCES `device` (`DEVICE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for evaluate
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate`  (
  `EVALUATE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `COMMODITY_ID` int(11) NOT NULL,
  `COMMODITY_LEVEL` int(11) NOT NULL,
  `COMMODITY_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CONTENT` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `ITEM_ID` int(11) NOT NULL,
  `ORDER_CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `ORDER_ITEM_PRICE` float NOT NULL,
  `PRODUCT_ID` int(11) NOT NULL,
  `PRODUCT_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SALE_PRICE` float NOT NULL,
  `SCORE` int(11) NOT NULL,
  PRIMARY KEY (`EVALUATE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite`  (
  `FAVORITE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `SALE_PRICE` float NOT NULL,
  `COMMODITY_ID` int(11) NOT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  PRIMARY KEY (`FAVORITE_ID`) USING BTREE,
  INDEX `FK_bbalgl6qip9d6g3s38sf144cg`(`COMMODITY_ID`) USING BTREE,
  INDEX `FK_g5furkxkl1lc5ye74x9huev9k`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `favorite_ibfk_2` FOREIGN KEY (`COMMODITY_ID`) REFERENCES `commodity` (`COMMODITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `images` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_customer_id`(`customer_id`) USING BTREE,
  CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for filter_item
-- ----------------------------
DROP TABLE IF EXISTS `filter_item`;
CREATE TABLE `filter_item`  (
  `ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SORT_ORDER` smallint(6) NULL DEFAULT NULL,
  `FILTER_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ITEM_ID`) USING BTREE,
  INDEX `FK_5iwib9ftt5d2t50f16nwmgylx`(`FILTER_ID`) USING BTREE,
  CONSTRAINT `filter_item_ibfk_1` FOREIGN KEY (`FILTER_ID`) REFERENCES `property_filter` (`FILTER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for floor_brand
-- ----------------------------
DROP TABLE IF EXISTS `floor_brand`;
CREATE TABLE `floor_brand`  (
  `BRAND_ID` int(11) NOT NULL,
  `FLOOR_ID` int(11) NOT NULL,
  `SORT_ORDER` int(11) NOT NULL,
  PRIMARY KEY (`BRAND_ID`, `FLOOR_ID`) USING BTREE,
  INDEX `FK_7tcdsbs43j61a2b9rc46ow49k`(`FLOOR_ID`) USING BTREE,
  CONSTRAINT `floor_brand_ibfk_1` FOREIGN KEY (`FLOOR_ID`) REFERENCES `home_floor` (`FLOOR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `floor_brand_ibfk_2` FOREIGN KEY (`BRAND_ID`) REFERENCES `brand` (`BRAND_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for floor_category
-- ----------------------------
DROP TABLE IF EXISTS `floor_category`;
CREATE TABLE `floor_category`  (
  `CATEGORY_ID` int(11) NOT NULL,
  `FLOOR_ID` int(11) NOT NULL,
  `SORT_ORDER` int(11) NOT NULL,
  PRIMARY KEY (`CATEGORY_ID`, `FLOOR_ID`) USING BTREE,
  INDEX `FK_7tcdsbs43j61a2b9rc46ow49k`(`FLOOR_ID`) USING BTREE,
  CONSTRAINT `floor_category_ibfk_1` FOREIGN KEY (`FLOOR_ID`) REFERENCES `home_floor` (`FLOOR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `floor_category_ibfk_2` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`CATEGORY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for floor_commodity
-- ----------------------------
DROP TABLE IF EXISTS `floor_commodity`;
CREATE TABLE `floor_commodity`  (
  `COMMODITY_ID` int(11) NOT NULL,
  `FLOOR_ID` int(11) NOT NULL,
  `SORT_ORDER` int(11) NOT NULL,
  PRIMARY KEY (`COMMODITY_ID`, `FLOOR_ID`) USING BTREE,
  INDEX `FK_6b7ou6ttghafy6o9w6amdrlse`(`FLOOR_ID`) USING BTREE,
  CONSTRAINT `floor_commodity_ibfk_1` FOREIGN KEY (`FLOOR_ID`) REFERENCES `home_floor` (`FLOOR_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `floor_commodity_ibfk_2` FOREIGN KEY (`COMMODITY_ID`) REFERENCES `commodity` (`COMMODITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for freight
-- ----------------------------
DROP TABLE IF EXISTS `freight`;
CREATE TABLE `freight`  (
  `FREIGHT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AREA_CODE` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `FIRST_PRICE` int(11) NOT NULL,
  `MORE_PRICE` int(11) NOT NULL,
  `TOTAL_PRICE` float NOT NULL,
  `COMMODITY_ID` int(11) NOT NULL,
  `SELLER_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`FREIGHT_ID`) USING BTREE,
  INDEX `FK_kg642x26ou89k9ss5hb8y54ot`(`COMMODITY_ID`) USING BTREE,
  INDEX `FK_i1v5kkxa46n9iiphx9drn287f`(`SELLER_ID`) USING BTREE,
  CONSTRAINT `freight_ibfk_1` FOREIGN KEY (`SELLER_ID`) REFERENCES `seller` (`SELLER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `freight_ibfk_2` FOREIGN KEY (`COMMODITY_ID`) REFERENCES `commodity` (`COMMODITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for fridge
-- ----------------------------
DROP TABLE IF EXISTS `fridge`;
CREATE TABLE `fridge`  (
  `FRIDGE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ARTICLE_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ARTICLE_TYPE` int(11) NOT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SHELF_LIFE` datetime(0) NULL DEFAULT NULL,
  `UNIT` int(11) NOT NULL,
  `WEIGHT` int(11) NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  PRIMARY KEY (`FRIDGE_ID`) USING BTREE,
  INDEX `FK_hjxffuo00dim6slvgupfwir92`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `FK_hjxffuo00dim6slvgupfwir92` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for goods_change
-- ----------------------------
DROP TABLE IF EXISTS `goods_change`;
CREATE TABLE `goods_change`  (
  `GOODS_CHANGE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `APPLICATION_TIME` datetime(0) NULL DEFAULT NULL,
  `CHANGE_COUNT` int(11) NOT NULL,
  `CITY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONTACT_NAME` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `DISTRICT` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ORDER_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PHONE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PIC_PATH` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PROVINCE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PURCHASING_TIME` datetime(0) NULL DEFAULT NULL,
  `REASON` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REMARK` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `STATUS` int(11) NOT NULL,
  `TEL` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `ORDER_DETAIL_ID` int(11) NOT NULL,
  PRIMARY KEY (`GOODS_CHANGE_ID`) USING BTREE,
  INDEX `FK_jp9j7mm8qvxk8yucd5ug0ale4`(`CUSTOMER_ID`) USING BTREE,
  INDEX `FK_hdts6o5ve9biakwq9s2tvjvaq`(`ORDER_DETAIL_ID`) USING BTREE,
  CONSTRAINT `goods_change_ibfk_1` FOREIGN KEY (`ORDER_DETAIL_ID`) REFERENCES `order_item` (`ITEM_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `goods_change_ibfk_2` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for goods_return
-- ----------------------------
DROP TABLE IF EXISTS `goods_return`;
CREATE TABLE `goods_return`  (
  `GOODS_RETURN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `APPLICATION_TIME` datetime(0) NULL DEFAULT NULL,
  `CITY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONTACT_NAME` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `DISTRICT` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ORDER_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PHONE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PIC_PATH` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PROVINCE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PURCHASING_TIME` datetime(0) NULL DEFAULT NULL,
  `REASON` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REMARK` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `STATUS` int(11) NOT NULL,
  `TEL` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `ORDER_DETAIL_ID` int(11) NOT NULL,
  PRIMARY KEY (`GOODS_RETURN_ID`) USING BTREE,
  INDEX `FK_45hd94pd91eyg15sf2ugc7sfo`(`CUSTOMER_ID`) USING BTREE,
  INDEX `FK_o4jnmfhl0olmpcfaj8sado4cl`(`ORDER_DETAIL_ID`) USING BTREE,
  CONSTRAINT `goods_return_ibfk_1` FOREIGN KEY (`ORDER_DETAIL_ID`) REFERENCES `order_item` (`ITEM_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `goods_return_ibfk_2` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for guarantee
-- ----------------------------
DROP TABLE IF EXISTS `guarantee`;
CREATE TABLE `guarantee`  (
  `TEE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CITY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CLEAN_YEAR_LIMIT` float NOT NULL,
  `CONTACT_NAME` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `DISTRICT` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `FREE_CLEAN_COUNTS` int(11) NOT NULL,
  `FREE_REPAIR_COUNTS` int(11) NOT NULL,
  `ORDER_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PHONE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PROVINCE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PURCHASING_TIME` datetime(0) NULL DEFAULT NULL,
  `REMARK` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REPAIR_YEAR_LIMIT` float NOT NULL,
  `TEL` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `ORDER_DETAIL_ID` int(11) NOT NULL,
  PRIMARY KEY (`TEE_ID`) USING BTREE,
  INDEX `FK_5tyg2wb603vm9rsw31arch0ls`(`CUSTOMER_ID`) USING BTREE,
  INDEX `FK_ag2w0a4hrju4o61hac2l9t4d9`(`ORDER_DETAIL_ID`) USING BTREE,
  CONSTRAINT `guarantee_ibfk_1` FOREIGN KEY (`ORDER_DETAIL_ID`) REFERENCES `order_item` (`ITEM_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `guarantee_ibfk_2` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for home_floor
-- ----------------------------
DROP TABLE IF EXISTS `home_floor`;
CREATE TABLE `home_floor`  (
  `FLOOR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY_AMOUNT` int(11) NOT NULL,
  `COMMODITY_AMOUNT` int(11) NOT NULL,
  `ENABLED` bit(1) NOT NULL,
  `FLOOR_LAYOUT` int(11) NOT NULL,
  `FLOOR_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ICON_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IMAGE_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SORT_ORDER` int(11) NOT NULL,
  `BRAND_AMOUNT` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`FLOOR_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for insurance_informed_matter
-- ----------------------------
DROP TABLE IF EXISTS `insurance_informed_matter`;
CREATE TABLE `insurance_informed_matter`  (
  `MATTER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '事项ID',
  `MATTER_DESCRIPTION` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '事项描述',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `SER_NO` int(11) NULL DEFAULT NULL COMMENT '序号',
  `ENABLED` int(255) NULL DEFAULT NULL COMMENT '是否启用',
  `GROUP_ID` int(11) NULL DEFAULT NULL COMMENT '所属组',
  `MATTER_TYPE` int(11) NULL DEFAULT 0 COMMENT '0：是否题；1：填空题',
  PRIMARY KEY (`MATTER_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 292 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '保险告知事项' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for insurance_informed_matter_group
-- ----------------------------
DROP TABLE IF EXISTS `insurance_informed_matter_group`;
CREATE TABLE `insurance_informed_matter_group`  (
  `GROUP_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '事项组ID',
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '事项组描述',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `SER_NO` int(11) NOT NULL COMMENT '序号',
  `ENABLED` int(11) NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`GROUP_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for insurance_order
-- ----------------------------
DROP TABLE IF EXISTS `insurance_order`;
CREATE TABLE `insurance_order`  (
  `ORDER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '保险订单ID',
  `ORDER_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单号',
  `CONTRACT_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '合同编号',
  `PROD_ID` int(11) NOT NULL COMMENT '产品ID',
  `PRICE_ID` int(11) NULL DEFAULT NULL COMMENT '保额',
  `CUSTOMER_ID` int(11) NOT NULL COMMENT '客户ID',
  `INSURED_ID` int(11) NOT NULL COMMENT '被保人ID',
  `POLICYHOLDER_ID` int(11) NOT NULL COMMENT '投保人ID',
  `BANK_ID` int(11) NOT NULL COMMENT '投保人银行ID',
  `LEGAL_BENEFICIARY` int(11) NOT NULL DEFAULT 0 COMMENT '是否法定收益人,0:不是,1:是否',
  `ORDER_STATE` int(11) NOT NULL DEFAULT 0 COMMENT '保单状态',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '订单创建时间',
  `OFFSITE_ID` int(11) NULL DEFAULT NULL COMMENT '异地投保信息',
  `BAR_CODE` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '条形码',
  PRIMARY KEY (`ORDER_ID`) USING BTREE,
  INDEX `fk_insura_prod`(`PROD_ID`) USING BTREE,
  INDEX `fk_insura_order_customer`(`CUSTOMER_ID`) USING BTREE,
  INDEX `fk_insura_order_price`(`PRICE_ID`) USING BTREE,
  INDEX `fk_insura_order_insured`(`INSURED_ID`) USING BTREE,
  INDEX `fk_insura_order_policyholder`(`POLICYHOLDER_ID`) USING BTREE,
  INDEX `fk_insura_order_policyholder_bank`(`BANK_ID`) USING BTREE,
  INDEX `fk_insura_order_offsite`(`OFFSITE_ID`) USING BTREE,
  CONSTRAINT `fk_insura_order_customer` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_insura_order_insured` FOREIGN KEY (`INSURED_ID`) REFERENCES `insurance_order_insured` (`INSURED_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_insura_order_offsite` FOREIGN KEY (`OFFSITE_ID`) REFERENCES `insurance_order_offsite` (`OFFSITE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_insura_order_policyholder` FOREIGN KEY (`POLICYHOLDER_ID`) REFERENCES `insurance_order_policyholder` (`POLICYHOLDER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_insura_order_policyholder_bank` FOREIGN KEY (`BANK_ID`) REFERENCES `insurance_order_policyholder_bank` (`BANK_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_insura_order_price` FOREIGN KEY (`PRICE_ID`) REFERENCES `insurance_product_price` (`PRICE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_insura_prod` FOREIGN KEY (`PROD_ID`) REFERENCES `insurance_product` (`PROD_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 195 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '保险订单' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for insurance_order_beneficiary
-- ----------------------------
DROP TABLE IF EXISTS `insurance_order_beneficiary`;
CREATE TABLE `insurance_order_beneficiary`  (
  `BENEFICIARY_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '受益人ID',
  `ORDER_ID` int(11) NOT NULL COMMENT '订单ID',
  `BENEFICIARY_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '受益人姓名',
  `BENEFICIARY_GENDER` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '受益人性别',
  `BENEFICIARY_BIRTHDAY` date NOT NULL COMMENT '受益人生日',
  `BENEFICIARY_CAREER` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '受益人职业',
  `BENEFICIARY_CARD_TYPE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '受益人证件类型',
  `BENEFICIARY_CARD_NO` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '受益人证件号',
  `BENEFICIARY_COUNTRY` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '受益人国籍',
  `BENEFICIARY_CARD_PEROID` date NOT NULL COMMENT '受益人证件有效期',
  `BENEFICIARY_HEIGHT` int(11) NULL DEFAULT NULL COMMENT '受益人身高',
  `BENEFICIARY_BODY_WEIGHT` float NULL DEFAULT NULL COMMENT '受益人体重',
  `BENEFICIARY_INCOME` int(255) NOT NULL COMMENT '受益人收入',
  `BENEFICIARY_MARRIAGE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '受益人婚否',
  `BENEFICIARY_TEL` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '受益人固定电话',
  `BENEFICIARY_MOBILE` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '受益人手机号',
  `BENEFICIARY_EMAIL` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '受益人邮箱',
  `BENEFICIARY_PROVINCE` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '受益人所在省',
  `BENEFICIARY_CITY` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '受益人所在市',
  `BENEFICIARY_DISTRICT` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '受益人所在区',
  `BENEFICIARY_ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '受益人详细地址',
  `INSURED_RELATION` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '与被保人关系',
  `BENEFICIARY_ORDER` int(11) NULL DEFAULT 1 COMMENT '收益顺序',
  `BENEFICIARY_PROPORTION` int(11) NULL DEFAULT 0 COMMENT '收益份额',
  PRIMARY KEY (`BENEFICIARY_ID`) USING BTREE,
  INDEX `fk_insura_order_beneficia`(`ORDER_ID`) USING BTREE,
  CONSTRAINT `fk_insura_order_beneficia` FOREIGN KEY (`ORDER_ID`) REFERENCES `insurance_order` (`ORDER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '保险订单受益人信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for insurance_order_informed_matter
-- ----------------------------
DROP TABLE IF EXISTS `insurance_order_informed_matter`;
CREATE TABLE `insurance_order_informed_matter`  (
  `COLLECT_ID` int(255) NOT NULL AUTO_INCREMENT COMMENT '事项采集ID',
  `ORDER_ID` int(11) NOT NULL COMMENT '订单ID',
  `MATTER_ID` int(11) NOT NULL COMMENT '告知事项ID',
  `RESULT` int(255) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '采集结果',
  `COLLECT_VALUES` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选项值，可以为多个，以json方式存储',
  `INSURED_REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '被保人备注',
  `INSURED_RESULT` int(11) NULL DEFAULT NULL COMMENT '被保人采集结果',
  `POLICYHOLDER_REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '投保人备注',
  `POLICYHOLDER_RESULT` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '投保人采集结果',
  PRIMARY KEY (`COLLECT_ID`) USING BTREE,
  INDEX `fk_insura_order_matter_order`(`ORDER_ID`) USING BTREE,
  INDEX `fk_insura_order_matter_matter`(`MATTER_ID`) USING BTREE,
  CONSTRAINT `fk_insura_order_matter_order` FOREIGN KEY (`ORDER_ID`) REFERENCES `insurance_order` (`ORDER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4776 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '保险订单告知事项采集' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for insurance_order_insured
-- ----------------------------
DROP TABLE IF EXISTS `insurance_order_insured`;
CREATE TABLE `insurance_order_insured`  (
  `INSURED_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '被保人ID',
  `INSURED_BIRTHDAY` date NOT NULL COMMENT '被保人生日',
  `INSURED_GENDER` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '被保人性别',
  `INSURED_CAREER` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '被保人职业',
  `INSURED_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '被保人姓名',
  `INSURED_CARD_TYPE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '被保人证件类型',
  `INSURED_CARD_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '被保人证件号',
  `INSURED_CARD_PERIOD` date NOT NULL COMMENT '被保人证件有效期',
  `INSURED_COUNTRY` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '被保人国籍',
  `INSURED_HEIGHT` int(11) NULL DEFAULT NULL COMMENT '被保人身高',
  `INSURED_BODY_WEIGHT` float NULL DEFAULT NULL COMMENT '被保人体重',
  `INSURED_INCOME` int(255) NULL DEFAULT NULL COMMENT '被保人收入',
  `INSURED_MARRIAGE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '被保人婚否',
  `INSURED_TEL` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '被保人固定电话',
  `INSURED_MOBILE` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '被保人手机号',
  `INSURED_EMAIL` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '被保人邮箱',
  `INSURED_PROVINCE` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '被保人所在省',
  `INSURED_CITY` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '被保人所在市',
  `INSURED_DISTRICT` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '被保人所在区',
  `INSURED_ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '被保人详细地址',
  `INSURED_RELATION` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '被保人与投保人关系',
  PRIMARY KEY (`INSURED_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 429 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '保险订单被保人信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for insurance_order_offsite
-- ----------------------------
DROP TABLE IF EXISTS `insurance_order_offsite`;
CREATE TABLE `insurance_order_offsite`  (
  `OFFSITE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '异地投保ID',
  `SENSUE` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '户籍',
  `WORKPLACE` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作相关',
  `LEAVE_REASON` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '离开投保地的原因',
  `STAY_TIME` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '逗留时间',
  `OFFSITE_ADDRESS` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '异地地址',
  `OTHER_MATTER` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他事项',
  PRIMARY KEY (`OFFSITE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '保险订单异地投保信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for insurance_order_policyholder
-- ----------------------------
DROP TABLE IF EXISTS `insurance_order_policyholder`;
CREATE TABLE `insurance_order_policyholder`  (
  `POLICYHOLDER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '投保人ID',
  `POLICYHOLDER_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保人姓名',
  `POLICYHOLDER_GENDER` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保人性别',
  `POLICYHOLDER_BIRTHDAY` date NOT NULL COMMENT '投保人生日',
  `POLICYHOLDER_CAREER` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保人职业',
  `POLICYHOLDER_CARD_TYPE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保人证件类型',
  `POLICYHOLDER_CARD_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保人证件号',
  `POLICYHOLDER_COUNTRY` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保人国籍',
  `POLICYHOLDER_CARD_PEROID` date NOT NULL COMMENT '投保人证件有效期',
  `POLICYHOLDER_HEIGHT` int(11) NULL DEFAULT NULL COMMENT '投保人身高',
  `POLICYHOLDER_BODY_WEIGHT` float NULL DEFAULT NULL COMMENT '投保人体重',
  `POLICYHOLDER_INCOME` int(255) NULL DEFAULT NULL COMMENT '投保人收入',
  `POLICYHOLDER_MARRIAGE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '投保人婚否',
  `POLICYHOLDER_TEL` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '投保人固定电话',
  `POLICYHOLDER_MOBILE` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '投保人手机号',
  `POLICYHOLDER_EMAIL` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保人邮箱',
  `POLICYHOLDER_PROVINCE` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保人所在省',
  `POLICYHOLDER_CITY` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保人所在市',
  `POLICYHOLDER_DISTRICT` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保人所在区',
  `POLICYHOLDER_ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保人详细地址',
  `POLICYHOLDER_TAX_RELATED` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '涉税人身份信息',
  `POLICYHOLDER_SIGN` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保人签名',
  `CARD_POSITIVE_IMG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保人身份证正面照片',
  `CARD_NEGATIVE_IMG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保人身份证反面照片',
  `OTHER_IMG1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他资料1',
  `OTHER_IMG2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他资料2',
  `OTHER_IMG3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他资料3',
  `POLICYHOLDER_AVATAR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '投保人头像',
  `SUBMISSION_SIGN` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '签名',
  PRIMARY KEY (`POLICYHOLDER_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 253 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '保险订单投保人信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for insurance_order_policyholder_bank
-- ----------------------------
DROP TABLE IF EXISTS `insurance_order_policyholder_bank`;
CREATE TABLE `insurance_order_policyholder_bank`  (
  `BANK_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '投保人银行ID',
  `BANK_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '持卡人姓名',
  `AMOUNT` int(11) NOT NULL COMMENT '交易金额',
  `BANK_MOBILE` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
  `ACCOUNT_BANK` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '开户行',
  `BANK_PROVINCE` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '开户行省',
  `BANK_CITY` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '开户行市',
  `ACCOUNT_TYPE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户类型',
  `ACCOUNT_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户号',
  `BANK_CARD_IMG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '银行卡照片',
  PRIMARY KEY (`BANK_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 234 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '保险订单投保人银行信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for insurance_product
-- ----------------------------
DROP TABLE IF EXISTS `insurance_product`;
CREATE TABLE `insurance_product`  (
  `PROD_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `PROD_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品名称',
  `DESCRIPTION` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '产品描述',
  `PROD_IMG` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品图片',
  `DESCRIPTION_IMG` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品详情图片',
  `TAGS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标签（多个用逗号隔开）',
  `INSURE_PERIOD` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '保险期间',
  `PROTECTION_YEAR` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '保障年限(10年，20年，终生)',
  `INSTRUCTION` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保须知',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`PROD_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '保险产品' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for insurance_product_informed_matter
-- ----------------------------
DROP TABLE IF EXISTS `insurance_product_informed_matter`;
CREATE TABLE `insurance_product_informed_matter`  (
  `PROD_ID` int(11) NOT NULL,
  `MATTER_ID` int(11) NOT NULL,
  PRIMARY KEY (`PROD_ID`, `MATTER_ID`) USING BTREE,
  INDEX `fk_insura_prod_matter_prod`(`MATTER_ID`) USING BTREE,
  CONSTRAINT `fk_insura_prod_matter_matter` FOREIGN KEY (`PROD_ID`) REFERENCES `insurance_product` (`PROD_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_insura_prod_matter_prod` FOREIGN KEY (`MATTER_ID`) REFERENCES `insurance_informed_matter` (`MATTER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '保险产品告知事项' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for insurance_product_price
-- ----------------------------
DROP TABLE IF EXISTS `insurance_product_price`;
CREATE TABLE `insurance_product_price`  (
  `PRICE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品价格ID',
  `PROD_ID` int(255) NOT NULL COMMENT '产品ID',
  `PRICE` int(10) NOT NULL COMMENT '产品价格',
  `UNIT` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '价格单位',
  `DESCRIPTION` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '描述',
  `CREATE_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  `REMARK` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`PRICE_ID`) USING BTREE,
  INDEX `fk_insurance_prod_price`(`PROD_ID`) USING BTREE,
  CONSTRAINT `fk_insurance_prod_price` FOREIGN KEY (`PROD_ID`) REFERENCES `insurance_product` (`PROD_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '保险产品价格' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for integral
-- ----------------------------
DROP TABLE IF EXISTS `integral`;
CREATE TABLE `integral`  (
  `INTEGRAL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `INTEGRAL_TIME` datetime(0) NULL DEFAULT NULL,
  `ORIGIN` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SCORE` int(11) NOT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `RULE_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`INTEGRAL_ID`) USING BTREE,
  INDEX `FK_tq6ercqnar3v3g5b73nm91iey`(`CUSTOMER_ID`) USING BTREE,
  INDEX `FK_lpw22n2warvnq3ucefg9r5mh6`(`RULE_ID`) USING BTREE,
  CONSTRAINT `integral_ibfk_1` FOREIGN KEY (`RULE_ID`) REFERENCES `rule_condition` (`RULE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `integral_ibfk_2` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for key_word
-- ----------------------------
DROP TABLE IF EXISTS `key_word`;
CREATE TABLE `key_word`  (
  `KEY_WORD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `KEY_KEY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `KEY_VALUE` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`KEY_WORD_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for kitchen
-- ----------------------------
DROP TABLE IF EXISTS `kitchen`;
CREATE TABLE `kitchen`  (
  `KITCHEN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `REMARK` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TITLE` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  PRIMARY KEY (`KITCHEN_ID`) USING BTREE,
  INDEX `FK_4216a2awd676ynt4t4imun42g`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `kitchen_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for letter
-- ----------------------------
DROP TABLE IF EXISTS `letter`;
CREATE TABLE `letter`  (
  `LETTER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `LETTER_CONTENT` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `LETTER_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `READED` bit(1) NOT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  PRIMARY KEY (`LETTER_ID`) USING BTREE,
  INDEX `FK_qyxbm5h3o1f3m2iyu84gy2ol1`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `letter_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for logistic
-- ----------------------------
DROP TABLE IF EXISTS `logistic`;
CREATE TABLE `logistic`  (
  `LOGISTIC_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CITY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `DISTRICT` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `EMAIL` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ENABLED` bit(1) NOT NULL,
  `LINKMAN` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `LOGISTIC_ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `LOGISTIC_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `LOGISTIC_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MOBILE` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PROVINCE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `QQ` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TELEPHONE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `URL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `WE_CHAT_NO` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SELLER_ID` int(11) NOT NULL,
  PRIMARY KEY (`LOGISTIC_ID`) USING BTREE,
  INDEX `FK_1swrgqoqdyxatpjmqy8tmubsn`(`SELLER_ID`) USING BTREE,
  CONSTRAINT `logistic_ibfk_1` FOREIGN KEY (`SELLER_ID`) REFERENCES `seller` (`SELLER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for logistic_price
-- ----------------------------
DROP TABLE IF EXISTS `logistic_price`;
CREATE TABLE `logistic_price`  (
  `PRICE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CITY_CODES` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CONTINUE_PRICE` float NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `WEIGHT` int(11) NOT NULL,
  `WEIGHT_PRICE` float NOT NULL,
  `LOGISTIC_ID` int(11) NOT NULL,
  PRIMARY KEY (`PRICE_ID`) USING BTREE,
  INDEX `FK_250j4jqoj5n9dhmwwo3mmt3wj`(`LOGISTIC_ID`) USING BTREE,
  CONSTRAINT `logistic_price_ibfk_1` FOREIGN KEY (`LOGISTIC_ID`) REFERENCES `logistic` (`LOGISTIC_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for logistic_price_commodity
-- ----------------------------
DROP TABLE IF EXISTS `logistic_price_commodity`;
CREATE TABLE `logistic_price_commodity`  (
  `COMMODITY_ID` int(11) NOT NULL,
  `PRICE_ID` int(11) NOT NULL,
  PRIMARY KEY (`COMMODITY_ID`, `PRICE_ID`) USING BTREE,
  INDEX `FK_fs8719hvg7l94q89jpvxgrcmo`(`PRICE_ID`) USING BTREE,
  CONSTRAINT `logistic_price_commodity_ibfk_1` FOREIGN KEY (`COMMODITY_ID`) REFERENCES `commodity` (`COMMODITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `logistic_price_commodity_ibfk_2` FOREIGN KEY (`PRICE_ID`) REFERENCES `logistic_price` (`PRICE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mails
-- ----------------------------
DROP TABLE IF EXISTS `mails`;
CREATE TABLE `mails`  (
  `MAIL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SEND_TIME` datetime(0) NULL DEFAULT NULL,
  `SUBJECT` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TO_ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CUSTOMER` int(11) NOT NULL,
  PRIMARY KEY (`MAIL_ID`) USING BTREE,
  INDEX `FK_qkjmqas5hq80e3qnlyg51sr6a`(`CUSTOMER`) USING BTREE,
  CONSTRAINT `mails_ibfk_1` FOREIGN KEY (`CUSTOMER`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_form
-- ----------------------------
DROP TABLE IF EXISTS `order_form`;
CREATE TABLE `order_form`  (
  `ORDER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BARTER_STATE` int(11) NULL DEFAULT NULL,
  `BUYER_MESSAGE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CANCEL_REASON` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CANCEL_TIME` datetime(0) NULL DEFAULT NULL,
  `CITY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONSIGNEE_ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONSIGNEE_MOBILE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CONSIGNEE_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONSIGNEE_TELEPHONE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `COUPONS_FEE` double NOT NULL,
  `COURIER_NUMBER` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `DELIVERY` bit(1) NOT NULL,
  `DELIVERY_FEE_TOTAL` double NOT NULL,
  `DELIVERY_STATE` int(11) NULL DEFAULT NULL,
  `DELIVERY_TYPE` int(11) NOT NULL,
  `DISCOUNT_DELIVERY_FEE_TOTAL` double NULL DEFAULT NULL,
  `DISCOUNT_TOTAL` double NULL DEFAULT NULL,
  `DISTRICT` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ENABLED` bit(1) NOT NULL,
  `FEE_TOTAL` double NULL DEFAULT NULL,
  `FINISH_TIME` datetime(0) NULL DEFAULT NULL,
  `ORDER_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ORDER_STATE` int(11) NOT NULL,
  `ORIGIN_ORDER_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PAY_BY_INTEGRAL` double NULL DEFAULT NULL,
  `PAYMENT_SEQUENCE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PAYMENT_TIME` datetime(0) NULL DEFAULT NULL,
  `PAYMENT_TYPE` int(11) NULL DEFAULT NULL,
  `POST_CODE` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PROD_QUANTITY` int(11) NOT NULL,
  `PROVINCE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `RETURN_REFUND_STATE` int(11) NULL DEFAULT NULL,
  `SCORE_TOTAL` int(11) NOT NULL,
  `TOTAL_PRICE` double NOT NULL,
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  `USED_SCORE` int(11) NULL DEFAULT NULL,
  `VOLUME_TOTAL` double NULL DEFAULT NULL,
  `WEIGHT_TOTAL` double NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `LOGISTIC_ID` int(11) NULL DEFAULT NULL,
  `SELLER_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`) USING BTREE,
  INDEX `FK_m4dks4m7i966ojgd6cjei0iwp`(`CUSTOMER_ID`) USING BTREE,
  INDEX `FK_aihhiwv1fgxgjhcyrveq24yue`(`LOGISTIC_ID`) USING BTREE,
  INDEX `FK_a6cgu9cpb79u3fe95cvwalp4l`(`SELLER_ID`) USING BTREE,
  CONSTRAINT `order_form_ibfk_1` FOREIGN KEY (`SELLER_ID`) REFERENCES `seller` (`SELLER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_form_ibfk_2` FOREIGN KEY (`LOGISTIC_ID`) REFERENCES `logistic` (`LOGISTIC_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_form_ibfk_3` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_invoice
-- ----------------------------
DROP TABLE IF EXISTS `order_invoice`;
CREATE TABLE `order_invoice`  (
  `INVOICE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BANK_ACCOUNT` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `BANK_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CONTENT` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `INVOICE_AMOUNT` float NULL DEFAULT NULL,
  `INVOICE_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `INVOICE_TITLE` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `INVOICE_TITLE_TYPE` int(11) NOT NULL,
  `INVOICE_TYPE` int(11) NOT NULL,
  `REGISTER_ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REGISTER_PHONE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TAXPAYER_NO` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ORDER_ID` int(11) NOT NULL,
  PRIMARY KEY (`INVOICE_ID`) USING BTREE,
  INDEX `FK_8p361aom5slam26lblbko2ce8`(`ORDER_ID`) USING BTREE,
  CONSTRAINT `order_invoice_ibfk_1` FOREIGN KEY (`ORDER_ID`) REFERENCES `order_form` (`ORDER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BUYER_MESSAGE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `EVALUATE` bit(1) NOT NULL,
  `ORDER_ITEM_PRICE` float NOT NULL,
  `PRODUCT_IMG` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PRODUCT_NUM` int(11) NOT NULL,
  `SALE_PRICE` float NOT NULL,
  `ACTIVITY_ID` int(11) NULL DEFAULT NULL,
  `ORDER_ID` int(11) NOT NULL,
  `PRODUCT_ID` int(11) NOT NULL,
  PRIMARY KEY (`ITEM_ID`) USING BTREE,
  INDEX `FK_j0twp5n0jyry9unv6txwau1nm`(`ACTIVITY_ID`) USING BTREE,
  INDEX `FK_3yfdkftxifg8s0e6bjr3yfim`(`ORDER_ID`) USING BTREE,
  INDEX `FK_g82mqiks65cynj20mhv3kalff`(`PRODUCT_ID`) USING BTREE,
  CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`ORDER_ID`) REFERENCES `order_form` (`ORDER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_item_ibfk_3` FOREIGN KEY (`ACTIVITY_ID`) REFERENCES `activity` (`ACTIVITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_loan_apply
-- ----------------------------
DROP TABLE IF EXISTS `order_loan_apply`;
CREATE TABLE `order_loan_apply`  (
  `LOAN_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '贷款id',
  `LOAN_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '贷款编号',
  `CUSTOMER_ID` int(11) NOT NULL COMMENT '客户id',
  `ORDER_ID` int(11) NOT NULL COMMENT '订单id',
  `LOAN_STATE` int(11) NULL DEFAULT NULL COMMENT '贷款状态 0:申请贷款 1:贷款通过 2:贷款失败3：取消贷款 ',
  `LOAN_PRICE` double NULL DEFAULT NULL COMMENT '贷款金额',
  `AUDIT_REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核备注',
  `AUDIT_STATE` int(11) NOT NULL COMMENT '审核状态 0:待审核 1:审核通过，2：审核失败',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`LOAN_ID`) USING BTREE,
  INDEX `FK_oql18xgkuy2497dxxwiogy1o`(`CUSTOMER_ID`) USING BTREE,
  INDEX `loan_apply_ibfk_2`(`ORDER_ID`) USING BTREE,
  CONSTRAINT `loan_apply_ibfk_2` FOREIGN KEY (`ORDER_ID`) REFERENCES `order_form` (`ORDER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `loan_apply_ibfk_3` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '贷款申请表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_log
-- ----------------------------
DROP TABLE IF EXISTS `order_log`;
CREATE TABLE `order_log`  (
  `ORDERS_LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `HANDLER` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ORDER_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `REMARK` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TIME` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ORDERS_LOG_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `PAY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BUYER_ACCOUNT` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CHANNEL_TYPE` int(11) NOT NULL,
  `COMPLETE_TIME` datetime(0) NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `ERROR_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ERROR_MSG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `NOTIFY_TIME` datetime(0) NULL DEFAULT NULL,
  `OPERATOR_ID` int(11) NOT NULL,
  `PAY_AMOUNT` double NOT NULL,
  `PAY_FLOW_CODE` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PAY_NOTIFY_STATE` int(11) NULL DEFAULT NULL,
  `PAY_STATE` int(11) NOT NULL,
  `PAY_TYPE` int(11) NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NULL DEFAULT NULL,
  `ORDER_ID` int(11) NULL DEFAULT NULL,
  `BATCH_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `BATCH_NUM` int(11) NULL DEFAULT NULL,
  `RETURN_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`PAY_ID`) USING BTREE,
  INDEX `FK_hhnecjhu99v9bu38a158nnmf6`(`CUSTOMER_ID`) USING BTREE,
  INDEX `FK_gc3nnok5gxi42hbmum0jf5v4l`(`ORDER_ID`) USING BTREE,
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`ORDER_ID`) REFERENCES `order_form` (`ORDER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `PERMISSION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(11) NOT NULL,
  `PRIVILEGE_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`PERMISSION_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 234 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '资源权限' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for price_section
-- ----------------------------
DROP TABLE IF EXISTS `price_section`;
CREATE TABLE `price_section`  (
  `SECTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ENABLED` bit(1) NOT NULL,
  `END_PRICE` int(11) NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SECTION_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `START_PRICE` int(11) NOT NULL,
  PRIMARY KEY (`SECTION_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `PRODUCT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADD_TIME` datetime(0) NULL DEFAULT NULL,
  `COST_PRICE` float NOT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `DEFAULT_PIC_PATH` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `MARKET_PRICE` float NOT NULL,
  `PRODUCT_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PRODUCT_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PRODUCT_STATE` int(11) NOT NULL,
  `PUBLISH_STATE` int(11) NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SALE_PRICE` float NOT NULL,
  `STORE_NUM` int(11) NULL DEFAULT NULL,
  `VOLUME` float NULL DEFAULT NULL,
  `WEIGHT` float NULL DEFAULT NULL,
  `COMMODITY_ID` int(11) NOT NULL,
  `STORE_ID` int(11) NOT NULL,
  `RESERVED_STORE_NUM` int(11) NULL DEFAULT 0 COMMENT '预占的库存数',
  PRIMARY KEY (`PRODUCT_ID`) USING BTREE,
  INDEX `FK_bw5i1lb9f1nj4yfklxuub5pb6`(`COMMODITY_ID`) USING BTREE,
  INDEX `FK_dh1hmyc8m1y9x6ho5yq4g8ywf`(`STORE_ID`) USING BTREE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`STORE_ID`) REFERENCES `store` (`STORE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`COMMODITY_ID`) REFERENCES `commodity` (`COMMODITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 472 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product_attribute
-- ----------------------------
DROP TABLE IF EXISTS `product_attribute`;
CREATE TABLE `product_attribute`  (
  `PRO_ATTR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ATTRIBUTE_ID` int(11) NOT NULL,
  `PRODUCT_ID` int(11) NOT NULL,
  PRIMARY KEY (`PRO_ATTR_ID`) USING BTREE,
  INDEX `FK_cilj75xfuulglewpqq1g9ww2x`(`ATTRIBUTE_ID`) USING BTREE,
  INDEX `FK_dnt1duep1jr86pow6xsujmx84`(`PRODUCT_ID`) USING BTREE,
  CONSTRAINT `product_attribute_ibfk_1` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_attribute_ibfk_2` FOREIGN KEY (`ATTRIBUTE_ID`) REFERENCES `attribute` (`ATTRIBUTE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 504 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product_barter
-- ----------------------------
DROP TABLE IF EXISTS `product_barter`;
CREATE TABLE `product_barter`  (
  `BARTER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `APPLY_TIME` datetime(0) NULL DEFAULT NULL,
  `AUDIT_REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `AUDIT_STATE` int(11) NOT NULL,
  `BARTER_QUANTITY` int(11) NOT NULL,
  `BARTER_STATE` int(11) NOT NULL,
  `CONSIGNEE_INFO_CITY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONSIGNEE_INFO_CONSIGNEE_ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONSIGNEE_INFO_CONSIGNEE_MOBILE` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CONSIGNEE_INFO_CONSIGNEE_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONSIGNEE_INFO_CONSIGNEE_TELEPHONE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CONSIGNEE_INFO_DISTRICT` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONSIGNEE_INFO_POST_CODE` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CONSIGNEE_INFO_PROVINCE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CONSIGNEE_INFO_REMARK` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PIC_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PURCHASING_TIME` datetime(0) NULL DEFAULT NULL,
  `REASON` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `ORDER_ID` int(11) NOT NULL,
  `ITEM_ID` int(11) NOT NULL,
  `BARTER_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `COURIER_NUMBER` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `DISPOSE_TIME` datetime(0) NULL DEFAULT NULL,
  `LOGISTIC_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `RECEIVED_SELLER_PRODUCT` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`BARTER_ID`) USING BTREE,
  INDEX `FK_pou0495kxfn9v01qu913k8p0r`(`CUSTOMER_ID`) USING BTREE,
  INDEX `FK_4dsuewkw0pgjl3tqs8e64mai4`(`ORDER_ID`) USING BTREE,
  INDEX `FK_nm7i0jk3vbm80xh369yob7vvo`(`ITEM_ID`) USING BTREE,
  CONSTRAINT `product_barter_ibfk_1` FOREIGN KEY (`ITEM_ID`) REFERENCES `order_item` (`ITEM_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_barter_ibfk_2` FOREIGN KEY (`ORDER_ID`) REFERENCES `order_form` (`ORDER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_barter_ibfk_3` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product_change_price
-- ----------------------------
DROP TABLE IF EXISTS `product_change_price`;
CREATE TABLE `product_change_price`  (
  `CHANGE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AUDIT_OPINION` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CHANGE_NO` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `NEW_PRICE` float NOT NULL,
  `OPERATOR_TIME` datetime(0) NULL DEFAULT NULL,
  `PRE_PRICE` float NOT NULL,
  `REMARK` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `STATUS` bit(1) NOT NULL,
  `PRODUCT_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`CHANGE_ID`) USING BTREE,
  INDEX `FK_ot9bgsqepdehkcocn63oogy55`(`PRODUCT_ID`) USING BTREE,
  INDEX `FK_8qd74lfb50jeo44waynv64f6q`(`USER_ID`) USING BTREE,
  CONSTRAINT `product_change_price_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user_info` (`USER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_change_price_ibfk_2` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `product_evaluate`;
CREATE TABLE `product_evaluate`  (
  `EVALUATE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `REPLY_STATUS` bit(1) NOT NULL,
  `SCORE` int(11) NOT NULL,
  `CUSTOMER_ID` int(11) NULL DEFAULT NULL,
  `ITEM_ID` int(11) NULL DEFAULT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `COMMODITY_ID` int(11) NULL DEFAULT NULL,
  `ORDER_ID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`EVALUATE_ID`) USING BTREE,
  INDEX `FK_hgtcq3sn8m0s5e95doc4f7dmx`(`CUSTOMER_ID`) USING BTREE,
  INDEX `FK_x5prf61r9iclvg1svosawcy1`(`ITEM_ID`) USING BTREE,
  INDEX `FK_7eu0pk8exnri419ewyxm4ymvy`(`COMMODITY_ID`) USING BTREE,
  INDEX `FK_1il4q8cb3fqgofmn42uog2o6m`(`ORDER_ID`) USING BTREE,
  CONSTRAINT `product_evaluate_ibfk_1` FOREIGN KEY (`ORDER_ID`) REFERENCES `order_form` (`ORDER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_evaluate_ibfk_2` FOREIGN KEY (`COMMODITY_ID`) REFERENCES `commodity` (`COMMODITY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_evaluate_ibfk_3` FOREIGN KEY (`ITEM_ID`) REFERENCES `order_item` (`ITEM_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_evaluate_ibfk_4` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product_evaluate_reply
-- ----------------------------
DROP TABLE IF EXISTS `product_evaluate_reply`;
CREATE TABLE `product_evaluate_reply`  (
  `REPLY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `REMARK` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REPLY_CONTENT` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `EVALUATE_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`REPLY_ID`) USING BTREE,
  INDEX `FK_ckt8u2qcsjb42attxwb8s9nxc`(`EVALUATE_ID`) USING BTREE,
  INDEX `FK_dp86u1u8q612vrwsh0wxg2sk3`(`USER_ID`) USING BTREE,
  CONSTRAINT `product_evaluate_reply_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user_info` (`USER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_evaluate_reply_ibfk_2` FOREIGN KEY (`EVALUATE_ID`) REFERENCES `product_evaluate` (`EVALUATE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for product_return
-- ----------------------------
DROP TABLE IF EXISTS `product_return`;
CREATE TABLE `product_return`  (
  `RETURN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `APPLY_TIME` datetime(0) NULL DEFAULT NULL,
  `PIC_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PURCHASING_TIME` datetime(0) NULL DEFAULT NULL,
  `REASON` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `RETURN_REFUND_STATE` int(11) NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `ORDER_ID` int(11) NOT NULL,
  `ITEM_ID` int(11) NOT NULL,
  `AUDIT_REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `AUDIT_STATE` int(11) NOT NULL,
  `COURIER_NUMBER` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `DISPOSE_TIME` datetime(0) NULL DEFAULT NULL,
  `LOGISTIC_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REASON_REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `RECEIVED_BUYER_PRODUCT` bit(1) NULL DEFAULT NULL,
  `RECEIVED_SELLER_PRODUCT` bit(1) NULL DEFAULT NULL,
  `REFUND_PRICE` double NULL DEFAULT NULL,
  `REFUND_REASON` int(11) NULL DEFAULT NULL,
  `RETURN_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `RETURN_REASON` int(11) NULL DEFAULT NULL,
  `REFUND_ONLY` bit(1) NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REJECT_REASON` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`RETURN_ID`) USING BTREE,
  INDEX `FK_oql18xgkuy2497dxxwiogy1o`(`CUSTOMER_ID`) USING BTREE,
  INDEX `FK_mwhxiq9u9x48qjypyyhlyoepn`(`ORDER_ID`) USING BTREE,
  INDEX `FK_n4s4s5rns6fjrahn8o33wke7o`(`ITEM_ID`) USING BTREE,
  CONSTRAINT `product_return_ibfk_1` FOREIGN KEY (`ITEM_ID`) REFERENCES `order_item` (`ITEM_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_return_ibfk_2` FOREIGN KEY (`ORDER_ID`) REFERENCES `order_form` (`ORDER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_return_ibfk_3` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for profile
-- ----------------------------
DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile`  (
  `FILE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `PROFILE_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `FILE_VALUE` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`FILE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统配置' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for programa
-- ----------------------------
DROP TABLE IF EXISTS `programa`;
CREATE TABLE `programa`  (
  `PROGRAMA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ARTICLE_AMOUNT` int(11) NOT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `DESCRIPTION` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ENABLED` bit(1) NOT NULL,
  `OPERA_IMG_PATH` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PROGRAMA_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PROGRAMA_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `RECOMMEND` bit(1) NOT NULL,
  `SEO_DESCRIPTION` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SEO_KEY` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SEO_TITLE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SORT_ORDER` int(11) NOT NULL,
  `CHANNEL_ID` int(11) NOT NULL,
  `TEMPLATE_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`PROGRAMA_ID`) USING BTREE,
  INDEX `FK_5b9rqu8p139p3dnitjsvmdw0p`(`CHANNEL_ID`) USING BTREE,
  CONSTRAINT `programa_ibfk_1` FOREIGN KEY (`CHANNEL_ID`) REFERENCES `article_channel` (`CHANNEL_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for property_filter
-- ----------------------------
DROP TABLE IF EXISTS `property_filter`;
CREATE TABLE `property_filter`  (
  `FILTER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `ENABLED` bit(1) NOT NULL,
  `FILTER_NAME` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SORT_ORDER` int(11) NOT NULL,
  `CATEGORY_ID` int(11) NOT NULL,
  PRIMARY KEY (`FILTER_ID`) USING BTREE,
  INDEX `FK_tn2xs442pexy9vryo2ffgcwhr`(`CATEGORY_ID`) USING BTREE,
  CONSTRAINT `property_filter_ibfk_1` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`CATEGORY_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for rank
-- ----------------------------
DROP TABLE IF EXISTS `rank`;
CREATE TABLE `rank`  (
  `RANK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEFAULT_RANK` bit(1) NOT NULL,
  `INTEGRAL` int(11) NOT NULL,
  `RANK_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `RULE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`RANK_ID`) USING BTREE,
  UNIQUE INDEX `UK_6t2l8y9q4jme0gy6r7xki330q`(`RANK_NAME`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for recipe
-- ----------------------------
DROP TABLE IF EXISTS `recipe`;
CREATE TABLE `recipe`  (
  `RECIPE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONDIMENT` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `COOKING_METHOD` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CUISINE_TYPE` int(11) NOT NULL,
  `DETAILED_STEPS` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `FOOD_MATERIAL_DETAILS` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PRECAUTIONS` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PRODUCTION_DIFFICULTY_TYPE` int(11) NOT NULL,
  `RECIPE_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `RELATED_GROUPS` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TASTE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `TIME_NEEDED` int(11) NULL DEFAULT NULL,
  `TIPS` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `RECIPE_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`RECIPE_ID`) USING BTREE,
  INDEX `FK_rbp1itjpd2j1x14m77i36qwk4`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `recipe_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for recipe_step
-- ----------------------------
DROP TABLE IF EXISTS `recipe_step`;
CREATE TABLE `recipe_step`  (
  `STEP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PIC_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `STEP_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `STEP_ORDER` int(11) NULL DEFAULT NULL,
  `STEP_WHEN` int(11) NULL DEFAULT NULL,
  `RECIPE_ID` int(11) NOT NULL,
  PRIMARY KEY (`STEP_ID`) USING BTREE,
  INDEX `FK_8itdpa7fsglcbn6owhgfhwhhg`(`RECIPE_ID`) USING BTREE,
  CONSTRAINT `FK_8itdpa7fsglcbn6owhgfhwhhg` FOREIGN KEY (`RECIPE_ID`) REFERENCES `recipe` (`RECIPE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for role_info
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info`  (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ROLE_CODE` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ROLE_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SELLER_ID` int(11) NOT NULL,
  PRIMARY KEY (`ROLE_ID`) USING BTREE,
  INDEX `FK_ob9w14j7bw54vqm5ouk5agulx`(`SELLER_ID`) USING BTREE,
  CONSTRAINT `role_info_ibfk_1` FOREIGN KEY (`SELLER_ID`) REFERENCES `seller` (`SELLER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for role_resc
-- ----------------------------
DROP TABLE IF EXISTS `role_resc`;
CREATE TABLE `role_resc`  (
  `RESC_ID` int(11) NOT NULL AUTO_INCREMENT,
  `RESC_CODE` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`RESC_ID`) USING BTREE,
  INDEX `FK_cldvmbqpegyv123nfoyhck0jt`(`ROLE_ID`) USING BTREE,
  CONSTRAINT `role_resc_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `role_info` (`ROLE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 574 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for rule_condition
-- ----------------------------
DROP TABLE IF EXISTS `rule_condition`;
CREATE TABLE `rule_condition`  (
  `RULE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPERATOR` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `RULE_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `RULE_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `RULE_VALUE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`RULE_ID`) USING BTREE,
  UNIQUE INDEX `UK_7sx5u2rfjf8y2c6ep5p40iqca`(`RULE_CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller`  (
  `SELLER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `AGREEMENT_PIC_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `AUDIT` bit(1) NULL DEFAULT NULL,
  `BANK_ACCOUNT` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `BANK_ACCOUNT_ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `BUS_NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `BUSLICENSE_NO` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `BUSLICENSE_PIC_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `CHANNEL_ACCOUNT` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CHANNEL_TYPE` int(11) NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `EMAIL` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ENABLED` bit(1) NOT NULL,
  `HOLD_ID_POTO_PIC_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `ID_CARD_NUM` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `INTRODUCTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `LINKMAN` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MOBILE` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `POSITIVE_ID_POTO_PIC_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `PUBLIC_ACCOUNT` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `QQ` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SELLER_ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SELLER_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SELLER_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SELLER_TYPE` int(11) NULL DEFAULT NULL,
  `TELEPHONE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `WECHAT` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SELLER_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share`  (
  `SHARE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `MEDIA_KEY` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SHARE_TIME` datetime(0) NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  PRIMARY KEY (`SHARE_ID`) USING BTREE,
  INDEX `FK_l1xbggeckk5i4j6h8965o4ocg`(`CUSTOMER_ID`) USING BTREE,
  CONSTRAINT `share_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `CART_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CART_NUM` int(11) NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `PRODUCT_TYPE` int(11) NULL DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  `PRODUCT_ID` int(11) NOT NULL,
  PRIMARY KEY (`CART_ID`) USING BTREE,
  INDEX `FK_8vqxuht0s4int4jajsps0gni3`(`CUSTOMER_ID`) USING BTREE,
  INDEX `FK_tlp8nv41tfnkvnlkoh5w71gcs`(`PRODUCT_ID`) USING BTREE,
  CONSTRAINT `shopping_cart_ibfk_1` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`PRODUCT_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `shopping_cart_ibfk_2` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sms_log
-- ----------------------------
DROP TABLE IF EXISTS `sms_log`;
CREATE TABLE `sms_log`  (
  `SMS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SMS_CODE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SMS_CONTENT` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SMS_PHONE` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SMS_REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SMS_RESULT` int(11) NOT NULL,
  `SMS_TIME` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`SMS_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for spec
-- ----------------------------
DROP TABLE IF EXISTS `spec`;
CREATE TABLE `spec`  (
  `SPEC_ID` int(11) NOT NULL AUTO_INCREMENT,
  `REMARK` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `SPEC_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `CATALOG_ID` int(11) NOT NULL,
  PRIMARY KEY (`SPEC_ID`) USING BTREE,
  INDEX `FK_oyu6essu6x57ogdfhabgj0y43`(`CATALOG_ID`) USING BTREE,
  CONSTRAINT `spec_ibfk_1` FOREIGN KEY (`CATALOG_ID`) REFERENCES `catalog` (`CATALOG_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 537 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for special_subject
-- ----------------------------
DROP TABLE IF EXISTS `special_subject`;
CREATE TABLE `special_subject`  (
  `SUBJECT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ARTICLE_AMOUNT` int(11) NOT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `DESCRIPTION` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ENABLED` bit(1) NOT NULL,
  `ORDER_TIME` datetime(0) NULL DEFAULT NULL,
  `PUBLISH_DATE` date NOT NULL,
  `RECOMMEND` bit(1) NOT NULL,
  `SHORT_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `SORT_ORDER` int(11) NOT NULL,
  `SUBJECT_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `PROGRAMA_ID` int(11) NOT NULL,
  PRIMARY KEY (`SUBJECT_ID`) USING BTREE,
  UNIQUE INDEX `UK_lbyhiyynph9snfiu9hi0bvvt6`(`SUBJECT_NAME`) USING BTREE,
  INDEX `FK_ckl6pwbsm5kj0e6o4map3nafu`(`PROGRAMA_ID`) USING BTREE,
  CONSTRAINT `special_subject_ibfk_1` FOREIGN KEY (`PROGRAMA_ID`) REFERENCES `programa` (`PROGRAMA_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `STORE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `CITY` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `DISTRICT` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `ENABLED` bit(1) NOT NULL,
  `POST` int(11) NULL DEFAULT NULL,
  `PROVINCE` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REMARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `STORE_CODE` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `STORE_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`STORE_ID`) USING BTREE,
  UNIQUE INDEX `UK_464sq80saic1wwfv1smnqufe6`(`STORE_CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `SUPPLIER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `ENABLED` bit(1) NOT NULL,
  `LOCATION` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REMARK` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SUPPLIER_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `SUPPLIER_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`SUPPLIER_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for system_letter
-- ----------------------------
DROP TABLE IF EXISTS `system_letter`;
CREATE TABLE `system_letter`  (
  `LETTER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(3000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `PUBLISH_TIME` datetime(0) NULL DEFAULT NULL,
  `PUBLISH_TYPE` int(11) NOT NULL,
  `PUBLISHED` bit(1) NOT NULL,
  `RECIPIENT` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `TITLE` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`LETTER_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log`  (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ARG_NAMES` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `METHOD_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `OPERATE_TYPE` int(11) NULL DEFAULT NULL,
  `REMARK` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `RETURNING` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `USER_ID` int(11) NOT NULL,
  `USER_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`LOG_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3429 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `EMAIL` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `ENABLED` bit(1) NULL DEFAULT NULL,
  `LAST_TIME` datetime(0) NULL DEFAULT NULL,
  `MOBILE` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `PASSWORD` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `POSITION` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `REAL_NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `REMARK` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SEX` bit(1) NULL DEFAULT NULL,
  `USER_NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `SELLER_ID` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`) USING BTREE,
  INDEX `FK_k74salpnsxpdredc9jnkpc2xg`(`SELLER_ID`) USING BTREE,
  CONSTRAINT `user_info_ibfk_1` FOREIGN KEY (`SELLER_ID`) REFERENCES `seller` (`SELLER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `USER_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`, `ROLE_ID`) USING BTREE,
  INDEX `FK_qxlog73d0t2auuod93t5qfw9h`(`ROLE_ID`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user_info` (`USER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`ROLE_ID`) REFERENCES `role_info` (`ROLE_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- View structure for statistics_day_money_paid_view
-- ----------------------------
DROP VIEW IF EXISTS `statistics_day_money_paid_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `statistics_day_money_paid_view` AS select `orderx`.`ORDER_ID` AS `MONEY_VIEW_ID`,date_format(`orderx`.`CREATE_TIME`,'%Y-%m-%d') AS `CREATE_TIME`,extract(year from `orderx`.`CREATE_TIME`) AS `year`,extract(month from `orderx`.`CREATE_TIME`) AS `month`,extract(day from `orderx`.`CREATE_TIME`) AS `day`,sum(`orderx`.`FEE_TOTAL`) AS `ORDER_PRICE` from `order_form` `orderx` where ((`orderx`.`ORDER_STATE` <> 0) and (`orderx`.`ORDER_STATE` <> 7) and (`orderx`.`ORDER_STATE` <> 8)) group by date_format(`orderx`.`CREATE_TIME`,'%Y-%m-%d'); ;

-- ----------------------------
-- View structure for statistics_day_money_view
-- ----------------------------
DROP VIEW IF EXISTS `statistics_day_money_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `statistics_day_money_view` AS select `orderx`.`ORDER_ID` AS `MONEY_VIEW_ID`,date_format(`orderx`.`CREATE_TIME`,'%Y-%m-%d') AS `CREATE_TIME`,extract(year from `orderx`.`CREATE_TIME`) AS `year`,extract(month from `orderx`.`CREATE_TIME`) AS `month`,extract(day from `orderx`.`CREATE_TIME`) AS `day`,sum(`orderx`.`FEE_TOTAL`) AS `ORDER_PRICE` from `order_form` `orderx` group by date_format(`orderx`.`CREATE_TIME`,'%Y-%m-%d'); ;

-- ----------------------------
-- View structure for statistics_day_order_paid_view
-- ----------------------------
DROP VIEW IF EXISTS `statistics_day_order_paid_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `statistics_day_order_paid_view` AS select `orderx`.`ORDER_ID` AS `ORDER_VIEW_ID`,date_format(`orderx`.`CREATE_TIME`,'%Y-%m-%d') AS `CREATE_TIME`,extract(year from `orderx`.`CREATE_TIME`) AS `year`,extract(month from `orderx`.`CREATE_TIME`) AS `month`,extract(day from `orderx`.`CREATE_TIME`) AS `day`,count(`orderx`.`ORDER_ID`) AS `ORDER_NUM` from `order_form` `orderx` where ((`orderx`.`ORDER_STATE` <> 0) and (`orderx`.`ORDER_STATE` <> 7) and (`orderx`.`ORDER_STATE` <> 8)) group by date_format(`orderx`.`CREATE_TIME`,'%Y-%m-%d'); ;

-- ----------------------------
-- View structure for statistics_day_order_view
-- ----------------------------
DROP VIEW IF EXISTS `statistics_day_order_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `statistics_day_order_view` AS select `orderx`.`ORDER_ID` AS `order_View_Id`,date_format(`orderx`.`CREATE_TIME`,'%Y-%m-%d') AS `CREATE_TIME`,extract(year from `orderx`.`CREATE_TIME`) AS `year`,extract(month from `orderx`.`CREATE_TIME`) AS `month`,extract(day from `orderx`.`CREATE_TIME`) AS `day`,count(`orderx`.`ORDER_ID`) AS `ORDER_NUM` from `order_form` `orderx` group by date_format(`orderx`.`CREATE_TIME`,'%Y-%m-%d'); ;

-- ----------------------------
-- View structure for statistics_month_money_paid_view
-- ----------------------------
DROP VIEW IF EXISTS `statistics_month_money_paid_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `statistics_month_money_paid_view` AS select `orderx`.`ORDER_ID` AS `MONEY_VIEW_ID`,extract(year from `orderx`.`CREATE_TIME`) AS `year`,extract(month from `orderx`.`CREATE_TIME`) AS `month`,sum(`orderx`.`FEE_TOTAL`) AS `ORDER_PRICE` from `order_form` `orderx` where ((`orderx`.`ORDER_STATE` <> 0) and (`orderx`.`ORDER_STATE` <> 7) and (`orderx`.`ORDER_STATE` <> 8)) group by date_format(`orderx`.`CREATE_TIME`,'%Y-%m'); ;

-- ----------------------------
-- View structure for statistics_month_money_view
-- ----------------------------
DROP VIEW IF EXISTS `statistics_month_money_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `statistics_month_money_view` AS select `orderx`.`ORDER_ID` AS `MONEY_VIEW_ID`,extract(year from `orderx`.`CREATE_TIME`) AS `year`,extract(month from `orderx`.`CREATE_TIME`) AS `month`,sum(`orderx`.`FEE_TOTAL`) AS `ORDER_PRICE` from `order_form` `orderx` group by date_format(`orderx`.`CREATE_TIME`,'%Y-%m'); ;

-- ----------------------------
-- View structure for statistics_month_order_paid_view
-- ----------------------------
DROP VIEW IF EXISTS `statistics_month_order_paid_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `statistics_month_order_paid_view` AS select `orderx`.`ORDER_ID` AS `ORDER_VIEW_ID`,extract(year from `orderx`.`CREATE_TIME`) AS `year`,extract(month from `orderx`.`CREATE_TIME`) AS `month`,count(`orderx`.`ORDER_ID`) AS `ORDER_NUM` from `order_form` `orderx` where ((`orderx`.`ORDER_STATE` <> 0) and (`orderx`.`ORDER_STATE` <> 7) and (`orderx`.`ORDER_STATE` <> 8)) group by date_format(`orderx`.`CREATE_TIME`,'%Y-%m'); ;

-- ----------------------------
-- View structure for statistics_month_order_view
-- ----------------------------
DROP VIEW IF EXISTS `statistics_month_order_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `statistics_month_order_view` AS select `orderx`.`ORDER_ID` AS `order_View_Id`,extract(year from `orderx`.`CREATE_TIME`) AS `year`,extract(month from `orderx`.`CREATE_TIME`) AS `month`,count(`orderx`.`ORDER_ID`) AS `ORDER_NUM` from `order_form` `orderx` group by date_format(`orderx`.`CREATE_TIME`,'%Y-%m'); ;

SET FOREIGN_KEY_CHECKS = 1;
