package com.kane.exception;

import com.kane.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 使用 @RestControllerAdvice 替代 @ControllerAdvice，确保返回 JSON 格式
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     * @param e 异常对象
     * @return 统一响应结果
     */
    @ExceptionHandler(BusinessException.class)
    public R<String> handleBusinessException(BusinessException e) {
        log.warn("业务异常: code={}, message={}", e.getCode(), e.getMessage());
        return R.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理所有其他异常
     * @param e 异常对象
     * @return 统一响应结果
     */
    @ExceptionHandler(Exception.class)
    public R<String> handleException(Exception e) {
        log.error("系统异常: {}", e.getMessage(), e);
        return R.error(500, "系统错误");
    }
}
