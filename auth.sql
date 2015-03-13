/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : auth

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2015-03-13 17:53:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_menu`
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` varchar(40) NOT NULL,
  `menu_name` varchar(40) NOT NULL,
  `menu_order` tinyint(4) DEFAULT NULL,
  `menu_action` varchar(100) DEFAULT NULL,
  `menu_level` tinyint(4) NOT NULL,
  `parent_id` varchar(40) DEFAULT NULL,
  `menu_code` varchar(10) DEFAULT NULL,
  `menu_status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('b73c2a486ab046d9b6f6e1248436df52', 'ç¨æ·ç®¡ç', '1', '/abc', '1', '', '00', '0');
INSERT INTO `tb_menu` VALUES ('eb03a11d55144385a0ecc71160dbee05', 'test', '2', 'ddd', '1', '', '01', '0');

-- ----------------------------
-- Table structure for `td_button`
-- ----------------------------
DROP TABLE IF EXISTS `td_button`;
CREATE TABLE `td_button` (
  `id` int(11) NOT NULL,
  `btn_name` varchar(50) NOT NULL,
  `btn_text` varchar(50) DEFAULT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单对应页面上的按钮';

-- ----------------------------
-- Records of td_button
-- ----------------------------

-- ----------------------------
-- Table structure for `td_login_user`
-- ----------------------------
DROP TABLE IF EXISTS `td_login_user`;
CREATE TABLE `td_login_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `first_letter` varchar(10) DEFAULT NULL,
  `passwd` varchar(128) NOT NULL,
  `role_type` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_login_user
-- ----------------------------
INSERT INTO `td_login_user` VALUES ('1', 'test', 'test2@ab.com', null, '111111', null, null, null, null);
INSERT INTO `td_login_user` VALUES ('2', '111', '111', null, '222', null, null, null, null);
INSERT INTO `td_login_user` VALUES ('3', '001', '张三', null, '1', null, null, null, null);
INSERT INTO `td_login_user` VALUES ('4', '002', '大米', null, '1', null, null, null, null);

-- ----------------------------
-- Table structure for `td_menu`
-- ----------------------------
DROP TABLE IF EXISTS `td_menu`;
CREATE TABLE `td_menu` (
  `id` int(11) NOT NULL,
  `menu_name` varchar(50) NOT NULL,
  `menu_action` varchar(100) DEFAULT NULL,
  `menu_order` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `menu_status` int(11) DEFAULT NULL,
  `system_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `td_site`
-- ----------------------------
DROP TABLE IF EXISTS `td_site`;
CREATE TABLE `td_site` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `site_name` varchar(100) NOT NULL,
  `site_url` varchar(100) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='描述需要配置权限的各个系统信息';

-- ----------------------------
-- Records of td_site
-- ----------------------------
INSERT INTO `td_site` VALUES ('4', 'test', '222', null);
INSERT INTO `td_site` VALUES ('5', 'test222', '3333', null);
INSERT INTO `td_site` VALUES ('6', 'aaa', 'aaa', null);
INSERT INTO `td_site` VALUES ('7', 'sdfsdf', 'sdfsd', null);
INSERT INTO `td_site` VALUES ('8', '3333333', '333333', null);
INSERT INTO `td_site` VALUES ('9', '555', '555555', null);
INSERT INTO `td_site` VALUES ('10', '111111', '11111', null);
INSERT INTO `td_site` VALUES ('11', 'ddddd', 'dddd', null);
INSERT INTO `td_site` VALUES ('12', '88888', '88', null);
INSERT INTO `td_site` VALUES ('13', '88888', '888', null);
INSERT INTO `td_site` VALUES ('14', '123', '123', null);
INSERT INTO `td_site` VALUES ('15', '123', '123', null);
INSERT INTO `td_site` VALUES ('16', '111', '111', null);
INSERT INTO `td_site` VALUES ('17', 'sdfsdf', 'sdfsdf', null);
INSERT INTO `td_site` VALUES ('18', 'ssssss', 'ssss', null);
