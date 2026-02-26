package com.kane.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 包名: com.kane.dto
 * 说明：账号认证DTO
 * 创建时间： 2026-01-17
 */
@Schema(description = "账号认证数据传输对象")
@Data
public class AccountAuthDTO {
    /**
     * 账号
     */
    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;
    /**
     * 密码
     */
    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(min = 6, max = 18, message = "密码长度必须在6到18个字符之间")
    private String password;
}
