/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : csdndemo

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-03-27 17:00:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dict`
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(50) DEFAULT NULL COMMENT '代码',
  `text` varchar(50) DEFAULT NULL COMMENT '描述',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `value` varchar(50) DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dict
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgcws90nffc311tac0vg2xy6cw` (`group_id`),
  CONSTRAINT `FKgcws90nffc311tac0vg2xy6cw` FOREIGN KEY (`group_id`) REFERENCES `org_group` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'hyll', '140b6ce18716153fba3bf98a52722bd5', 'linzf', 'xxxx超大大厦', 'xx小兵', '10', null, 'xx市', 'xx区', 'xx省', 'xx大厦', '1', '1', '2017-12-07 11:17:19');

-- ----------------------------
-- Table structure for `user_associate_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_associate_role`;
CREATE TABLE `user_associate_role` (
  `user_id` int(11) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK4kspd00l9tn3oi6swv1cjf7vh` (`role_id`),
  KEY `FKl7kmw86eevxmoxwxu55poq7bm` (`user_id`),
  CONSTRAINT `FK4kspd00l9tn3oi6swv1cjf7vh` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`id`),
  CONSTRAINT `FKl7kmw86eevxmoxwxu55poq7bm` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_associate_role
-- ----------------------------
INSERT INTO `user_associate_role` VALUES ('1', '1');
INSERT INTO `user_associate_role` VALUES ('1', '2');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', 'ROLE_ADMIN', '系统管理员');
INSERT INTO `user_role` VALUES ('2', 'ROLE_USER', '普通用户');
