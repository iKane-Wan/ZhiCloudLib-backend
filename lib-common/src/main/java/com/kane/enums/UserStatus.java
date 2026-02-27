package com.kane.enums;

/**
 * 用户状态枚举
 * 用于表示用户的账号状态（正常、禁用等）
 * 
 * @author kane
 * @version 1.0
 * @since 2025-12-15
 */
public enum UserStatus {
    /**
     * 正常状态
     */
    NORMAL(0),
    /**
     * 禁用状态
     */
    DISABLED(1);
    
    /**
     * 状态值
     */
    private Integer value;
    
    /**
     * 构造函数
     * 
     * @param value 状态值
     */
    UserStatus(Integer value) {
        this.value = value;
    }
    
    /**
     * 获取状态值
     * 
     * @return 状态值
     */
    public Integer getValue() {
        return value;
    }
}
