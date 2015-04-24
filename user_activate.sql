/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : mediacenter

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2015-04-24 17:56:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user_activate`
-- ----------------------------
DROP TABLE IF EXISTS `user_activate`;
CREATE TABLE `user_activate` (
  `user_id` varchar(40) NOT NULL,
  `valid_date` varchar(20) NOT NULL,
  `valid_code` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table USERS add COLUMN activate_status int;
