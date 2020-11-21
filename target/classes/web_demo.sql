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

 Date: 21/11/2020 22:26:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `OID` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工单编号',
  `UID` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号，外键',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型，外键',
  `desc_text` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题描述',
  `photos` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `submit_time` datetime(0) NOT NULL COMMENT '提交时间',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态描述，外键',
  `resp_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '反馈内容',
  `resp_time` datetime(0) NULL DEFAULT NULL COMMENT '反馈时间',
  PRIMARY KEY (`OID`) USING BTREE,
  INDEX `UID`(`UID`) USING BTREE,
  INDEX `type`(`type`) USING BTREE,
  INDEX `state`(`state`) USING BTREE,
  CONSTRAINT `state` FOREIGN KEY (`state`) REFERENCES `state` (`state_desc`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `type` FOREIGN KEY (`type`) REFERENCES `order_type` (`type_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `UID` FOREIGN KEY (`UID`) REFERENCES `user` (`UID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_type
-- ----------------------------
DROP TABLE IF EXISTS `order_type`;
CREATE TABLE `order_type`  (
  `type_num` int(0) NOT NULL COMMENT '类型编码',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型描述',
  PRIMARY KEY (`type_num`) USING BTREE,
  INDEX `type_name`(`type_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for passage_category
-- ----------------------------
DROP TABLE IF EXISTS `passage_category`;
CREATE TABLE `passage_category`  (
  `cate_code` int(0) NOT NULL COMMENT '编码',
  `cate_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  PRIMARY KEY (`cate_code`) USING BTREE,
  INDEX `cate_desc`(`cate_desc`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for passages
-- ----------------------------
DROP TABLE IF EXISTS `passages`;
CREATE TABLE `passages`  (
  `passage_id` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章编码',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布者',
  `time` datetime(0) NOT NULL COMMENT '发布时间',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章内容',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章类型',
  `views` int(0) NOT NULL DEFAULT 0 COMMENT '点击量',
  `photo_src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`passage_id`) USING BTREE,
  INDEX `auth`(`author`) USING BTREE,
  INDEX `category`(`category`) USING BTREE,
  CONSTRAINT `auth` FOREIGN KEY (`author`) REFERENCES `user` (`name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `category` FOREIGN KEY (`category`) REFERENCES `passage_category` (`cate_desc`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state`  (
  `state_code` int(0) NOT NULL COMMENT '状态码',
  `state_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态描述',
  PRIMARY KEY (`state_code`) USING BTREE,
  INDEX `state_desc`(`state_desc`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `UID` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户唯一识别码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `passwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号密码',
  `sex` enum('','男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `major_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业班级',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `idendity` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份识别码',
  `reg_time` datetime(0) NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`UID`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
