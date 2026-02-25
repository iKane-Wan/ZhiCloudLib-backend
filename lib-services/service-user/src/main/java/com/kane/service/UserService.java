package com.kane.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kane.entity.dto.AccountAuthDTO;
import com.kane.entity.po.User;
import com.kane.entity.vo.CredentialsVO;

public interface UserService extends IService<User> {
    /**
     * 账号密码登录
     * @return Jwt token
     */
    CredentialsVO accountLogin(AccountAuthDTO accountAuthDTO, String device);
}
