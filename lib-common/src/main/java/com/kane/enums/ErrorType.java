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
    USER_NOT_EXIST(400, "用户不存在"),
    USER_NOT_ACTIVE(401, "该用户已被封禁"),
    PASSWORD_ERROR(402, "密码错误"),
    USER_NOT_LOGIN(403, "用户未登录"),
    CODE_NOT_EXIST(404, "验证码不存在"),
    CODE_ERROR(405, "验证码错误"),
    FAIL(500, "服务器开小差了，请稍后再试~");

    private final Integer code;
    private final String msg;
}
