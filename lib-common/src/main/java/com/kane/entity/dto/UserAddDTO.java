package com.kane.entity.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserAddDTO {


    @Pattern(regexp = "^1[3-9]\\d{8}$", message = "手机号格式错误")
    @Length(min = 11, max = 11, message = "手机号长度必须为11位")
    private String phone;

    @Length(min = 6, max = 20, message = "密码长度必须在6-20之间")
    private String password;
}
