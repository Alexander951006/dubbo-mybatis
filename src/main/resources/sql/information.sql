DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
                                `id`                  bigint(20)      NOT NULL          COMMENT 'id,用户手机号码',
                                `name`                varchar(255)    NOT NULL          COMMENT '用户昵称',
                                `password`            varchar(32)     DEFAULT NULL      COMMENT '密码(md5((md5(明文密码+salt))+salt))',
                                `salt`                varchar(10)     DEFAULT NULL      COMMENT 'MD5的salt',
                                `head`                varchar(128)    DEFAULT NULL      COMMENT '用户头像，云存储id',
                                `register_time`       datetime        DEFAULT NULL      COMMENT '注册时间',
                                `last_login`          datetime        DEFAULT NULL      COMMENT '上次登陆时间',
                                `login_count`         int(11)         DEFAULT '0'       COMMENT '用户登陆次数',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户登陆表';

DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
                         `id` bigint(20)     NOT NULL      AUTO_INCREMENT  COMMENT '商品ID',
                         `goods_name`        varchar(16)   DEFAULT NULL    COMMENT '商品名称',
                         `goods_title`       varchar(64)   DEFAULT NULL    COMMENT '商品标题',
                         `goods_img`         varchar(64)   DEFAULT NULL    COMMENT '商品的图片',
                         `goods_detail`      longtext                      COMMENT '商品的详情介绍',
                         `goods_price`       decimal(10,2) DEFAULT '0.00'  COMMENT '商品单价',
                         `goods_stock`       int(11)       DEFAULT '0'     COMMENT '商品库存，-1表示没有限制',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
                                 `id`       BIGINT(20) NOT NULL AUTO_INCREMENT,
                                 `user_id`  BIGINT(20)          DEFAULT NULL     COMMENT '用户ID',
                                 `order_id` BIGINT(20)          DEFAULT NULL     COMMENT '订单ID',
                                 `goods_id`  BIGINT(20)          DEFAULT NULL     COMMENT '商品ID',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `u_uid_gid` (`user_id`, `goods_id`) USING BTREE COMMENT '定义联合索引'
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1551
    DEFAULT CHARSET = utf8mb4;