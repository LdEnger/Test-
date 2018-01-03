/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : xiach_hiveview

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2017-12-21 10:21:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_category
-- ----------------------------
DROP TABLE IF EXISTS `app_category`;
CREATE TABLE `app_category` (
  `category_id` bigint(64) NOT NULL auto_increment,
  `seq` int(11) NOT NULL default '0',
  `category_name` varchar(64) default NULL COMMENT '分类名称',
  `state` tinyint(2) NOT NULL default '1' COMMENT '1:有效，0:无效',
  `create_time` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `update_time` timestamp NULL default NULL,
  PRIMARY KEY  (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用分类';

-- ----------------------------
-- Records of app_category
-- ----------------------------
INSERT INTO `app_category` VALUES ('1', '1', '游戏', '1', '2014-04-28 19:34:37', null);
INSERT INTO `app_category` VALUES ('2', '2', '应用', '1', '2014-04-28 19:34:37', null);
INSERT INTO `app_category` VALUES ('3', '3', '教育', '1', '2015-04-01 11:15:47', null);

-- ----------------------------
-- Table structure for area_group
-- ----------------------------
DROP TABLE IF EXISTS `area_group`;
CREATE TABLE `area_group` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `area_code` varchar(64) default NULL COMMENT '区域代码',
  `area_name` varchar(128) default NULL COMMENT '区域名称',
  PRIMARY KEY  (`id`),
  KEY `index_area_code` (`area_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area_group
-- ----------------------------
INSERT INTO `area_group` VALUES ('28', '0d', '3333');
INSERT INTO `area_group` VALUES ('29', '0b', 'www');
INSERT INTO `area_group` VALUES ('30', '0p', '同一人');
INSERT INTO `area_group` VALUES ('33', '0v', 'fgh');
INSERT INTO `area_group` VALUES ('35', '0h', '3432');
INSERT INTO `area_group` VALUES ('36', '1a', '1a');
INSERT INTO `area_group` VALUES ('38', '1c', '1c');
INSERT INTO `area_group` VALUES ('40', '5y', 'w');
INSERT INTO `area_group` VALUES ('41', '5c', 'asd');
INSERT INTO `area_group` VALUES ('42', '3e', 'aw');
INSERT INTO `area_group` VALUES ('45', '0e', 'ee');
INSERT INTO `area_group` VALUES ('47', 'xj', 'xj');
INSERT INTO `area_group` VALUES ('48', 'xj2', 'xj2');
INSERT INTO `area_group` VALUES ('49', 'asd', 'adtry');

-- ----------------------------
-- Table structure for mac_area
-- ----------------------------
DROP TABLE IF EXISTS `mac_area`;
CREATE TABLE `mac_area` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `mac` varchar(128) default NULL COMMENT 'mac',
  `sn` varchar(128) default NULL COMMENT 'sn',
  `area_code` varchar(64) default NULL COMMENT '所属地区代码',
  `area_name` varchar(128) default NULL COMMENT '所属地区名称',
  PRIMARY KEY  (`id`),
  KEY `mac_area_index` (`mac`,`sn`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mac_area
-- ----------------------------
INSERT INTO `mac_area` VALUES ('137985', '333.0', '333.0', '0h', '3432');
INSERT INTO `mac_area` VALUES ('137988', 'SADAA', 'SS', '0d', '3333');
INSERT INTO `mac_area` VALUES ('138271', 'SAD', 'R1', '0b', 'www');
INSERT INTO `mac_area` VALUES ('138206', '9090.0', '9090.0', '0b', '3333');
INSERT INTO `mac_area` VALUES ('138246', 'AA', 'Q:A:OP', '0d', '3333');
INSERT INTO `mac_area` VALUES ('137973', 'SAD', 'SS', '0h', '3432');
INSERT INTO `mac_area` VALUES ('137969', 'SD', 'SDS', '0b', 'www');
INSERT INTO `mac_area` VALUES ('137968', '444.0', '444.0', '0b', 'www');
INSERT INTO `mac_area` VALUES ('137967', '333.0', '333', '0b', 'www');
INSERT INTO `mac_area` VALUES ('137966', '111.0', '11.0', '0b', 'www');
INSERT INTO `mac_area` VALUES ('137965', '11', '\"11\"', '0b', 'www');
INSERT INTO `mac_area` VALUES ('137964', '5555', '66', '0p', '同一人');
INSERT INTO `mac_area` VALUES ('137952', 'CDCDCD', '122222', '0b', 'www');
INSERT INTO `mac_area` VALUES ('137975', 'MAC', 'SN', '0h', '3432');
INSERT INTO `mac_area` VALUES ('137976', '123', 'DMA30112150600069', '0p', '同一人');

-- ----------------------------
-- Table structure for new_templet_apk_channel
-- ----------------------------
DROP TABLE IF EXISTS `new_templet_apk_channel`;
CREATE TABLE `new_templet_apk_channel` (
  `id` int(11) NOT NULL COMMENT '主键',
  `c_type` tinyint(4) NOT NULL default '0' COMMENT '频道类型（0单个专题，频道ID为专题ID；1单个节目集，频道ID为节目集ID；3单个热词，频道ID为热词ID，同时参考hotword_cid和hotwod_ctype字段；8单个标签，频道ID为标签ID，同时参考hotword_cid和hotword_ctype字段；9自定义频道，请参考show_category、is_multichip、is_has_detail、is_horizontal、is_specific字段；10基础频道，指从媒资拉取的频道，频道类型可参考自定义频道中参考的字段）',
  `apk_id` int(11) default NULL COMMENT '模板apkId',
  `apk_bag_name` varchar(128) NOT NULL COMMENT '模板apk包名',
  `templet_id` int(11) default NULL COMMENT '模板Id',
  `cname` varchar(256) default NULL COMMENT '频道名称',
  `show_category` tinyint(4) default NULL COMMENT '展示类型 1常规类型2特殊类型',
  `is_multichip` tinyint(4) default NULL COMMENT '展示类型为常规类型：1单片2多片',
  `is_has_detail` tinyint(4) default NULL COMMENT '展示类型为常规类型：0无详情1有详情',
  `is_horizontal` tinyint(4) default NULL COMMENT '展示类型为常规类型：1竖图0横图',
  `is_specific` tinyint(4) default NULL COMMENT '展示类型为特殊类型：1专题类，请求专题列表接口；2商品类，请求商品列表接口；3综艺类，请求综艺列表；4直播类，下期需求；5轮播类，下期需求；',
  `seq` int(4) default NULL COMMENT '顺序',
  `is_effective` tinyint(4) default NULL COMMENT ' 0禁用1隐藏2启用',
  `combox_id` int(11) NOT NULL auto_increment COMMENT '标识Id',
  `create_time` timestamp NULL default NULL COMMENT '创建时间',
  `update_time` timestamp NULL default NULL COMMENT '修改时间',
  `c_type_name` varchar(128) default NULL COMMENT '频道类型名称',
  `parent_cid` int(11) default NULL COMMENT '当c_type=1、3、8时，该字段表示所属的频道ID',
  `parent_ctype` int(11) default NULL COMMENT '当c_type=1、3、8时，该字段表示所属的频道类型',
  `parent_apkName` varchar(200) default NULL COMMENT '当c_type=1、3、8时，该字段表示所属的频道从属的apk包名',
  PRIMARY KEY  (`id`,`c_type`,`apk_bag_name`),
  KEY `combox_id` (`combox_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of new_templet_apk_channel
-- ----------------------------
INSERT INTO `new_templet_apk_channel` VALUES ('1', '10', '16', 'com.hiveview.cloudscreen.py', '1', '电影', '1', '2', '1', '0', null, '1', '1', '75', '2017-07-04 17:32:17', '2017-09-11 10:34:28', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('1', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '电影', '1', '2', '1', '0', null, '1', '2', '4', '2016-11-09 17:22:28', '2017-09-15 16:12:57', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('1', '10', '15', 'com.hiveview.livevideo', '1', '电影', '1', '2', '1', '0', null, '1', '1', '66', '2017-05-25 14:28:41', '2017-09-11 11:10:41', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('1', '10', '17', 'test', '1', '电影', null, null, null, '1', null, '1', '1', '79', '2017-07-17 17:25:01', '2017-07-17 17:25:01', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('1', '10', '18', 'test3', '1', '电影', null, null, null, '1', null, '1', '1', '80', '2017-07-17 17:25:15', '2017-07-17 17:25:15', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('2', '10', '16', 'com.hiveview.cloudscreen.py', '1', '电视剧', null, null, null, '1', null, '1', '1', '82', '2017-08-30 15:39:07', '2017-08-30 15:39:07', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('2', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '电视剧', '1', '2', '1', '0', null, '1', '2', '1', '2016-11-09 17:22:26', '2017-12-05 15:11:42', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('2', '10', '15', 'com.hiveview.livevideo', '1', '电视剧', null, null, null, '1', null, '1', '1', '67', '2017-05-25 14:28:41', '2017-05-25 14:28:41', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('3', '10', '16', 'com.hiveview.cloudscreen.py', '1', '纪录片', null, null, null, '1', null, '1', '1', '81', '2017-08-30 15:39:07', '2017-08-30 15:39:07', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('3', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '纪录片', null, null, null, null, null, '1', '0', '8', '2016-11-09 17:22:30', '2017-03-10 14:50:46', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('3', '10', '15', 'com.hiveview.livevideo', '1', '纪录片', null, null, null, '1', null, '1', '1', '68', '2017-05-25 14:28:41', '2017-05-25 14:28:41', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('4', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '动漫', null, null, null, null, null, '1', '2', '2', '2016-11-09 17:22:27', '2016-11-09 17:22:27', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('4', '10', '15', 'com.hiveview.livevideo', '1', '动漫', null, null, null, '1', null, '1', '1', '69', '2017-05-25 14:28:41', '2017-05-25 14:28:41', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('5', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '音乐', '1', '2', '1', '0', null, '1', '2', '7', '2016-11-09 17:22:30', '2017-09-11 11:10:06', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('5', '10', '15', 'com.hiveview.livevideo', '1', '音乐', null, null, null, '1', null, '1', '1', '70', '2017-05-25 14:28:41', '2017-05-25 14:28:41', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('6', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '综艺', null, null, null, null, null, '1', '2', '5', '2016-11-09 17:22:29', '2016-11-09 17:22:29', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('7', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '娱乐', null, null, null, null, null, '1', '2', '6', '2016-11-09 17:22:30', '2016-11-09 17:22:30', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('10', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '片花', null, null, null, null, null, '1', '2', '12', '2016-11-09 17:22:33', '2016-11-09 17:22:33', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('12', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '教育', null, null, null, null, null, '1', '2', '10', '2016-11-09 17:22:31', '2016-11-09 17:22:31', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('13', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '时尚', null, null, null, null, null, '1', '2', '14', '2016-11-09 17:22:34', '2016-11-09 17:22:34', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('15', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '少儿', null, null, null, null, null, '1', '2', '3', '2016-11-09 17:22:28', '2016-11-09 17:22:28', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('17', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '体育', null, null, null, null, null, '1', '2', '11', '2016-11-09 17:22:32', '2016-11-09 17:22:32', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('20', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '广告', null, null, null, null, null, '1', '2', '16', '2016-11-09 17:22:35', '2016-11-09 17:22:35', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('21', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '生活', null, null, null, null, null, '1', '2', '15', '2016-11-09 17:22:34', '2016-11-09 17:22:34', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('22', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '搞笑', null, null, null, null, null, '2', '2', '9', '2016-11-09 17:22:31', '2016-12-12 16:03:42', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('26', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '汽车', null, null, null, null, null, '1', '2', '13', '2016-11-09 17:22:33', '2016-11-09 17:22:33', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('34', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '', null, null, null, null, null, '1', '0', '57', '2017-03-15 18:43:15', '2017-09-12 09:54:47', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('972', '8', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '早教益智', null, null, null, null, null, '1', '2', '49', '2016-12-27 14:50:55', '2016-12-27 14:50:55', '标签', '21', '10', '');
INSERT INTO `new_templet_apk_channel` VALUES ('1020', '8', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '2011-2016', null, null, null, null, null, '1', '2', '48', '2016-12-27 14:48:36', '2016-12-27 14:48:36', '标签', '2', '10', '');
INSERT INTO `new_templet_apk_channel` VALUES ('2007', '10', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '购物', null, null, null, '1', null, '1', '1', '56', '2017-03-15 18:20:26', '2017-03-15 18:20:26', '基础频道', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('6000', '9', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '自定义频道', '1', '1', '1', '0', null, '1', '1', '17', '2016-11-09 20:05:23', '2017-03-10 14:52:38', '自定义', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('6001', '9', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '专题类', '2', null, null, null, '1', '100', '2', '27', '2016-11-22 21:28:43', '2016-11-22 21:28:43', '自定义', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('6002', '9', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '商品类', '2', null, null, null, '2', '101', '2', '28', '2016-11-22 21:51:04', '2016-11-24 10:25:56', '自定义', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('6004', '9', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '商品包', '2', null, null, null, '2', '2', '2', '59', '2017-03-20 14:20:35', '2017-03-20 14:20:35', '自定义', null, null, '');
INSERT INTO `new_templet_apk_channel` VALUES ('6005', '9', '15', 'com.hiveview.livevideo', '1', '最新', '2', null, null, null, '6', '-1', '1', '71', '2017-05-25 14:28:41', '2017-05-25 14:28:41', '自定义', null, null, null);
INSERT INTO `new_templet_apk_channel` VALUES ('6006', '9', '15', 'com.hiveview.livevideo', '1', '直播频道', '1', '2', '1', '0', null, '11', '1', '72', '2017-06-07 17:28:58', '2017-06-07 17:28:58', '自定义', null, null, '');
INSERT INTO `new_templet_apk_channel` VALUES ('6007', '9', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '教育商品包', '2', null, null, null, '7', '1', '2', '73', '2017-06-15 10:38:29', '2017-06-30 17:01:20', '自定义', null, null, '');
INSERT INTO `new_templet_apk_channel` VALUES ('6008', '9', '1', 'com.hiveview.cloudscreen.vipvideo', '1', '教育商品包频道2', '2', null, null, null, '7', '2', '2', '74', '2017-07-04 09:50:44', '2017-07-04 09:50:44', '自定义', null, null, '');
INSERT INTO `new_templet_apk_channel` VALUES ('100000', '3', '16', 'com.hiveview.cloudscreen.py', '1', '我的影票', null, null, null, null, null, '3', '2', '78', '2017-07-10 14:22:16', '2017-07-10 14:26:46', '热词', '100000', '9', 'com.hiveview.premiere');
INSERT INTO `new_templet_apk_channel` VALUES ('100000', '9', '2', 'com.hiveview.premiere', '1', '极清频道', null, null, null, null, null, '1', '1', '18', '2016-11-11 09:29:42', '2016-11-11 09:29:45', '自定义', null, null, null);

-- ----------------------------
-- Table structure for new_templet_channel_words
-- ----------------------------
DROP TABLE IF EXISTS `new_templet_channel_words`;
CREATE TABLE `new_templet_channel_words` (
  `id` int(11) NOT NULL COMMENT 'id',
  `type` tinyint(4) NOT NULL default '0' COMMENT '热词类型（3自定义8标签7商品包）',
  `apk_id` int(11) default NULL COMMENT '模板apkId',
  `templet_id` int(11) default NULL COMMENT '模板Id',
  `apk_channel_id` int(11) default NULL COMMENT '模板apk频道id',
  `apk_channel_type` int(11) default NULL COMMENT '频道类型',
  `name` varchar(256) default NULL COMMENT '热词名称',
  `seq` int(11) default NULL COMMENT '顺序',
  `is_effective` tinyint(4) default NULL COMMENT ' 0禁用1隐藏2启用',
  `combox_id` int(11) NOT NULL auto_increment COMMENT '标识Id',
  `create_time` timestamp NULL default NULL COMMENT '创建时间',
  `update_time` timestamp NULL default NULL COMMENT '修改时间',
  `apk_bag_name` varchar(255) default NULL COMMENT 'APK包名',
  PRIMARY KEY  (`id`,`type`),
  KEY `combox_id` (`combox_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of new_templet_channel_words
-- ----------------------------
INSERT INTO `new_templet_channel_words` VALUES ('1', '3', '1', '1', '1', '10', '全部节目', '1', '0', '13', '2016-11-11 09:59:18', '2017-09-11 14:51:24', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('2', '0', null, null, null, null, null, null, null, '55', null, null, null);
INSERT INTO `new_templet_channel_words` VALUES ('2', '8', '1', '1', '6000', '9', '地区', '66', '1', '6', '2016-11-06 21:53:10', '2016-11-06 21:53:10', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('4', '8', '1', '1', '6000', '9', '标签4', '2', '2', '4', '2016-11-03 20:28:03', '2016-11-03 20:19:00', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('15', '8', '1', '1', '6000', '9', '专区', '6', '2', '8', '2016-11-09 15:27:14', '2016-11-09 15:27:14', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('24', '0', null, null, '1', '10', '即将上映', null, '2', '72', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('46', '0', null, null, '1', '10', '即将上映', null, '2', '69', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('65', '0', null, null, '1', '10', '即将上映', null, '2', '68', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('75', '0', null, null, '1', '10', '即将上映', null, '2', '65', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('234', '0', null, null, '1', '10', '即将上映', null, '2', '66', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('333', '0', null, null, null, null, null, null, null, '56', null, null, null);
INSERT INTO `new_templet_channel_words` VALUES ('426', '0', null, null, '1', '10', '即将上映', null, '2', '71', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('548', '0', null, null, '1', '10', '即将上映', null, '2', '73', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('754', '0', null, null, '1', '10', '即将上映', null, '2', '79', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('871', '8', '1', '1', '1', '10', '全部', '1', '2', '21', '2016-12-15 18:13:56', '2016-12-15 18:13:56', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('873', '8', '1', '1', '6000', '9', '韩国', '3', '1', '9', '2016-11-09 15:43:46', '2016-11-09 15:43:46', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('978', '0', null, null, '1', '10', '即将上映', null, '2', '63', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('990', '3', '3', '1', '2', '10', '全部节目', '5', '1', '22', '2016-12-15 18:20:12', '2017-04-05 10:28:46', 'com.hiveview.livevideo');
INSERT INTO `new_templet_channel_words` VALUES ('6003', '3', '1', '1', '2', '10', '全部节目', '4', '2', '18', '2016-11-11 10:29:20', '2017-09-11 11:30:09', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('6005', '3', '2', '1', '100000', '9', '极清热词', '1', '2', '20', '2016-12-15 11:21:28', '2016-12-15 11:21:28', 'com.hiveview.premiere');
INSERT INTO `new_templet_channel_words` VALUES ('6006', '3', '1', '1', '2', '10', '电视剧标签', '1', '2', '23', '2017-03-09 10:12:58', '2017-03-09 10:12:58', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('6007', '3', '1', '1', '1', '10', '第二次修改', '3', '2', '24', '2017-03-21 18:54:27', '2017-09-11 11:29:32', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('6008', '3', '15', '1', '1', '10', '直播电影', '1', '2', '26', '2017-06-06 10:28:38', '2017-06-06 10:28:38', 'com.hiveview.livevideo');
INSERT INTO `new_templet_channel_words` VALUES ('6009', '3', '15', '1', '6006', '9', '直播热词', '1', '2', '27', '2017-06-07 17:29:14', '2017-06-07 17:29:14', 'com.hiveview.livevideo');
INSERT INTO `new_templet_channel_words` VALUES ('6010', '3', '1', '1', '1', '10', '奇异果会员', '2', '2', '28', '2017-06-08 10:17:54', '2017-06-08 10:17:54', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('6011', '3', '16', '1', '2', '10', '电视剧', '1', '2', '29', '2017-08-30 15:39:54', '2017-08-30 15:39:54', 'com.hiveview.cloudscreen.py');
INSERT INTO `new_templet_channel_words` VALUES ('6012', '3', '1', '1', '1', '10', 'test', '12', '0', '37', '2017-09-21 15:12:35', '2017-09-21 15:13:40', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('6234', '0', null, null, '1', '10', '即将上映', null, '2', '67', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('6546', '0', null, null, '1', '10', '即将上映', null, '2', '61', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('8564', '0', null, null, '1', '10', '即将上映', null, '2', '64', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('32142', '0', null, null, '1', '10', '即将上映', null, '2', '58', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('32152', '0', null, null, '1', '10', '即将上映', null, '2', '77', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('33333', '0', null, null, null, null, null, null, '2', '57', '2017-09-21 15:12:35', '2017-09-21 15:12:35', null);
INSERT INTO `new_templet_channel_words` VALUES ('100000', '3', '2', '1', '100000', '9', '我的影票', '2', '1', '11', '2016-11-11 09:27:27', '2016-12-14 17:10:21', 'com.hiveview.premiere');
INSERT INTO `new_templet_channel_words` VALUES ('100001', '3', '2', '1', '100000', '9', '即将上映', '2', '2', '12', '2016-11-11 09:27:33', '2016-12-14 17:10:13', 'com.hiveview.premiere');
INSERT INTO `new_templet_channel_words` VALUES ('3215231', '0', null, null, '1', '10', '即将上映', null, '2', '59', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('5434123', '0', null, null, '1', '10', '即将上映', null, '2', '62', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('5435342', '0', null, null, '1', '10', '即将上映', null, '2', '60', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('11111111', '0', null, null, '1', '10', '即将上映', null, '2', '51', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('312321312', '0', null, null, '1', '10', '即将上映', null, '2', '76', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');
INSERT INTO `new_templet_channel_words` VALUES ('2147483647', '0', null, null, '1', '10', '即将上映', null, '2', '52', '2017-09-21 15:12:35', '2017-09-21 15:12:35', 'com.hiveview.cloudscreen.vipvideo');

-- ----------------------------
-- Table structure for portal_area_administration
-- ----------------------------
DROP TABLE IF EXISTS `portal_area_administration`;
CREATE TABLE `portal_area_administration` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键id',
  `area_name` varchar(255) NOT NULL COMMENT '专区名称',
  `area_title` varchar(255) NOT NULL COMMENT '专区标题名称',
  `area_template` int(11) NOT NULL COMMENT '专区模版',
  `effective` int(11) default '0' COMMENT '状态(0下线,1上线)',
  `area_introduce` varchar(255) default NULL COMMENT '专区介绍',
  `area_img` varchar(255) default NULL COMMENT '专区外显图',
  `templete_img` varchar(255) NOT NULL default 'null' COMMENT '模版预览',
  `background_img` varchar(255) default 'null' COMMENT '专区背景图',
  `album_pic_type` int(11) NOT NULL default '0' COMMENT '横竖图,0横图,1竖图',
  `create_time` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专区';

-- ----------------------------
-- Records of portal_area_administration
-- ----------------------------

-- ----------------------------
-- Table structure for portal_area_content
-- ----------------------------
DROP TABLE IF EXISTS `portal_area_content`;
CREATE TABLE `portal_area_content` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键id',
  `content_name` varchar(255) default NULL COMMENT '标题',
  `seq` int(11) default NULL COMMENT '顺序',
  `area_id` int(11) default NULL COMMENT '专区管理ID',
  `area_content` text COMMENT '内容介绍',
  `recommend_img` varchar(255) default NULL COMMENT '推荐位图片',
  `area_type` int(11) default NULL COMMENT '跳转状态(0可跳转,1不可跳转)',
  `recommend_type` int(11) default NULL COMMENT '推荐位类型(1专辑详情,2直播)',
  `package_name` varchar(255) default NULL COMMENT '包名',
  `area_template` int(4) default NULL COMMENT '模版',
  `channel_type` int(11) default NULL COMMENT '频道类型',
  `channel` int(11) default NULL COMMENT '频道',
  `pay_state` int(11) default NULL COMMENT '付费(0全部,1免费,2大麦付费,3爱奇异付费)',
  `vip_state` int(11) default NULL COMMENT 'VIP(0全部,1非VIP,2大麦VIP,3爱奇异VIP)',
  `seq_is_top` int(11) default '0' COMMENT '置顶字段',
  `content_id` int(4) default NULL COMMENT '内容id',
  `video_id` int(4) default NULL COMMENT '关联剧集id',
  `create_time` timestamp NULL default NULL COMMENT '创建时间',
  `update_time` timestamp NULL default NULL on update CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容管理';

-- ----------------------------
-- Records of portal_area_content
-- ----------------------------

-- ----------------------------
-- Table structure for portal_area_group
-- ----------------------------
DROP TABLE IF EXISTS `portal_area_group`;
CREATE TABLE `portal_area_group` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `area_code` varchar(64) default NULL COMMENT '区域代码',
  `area_name` varchar(128) default NULL COMMENT '区域名称',
  PRIMARY KEY  (`id`),
  KEY `index_area_code` (`area_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='MACSN名单分组';

-- ----------------------------
-- Records of portal_area_group
-- ----------------------------

-- ----------------------------
-- Table structure for portal_bean_curd
-- ----------------------------
DROP TABLE IF EXISTS `portal_bean_curd`;
CREATE TABLE `portal_bean_curd` (
  `curd_id` int(11) NOT NULL auto_increment COMMENT '豆腐块ID',
  `curd_name` varchar(255) default NULL COMMENT '豆腐块名称',
  `create_time` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_effective` int(11) default '0' COMMENT '豆腐块状态',
  PRIMARY KEY  (`curd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='豆腐块列表';

-- ----------------------------
-- Records of portal_bean_curd
-- ----------------------------

-- ----------------------------
-- Table structure for portal_bean_curd_edit
-- ----------------------------
DROP TABLE IF EXISTS `portal_bean_curd_edit`;
CREATE TABLE `portal_bean_curd_edit` (
  `entrance_id` int(11) NOT NULL auto_increment COMMENT 'id',
  `entrance_name` varchar(255) default NULL COMMENT '入口名称',
  `is_effective` int(11) default NULL COMMENT '状态(1上线，2下线)',
  `entrance_type` int(11) default NULL COMMENT '入口应用类型(0系统应用，1定制应用)',
  `seq` int(11) default NULL COMMENT '顺序',
  `entrance_templete_id` int(11) default NULL COMMENT '入口模板id',
  `customize_app_id` int(11) default NULL COMMENT '定制apkId',
  `customize_app_name` varchar(255) default NULL COMMENT '订制应用名称',
  `entrance_app_id` int(11) default NULL COMMENT '系统APkid',
  `entrance_app_name` varchar(255) default NULL COMMENT '应用名称',
  `entrance_app_str` varchar(255) default NULL COMMENT '关联字符串',
  `entrance_app_version` varchar(255) default NULL COMMENT '最新版本',
  `entrance_app_install` varchar(255) default NULL COMMENT '安装方式',
  `entrance_app_url` varchar(255) default NULL COMMENT '定制apk url',
  PRIMARY KEY  (`entrance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='豆腐块列表编辑';

-- ----------------------------
-- Records of portal_bean_curd_edit
-- ----------------------------

-- ----------------------------
-- Table structure for portal_bean_curd_img
-- ----------------------------
DROP TABLE IF EXISTS `portal_bean_curd_img`;
CREATE TABLE `portal_bean_curd_img` (
  `img_id` int(11) NOT NULL auto_increment COMMENT 'id',
  `entrance_id` int(11) default NULL,
  `entrance_title` varchar(255) default NULL COMMENT '标题',
  `entrance_subtitle` varchar(255) default NULL COMMENT '副标题',
  `seq` int(11) default NULL COMMENT '顺序',
  `is_effective` int(11) default NULL COMMENT '状态',
  `entrance_img` varchar(255) default NULL COMMENT '图片',
  PRIMARY KEY  (`img_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='豆腐块内容图';

-- ----------------------------
-- Records of portal_bean_curd_img
-- ----------------------------

-- ----------------------------
-- Table structure for portal_bean_curd_mapping
-- ----------------------------
DROP TABLE IF EXISTS `portal_bean_curd_mapping`;
CREATE TABLE `portal_bean_curd_mapping` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `entrance_id` int(11) default NULL COMMENT '应用id',
  `entrance_name` varchar(255) default NULL COMMENT '应用名称',
  `is_effective` int(11) default NULL COMMENT '状态',
  `entrance_type` int(11) default NULL COMMENT '入口应用类型',
  `seq` int(11) default NULL COMMENT '顺序',
  `bean_curd_id` int(11) default NULL COMMENT '豆腐块id',
  `bean_curd_name` varchar(255) default NULL COMMENT '入口模板名称',
  `create_time` timestamp NULL default NULL COMMENT '创建时间',
  `update_time` timestamp NULL default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='豆腐块关联关系表';

-- ----------------------------
-- Records of portal_bean_curd_mapping
-- ----------------------------

-- ----------------------------
-- Table structure for portal_customize_apk
-- ----------------------------
DROP TABLE IF EXISTS `portal_customize_apk`;
CREATE TABLE `portal_customize_apk` (
  `id` int(11) NOT NULL auto_increment,
  `apk_name` varchar(128) default NULL COMMENT '包名',
  `last_version` varchar(64) default NULL COMMENT '最新版本号',
  `is_effective` tinyint(4) default NULL COMMENT '是否有效 0 无效 1 有效',
  `equipment_nos` varchar(500) default NULL COMMENT '设备型号',
  `install_style` tinyint(4) default NULL COMMENT '安装方式  0 询问安装 1 静默安装',
  `create_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `package_name` varchar(128) default NULL COMMENT '包名',
  `app_type` tinyint(4) default NULL COMMENT '应用类型，0系统应用，1定制应用',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定制apk';

-- ----------------------------
-- Records of portal_customize_apk
-- ----------------------------

-- ----------------------------
-- Table structure for portal_customize_apk_version
-- ----------------------------
DROP TABLE IF EXISTS `portal_customize_apk_version`;
CREATE TABLE `portal_customize_apk_version` (
  `id` int(11) NOT NULL auto_increment,
  `apk_id` int(11) default NULL COMMENT 'apk的id',
  `version_no` varchar(64) default NULL COMMENT '版本号',
  `is_latest_version` tinyint(2) default '0' COMMENT '是否是最新版本 0 不是 1是',
  `package_name` varchar(128) default NULL COMMENT '(废弃)包名',
  `version_describe` varchar(512) default NULL COMMENT '版本描述',
  `version_url` varchar(512) default NULL COMMENT '地址',
  `create_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定制apk版本';

-- ----------------------------
-- Records of portal_customize_apk_version
-- ----------------------------

-- ----------------------------
-- Table structure for portal_custom_recom_backups_content
-- ----------------------------
DROP TABLE IF EXISTS `portal_custom_recom_backups_content`;
CREATE TABLE `portal_custom_recom_backups_content` (
  `id` int(11) NOT NULL auto_increment,
  `content_id` int(11) NOT NULL COMMENT '推荐位内容ID',
  `content_type` varchar(255) default NULL COMMENT '内容类型',
  `content_name` varchar(50) default NULL COMMENT '内容名称',
  `content_subtitle` varchar(50) default NULL COMMENT '内容名称',
  `content_img` varchar(255) default NULL COMMENT '内容图片地址',
  `content_outcrop_img` varchar(255) default NULL COMMENT '内容露头图片地址',
  `content_big_pic` varchar(255) default NULL COMMENT '大图地址',
  `custom_content_img_sel` int(11) default '1' COMMENT '积木推荐位内容图片控制(1原图2露头图)',
  `category_id` tinyint(1) default NULL COMMENT '应用类型',
  `seq` int(4) default NULL COMMENT '顺序',
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `is_effective` tinyint(1) default '0' COMMENT '备份数据状态0无效，1有效',
  `templete_id` int(11) default NULL COMMENT '模板ID',
  `chn_id` int(1) default NULL COMMENT '频道',
  `chn_type` tinyint(1) default NULL COMMENT '频道类型',
  `hot_id` int(1) default NULL COMMENT '热词ID',
  `hot_type` tinyint(1) default NULL COMMENT '热词类型',
  `aqy_is_vip` tinyint(1) default NULL COMMENT '是否爱奇艺VIP',
  `apk_bag_name` varchar(255) default NULL COMMENT 'APK包名',
  `layout_type` tinyint(1) default '0' COMMENT '矩阵类型',
  `layout_id` int(11) default NULL COMMENT '矩阵ID',
  `templete_name` varchar(255) default NULL COMMENT 'Group名称',
  `video_id` int(11) default NULL COMMENT '剧集id',
  `is_play` tinyint(4) default NULL COMMENT '1 视频  0图片',
  `action` varchar(255) default NULL COMMENT 'action',
  `apk` varchar(255) default NULL COMMENT '可定制apk',
  `apk_version_code` varchar(255) default NULL COMMENT 'apk版本号',
  `apk_down_url` varchar(255) default NULL COMMENT 'apk下载地址',
  `spec_sn` varchar(255) default NULL COMMENT '优购专题的唯一标识',
  `video_url` varchar(255) default NULL COMMENT '视频地址',
  `install_style` tinyint(4) default NULL COMMENT '安装方式  0 询问安装 1 静默安装',
  `app_type` tinyint(4) default NULL COMMENT '应用类型，0系统应用，1定制应用',
  `is_usered_count` int(11) default '0' COMMENT '被使用次数',
  `sale_style` tinyint(4) default NULL COMMENT '销售样式1单个内容2多个内容',
  `price_style` tinyint(4) default NULL COMMENT '价格样式1按次售卖2按段售卖',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自定义推荐位备份内容';

-- ----------------------------
-- Records of portal_custom_recom_backups_content
-- ----------------------------

-- ----------------------------
-- Table structure for portal_custom_recom_content
-- ----------------------------
DROP TABLE IF EXISTS `portal_custom_recom_content`;
CREATE TABLE `portal_custom_recom_content` (
  `id` int(11) NOT NULL auto_increment,
  `content_id` int(11) NOT NULL COMMENT '推荐位内容ID',
  `content_type` varchar(255) default NULL COMMENT '内容类型',
  `content_name` varchar(50) default NULL COMMENT '内容名称',
  `content_subtitle` varchar(50) default NULL COMMENT '内容名称',
  `content_img` varchar(255) default NULL COMMENT '内容图片地址',
  `content_outcrop_img` varchar(255) default NULL COMMENT '内容露头图片地址',
  `category_id` tinyint(1) default NULL COMMENT '应用类型',
  `seq` int(4) default NULL COMMENT '顺序',
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `is_effective` tinyint(1) default NULL COMMENT '0无效，1有效',
  `templete_id` int(11) default NULL COMMENT '模板ID',
  `layout_id` int(11) default NULL COMMENT '矩阵ID',
  `chn_id` int(11) default NULL COMMENT '频道',
  `chn_type` int(11) default NULL COMMENT '频道类型',
  `hot_id` int(11) default NULL COMMENT '热词ID',
  `hot_type` int(11) default NULL COMMENT '热词类型',
  `video_id` int(11) default NULL COMMENT '剧集Id',
  `aqy_is_vip` int(11) default NULL COMMENT '是否爱奇艺VIP',
  `apk_bag_name` varchar(255) default NULL COMMENT 'APK包名',
  `video_templet_id` int(11) default NULL COMMENT '大麦模板ID',
  `recom_templet_id` int(11) default NULL COMMENT '积木推荐位模板ID',
  `content_big_pic` varchar(255) default NULL COMMENT '大图地址',
  `custom_content_img_sel` int(11) default '1' COMMENT '积木推荐位内容图片控制(1原图2露头图)',
  `is_play` tinyint(11) default '0' COMMENT '1 视频  0 图片',
  `action` varchar(255) default NULL COMMENT '跳转action  供客户端使用',
  `apk` varchar(255) default NULL COMMENT '可定制的apk',
  `apk_version_code` varchar(255) default NULL COMMENT '运营配置的当前下载apk的版本号',
  `apk_down_url` varchar(255) default NULL COMMENT '下载地址',
  `spec_sn` varchar(255) default NULL COMMENT '优购专题的唯一标识',
  `video_url` varchar(255) default NULL COMMENT '视频地址',
  `app_type` tinyint(4) default NULL COMMENT '应用类型，0系统应用，1定制应用',
  `install_style` tinyint(4) default NULL COMMENT '安装方式 0 询问安装 1 静默安装',
  `sale_style` tinyint(4) default NULL COMMENT '销售样式1单个内容2多个内容',
  `price_style` tinyint(4) default NULL COMMENT '价格样式1按次售卖2按段售卖',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自定义推荐位内容';

-- ----------------------------
-- Records of portal_custom_recom_content
-- ----------------------------

-- ----------------------------
-- Table structure for portal_custom_recom_layout
-- ----------------------------
DROP TABLE IF EXISTS `portal_custom_recom_layout`;
CREATE TABLE `portal_custom_recom_layout` (
  `layout_id` int(11) NOT NULL auto_increment,
  `layout_x` int(11) default NULL COMMENT '矩阵横坐标',
  `layout_y` int(11) default NULL COMMENT '矩阵纵坐标',
  `layout_w` int(11) default NULL COMMENT '矩阵宽',
  `layout_h` int(11) default NULL COMMENT '矩阵高',
  `templete_id` int(11) default NULL COMMENT '模板ID',
  `position_seq` int(11) default '0' COMMENT '推荐位位置',
  `layout_team` int(11) default NULL COMMENT '矩阵分组ID',
  `layout_team_type` int(11) default NULL COMMENT '矩阵分组类型（1：大图，2：上2下1，3：上1下2）',
  `col` int(11) default NULL COMMENT '列',
  `row` int(11) default NULL COMMENT '行',
  PRIMARY KEY  (`layout_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自定义推荐位模板布局';

-- ----------------------------
-- Records of portal_custom_recom_layout
-- ----------------------------

-- ----------------------------
-- Table structure for portal_custom_recom_templete
-- ----------------------------
DROP TABLE IF EXISTS `portal_custom_recom_templete`;
CREATE TABLE `portal_custom_recom_templete` (
  `templete_id` int(11) NOT NULL auto_increment,
  `templete_name` varchar(255) default NULL COMMENT '模板名称',
  `is_effective` tinyint(1) default '1' COMMENT '0下线，1上线',
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `update_user` varchar(255) default NULL,
  `content_name` varchar(255) default NULL COMMENT '推荐位模板名称',
  `templete_leve` int(11) default '1' COMMENT '1父类模板ID，2 子类（推荐位模板）',
  `father_id` int(11) default NULL COMMENT '父类模板ID',
  `layout_count` int(11) default NULL COMMENT '矩阵数量',
  `templete_title` varchar(255) default NULL COMMENT '标题',
  `back_pic` varchar(255) default NULL COMMENT '背景图片',
  PRIMARY KEY  (`templete_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自定义推荐位模板列表';

-- ----------------------------
-- Records of portal_custom_recom_templete
-- ----------------------------

-- ----------------------------
-- Table structure for portal_data_group
-- ----------------------------
DROP TABLE IF EXISTS `portal_data_group`;
CREATE TABLE `portal_data_group` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键id',
  `data_group_name` varchar(255) default NULL COMMENT 'Data Group名称',
  `data_group_title` varchar(255) default NULL COMMENT 'Data Group标题名称',
  `row` int(11) default NULL COMMENT '行',
  `col` int(11) default NULL COMMENT '列',
  `content_type` int(11) default NULL COMMENT '内容类型(0频道推荐,1热词内容列表,2频道内容列表)',
  `effective` int(11) default '0' COMMENT '状态(0下线,1上线,-1假删除)',
  `package_name` int(11) default NULL COMMENT '包名(2应用游戏,1大麦影视)',
  `channel` int(11) default NULL COMMENT '频道(2电视剧,1电影,15儿童,4动漫,6综艺)',
  `width` int(11) default NULL COMMENT '宽度',
  `height` int(11) default NULL COMMENT '高度',
  `create_time` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
  `apk_package_name` varchar(255) default NULL COMMENT 'APK包名',
  `category_id` int(11) default NULL COMMENT 'APK频道',
  `hot_id` int(11) default NULL COMMENT '热词ID',
  `channel_type` int(11) default NULL COMMENT '频道类型(基础频道10,自定义频道9)',
  `hot_name` varchar(255) default NULL COMMENT '热词名称',
  `up_and_down` int(11) default NULL COMMENT '横纵(1横,2纵)',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Data Group管理';

-- ----------------------------
-- Records of portal_data_group
-- ----------------------------

-- ----------------------------
-- Table structure for portal_fixed_recom
-- ----------------------------
DROP TABLE IF EXISTS `portal_fixed_recom`;
CREATE TABLE `portal_fixed_recom` (
  `fixed_recom_id` int(11) NOT NULL auto_increment COMMENT '固定推荐位ID',
  `fixed_recom_name` varchar(255) default NULL COMMENT '固定推荐位名称',
  `operate_type` int(11) default NULL COMMENT '运营类型(0运营，1自动)',
  `operate_content` int(11) default NULL COMMENT '运营内容（1大麦影视，2应用游戏）',
  `app_category` int(11) default NULL COMMENT '应用类型',
  `chn_id` int(11) default NULL COMMENT '频道ID',
  `chn_type` int(11) default NULL COMMENT '频道类型',
  `apk_bag_name` varchar(255) default NULL COMMENT 'APK包名',
  `templet_id` int(11) default NULL COMMENT '模板ID',
  `create_time` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_effective` int(11) default NULL COMMENT '固定推荐位状态（1：上线，0：下线）',
  PRIMARY KEY  (`fixed_recom_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='固定推荐位';

-- ----------------------------
-- Records of portal_fixed_recom
-- ----------------------------

-- ----------------------------
-- Table structure for portal_fixed_recom_content
-- ----------------------------
DROP TABLE IF EXISTS `portal_fixed_recom_content`;
CREATE TABLE `portal_fixed_recom_content` (
  `id` int(11) NOT NULL auto_increment,
  `content_id` int(11) default NULL COMMENT '专辑id',
  `content_type` int(4) default NULL COMMENT '1专辑详情 0 影视专题2应用详情3应用专题 4 活动详情 5启动应用6全屏大图',
  `content_name` varchar(255) default NULL COMMENT '内容标题',
  `content_subtitle` varchar(255) default NULL COMMENT '内容副标题',
  `content_img` varchar(255) default NULL COMMENT '内容图片',
  `fixed_recom_id` int(11) default NULL COMMENT '固定推荐位ID',
  `chn_id` int(11) default NULL COMMENT '频道ID',
  `chn_type` int(11) default NULL COMMENT '频道类型',
  `apk_bag_name` varchar(255) default NULL COMMENT 'APK包名',
  `templet_id` int(11) default NULL COMMENT '模板ID',
  `create_time` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
  `operate_content` int(11) default NULL COMMENT '运营内容（1大麦影视，2应用游戏）',
  `app_category` int(11) default NULL COMMENT '应用类型',
  `video_id` int(11) default NULL COMMENT '剧集id',
  `qiyi_is_vip` int(11) default NULL COMMENT '专辑是否是爱奇艺VIP（1是，0否）',
  `is_effective` tinyint(4) default '1' COMMENT '0 无效  1有效',
  `seq` int(11) default NULL COMMENT '顺序',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门户固定推荐位内容管理';

-- ----------------------------
-- Records of portal_fixed_recom_content
-- ----------------------------

-- ----------------------------
-- Table structure for portal_jump_instruction
-- ----------------------------
DROP TABLE IF EXISTS `portal_jump_instruction`;
CREATE TABLE `portal_jump_instruction` (
  `id` int(11) NOT NULL COMMENT 'id 手动录入',
  `start_apk` int(11) default NULL,
  `type` varchar(255) NOT NULL COMMENT '跳转类型',
  `action_name` varchar(255) NOT NULL COMMENT 'action 名',
  `effective` tinyint(2) default '1' COMMENT '上下线 0上线 1下线 默认下线',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='跳转指令管理';

-- ----------------------------
-- Records of portal_jump_instruction
-- ----------------------------

-- ----------------------------
-- Table structure for portal_launcher_tab
-- ----------------------------
DROP TABLE IF EXISTS `portal_launcher_tab`;
CREATE TABLE `portal_launcher_tab` (
  `id` int(11) NOT NULL auto_increment,
  `belong_templet_id` int(11) default NULL COMMENT '所属模板Id',
  `tab_id` int(11) default NULL COMMENT 'tab的Id',
  `tab_name` varchar(255) default NULL COMMENT 'tab的名称',
  `tab_icon` varchar(255) default NULL COMMENT 'tab的icon',
  `seq_is_top` int(11) default NULL COMMENT '置顶顺序',
  `seq` int(11) default NULL COMMENT '顺序',
  `is_effective` int(11) default '1' COMMENT '1 有效  0 无效',
  `create_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portal_launcher_tab
-- ----------------------------

-- ----------------------------
-- Table structure for portal_launcher_templet
-- ----------------------------
DROP TABLE IF EXISTS `portal_launcher_templet`;
CREATE TABLE `portal_launcher_templet` (
  `id` int(11) NOT NULL auto_increment COMMENT '自启动应用模板Id',
  `type` tinyint(4) default NULL COMMENT '控制类型1 ip 2 mac,sn 3 硬件型号',
  `templet_name` varchar(128) default NULL COMMENT '名称',
  `is_hide` int(11) default NULL COMMENT '（废弃）0 为隐藏  1为展示',
  `logo_id` int(11) default NULL COMMENT 'logo的id',
  `auto_id` int(11) default NULL COMMENT '自启动id',
  `block_id` int(11) default NULL COMMENT '豆腐块列表的id',
  `big_image_id` int(11) default NULL COMMENT '（废弃）大图推荐位id',
  `small_image_id` int(11) default NULL COMMENT '（废弃）横竖图推荐位',
  `city_names` varchar(255) default NULL COMMENT '页面区域展示',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域模板配置';

-- ----------------------------
-- Records of portal_launcher_templet
-- ----------------------------

-- ----------------------------
-- Table structure for portal_launcher_templet_area
-- ----------------------------
DROP TABLE IF EXISTS `portal_launcher_templet_area`;
CREATE TABLE `portal_launcher_templet_area` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键Id',
  `templet_id` int(11) default NULL COMMENT '模板Id',
  `area_code` varchar(64) default NULL COMMENT '区域代码',
  `type` tinyint(4) default NULL COMMENT '控制类型1 ip 2 mac,sn 3 硬件型号 4 ROM版本 5 硬件型号+IP地址',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='launcher模板区域配置';

-- ----------------------------
-- Records of portal_launcher_templet_area
-- ----------------------------

-- ----------------------------
-- Table structure for portal_launcher_templet_content
-- ----------------------------
DROP TABLE IF EXISTS `portal_launcher_templet_content`;
CREATE TABLE `portal_launcher_templet_content` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键Id',
  `templet_id` int(11) default NULL COMMENT '模板id',
  `recommend_id` int(11) default NULL COMMENT '推荐位列表的id',
  `recom_name` varchar(255) default NULL COMMENT '推荐位名称',
  `oper_type` tinyint(4) default NULL COMMENT '运营类型',
  `recommend_type` int(11) default NULL COMMENT '推荐位列表的类型（1：固定推荐位，2：积木推荐位）',
  `seq` int(11) default NULL COMMENT '顺序',
  `create_time` timestamp NULL default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='launcher推荐位列表';

-- ----------------------------
-- Records of portal_launcher_templet_content
-- ----------------------------

-- ----------------------------
-- Table structure for portal_logo_manage
-- ----------------------------
DROP TABLE IF EXISTS `portal_logo_manage`;
CREATE TABLE `portal_logo_manage` (
  `logo_id` int(11) NOT NULL auto_increment,
  `logo_name` varchar(200) default NULL,
  `logo_img` varchar(500) default NULL,
  `is_online` int(11) default '0',
  `create_time` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY  (`logo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='牌照LOGO列表';

-- ----------------------------
-- Records of portal_logo_manage
-- ----------------------------

-- ----------------------------
-- Table structure for portal_mac_area
-- ----------------------------
DROP TABLE IF EXISTS `portal_mac_area`;
CREATE TABLE `portal_mac_area` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `mac` varchar(128) default NULL COMMENT 'mac',
  `sn` varchar(128) default NULL COMMENT 'sn',
  `area_code` varchar(64) default NULL COMMENT '所属地区代码',
  `area_name` varchar(128) default NULL COMMENT '所属地区名称',
  PRIMARY KEY  (`id`),
  KEY `mac_area_index` (`mac`,`sn`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='MACSN管理';

-- ----------------------------
-- Records of portal_mac_area
-- ----------------------------

-- ----------------------------
-- Table structure for portal_not_start_instruction
-- ----------------------------
DROP TABLE IF EXISTS `portal_not_start_instruction`;
CREATE TABLE `portal_not_start_instruction` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `app_name` varchar(255) default NULL COMMENT '启动指令名称',
  `start_apk` int(11) default NULL COMMENT '启动apk',
  `action_str` varchar(255) default NULL COMMENT 'action指令',
  `effective` int(11) NOT NULL default '0' COMMENT '状态 0无效 1,有效',
  `create_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='非开机启动指令';

-- ----------------------------
-- Records of portal_not_start_instruction
-- ----------------------------

-- ----------------------------
-- Table structure for portal_screent_recommend_content
-- ----------------------------
DROP TABLE IF EXISTS `portal_screent_recommend_content`;
CREATE TABLE `portal_screent_recommend_content` (
  `id` int(11) NOT NULL auto_increment,
  `belong_recomend_id` int(11) default NULL COMMENT '所属首屏推荐位id',
  `title` varchar(128) default NULL COMMENT '标题',
  `second_title` varchar(128) default NULL COMMENT '副标题',
  `is_play` int(11) default NULL COMMENT '是否自动播放',
  `content_type` int(4) default NULL COMMENT '1专辑详情 0 影视专题2应用详情3应用专题 4 活动详情 5启动应用6全屏大图 7商品包',
  `content_name` varchar(255) default NULL COMMENT '内容名称',
  `content_id` int(11) default NULL COMMENT '专辑/专题/活动id',
  `position` int(4) default NULL COMMENT '推荐位置',
  `seq` int(11) default NULL COMMENT '排序字段',
  `is_use_app` tinyint(1) default '0' COMMENT '是否启动应用 0否 1是',
  `content_focus` varchar(2000) default NULL COMMENT '推荐内容看点(副标题)',
  `recommend_name` varchar(50) default NULL COMMENT '推荐名称',
  `apk_name` varchar(256) default NULL COMMENT '应用包名称或者 模板下apk包名',
  `content_img` varchar(200) default NULL COMMENT '推荐图片',
  `content_update` varchar(50) default NULL COMMENT '推荐内容更新',
  `content_total` int(11) default NULL COMMENT '总集数',
  `is_txt_show` tinyint(1) default '0' COMMENT '推荐是否显示副标题【0:不显示，1:显示】',
  `is_effective` tinyint(4) default '1' COMMENT '0 无效 1 有效  2 备份',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_time` datetime default NULL COMMENT '更新时间',
  `album_stream` varchar(200) default NULL COMMENT '极清码流',
  `videoset_stream` varchar(200) default NULL COMMENT '爱奇艺码流',
  `cname` varchar(100) default NULL COMMENT '频道名称',
  `cid` int(11) default NULL COMMENT '频道id',
  `album_type` tinyint(4) default NULL COMMENT '专辑类型 0单剧集 1多剧集',
  `big_Pic` varchar(512) default NULL COMMENT '图片',
  `templet_id` int(11) default NULL COMMENT '模板Id',
  `hot_word_id` int(11) default NULL COMMENT '热词Id',
  `c_type` tinyint(4) default NULL COMMENT '频道类型',
  `image_type` tinyint(4) default NULL COMMENT '1横图 2 竖图 0 大图',
  `hot_word_type` tinyint(4) default NULL COMMENT '热词类型',
  `is_pay` tinyint(4) default NULL COMMENT '大麦付费0否1是',
  `qiyi_vip` tinyint(4) default NULL COMMENT '爱奇艺vip 0否1是',
  `qiyi_pay` tinyint(4) default NULL COMMENT '爱奇艺付费 0否1是',
  `is_vip` tinyint(4) default NULL COMMENT '大麦vip 0否1是',
  `qiyi_effective` tinyint(4) default '0' COMMENT '爱奇艺专辑状态 0无效1有效',
  `jq_effective` tinyint(4) default '0' COMMENT '极清有效状态 0 无效 1有效',
  PRIMARY KEY  (`id`),
  KEY `idx_is_effective_position_seq` (`is_effective`,`position`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='首屏大图推荐位内容管理';

-- ----------------------------
-- Records of portal_screent_recommend_content
-- ----------------------------

-- ----------------------------
-- Table structure for portal_screen_big_pics
-- ----------------------------
DROP TABLE IF EXISTS `portal_screen_big_pics`;
CREATE TABLE `portal_screen_big_pics` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键Id',
  `img_name` varchar(128) default NULL COMMENT '图片名称',
  `img_addr` varchar(128) default NULL COMMENT '图片地址',
  `tab_background` int(11) NOT NULL default '0' COMMENT '背景图片(0不设为,1设为)',
  `is_online` int(11) default '0',
  `create_time` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全屏大图';

-- ----------------------------
-- Records of portal_screen_big_pics
-- ----------------------------

-- ----------------------------
-- Table structure for portal_screen_recommend_list
-- ----------------------------
DROP TABLE IF EXISTS `portal_screen_recommend_list`;
CREATE TABLE `portal_screen_recommend_list` (
  `recommend_id` int(11) NOT NULL auto_increment COMMENT '主键Id',
  `recommend_name` varchar(128) default NULL COMMENT '推荐位名称',
  `recommend_type` int(11) default NULL COMMENT '推荐位类型  0 大图  1横图 2竖图',
  `time_span` varchar(64) default NULL COMMENT '时间间隔  以分为单位',
  `is_effective` tinyint(2) default '0' COMMENT '有效状态  1 有效 0无效 -1假删除',
  PRIMARY KEY  (`recommend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='首屏大图推荐位';

-- ----------------------------
-- Records of portal_screen_recommend_list
-- ----------------------------

-- ----------------------------
-- Table structure for portal_spe_group
-- ----------------------------
DROP TABLE IF EXISTS `portal_spe_group`;
CREATE TABLE `portal_spe_group` (
  `id` int(11) NOT NULL default '0',
  `spe_group_name` varchar(255) default NULL COMMENT '名称',
  `spe_group_title` varchar(255) default NULL COMMENT '标题',
  `create_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portal_spe_group
-- ----------------------------

-- ----------------------------
-- Table structure for portal_start_instruction
-- ----------------------------
DROP TABLE IF EXISTS `portal_start_instruction`;
CREATE TABLE `portal_start_instruction` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `app_name` varchar(255) default NULL COMMENT '应用名称',
  `character_string` varchar(255) default NULL COMMENT '字符串',
  `instruction_type` tinyint(4) default NULL COMMENT '指令类型，1-action方式启动 2-包名方式启动',
  `is_effective` tinyint(4) default '0' COMMENT '0 无效 1 有效',
  `start_type` tinyint(4) default NULL COMMENT '（废弃）启动类型，1-开机启动 2-非开机启动',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='启动指令管理';

-- ----------------------------
-- Records of portal_start_instruction
-- ----------------------------

-- ----------------------------
-- Table structure for portal_sys_app_icons
-- ----------------------------
DROP TABLE IF EXISTS `portal_sys_app_icons`;
CREATE TABLE `portal_sys_app_icons` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `app_name` varchar(128) default NULL COMMENT '应用名称',
  `app_package` varchar(128) default NULL COMMENT '包名',
  `is_show` tinyint(4) default '1' COMMENT '是否显示',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='桌面图标过滤';

-- ----------------------------
-- Records of portal_sys_app_icons
-- ----------------------------

-- ----------------------------
-- Table structure for portal_tab
-- ----------------------------
DROP TABLE IF EXISTS `portal_tab`;
CREATE TABLE `portal_tab` (
  `id` int(11) NOT NULL auto_increment,
  `tab_name` varchar(255) default NULL COMMENT 'tab名称',
  `tab_title` varchar(255) default NULL COMMENT 'tab标题名称',
  `seq_is_top` int(11) default NULL COMMENT '置顶顺序',
  `seq` int(11) default NULL COMMENT '普通顺序',
  `is_effective` int(11) default NULL COMMENT '有效状态   1 上线  0 下线  -1 假删除',
  `tip` varchar(255) default NULL COMMENT '备用字段',
  `tab_back_pic` varchar(255) default NULL COMMENT '背景图片',
  `tab_icon` varchar(255) default NULL COMMENT 'tab图标',
  `create_time` timestamp NULL default '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portal_tab
-- ----------------------------

-- ----------------------------
-- Table structure for portal_tab_group
-- ----------------------------
DROP TABLE IF EXISTS `portal_tab_group`;
CREATE TABLE `portal_tab_group` (
  `id` int(11) NOT NULL auto_increment,
  `belong_tab_id` int(11) default NULL COMMENT '所属tab的Id',
  `belong_group_id` int(11) default NULL COMMENT '所属group的Id',
  `group_type` tinyint(4) default NULL COMMENT 'Group类型：1.Group  2.Spe Group  3.Data Group',
  `content_type` tinyint(4) default NULL COMMENT '内容类型 1频道推荐 2 热词内容列表 3频道内容列表',
  `apk_bag_name` varchar(255) default NULL COMMENT '包名',
  `chn_id` int(11) default NULL COMMENT '频道Id',
  `chn_type` int(11) default NULL COMMENT '频道类型',
  `hot_id` int(11) default NULL COMMENT '热词Id',
  `is_show` tinyint(4) default '0' COMMENT '是否显示',
  `group_name` varchar(255) default NULL COMMENT '名称',
  `group_title` varchar(255) default NULL COMMENT '标题',
  `seq_is_top` int(11) default NULL COMMENT '置顶顺序',
  `seq` int(11) default NULL COMMENT '普通顺序',
  `is_effective` tinyint(4) default '1' COMMENT '状态 1默认有效  0 暂时不用  -1假删除',
  `col` int(11) default NULL COMMENT '列',
  `row` int(11) default NULL COMMENT '行',
  `width` int(11) default NULL COMMENT '宽度',
  `height` int(11) default NULL COMMENT '高度',
  `create_time` timestamp NULL default '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '更新时间',
  `up_and_down` int(11) default NULL COMMENT '横纵(1横,2纵)',
  `group_background` varchar(255) default NULL COMMENT 'group背景图',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portal_tab_group
-- ----------------------------

-- ----------------------------
-- Table structure for portal_vip_logo
-- ----------------------------
DROP TABLE IF EXISTS `portal_vip_logo`;
CREATE TABLE `portal_vip_logo` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `seq` int(11) NOT NULL default '1' COMMENT '排序',
  `logo_id` varchar(255) NOT NULL COMMENT '标识id',
  `vip_name` varchar(255) NOT NULL COMMENT 'vip名称',
  `vip_logo` varchar(255) NOT NULL COMMENT 'vip图标url',
  `effective` tinyint(2) default '1' COMMENT '上下线 1 上线   0 下线    默认 1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='vip图标';

-- ----------------------------
-- Records of portal_vip_logo
-- ----------------------------
