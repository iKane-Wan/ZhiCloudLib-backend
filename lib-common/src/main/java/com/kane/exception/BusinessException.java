package com.kane.exception;

import com.kane.enums.ErrorType;
import lombok.Getter;

/**
 * 业务异常类
 * 用于处理业务逻辑中的异常情况
 * 包含错误码和错误信息，便于前端展示和日志记录
 * 
 * @author kane
 * @version 1.0
 * @since 2025-12-16
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 构造业务异常
     * 
     * @param code 错误码
     * @param message 错误信息
     */
    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造业务异常
     * 使用预定义的错误类型枚举
     * 
     * @param errorType 错误类型枚举
     */
    public BusinessException(ErrorType errorType) {
        super(errorType.getMsg());
        this.code = errorType.getCode();
    }
}
