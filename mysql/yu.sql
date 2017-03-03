/*
Navicat MySQL Data Transfer

Source Server         : library
Source Server Version : 50548
Source Host           : localhost:3306
Source Database       : yu

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2016-05-18 17:31:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `barCode` varchar(255) NOT NULL,
  `bName` varchar(255) DEFAULT NULL,
  `writer` varchar(255) DEFAULT NULL,
  `press` varchar(255) DEFAULT NULL,
  `presstime` varchar(255) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `sort` varchar(255) DEFAULT NULL,
  `pageNum` int(11) DEFAULT NULL,
  `LendNum` int(11) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`barCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('2015001', 'Java疯狂讲义', '李刚', '电子工程出版社', '2008-09-01', '109', '计算机', '877', '1', '已借');
INSERT INTO `books` VALUES ('2015002', '王文兴文集', '王文兴', '北京科学出版社', '2007-12-01', '120', '文学', '430', '1', '未借');
INSERT INTO `books` VALUES ('2015003', 'C语言', '谭浩强', '清华大学出版社', '2016-04-12', '50', '计算机', '500', '3', '已借');
INSERT INTO `books` VALUES ('2015006', '大学物理', '赵近芳', '北京邮电大学', '2014-11-01', '36', '物理', '292', '1', '已借');
INSERT INTO `books` VALUES ('2015007', '大学英语', '康璐璐', '清华电视出版社', '2012-02-01', '65', '英语', '454', '1', '已借');
INSERT INTO `books` VALUES ('2015008', 'Java Web开发与应用', '郭克华', '清华大学出版社', '2012-04-01', '45', '计算机', '435', '0', '未借');
INSERT INTO `books` VALUES ('2343545', 'fcd', 'fff', 'refr', '1012', '423', '文学', '324', '0', '未借');
INSERT INTO `books` VALUES ('5646', 'gv', 'gvf', 'gvf', 'vgd', '54', '文学', '55', '0', '未借');

-- ----------------------------
-- Table structure for booksort
-- ----------------------------
DROP TABLE IF EXISTS `booksort`;
CREATE TABLE `booksort` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bSort` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of booksort
-- ----------------------------
INSERT INTO `booksort` VALUES ('1', '文学');
INSERT INTO `booksort` VALUES ('2', '物理');
INSERT INTO `booksort` VALUES ('3', '英语');
INSERT INTO `booksort` VALUES ('4', '计算机');
INSERT INTO `booksort` VALUES ('5', '机械');

-- ----------------------------
-- Table structure for lendbooks
-- ----------------------------
DROP TABLE IF EXISTS `lendbooks`;
CREATE TABLE `lendbooks` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `rNo` varchar(255) DEFAULT NULL,
  `lendbook` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lendbooks
-- ----------------------------
INSERT INTO `lendbooks` VALUES ('2', '201501001', 'Java疯狂讲义');
INSERT INTO `lendbooks` VALUES ('4', '201501004', '大学物理');
INSERT INTO `lendbooks` VALUES ('6', '201501003', '大学英语');
INSERT INTO `lendbooks` VALUES ('10', '201501004', 'C语言');

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader` (
  `rNo` varchar(255) NOT NULL,
  `rName` varchar(255) DEFAULT NULL,
  `rsex` varchar(255) DEFAULT NULL,
  `rage` int(11) DEFAULT NULL,
  `rClass` varchar(255) DEFAULT NULL,
  `rSort` varchar(255) DEFAULT NULL,
  `rPhoneNum` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `lendNum` int(2) DEFAULT NULL,
  `canLendNum` int(2) DEFAULT NULL,
  PRIMARY KEY (`rNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES ('201501001', '钱静', '女', '19', '计科151', '学生', '14322556546', '123456', '1', '9');
INSERT INTO `reader` VALUES ('201501002', '刘韵', '女', '18', '会计152', '学生', '14543565667', '123456', '0', '10');
INSERT INTO `reader` VALUES ('201501003', '周武', '男', '19', '计科153', '学生', '14546546575', '123456', '1', '9');
INSERT INTO `reader` VALUES ('201501004', '王怡', '女', '19', '会计151', '学生', '17637453733', '654321', '2', '8');
INSERT INTO `reader` VALUES ('201501005', '焦祥宇', '男', '18', '计科151', '管理员', '18273656665', '654321', '0', '10');
INSERT INTO `reader` VALUES ('345435', 'rfg', '女', '34', 'fref', 'rfgr', '43536547', '123456', '0', '10');
INSERT INTO `reader` VALUES ('435234', 'tgg', '女', '4', 'vgf', 'fg', '54654657', '123456', '0', '10');
INSERT INTO `reader` VALUES ('fcdg', 'rfc', '女', '34', 'fd', '435', '3454365', '123456', '0', '10');
