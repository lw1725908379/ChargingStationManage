/*
Navicat MySQL Data Transfer

Source Server         : 主库
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : db_charging_station_manage

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2024-01-14 20:29:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `appoint`
-- ----------------------------
DROP TABLE IF EXISTS `appoint`;
CREATE TABLE `appoint` (
  `id` char(8) NOT NULL DEFAULT '' COMMENT '预约id',
  `user_id` char(8) NOT NULL DEFAULT '' COMMENT '预约所属用户id',
  `charge_id` char(8) NOT NULL DEFAULT '' COMMENT '预约所属充电桩id',
  `charge_name` varchar(32) NOT NULL DEFAULT '' COMMENT '预约所属充电桩名称',
  `charge_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '预约所属充电桩收费价格  元/分钟',
  `create_time` datetime NOT NULL COMMENT '预约创建时间',
  `day` date NOT NULL COMMENT '预约日期',
  `time` varchar(16) NOT NULL DEFAULT '' COMMENT '预约时间段',
  `state` int(2) NOT NULL DEFAULT '1' COMMENT '预约状态 1：待使用；2：充电中；3：已完成；4：已取消',
  `start_time` datetime DEFAULT NULL COMMENT '开始充电时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束充电时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appoint
-- ----------------------------
INSERT INTO `appoint` VALUES ('kJ21vOSa', 'NBZMq4n6', 'ggdR7bij', '大功率直流充电桩', '4.00', '2024-01-07 08:56:25', '2024-01-07', '10:00-12:00', '3', '2024-01-07 10:03:00', '2024-01-07 10:06:00');

-- ----------------------------
-- Table structure for `charge`
-- ----------------------------
DROP TABLE IF EXISTS `charge`;
CREATE TABLE `charge` (
  `id` char(8) NOT NULL DEFAULT '' COMMENT '充电桩id',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '充电桩名称',
  `station_id` char(8) NOT NULL DEFAULT '' COMMENT '充电桩所属电站id',
  `photo` varchar(256) NOT NULL DEFAULT 'common/no_image.jpg' COMMENT '充电桩图片',
  `state` int(2) NOT NULL DEFAULT '1' COMMENT '充电桩状态  1：正常；2：报修中',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '充电桩收费价格  元/分钟',
  `description` varchar(512) NOT NULL DEFAULT '' COMMENT '充电桩规格描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of charge
-- ----------------------------
INSERT INTO `charge` VALUES ('ggdR7bij', '大功率交流充电桩', 'p4C8GnU8', '20240107/1704595932512.jpg', '1', '4.00', '70KW大功率，好用的充电桩！');
INSERT INTO `charge` VALUES ('iFOngakd', '大功率直流充电桩', '6uiT2l58', '20240107/1704595570324.jpg', '1', '3.00', '60KW大功率、安全可靠、操作简单');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` char(8) NOT NULL DEFAULT '' COMMENT '留言id',
  `content` varchar(512) NOT NULL DEFAULT '' COMMENT '留言内容',
  `create_time` datetime NOT NULL COMMENT '留言时间',
  `reply_content` varchar(512) NOT NULL DEFAULT '' COMMENT '管理员回复内容',
  `user_id` char(8) NOT NULL DEFAULT '' COMMENT '留言所属用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('eTYZ2pFt', '充电站以后能多建几个吗？', '2024-01-07 11:00:19', '肯定会的！', 'NvMY01ea');

-- ----------------------------
-- Table structure for `operator`
-- ----------------------------
DROP TABLE IF EXISTS `operator`;
CREATE TABLE `operator` (
  `id` char(8) NOT NULL DEFAULT '' COMMENT '运营商id',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '运营商名称',
  `description` varchar(512) NOT NULL DEFAULT '' COMMENT '运营商描述',
  `station_num` int(2) NOT NULL DEFAULT '0' COMMENT '运营商下属电站数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operator
-- ----------------------------
INSERT INTO `operator` VALUES ('ehteT4Ia', '特来电', '特来电新能源股份有限公司是新能源汽车充电设备制造商和充电网运营商，主要从事充电设备的研发、生产、销售及充电网的建设与运营，为用户提供充电系统解决方案及充电网运营服务。', '1');
INSERT INTO `operator` VALUES ('WuN1ITuy', '星星充电', '星星充电，专注于新能源汽车充电设备制造，为全球客户提供设备、平台、用户和数据运营服务，借助车辆销售、私人充电、公共充电、金融保险等业务打造用户充电全生命周期软件。', '1');
INSERT INTO `operator` VALUES ('X1Gdme33', '云快充', '云快充是中国充电物联网与能源管理解决方案服务商,打造了行业第三方充电物联网SaaS平台,致力为全产业客户提供充电服务和能源管理综合解决方案。', '0');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` char(8) NOT NULL DEFAULT '' COMMENT '订单id',
  `total_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单总价格',
  `user_id` char(8) NOT NULL DEFAULT '' COMMENT '订单所属用户id',
  `charge_id` char(8) NOT NULL DEFAULT '' COMMENT '订单所属充电桩id',
  `charge_name` varchar(32) NOT NULL DEFAULT '' COMMENT '订单所属充电桩名称',
  `create_time` datetime NOT NULL COMMENT '订单创建时间',
  `state` int(2) NOT NULL DEFAULT '1' COMMENT '订单状态  1：未支付；2：已支付',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('XCM5qPeM', '12.00', 'NBZMq4n6', 'ggdR7bij', '大功率直流充电桩', '2024-01-07 10:06:00', '2');

-- ----------------------------
-- Table structure for `rate`
-- ----------------------------
DROP TABLE IF EXISTS `rate`;
CREATE TABLE `rate` (
  `id` char(8) NOT NULL DEFAULT '' COMMENT '积分明细id',
  `user_id` char(8) NOT NULL DEFAULT '' COMMENT '积分明细所属用户id',
  `warn_id` char(8) NOT NULL DEFAULT '' COMMENT '积分明细所属告警id',
  `type` int(2) NOT NULL DEFAULT '1' COMMENT '积分变化类型 1：增加；2：减少',
  `score` int(2) NOT NULL DEFAULT '0' COMMENT '积分变化分数',
  `create_time` datetime NOT NULL COMMENT '积分明细变动时间',
  `content` varchar(512) NOT NULL DEFAULT '' COMMENT '积分变动描述',
  `now_score` int(2) NOT NULL DEFAULT '0' COMMENT '变动后的积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rate
-- ----------------------------
INSERT INTO `rate` VALUES ('P8rNaNdw', 'NBZMq4n6', 'yWiuiOwm', '2', '3', '2024-01-07 12:02:31', '充电完成没及时离开充电桩！', '97');
INSERT INTO `rate` VALUES ('qfgfq614', 'NBZMq4n6', '', '1', '5', '2024-01-07 10:06:44', '成功支付，奖励信誉分5分！', '100');

-- ----------------------------
-- Table structure for `repair`
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair` (
  `id` char(8) NOT NULL DEFAULT '' COMMENT '报修id',
  `user_id` char(8) NOT NULL DEFAULT '' COMMENT '报修所属用户id',
  `charge_id` char(8) NOT NULL DEFAULT '' COMMENT '报修所属充电桩id',
  `state` int(2) NOT NULL DEFAULT '1' COMMENT '报修状态  1：未受理；2：已受理；3：已撤销；4：已完成',
  `create_time` datetime NOT NULL COMMENT '报修时间',
  `charge_name` varchar(32) NOT NULL DEFAULT '' COMMENT '报修所属充电桩名称',
  `username` varchar(8) NOT NULL DEFAULT '' COMMENT '报修所属用户昵称',
  `description` varchar(512) NOT NULL DEFAULT '' COMMENT '报修内容描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair
-- ----------------------------

-- ----------------------------
-- Table structure for `station`
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `id` char(8) NOT NULL DEFAULT '' COMMENT '电站id',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '电站名称',
  `location` varchar(256) NOT NULL DEFAULT '' COMMENT '电站地址',
  `photo` varchar(256) NOT NULL DEFAULT 'common/no_image.jpg' COMMENT '电站图片',
  `charge_num` int(2) NOT NULL DEFAULT '0' COMMENT '电站的充电桩总数量',
  `available_charge_num` int(2) NOT NULL DEFAULT '0' COMMENT '电站的可用充电桩数量',
  `operator_id` char(8) NOT NULL DEFAULT '' COMMENT '电站所属运营商id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of station
-- ----------------------------
INSERT INTO `station` VALUES ('6uiT2l58', '特来电下属充电站', '福建省福州市仓山区32号', '20240107/1704595426817.jpg', '1', '1', 'ehteT4Ia');
INSERT INTO `station` VALUES ('p4C8GnU8', '星星快充下属充电站', '福建省厦门市湖里区33号', '20240107/1704595496742.jpg', '1', '1', 'WuN1ITuy');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` char(8) NOT NULL DEFAULT '' COMMENT '用户id',
  `username` varchar(8) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `password` varchar(16) NOT NULL DEFAULT '' COMMENT '用户密码',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '用户手机号',
  `head_pic` varchar(256) NOT NULL DEFAULT 'common/no_image.jpg' COMMENT '用户头像',
  `sex` int(2) NOT NULL DEFAULT '3' COMMENT '用户性别  1：男；2：女；3：未知',
  `role_id` int(2) NOT NULL DEFAULT '1' COMMENT '用户角色 1：普通用户；2：管理员',
  `rate` int(2) NOT NULL DEFAULT '100' COMMENT '信誉积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('NBZMq4n6', '小杨', '123456', '13774559483', 'common/no_image.jpg', '1', '1', '97');
INSERT INTO `user` VALUES ('NvMY01ea', '杨杨吖', '123456', '13771559485', '20240107/1704595122454.jpg', '1', '2', '100');

-- ----------------------------
-- Table structure for `warn`
-- ----------------------------
DROP TABLE IF EXISTS `warn`;
CREATE TABLE `warn` (
  `id` char(8) NOT NULL DEFAULT '' COMMENT '告警id',
  `user_id` char(8) NOT NULL DEFAULT '' COMMENT '告警所属用户id',
  `content` varchar(512) NOT NULL DEFAULT '' COMMENT '告警内容描述',
  `create_time` datetime NOT NULL COMMENT '告警产生时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of warn
-- ----------------------------
INSERT INTO `warn` VALUES ('yWiuiOwm', 'NBZMq4n6', '充电完成没及时离开充电桩！', '2024-01-07 12:02:31');
