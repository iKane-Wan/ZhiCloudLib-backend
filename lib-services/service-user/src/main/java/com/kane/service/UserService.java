package com.kane.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kane.entity.dto.AccountAuthDTO;
import com.kane.entity.dto.UserAddDTO;
import com.kane.entity.po.User;
import com.kane.entity.vo.CredentialsVO;
import jakarta.validation.constraints.Pattern;

public interface UserService extends IService<User> {
    /**
     * 账号密码登录
     * @return Jwt token
     */
    CredentialsVO accountLogin(AccountAuthDTO accountAuthDTO);

    void addUser(UserAddDTO userAddDTO);

    void getPhoneCode(String phone);

    CredentialsVO phoneLogin(@Pattern(regexp = "^1[3-9]\\d{8}$", message = "手机号格式错误") String phone, String code);
}
