package com.kane.exception;

import com.kane.enums.ErrorType;
import lombok.Getter;

/**
 * 包名: com.kane.com.kane.exception
 * 说明：商业异常
 * 创建时间： 2025-12-16
 */
@Getter
public class BusinessException extends RuntimeException {

    private final Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorType errorType) {
        super(errorType.getMsg());
        this.code = errorType.getCode();
    }
}
