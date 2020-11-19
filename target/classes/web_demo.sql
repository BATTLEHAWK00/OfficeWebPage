/*
 Navicat Premium Data Transfer

 Source Server         : firs_con
 Source Server Type    : MySQL
 Source Server Version : 50022
 Source Host           : localhost:3306
 Source Schema         : web_demo

 Target Server Type    : MySQL
 Target Server Version : 50022
 File Encoding         : 65001

 Date: 03/11/2020 20:56:26
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `OID` bigint(10) NOT NULL DEFAULT '' COMMENT '工单编号',
  `UID` bigint(13) NOT NULL DEFAULT '' COMMENT '用户编号，外键',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '类型，外键',
  `desc_text` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题描述',
  `photos` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `submit_time` datetime NOT NULL DEFAULT '' COMMENT '提交时间',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '当前状态，外键',
  `res_text` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '反馈描述',
  `res_time` datetime NULL DEFAULT NULL COMMENT '反馈时间',
  PRIMARY KEY USING BTREE (`OID`),
  INDEX `UID` USING BTREE(`UID`),
  INDEX `ytpe` USING BTREE(`type`),
  INDEX `state` USING BTREE(`state`),
  CONSTRAINT `UID` FOREIGN KEY (`UID`) REFERENCES `user` (`UID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ytpe` FOREIGN KEY (`type`) REFERENCES `order_type` (`type_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `state` FOREIGN KEY (`state`) REFERENCES `state` (`state_desc`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 11264 kB; (`UID`) REFER `web_demo/user`(`UID`); (`type`) REFER `web' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (2020110301, 2019310340123, '系统维护', '重装系统', NULL, '2020-11-03 20:37:08', '已提交', '', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for order_type
-- ----------------------------
DROP TABLE IF EXISTS `order_type`;
CREATE TABLE `order_type`  (
  `type_num` int(2) NOT NULL DEFAULT '' COMMENT '类型编号',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '类型描述',
  PRIMARY KEY USING BTREE (`type_num`),
  INDEX `type_name` USING BTREE(`type_name`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state`  (
  `state_code` int(1) NOT NULL DEFAULT '' COMMENT '状态码',
  `state_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '状态描述',
  PRIMARY KEY USING BTREE (`state_code`),
  INDEX `state_desc` USING BTREE(`state_desc`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of state
-- ----------------------------
INSERT INTO `state` VALUES (4, '已完成');
INSERT INTO `state` VALUES (2, '已接收');
INSERT INTO `state` VALUES (1, '已提交');
INSERT INTO `state` VALUES (3, '待付款');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `UID` bigint(13) NOT NULL DEFAULT '' COMMENT '用户编号，用学号/另外编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名称',
  `psd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '登录密码',
  `sex` enum('男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '性别',
  `profess_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业班级',
  `tel` bigint(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `identity` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户身份标识',
  `register_time` date NOT NULL DEFAULT '' COMMENT '注册时间',
  PRIMARY KEY USING BTREE (`UID`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2019310340123, 'Czzzz', '123456', '男', '信管1901', 17671317643, '0', '2020-11-03');

SET FOREIGN_KEY_CHECKS = 1;
