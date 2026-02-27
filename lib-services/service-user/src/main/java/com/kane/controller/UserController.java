package com.kane.controller;

import com.kane.R;
import com.kane.entity.dto.UserAddDTO;
import com.kane.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 * 处理用户相关的 HTTP 请求
 */
@Tag(name = "用户管理", description = "用户相关接口")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param userAddDTO 用户添加数据传输对象
     * @return 操作结果
     */
    @Operation(summary = "用户注册", description = "注册新用户账号")
    @PostMapping("/register")
    public R<String> register(@Validated @RequestBody UserAddDTO userAddDTO) {
        userService.addUser(userAddDTO);
        return R.success("注册成功");
    }
}
