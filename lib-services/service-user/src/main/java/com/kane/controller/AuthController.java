package com.kane.controller;
import com.kane.R;
import com.kane.annotation.Authorization;
import com.kane.entity.dto.AccountAuthDTO;
import com.kane.entity.vo.CredentialsVO;
import com.kane.enums.ErrorType;
import com.kane.exception.BusinessException;
import com.kane.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制器
 * 处理用户认证相关的HTTP请求
 */
@Tag(name = "用户认证", description = "用户认证相关接口")
@RestController
@RequestMapping("/api/user/auth")
public class AuthController {

    @Resource
    private UserService userService;

    /**
     * 账号密码登录
     * @param accountAuthDTO 账号认证数据传输对象
     * @return Jwt token
     */
    @Operation(summary = "账号密码登录", description = "使用手机号和密码进行登录")
    @PostMapping("/accountLogin")
    public R<CredentialsVO> accountLogin(@RequestBody @Validated AccountAuthDTO accountAuthDTO) {
        return R.success(userService.accountLogin(accountAuthDTO));
    }
}
