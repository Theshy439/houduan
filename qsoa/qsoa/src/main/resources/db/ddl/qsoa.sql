/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : qsoa

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-09-01 09:50:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info` (
  `id` bigint(10) NOT NULL,
  `username` varchar(50) NOT NULL COMMENT '姓名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `phone` bigint(50) NOT NULL,
  `create_by` varchar(10) NOT NULL,
  `create_date` bigint(15) NOT NULL,
  `update_by` varchar(10) NOT NULL,
  `update_date` bigint(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
