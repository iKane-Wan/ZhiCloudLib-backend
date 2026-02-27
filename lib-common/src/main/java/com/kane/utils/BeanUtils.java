package com.kane.utils;

import com.kane.enums.ErrorType;
import com.kane.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * Bean 工具类
 * 提供对象属性复制功能，基于 Spring BeanUtils 实现
 * 用于在不同层之间转换对象（如 DTO、VO、PO 等）
 * 
 * @author kane
 * @version 1.0
 * @since 2025-12-16
 */
@Slf4j
public class BeanUtils<T> {

    /**
     * 复制对象属性
     * 将源对象的属性值复制到目标对象
     * 
     * @param source 源对象
     * @param target 目标对象的 Class
     * @param <T> 目标对象类型
     * @return 复制后的目标对象实例
     * @throws BusinessException 当目标对象实例化失败时抛出
     */
    public static <T> T copyProperties(Object source, Class<T> target) {
        T t;
        try {
            // 通过反射创建目标对象实例
            t = target.getConstructor().newInstance();
        } catch (Exception e) {
            // 记录错误日志并抛出业务异常
            log.error("Bean copy properties error: {}", e.getMessage());
            throw new BusinessException(ErrorType.FAIL);
        }
        // 使用 Spring 的 BeanUtils 进行属性复制
        org.springframework.beans.BeanUtils.copyProperties(source, t);
        return t;
    }
}
