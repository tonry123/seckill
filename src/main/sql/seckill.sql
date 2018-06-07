/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50639
Source Host           : localhost:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-06-07 19:32:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '库存数量',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '秒杀开启时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('1000', '1000元秒杀iphone6', '97', '2018-06-08 21:40:49', '2018-06-09 00:00:00', '2018-06-05 22:18:53');
INSERT INTO `seckill` VALUES ('1001', '500元秒杀iPad4', '196', '2018-06-07 13:49:29', '2018-06-15 00:00:00', '2018-06-05 22:18:53');
INSERT INTO `seckill` VALUES ('1002', '300元秒杀小米6', '300', '2018-06-05 00:00:00', '2018-06-06 00:00:00', '2018-06-05 22:18:53');
INSERT INTO `seckill` VALUES ('1003', '200元秒杀锤子pro', '400', '2018-06-05 00:00:00', '2018-06-06 00:00:00', '2018-06-05 22:18:53');

-- ----------------------------
-- Table structure for success_killed
-- ----------------------------
DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
  `seckill_id` bigint(20) NOT NULL COMMENT '秒杀商品id',
  `user_phone` bigint(20) NOT NULL COMMENT '用户手机号',
  `state` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态表示：-1：无效 0：成功 1：已付款',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`,`user_phone`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

-- ----------------------------
-- Records of success_killed
-- ----------------------------
INSERT INTO `success_killed` VALUES ('1000', '18382325641', '0', '2018-06-06 17:39:11');
INSERT INTO `success_killed` VALUES ('1000', '18382325666', '0', '2018-06-06 17:42:47');
INSERT INTO `success_killed` VALUES ('1000', '18382328910', '0', '2018-06-06 11:10:02');
INSERT INTO `success_killed` VALUES ('1001', '12345678901', '0', '2018-06-07 13:09:54');
INSERT INTO `success_killed` VALUES ('1001', '12345678911', '0', '2018-06-07 13:17:12');
INSERT INTO `success_killed` VALUES ('1001', '18382328910', '0', '2018-06-07 13:49:29');
