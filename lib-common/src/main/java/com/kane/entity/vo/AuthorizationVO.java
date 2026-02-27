package com.kane.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 认证信息视图对象
 * 用于 JWT Token 中存储用户认证信息
 * 
 * @author kane
 * @version 1.0
 * @since 2026-01-17
 */
@Schema(description = "认证信息视图对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationVO implements Serializable {
    
    /**
     * 序列化版本 UID
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户 ID
     */
    @Schema(description = "用户 ID")
    private Long userId;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;

    /**
     * 微信 OpenID
     */
    @Schema(description = "微信 OpenID")
    private String openId;

    /**
     * 用户类型：1-用户，2-管理员
     */
    @Schema(description = "用户类型：1-用户，2-管理员")
    private Integer userType;

    /**
     * 设备信息
     */
    @Schema(description = "设备信息")
    private String device;
}
