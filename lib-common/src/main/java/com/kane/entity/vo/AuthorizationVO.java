package com.kane.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 包名: com.kane.vo
 * 说明：
 * 创建时间： 2026-01-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 微信OpenID
     */
    private String openId;

    /**
     * 用户类型：1-用户，2-管理员
     */
    private Integer userType;

    /**
     * 设备信息
     */
    private String device;
}
