package com.kane.utils;

import com.kane.enums.ErrorType;
import com.kane.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * 包名: com.kane.utils
 * 说明：Bean工具类
 * 创建时间： 2025-12-16
 */
@Slf4j
public class BeanUtils<T> {

    public static <T> T copyProperties(Object source, Class<T> target) {
        T t;
        try {
            t = target.getConstructor().newInstance();
        } catch (Exception e) {
            log.error("Bean copy properties error: {}", e.getMessage());
            throw new BusinessException(ErrorType.FAIL);
        }
        org.springframework.beans.BeanUtils.copyProperties(source, t);
        return t;
    }
}
