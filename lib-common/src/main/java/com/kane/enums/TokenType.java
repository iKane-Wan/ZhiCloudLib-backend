package com.kane.enums;

/**
 * Token 类型枚举
 * 用于区分 JWT 访问令牌和刷新令牌
 * 
 * @author kane
 * @version 1.0
 * @since 2025-12-16
 */
public enum TokenType {
    /**
     * 访问令牌，用于访问受保护的资源
     */
    ACCESS_TOKEN,
    /**
     * 刷新令牌，用于刷新访问令牌
     */
    REFRESH_TOKEN
}
