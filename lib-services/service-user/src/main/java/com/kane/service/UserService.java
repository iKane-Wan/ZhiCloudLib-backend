package com.kane.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kane.entity.dto.AccountAuthDTO;
import com.kane.entity.dto.UserAddDTO;
import com.kane.entity.po.User;
import com.kane.entity.vo.CredentialsVO;
import jakarta.validation.constraints.Pattern;

/**
 * 用户服务接口
 * 定义用户相关的业务逻辑方法
 */
public interface UserService extends IService<User> {
    /**
     * 账号密码登录
     * 验证用户手机号和密码，生成 JWT Token
     * @param accountAuthDTO 账号认证数据传输对象
     * @return 凭证对象，包含访问令牌和刷新令牌
     */
    CredentialsVO accountLogin(AccountAuthDTO accountAuthDTO);

    /**
     * 添加用户
     * 注册新用户，对密码进行加密存储
     * @param userAddDTO 用户添加数据传输对象
     */
    void addUser(UserAddDTO userAddDTO);

    /**
     * 获取手机验证码
     * 生成随机验证码并发送到用户手机
     * @param phone 手机号
     */
    void getPhoneCode(String phone);

    /**
     * 手机验证码登录
     * 验证手机号和验证码，登录或注册新用户
     * @param phone 手机号
     * @param code 验证码
     * @return 凭证对象，包含访问令牌和刷新令牌
     */
    CredentialsVO phoneLogin(@Pattern(regexp = "^1[3-9]\\d{8}$", message = "手机号格式错误") String phone, String code);
}
