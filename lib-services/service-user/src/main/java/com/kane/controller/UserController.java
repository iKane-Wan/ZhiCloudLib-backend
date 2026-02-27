package com.kane.controller;

import com.kane.R;
import com.kane.entity.dto.UserAddDTO;
import com.kane.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public R<String> register(@Validated @RequestBody UserAddDTO userAddDTO) {
        userService.addUser(userAddDTO);
        return R.success();
    }
}
