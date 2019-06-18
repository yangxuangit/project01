/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50550
Source Host           : localhost:3306
Source Database       : dailysystem

Target Server Type    : MYSQL
Target Server Version : 50550
File Encoding         : 65001

Date: 2018-09-01 22:00:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for daily
-- ----------------------------
DROP TABLE IF EXISTS `daily`;
CREATE TABLE `daily` (
  `id` varchar(255) NOT NULL,
  `todaytask` varchar(2000) DEFAULT NULL,
  `completeinfo` varchar(2000) DEFAULT NULL,
  `nextplan` varchar(2000) DEFAULT NULL,
  `subtime` datetime DEFAULT NULL,
  `lastupdatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_daily_student_1` (`sid`),
  CONSTRAINT `fk_daily_student_1` FOREIGN KEY (`sid`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of daily
-- ----------------------------
INSERT INTO `daily` VALUES ('56662ac6-36bf-40e9-baee-900b555a3f98', '基于servlet,jsp，jq,bootstrap,bv框架的一个日报提交功能							', '基本完全掌握，还需要通过过项目练习加强与巩固							', 'servlet过滤器，token机制防止页面刷新表单重复提交功能											', '2018-09-01 10:01:05', '2018-09-01 10:01:05', '5');
INSERT INTO `daily` VALUES ('7f77a789-bdf3-4470-bfee-08b5eeade5b2', '基于servlet,jsp，jq,bootstrap,bv框架的一个日报提交功能							', '基于servlet,jsp，jq,bootstrap,bv框架的一个日报提交功能							', 'servlet过滤器，token机制防止页面刷新表单重复提交功能							', '2018-09-01 10:03:54', '2018-09-01 10:03:54', '9');
INSERT INTO `daily` VALUES ('9bfcfd33-d11d-4e94-9977-2949f037ff64', '下课回家', '快完成了', '明天继续上课', '2018-08-31 14:21:29', '2018-08-31 14:21:29', '5');
INSERT INTO `daily` VALUES ('d64f822b-eac8-4c0f-8212-219fb4c50bb7', '熟练使用基于servlet的crud操作，熟悉EL表达式与JSTL标准标签库', '基本掌握，还要练习加强', 'ajax技术，异步刷新，通用分页技术', '2018-08-31 14:15:01', '2018-08-31 14:15:01', '5');
INSERT INTO `daily` VALUES ('e5337b70-6e11-4a38-8af9-cc01fb665087', '熟练使用基于servlet的crud操作，熟悉EL表达式与JSTL标准标签库', '基本掌握，还要练习加强', 'ajax技术，异步刷新，通用分页技术', '2018-08-31 14:17:50', '2018-08-31 14:17:50', '5');
INSERT INTO `daily` VALUES ('f21c3bcd-fdc2-477f-bcc8-85c460e37534', '熟练使用基于servlet的crud操作，熟悉EL表达式与JSTL标准标签库', '基本掌握，还要练习加强', 'ajax技术，异步刷新，通用分页技术', '2018-08-31 14:18:22', '2018-08-31 14:18:22', '5');
INSERT INTO `daily` VALUES ('f4347ad4-097d-4189-a64a-1e199e493f95', 'helloworld hello softeem							', 'ok fine thank you and you?							', 'nice to meet you,how are you?							', '2018-09-01 10:02:04', '2018-09-01 10:02:04', '5');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `regtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('5', '周润发', '13247116003', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2018-08-30 17:31:14');
INSERT INTO `student` VALUES ('6', '张三', '13476543211', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2018-08-31 10:09:13');
INSERT INTO `student` VALUES ('7', '德玛西亚', '18686868886', 'C8837B23FF8AAA8A2DDE915473CE0991', '男', '2018-08-31 11:54:50');
INSERT INTO `student` VALUES ('8', '易大师', '13688888888', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2018-08-31 11:57:38');
INSERT INTO `student` VALUES ('9', '艾希', '13666666666', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2018-08-31 11:59:00');
INSERT INTO `student` VALUES ('10', 'admin', '15811111111', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2018-08-31 16:49:14');
INSERT INTO `student` VALUES ('11', '马儿扎哈', '13999999999', 'E10ADC3949BA59ABBE56E057F20F883E', '男', '2018-09-01 08:54:28');
