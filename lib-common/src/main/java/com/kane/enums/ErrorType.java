package com.kane.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 包名: com.kane.com.kane.enums
 * 说明：错误类型枚举
 * 创建时间： 2025-12-15
 */
@Getter
@AllArgsConstructor
public enum ErrorType {
    USER_NOT_LOGIN(4001, "用户未登录"),
    USER_LOGIN_EXPIRED(4002, "用户登录已过期"),
    USER_NOT_PERMITTED(4003, "用户无权限"),
    FAIL(5000, "服务器开小差了，请稍后再试~");

    private final Integer code;
    private final String msg;
}
