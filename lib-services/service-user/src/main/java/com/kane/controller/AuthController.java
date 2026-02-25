package com.kane.controller;
import com.kane.R;
import com.kane.entity.dto.AccountAuthDTO;
import com.kane.entity.vo.CredentialsVO;
import com.kane.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/auth")
public class AuthController {

    @Resource
    private UserService userService;

    /**
     * 账号密码登录
     * @return Jwt token
     */
    @PostMapping("/accountLogin")
    public R<CredentialsVO> accountLogin(@RequestBody @Validated AccountAuthDTO accountAuthDTO, @RequestHeader("User-Device") String device) {
        return R.success(userService.accountLogin(accountAuthDTO, device));
    }
}
