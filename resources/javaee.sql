/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50523
Source Host           : localhost:3306
Source Database       : javaee

Target Server Type    : MYSQL
Target Server Version : 50523
File Encoding         : 65001

Date: 2019-01-05 20:05:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blackuser`
-- ----------------------------
DROP TABLE IF EXISTS `blackuser`;
CREATE TABLE `blackuser` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `current_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blackuser
-- ----------------------------
INSERT INTO `blackuser` VALUES ('1', 'Fcscanf11', '2019-01-05');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `course_number` varchar(20) DEFAULT NULL,
  `course_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('4', '103', '计算机原理');
INSERT INTO `course` VALUES ('5', '101', '计算机基础');
INSERT INTO `course` VALUES ('6', '102', '数据库原理');
INSERT INTO `course` VALUES ('7', '103', '计算机原理');
INSERT INTO `course` VALUES ('8', '101', '计算机基础');
INSERT INTO `course` VALUES ('9', '102', '数据库原理');

-- ----------------------------
-- Table structure for `detail`
-- ----------------------------
DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `truename` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of detail
-- ----------------------------
INSERT INTO `detail` VALUES ('1', 'DFD', 'fcscanf@126.com');

-- ----------------------------
-- Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `login_time` varchar(20) DEFAULT NULL,
  `log_ip` varchar(20) DEFAULT NULL,
  `logout_time` varchar(20) DEFAULT NULL,
  `log` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('1', 'Fcscanf', '2019-01-05 19:43:47', '127.0.0.1', null, '用户登录');
INSERT INTO `log` VALUES ('2', '古赛昆', '2019-01-05 19:53:00', '127.0.0.1', null, '用户登录');
INSERT INTO `log` VALUES ('3', '古赛昆', '2019-01-05 19:53:45', '127.0.0.1', null, '用户登录');
INSERT INTO `log` VALUES ('4', 'Fcscanf', '2019-01-05 20:03:31', '127.0.0.1', null, '用户登录');
INSERT INTO `log` VALUES ('5', 'Fcant', '2019-01-05 20:03:51', '127.0.0.1', null, '用户注册');

-- ----------------------------
-- Table structure for `login`
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES ('1', 'FCC', '123');

-- ----------------------------
-- Table structure for `lytable`
-- ----------------------------
DROP TABLE IF EXISTS `lytable`;
CREATE TABLE `lytable` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `userId` int(4) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `title` varchar(20) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId-lyId` (`userId`),
  CONSTRAINT `userId-lyId` FOREIGN KEY (`userId`) REFERENCES `usertable` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lytable
-- ----------------------------
INSERT INTO `lytable` VALUES ('1', '1', '2018-11-10 00:00:00', '古赛昆', null);
INSERT INTO `lytable` VALUES ('2', '1', '2018-11-10 00:00:00', 'Fcc', null);
INSERT INTO `lytable` VALUES ('3', '1', '2018-11-10 00:00:00', 'Fcc', null);
INSERT INTO `lytable` VALUES ('4', '1', '2018-11-10 00:00:00', '古赛昆', null);

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `room_id` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `room_id` (`room_id`),
  CONSTRAINT `room_id` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', 'FCC', '1');
INSERT INTO `person` VALUES ('2', 'DGG', '2');
INSERT INTO `person` VALUES ('3', '白金卡', '3');
INSERT INTO `person` VALUES ('4', '电子卡', '3');
INSERT INTO `person` VALUES ('5', '建设银行', '4');
INSERT INTO `person` VALUES ('6', '中国银行', '4');

-- ----------------------------
-- Table structure for `room`
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `address` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('1', '北京市');
INSERT INTO `room` VALUES ('2', '南京市');
INSERT INTO `room` VALUES ('3', '重庆市');
INSERT INTO `room` VALUES ('4', '重庆市');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(8) DEFAULT NULL,
  `gender` int(2) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  `professional` varchar(50) DEFAULT NULL,
  `note` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1610701110 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1610701106', 'fcc', '1', '软件工程', '2018-11-15', 'Java');
INSERT INTO `student` VALUES ('1610701107', 're', '1', '软件工程', '2018-11-15', 'Java Web');
INSERT INTO `student` VALUES ('1610701108', 'asm', '1', '1998.9.16', '软件工程', '嵌入式');
INSERT INTO `student` VALUES ('1610701109', '歌莉娅', '1', '1998.9.16', '软件工程', '嵌入式');

-- ----------------------------
-- Table structure for `student_course`
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
  `student_id` int(5) NOT NULL DEFAULT '0',
  `course_id` int(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`student_id`,`course_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES ('1610701108', '4');
INSERT INTO `student_course` VALUES ('1610701108', '5');
INSERT INTO `student_course` VALUES ('1610701108', '6');
INSERT INTO `student_course` VALUES ('1610701109', '7');
INSERT INTO `student_course` VALUES ('1610701109', '8');
INSERT INTO `student_course` VALUES ('1610701109', '9');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('9', 'Fcscanf4', 'uQlUxAlQVyA8drsLy+AdLQ==', '17826260016', '12', '北京市朝阳区');
INSERT INTO `user` VALUES ('10', 'Fcscanf', 'Mlr9Y9LWQHK5DXoRTbWQCA==', '17826260016', '12', '盐城工学院');
INSERT INTO `user` VALUES ('11', 'Fcscanf11', 'Mlr9Y9LWQHK5DXoRTbWQCA==', '17826260016', '12', '南京市玄武区');
INSERT INTO `user` VALUES ('12', '古赛昆', 'Mlr9Y9LWQHK5DXoRTbWQCA==', '17826260016', '12', '北京市朝阳区');
INSERT INTO `user` VALUES ('13', 'Fcant', 'Mlr9Y9LWQHK5DXoRTbWQCA==', '17826260016', '12', '北京市朝阳区');
INSERT INTO `user` VALUES ('14', 'Fcant', 'Mlr9Y9LWQHK5DXoRTbWQCA==', '17826260016', '12', '盐城工学院');

-- ----------------------------
-- Table structure for `usertable`
-- ----------------------------
DROP TABLE IF EXISTS `usertable`;
CREATE TABLE `usertable` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usertable
-- ----------------------------
INSERT INTO `usertable` VALUES ('1', 'fc', '123');
