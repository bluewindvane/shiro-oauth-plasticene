/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.160
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 192.168.1.160:3306
 Source Schema         : plasticene

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 04/09/2020 17:24:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `token_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '该字段的值是将access_token的值通过MD5加密后存储的.',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户类型 ',
  `refresh_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '该字段的值是将refresh_token的值通过MD5加密后存储的.',
  `authentication` blob NULL COMMENT '用户实体类对象序列化后的二进制数据',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '存储code接口生成的code值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_approve
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approve`;
CREATE TABLE `oauth_approve`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型 ip 通过IP拦截',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `authentication` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '鉴权内容 类型为ip即填写地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端Id',
  `client_secret` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端secret',
  `additional_information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预留字段',
  `access_token_validity` bigint(20) NULL DEFAULT NULL COMMENT '客户端的access_token的有效时间值(单位:秒)',
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '描述',
  `trusted` tinyint(1) NULL DEFAULT NULL COMMENT '默认为’0’(即不受信任的,1为受信任的)',
  `trust_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '如果不受信任，该字段必填，关联不受信任表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES (1, 'wAKIIXwzx1X9NRMW75', 'UIdkPJuMj1a6jAuZJM', 'test', 600, '测试', 1, 'none');

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '存储code接口生成的code值',
  `authentication` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '三方接口接入（存储code值）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------
INSERT INTO `oauth_code` VALUES (2, '2020-08-25 18:10:23', '0cfe7b3a1eefb9ffcffe211ec790aca0', 'wAKIIXwzx1X9NRMW75');
INSERT INTO `oauth_code` VALUES (3, '2020-08-25 18:10:43', 'e0f4bcdef35bcc3f40fdeba5ee0f91ce', 'wAKIIXwzx1X9NRMW75');
INSERT INTO `oauth_code` VALUES (4, '2020-08-26 08:44:02', 'ff4b7e18d5a150af965775478ef6b8e4', 'wAKIIXwzx1X9NRMW75');

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `token_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '该字段的值是将refresh_token的值通过MD5加密后存储的.',
  `token` blob NULL COMMENT '存储将OAuth2RefreshToken.java对象序列化后的二进制数据.',
  `authentication` blob NULL COMMENT '存储将OAuth2Authentication.java对象序列化后的二进制数据.',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------
INSERT INTO `oauth_refresh_token` VALUES (2, '2020-08-25 10:14:37', 'abcbb35ce2306accbe2b4b71507ae58a', 0x65794A68624763694F694A49557A55784D694A392E65794A6A636D56686447566B496A6F784E546B344D7A49784E6A63344E6A55794C434A6C654841694F6A45314F54677A4D6A4D304E7A6773496E526C626D467564434936496E4E4B5657524C545649725A6B5A52526B7856596C70774D564E4C596D4E484D31706F4F58427564433931574535705547783156584D3151553039496E302E44434B7246684A6A5651644C3252434C3170335A476954707339704747745A51496F5975554E5A523336794768397364366B4F4476667A54474A464E6B64564E7A3949627447316E505944417447594945684A5A6841, 0xACED000573720024636F6D2E6D61706C652E6F617574682E656E746974792E436C69656E7444657461696C7300000000000000010200084C0013616363657373546F6B656E56616C69646974797400104C6A6176612F6C616E672F4C6F6E673B4C00156164646974696F6E616C496E666F726D6174696F6E7400124C6A6176612F6C616E672F537472696E673B4C0008636C69656E74496471007E00024C000C636C69656E7453656372657471007E00024C000B6465736372697074696F6E71007E00024C000269647400134C6A6176612F6C616E672F496E74656765723B4C000974727573745479706571007E00024C0007747275737465647400134C6A6176612F6C616E672F426F6F6C65616E3B78707372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000002587400047465737474001277414B494958777A783158394E524D5737357400125549646B504A754D6A3161366A41755A4A4D740006E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0007000000017400046E6F6E65737200116A6176612E6C616E672E426F6F6C65616ECD207280D59CFAEE0200015A000576616C7565787001);
INSERT INTO `oauth_refresh_token` VALUES (4, '2020-09-04 17:20:07', 'b11bac0d4cab6547f4444fe18f3903d0', 0x65794A68624763694F694A49557A55784D694A392E65794A6A636D56686447566B496A6F784E546B354D6A45784D6A45774F444D314C434A6C654841694F6A45314F546B794D544D774D544173496E526C626D467564434936496E704B4E475A6F565338344B306478554552776445643262564A5856475A43515864435A57354A564549305A4656534D6D705463306C3357586339496E302E414E424A7471576233366668666A4C6F66506E4D564573336D6C44436C746A50455F7569526972556135465656786257444445514871375F516876696D584C476A654D5130694333616D355579357231794932615641, 0xACED000573720024636F6D2E6D61706C652E6F617574682E656E746974792E436C69656E7444657461696C7300000000000000010200084C0013616363657373546F6B656E56616C69646974797400104C6A6176612F6C616E672F4C6F6E673B4C00156164646974696F6E616C496E666F726D6174696F6E7400124C6A6176612F6C616E672F537472696E673B4C0008636C69656E74496471007E00024C000C636C69656E7453656372657471007E00024C000B6465736372697074696F6E71007E00024C000269647400134C6A6176612F6C616E672F496E74656765723B4C000974727573745479706571007E00024C0007747275737465647400134C6A6176612F6C616E672F426F6F6C65616E3B78707372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000002587400047465737474001277414B494958777A783158394E524D5737357400125549646B504A754D6A3161366A41755A4A4D740006E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0007000000017400046E6F6E65737200116A6176612E6C616E672E426F6F6C65616ECD207280D59CFAEE0200015A000576616C7565787001);

SET FOREIGN_KEY_CHECKS = 1;