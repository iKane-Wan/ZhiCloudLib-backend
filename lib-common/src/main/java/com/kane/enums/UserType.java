package com.kane.enums;

/**
 * 用户类型枚举
 * 用于区分普通用户和管理员
 * 
 * @author kane
 * @version 1.0
 * @since 2025-12-15
 */
public enum UserType {
    /**
     * 普通用户，具有基本的借阅权限
     */
    USER(0),
    /**
     * 管理员，具有图书管理、用户管理等高级权限
     */
    ADMIN(1);
    
    /**
     * 用户类型值
     */
    private Integer value;
    
    /**
     * 构造函数
     * 
     * @param value 用户类型值
     */
    UserType(Integer value) {
        this.value = value;
    }
    
    /**
     * 获取用户类型值
     * 
     * @return 用户类型值
     */
    public Integer getValue() {
        return value;
    }
}
