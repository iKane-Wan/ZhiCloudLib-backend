package com.kane.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通知类型枚举
 * 用于区分不同的通知方式（短信、邮件等）
 * 
 * @author kane
 * @version 1.0
 * @since 2025-12-15
 */
@Getter
@AllArgsConstructor
public enum NotifyType {

    /**
     * 短信通知
     */
    SMS(1, "短信"),
    /**
     * 邮件通知
     */
    EMAIL(2, "邮件");

    /**
     * 通知类型代码
     */
    private final Integer code;
    /**
     * 通知类型描述
     */
    private final String msg;
}
