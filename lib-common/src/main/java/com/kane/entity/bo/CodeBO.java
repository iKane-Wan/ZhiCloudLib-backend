package com.kane.entity.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 验证码业务对象
 * 用于传递验证码相关信息
 * 
 * @author kane
 * @version 1.0
 * @since 2025-12-15
 */
@Schema(description = "验证码业务对象")
@Data
public class CodeBO implements Serializable {
    
    /**
     * 序列化版本 UID
     */
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * 账号（手机号或邮箱）
     */
    @Schema(description = "账号（手机号或邮箱）")
    private String account;
    
    /**
     * 验证码
     */
    @Schema(description = "验证码")
    private String code;
}
