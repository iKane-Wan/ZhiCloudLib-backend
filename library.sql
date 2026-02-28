/*
 Navicat Premium Data Transfer

 Source Server         : ik-server
 Source Server Type    : MySQL
 Source Server Version : 80407
 Source Host           : 192.168.0.3:3306
 Source Schema         : library

 Target Server Type    : MySQL
 Target Server Version : 80407
 File Encoding         : 65001

 Date: 28/02/2026 15:56:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book`  (
  `book_id` bigint NOT NULL AUTO_INCREMENT COMMENT '图书唯一标识（主键）',
  `book_isbn` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ISBN编号（唯一）',
  `book_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图书名称',
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者',
  `publisher` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '出版社',
  `publish_date` date NOT NULL COMMENT '出版日期',
  `category_id` bigint NOT NULL COMMENT '关联图书分类表t_book_category的外键',
  `sub_category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '子分类（如：计算机-Java）',
  `book_status` tinyint NOT NULL DEFAULT 1 COMMENT '图书状态：1-可借 2-借出 3-维修 4-丢失',
  `total_num` int NOT NULL DEFAULT 0 COMMENT '总馆藏数量',
  `available_num` int NOT NULL DEFAULT 0 COMMENT '可借数量',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '图书定价',
  `introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '图书简介',
  `cover_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '封面图片地址',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标识：0-未删除 1-已删除',
  PRIMARY KEY (`book_id`) USING BTREE,
  UNIQUE INDEX `uk_t_book_isbn`(`book_isbn` ASC) USING BTREE,
  INDEX `idx_t_book_category`(`category_id` ASC) USING BTREE,
  INDEX `idx_t_book_status`(`book_status` ASC) USING BTREE,
  INDEX `idx_t_book_delete_flag`(`delete_flag` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图书表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_book_category
-- ----------------------------
DROP TABLE IF EXISTS `t_book_category`;
CREATE TABLE `t_book_category`  (
  `category_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类唯一标识（主键）',
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称（如计算机、文学）',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父分类ID，顶级分类为0',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序优先级，数值越小越靠前',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标识：0-未删除 1-已删除',
  PRIMARY KEY (`category_id`) USING BTREE,
  INDEX `idx_t_book_category_parent`(`parent_id` ASC) USING BTREE,
  INDEX `idx_t_book_category_delete`(`delete_flag` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图书分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_borrow_record
-- ----------------------------
DROP TABLE IF EXISTS `t_borrow_record`;
CREATE TABLE `t_borrow_record`  (
  `record_id` bigint NOT NULL AUTO_INCREMENT COMMENT '借阅记录唯一标识（主键）',
  `book_id` bigint NOT NULL COMMENT '关联图书表t_book的主键',
  `user_id` bigint NOT NULL COMMENT '关联读者表t_reader的主键',
  `borrow_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '借阅时间',
  `due_time` datetime NOT NULL COMMENT '应归还时间',
  `return_time` datetime NULL DEFAULT NULL COMMENT '实际归还时间，未归还则为NULL',
  `borrow_status` tinyint NOT NULL DEFAULT 1 COMMENT '借阅状态：1-借阅中 2-已归还 3-超期 4-丢失',
  `fine_amount` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '滞纳金金额，未超期则为0',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '经办人（管理员账号/姓名）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注（如续借、图书破损）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  `delete_flag` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标识：0-未删除 1-已删除',
  PRIMARY KEY (`record_id`) USING BTREE,
  INDEX `idx_t_borrow_record_book`(`book_id` ASC) USING BTREE,
  INDEX `idx_t_borrow_record_reader`(`user_id` ASC) USING BTREE,
  INDEX `idx_t_borrow_record_status`(`borrow_status` ASC) USING BTREE,
  INDEX `idx_t_borrow_record_delete`(`delete_flag` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '借阅记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户唯一ID（主键）',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号（唯一）',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱（唯一）',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '加密密码（MD5）',
  `salt` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '盐值',
  `open_id` char(28) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信小程序openid',
  `user_type` tinyint NULL DEFAULT 0 COMMENT '用户类型：0-用户，1-管理员',
  `status` tinyint NULL DEFAULT 0 COMMENT '状态：0-正常，1-禁用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uk_phone`(`phone` ASC) USING BTREE,
  UNIQUE INDEX `uk_email`(`email` ASC) USING BTREE,
  UNIQUE INDEX `uk_open_id`(`open_id` ASC) USING BTREE,
  INDEX `idx_user_type`(`user_type` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '基础认证表' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
