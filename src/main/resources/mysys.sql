/*
Navicat MySQL Data Transfer

Source Server         : MyLocalhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : mysys

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-09-15 15:37:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_aci
-- ----------------------------
DROP TABLE IF EXISTS `sys_aci`;
CREATE TABLE `sys_aci` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(11) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '权限码',
  `name` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  `url` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '权限url',
  `acim_id` int(11) NOT NULL DEFAULT '0',
  `level` tinyint(4) NOT NULL DEFAULT '1' COMMENT '级别',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '权限类型 1菜单 2按钮',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 ;1正常 0冻结 ',
  `remark` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `seq` tinyint(4) NOT NULL DEFAULT '1',
  `operator` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  `operator_time` datetime NOT NULL,
  `operator_ip` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_aci
-- ----------------------------

-- ----------------------------
-- Table structure for sys_acim
-- ----------------------------
DROP TABLE IF EXISTS `sys_acim`;
CREATE TABLE `sys_acim` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `level` tinyint(4) NOT NULL DEFAULT '1' COMMENT '级别',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 ;1正常 0冻结 ',
  `remark` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `seq` tinyint(4) NOT NULL DEFAULT '1',
  `operator` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  `operator_time` datetime NOT NULL,
  `operator_ip` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_acim
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '部门名称',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `seq` int(4) NOT NULL DEFAULT '1' COMMENT '排序',
  `level` varchar(64) COLLATE utf8_bin NOT NULL,
  `remark` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  `operator` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后一次操作的人',
  `operator_time` datetime NOT NULL,
  `operator_ip` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '总公司', '0', '1', '0', '总公司', 'sys_save', '2018-08-30 22:46:42', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('2', '上海分公司', '1', '2', '0.1', '分公司', 'sys_save', '2018-08-30 22:47:08', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('3', '北京分公司', '1', '3', '0.1', '北京分公司', 'sys_save', '2018-08-30 22:47:29', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('4', '北京分公司朝阳区', '3', '1', '0.1.3', '北京分公司朝阳', 'sys_save', '2018-08-30 22:48:17', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('5', '北京分公司朝阳区王府井', '4', '1', '0.1.3.4', '北京分公司朝阳王府', 'sys_save', '2018-08-30 22:49:11', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('6', '北京分公司河水区', '3', '2', '0.1.3', '北京分公司河水', 'sys_save', '2018-08-30 22:50:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('7', '北京分公司海定区', '3', '2', '0.1.3', '北京分公司河水', 'sys_save', '2018-08-31 15:44:03', '127.0.0.1');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `target_id` int(11) NOT NULL,
  `type` tinyint(4) NOT NULL COMMENT '状态 ',
  `old_value` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '更新前的值',
  `new_value` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '新值',
  `remark` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  `operator` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后一次操作的人',
  `operator_time` datetime NOT NULL,
  `operator_ip` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '部门名称',
  `type` tinyint(4) NOT NULL COMMENT '类型 1 管理员角色 2普通角色',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1正常 0冻结',
  `remark` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  `operator` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后一次操作的人',
  `operator_time` datetime NOT NULL,
  `operator_ip` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_aci
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_aci`;
CREATE TABLE `sys_role_aci` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `aci_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_aci_id` (`aci_id`) USING BTREE,
  KEY `index_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_role_aci
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '加密后密码',
  `email` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '邮箱',
  `phone` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '手机号',
  `remark` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  `dept_id` int(11) NOT NULL COMMENT '部门id',
  `status` tinyint(4) NOT NULL COMMENT '状态：1正常 0冻结 2删除',
  `operator` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  `operator_time` datetime NOT NULL,
  `operator_ip` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_copy
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_copy`;
CREATE TABLE `sys_user_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '加密后密码',
  `email` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '邮箱',
  `phone` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '手机号',
  `remark` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  `dept_id` int(11) NOT NULL COMMENT '部门id',
  `status` tinyint(4) NOT NULL COMMENT '状态：1正常 0冻结 2删除',
  `operator` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  `operator_time` datetime NOT NULL,
  `operator_ip` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_user_copy
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`) USING BTREE,
  KEY `index_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
