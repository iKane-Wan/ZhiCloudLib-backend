package com.kane.controller;
import com.kane.R;
import com.kane.annotation.Authorization;
import com.kane.entity.dto.AccountAuthDTO;
import com.kane.entity.vo.CredentialsVO;
import com.kane.enums.ErrorType;
import com.kane.exception.BusinessException;
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
    public R<CredentialsVO> accountLogin(@RequestBody @Validated AccountAuthDTO accountAuthDTO) {
        return R.success(userService.accountLogin(accountAuthDTO));
    }

    @GetMapping("/hello")
    @Authorization
    public R<String> hello() {
        throw new BusinessException(55255,"asdasddfas");
//        return R.success("hello");
    }
}
