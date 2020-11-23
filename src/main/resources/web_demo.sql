/*
 Navicat Premium Data Transfer

 Source Server         : sad
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : web_demo

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 23/11/2020 22:01:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_state
-- ----------------------------
DROP TABLE IF EXISTS `order_state`;
CREATE TABLE `order_state`  (
  `state_code` int(0) NOT NULL COMMENT '状态码',
  `state_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态描述',
  PRIMARY KEY (`state_code`) USING BTREE,
  INDEX `state_desc`(`state_desc`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_state
-- ----------------------------
INSERT INTO `order_state` VALUES (2, '已受理');
INSERT INTO `order_state` VALUES (3, '已完成');
INSERT INTO `order_state` VALUES (5, '已完结');
INSERT INTO `order_state` VALUES (1, '已提交');
INSERT INTO `order_state` VALUES (4, '待付款');
INSERT INTO `order_state` VALUES (6, '待完善');

-- ----------------------------
-- Table structure for order_type
-- ----------------------------
DROP TABLE IF EXISTS `order_type`;
CREATE TABLE `order_type`  (
  `type_num` int(0) NOT NULL AUTO_INCREMENT COMMENT '类型编码',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型描述',
  PRIMARY KEY (`type_num`) USING BTREE,
  INDEX `type_name`(`type_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_type
-- ----------------------------
INSERT INTO `order_type` VALUES (5, 'PPT定制');
INSERT INTO `order_type` VALUES (6, 'VR定制');
INSERT INTO `order_type` VALUES (7, '小程序开发');
INSERT INTO `order_type` VALUES (4, '打印复印');
INSERT INTO `order_type` VALUES (1, '硬件维修');
INSERT INTO `order_type` VALUES (3, '系统维护');
INSERT INTO `order_type` VALUES (8, '软件定制');
INSERT INTO `order_type` VALUES (2, '软件配置');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `OID` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工单编号',
  `UID` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号，外键',
  `type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型，外键',
  `desc_text` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题描述',
  `photos` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片路径',
  `submit_time` datetime(0) NOT NULL COMMENT '提交时间',
  `state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态描述，外键',
  `resp_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '反馈内容',
  `resp_time` datetime(0) NULL DEFAULT NULL COMMENT '反馈时间',
  PRIMARY KEY (`OID`) USING BTREE,
  INDEX `OID`(`OID`) USING BTREE,
  INDEX `UID`(`UID`) USING BTREE,
  INDEX `type`(`type`) USING BTREE,
  INDEX `state`(`state`) USING BTREE,
  CONSTRAINT `state` FOREIGN KEY (`state`) REFERENCES `order_state` (`state_desc`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `type` FOREIGN KEY (`type`) REFERENCES `order_type` (`type_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `UID` FOREIGN KEY (`UID`) REFERENCES `users` (`UID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for passage_category
-- ----------------------------
DROP TABLE IF EXISTS `passage_category`;
CREATE TABLE `passage_category`  (
  `cate_code` int(0) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `cate_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  PRIMARY KEY (`cate_code`) USING BTREE,
  INDEX `cate_desc`(`cate_desc`) USING BTREE,
  INDEX `cate_desc_2`(`cate_desc`, `cate_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of passage_category
-- ----------------------------
INSERT INTO `passage_category` VALUES (2, '公告');
INSERT INTO `passage_category` VALUES (3, '教程');
INSERT INTO `passage_category` VALUES (1, '未分类');

-- ----------------------------
-- Table structure for passages
-- ----------------------------
DROP TABLE IF EXISTS `passages`;
CREATE TABLE `passages`  (
  `PID` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章编码',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布者',
  `pub_time` datetime(0) NOT NULL COMMENT '发布时间',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章内容',
  `category` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '未分类' COMMENT '文章类型',
  `views` int(0) NOT NULL DEFAULT 0 COMMENT '点击量',
  `photo_src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `isPublished` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '是否公开显示',
  PRIMARY KEY (`PID`) USING BTREE,
  INDEX `author`(`author`) USING BTREE,
  INDEX `category`(`category`) USING BTREE,
  CONSTRAINT `author` FOREIGN KEY (`author`) REFERENCES `users` (`UID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `category` FOREIGN KEY (`category`) REFERENCES `passage_category` (`cate_desc`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `UID` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户唯一识别码',
  `disp_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `passwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号密码',
  `sex` enum('','男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '性别',
  `major_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业班级',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `idendity` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份标识',
  `reg_time` datetime(0) NOT NULL COMMENT '注册时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆时间',
  PRIMARY KEY (`UID`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `UID`(`UID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
