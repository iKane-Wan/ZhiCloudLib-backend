package com.kane.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 包名: com.kane.vo
 * 说明：认证信息视图对象
 * 创建时间： 2026-01-17
 */
@Schema(description = "认证信息视图对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;

    /**
     * 微信OpenID
     */
    @Schema(description = "微信OpenID")
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
