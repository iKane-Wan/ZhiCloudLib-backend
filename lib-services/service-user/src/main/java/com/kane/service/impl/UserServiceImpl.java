package com.kane.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kane.entity.dto.AccountAuthDTO;
import com.kane.entity.po.User;
import com.kane.entity.vo.AuthorizationVO;
import com.kane.entity.vo.CredentialsVO;
import com.kane.enums.TokenType;
import com.kane.mapper.UserMapper;
import com.kane.service.UserService;
import com.kane.utils.BeanUtils;
import com.kane.utils.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public CredentialsVO accountLogin(AccountAuthDTO accountAuthDTO) {
        // 根据手机号查询用户信息
        User user = baseMapper.selectByPhone(accountAuthDTO.getPhone());
        if (user == null)
            throw new IllegalArgumentException("用户不存在");
        // 对输入的密码进行MD5加密（加上用户盐值）
        accountAuthDTO.setPassword(DigestUtils.md5DigestAsHex((accountAuthDTO.getPassword()+user.getSalt()).getBytes()));
        if (!user.getPassword().equals(accountAuthDTO.getPassword()))
            throw new IllegalArgumentException("密码错误");
        // 将用户信息复制到授权对象
        AuthorizationVO authorizationVO = BeanUtils.copyProperties(user, AuthorizationVO.class);
        // 生成访问令牌和刷新令牌并返回
        return  new CredentialsVO()
                .setAccessToken(JwtUtils.generateToken(authorizationVO, TokenType.ACCESS_TOKEN))
                .setRefreshToken(JwtUtils.generateToken(authorizationVO, TokenType.REFRESH_TOKEN));
    }
}
