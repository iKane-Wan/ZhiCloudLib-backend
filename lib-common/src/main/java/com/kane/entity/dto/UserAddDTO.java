package com.kane.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 用户添加数据传输对象
 * 用于用户注册时接收和验证用户输入
 * 
 * @author kane
 * @version 1.0
 * @since 2025-12-15
 */
@Schema(description = "用户添加数据传输对象")
@Data
public class UserAddDTO {

    /**
     * 手机号
     * 必须符合中国大陆手机号格式（1 开头，第二位 3-9，共 11 位）
     */
    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp = "^1[3-9]\\d{8}$", message = "手机号格式错误")
    @Length(min = 11, max = 11, message = "手机号长度必须为 11 位")
    private String phone;

    /**
     * 密码
     * 长度必须在 6-20 个字符之间
     */
    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @Length(min = 6, max = 20, message = "密码长度必须在 6-20 之间")
    private String password;
}
