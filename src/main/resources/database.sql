/*
 Navicat Premium Data Transfer

 Source Server         : sad
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : office_web

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 29/11/2020 17:32:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_state
-- ----------------------------
DROP TABLE IF EXISTS `order_state`;
CREATE TABLE `order_state`  (
  `state_index` int(0) NOT NULL COMMENT '状态码',
  `state_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态描述',
  PRIMARY KEY (`state_index`) USING BTREE,
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
  `type_index` int(0) NOT NULL AUTO_INCREMENT COMMENT '类型编码',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型描述',
  PRIMARY KEY (`type_index`) USING BTREE,
  INDEX `type_name`(`type_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `OID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '工单编号',
  `UID` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户编号，外键',
  `type` int(0) NOT NULL COMMENT '类型，外键',
  `desc_text` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题描述',
  `photos` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片路径',
  `submit_time` datetime(0) NOT NULL COMMENT '提交时间',
  `state` int(0) NOT NULL COMMENT '状态描述，外键',
  `resp_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '反馈内容',
  `resp_time` datetime(0) NULL DEFAULT NULL COMMENT '反馈时间',
  PRIMARY KEY (`OID`) USING BTREE,
  INDEX `OID`(`OID`) USING BTREE,
  INDEX `UID`(`UID`) USING BTREE,
  INDEX `type`(`type`) USING BTREE,
  INDEX `state`(`state`) USING BTREE,
  CONSTRAINT `order_uid` FOREIGN KEY (`UID`) REFERENCES `users` (`UID`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `state` FOREIGN KEY (`state`) REFERENCES `order_state` (`state_index`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `type` FOREIGN KEY (`type`) REFERENCES `order_type` (`type_index`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for passage_category
-- ----------------------------
DROP TABLE IF EXISTS `passage_category`;
CREATE TABLE `passage_category`  (
  `cate_index` int(0) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `cate_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  PRIMARY KEY (`cate_index`) USING BTREE,
  INDEX `cate_desc`(`cate_desc`) USING BTREE,
  INDEX `cate_desc_2`(`cate_desc`, `cate_index`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `PID` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章编码',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发布者',
  `pub_time` datetime(0) NOT NULL COMMENT '发布时间',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章内容',
  `category` int(0) NOT NULL DEFAULT 1 COMMENT '文章类型',
  `views` int(0) NOT NULL DEFAULT 0 COMMENT '点击量',
  `photo_src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `isPublished` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '是否公开显示',
  PRIMARY KEY (`PID`) USING BTREE,
  INDEX `author`(`author`) USING BTREE,
  INDEX `category`(`category`) USING BTREE,
  CONSTRAINT `author_uid` FOREIGN KEY (`author`) REFERENCES `users` (`UID`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `passage_category` FOREIGN KEY (`category`) REFERENCES `passage_category` (`cate_index`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_identity
-- ----------------------------
DROP TABLE IF EXISTS `user_identity`;
CREATE TABLE `user_identity`  (
  `identity_index` int(0) NOT NULL AUTO_INCREMENT COMMENT '身份编码',
  `identity_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份文本',
  PRIMARY KEY (`identity_index`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_identity
-- ----------------------------
INSERT INTO `user_identity` VALUES (1, '管理员');
INSERT INTO `user_identity` VALUES (2, '学生');
INSERT INTO `user_identity` VALUES (3, '其他');

-- ----------------------------
-- Table structure for userinfo_other
-- ----------------------------
DROP TABLE IF EXISTS `userinfo_other`;
CREATE TABLE `userinfo_other`  (
  `uid` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户UID',
  `faculty` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '其它' COMMENT '学院',
  `organization` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '其它' COMMENT '单位',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`uid`) USING BTREE,
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `users` (`UID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for userinfo_student
-- ----------------------------
DROP TABLE IF EXISTS `userinfo_student`;
CREATE TABLE `userinfo_student`  (
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户UID',
  `major` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '专业',
  `class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '班级',
  `stu_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学号',
  PRIMARY KEY (`uid`) USING BTREE,
  CONSTRAINT `stu_uid` FOREIGN KEY (`uid`) REFERENCES `users` (`UID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `UID` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户唯一识别码',
  `disp_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `passwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号密码',
  `sex` enum('未指定','男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '未指定' COMMENT '性别',
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '邮箱',
  `identity` int(0) NOT NULL COMMENT '身份标识',
  `reg_time` datetime(0) NOT NULL COMMENT '注册时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆时间',
  PRIMARY KEY (`UID`) USING BTREE,
  INDEX `UID`(`UID`) USING BTREE,
  INDEX `name_passwd`(`passwd`, `name`) USING BTREE,
  INDEX `disp_name`(`disp_name`) USING BTREE,
  INDEX `identity`(`identity`) USING BTREE,
  CONSTRAINT `identity` FOREIGN KEY (`identity`) REFERENCES `user_identity` (`identity_index`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for v_login
-- ----------------------------
DROP VIEW IF EXISTS `v_login`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_login` AS select `users`.`UID` AS `UID`,`users`.`name` AS `name`,`users`.`passwd` AS `passwd` from `users`;

-- ----------------------------
-- View structure for v_orders
-- ----------------------------
DROP VIEW IF EXISTS `v_orders`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_orders` AS select `orders`.`OID` AS `OID`,`orders`.`UID` AS `UID`,`orders`.`desc_text` AS `desc_text`,`orders`.`photos` AS `photos`,`orders`.`submit_time` AS `submit_time`,`orders`.`resp_text` AS `resp_text`,`orders`.`resp_time` AS `resp_time`,`order_type`.`type_name` AS `type`,`order_state`.`state_desc` AS `state` from ((`orders` join `order_type` on((`orders`.`type` = `order_type`.`type_index`))) join `order_state` on((`orders`.`state` = `order_state`.`state_index`))) where ((`orders`.`type` = `order_type`.`type_index`) and (`orders`.`state` = `order_state`.`state_index`));

-- ----------------------------
-- View structure for v_posts
-- ----------------------------
DROP VIEW IF EXISTS `v_posts`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_posts` AS select `passages`.`PID` AS `PID`,`passages`.`title` AS `title`,`passages`.`author` AS `author`,`passages`.`pub_time` AS `pub_time`,`passages`.`content` AS `content`,`passages`.`category` AS `category`,`passages`.`views` AS `views`,`passages`.`photo_src` AS `photo_src` from `passages` where (`passages`.`isPublished` = 1);

-- ----------------------------
-- View structure for v_user_other
-- ----------------------------
DROP VIEW IF EXISTS `v_user_other`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_user_other` AS select `users`.`UID` AS `UID`,`users`.`disp_name` AS `disp_name`,`users`.`name` AS `name`,`users`.`tel` AS `tel`,`users`.`email` AS `email`,`users`.`reg_time` AS `reg_time`,`users`.`login_time` AS `login_time`,`userinfo_other`.`faculty` AS `faculty`,`userinfo_other`.`organization` AS `organization`,`userinfo_other`.`remark` AS `remark` from (`users` join `userinfo_other` on((`userinfo_other`.`uid` = `users`.`UID`))) where ((`users`.`identity` = 3) and (`users`.`UID` = `userinfo_other`.`uid`));

-- ----------------------------
-- View structure for v_user_stu
-- ----------------------------
DROP VIEW IF EXISTS `v_user_stu`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_user_stu` AS select `users`.`UID` AS `UID`,`users`.`disp_name` AS `disp_name`,`users`.`sex` AS `sex`,`users`.`tel` AS `tel`,`users`.`email` AS `email`,`users`.`reg_time` AS `reg_time`,`users`.`login_time` AS `login_time`,`userinfo_student`.`major` AS `major`,`userinfo_student`.`class` AS `class`,`userinfo_student`.`stu_number` AS `stu_number` from (`users` join `userinfo_student` on((`userinfo_student`.`uid` = `users`.`UID`))) where ((`users`.`identity` = 2) and (`users`.`UID` = `userinfo_student`.`uid`));

SET FOREIGN_KEY_CHECKS = 1;
