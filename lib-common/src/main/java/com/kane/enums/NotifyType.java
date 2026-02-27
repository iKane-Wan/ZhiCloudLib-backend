package com.kane.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotifyType {

    SMS(1, "短信"),
    EMAIL(2, "邮件");

    private final Integer code;
    private final String msg;
}
