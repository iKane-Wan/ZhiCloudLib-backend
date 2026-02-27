package com.kane.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kane.config.RabbitMQConfig;
import com.kane.entity.bo.MessageBO;
import com.kane.entity.bo.SmsBO;
import com.kane.entity.dto.AccountAuthDTO;
import com.kane.entity.dto.UserAddDTO;
import com.kane.entity.po.User;
import com.kane.entity.vo.AuthorizationVO;
import com.kane.entity.vo.CredentialsVO;
import com.kane.enums.NotifyType;
import com.kane.enums.TokenType;
import com.kane.exception.BusinessException;
import com.kane.mapper.UserMapper;
import com.kane.service.UserService;
import com.kane.utils.BeanUtils;
import com.kane.utils.JwtUtils;
import com.kane.utils.RandomUtils;
import com.kane.utils.RedisUtils;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private RedisUtils redisTemplate;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public CredentialsVO accountLogin(AccountAuthDTO accountAuthDTO) {
        // 根据手机号查询用户信息
        User user = baseMapper.selectByPhone(accountAuthDTO.getPhone());
        if (user == null)
            throw new BusinessException(404,"用户不存在");
        // 对输入的密码进行MD5加密（加上用户盐值）
        accountAuthDTO.setPassword(DigestUtils.md5DigestAsHex((accountAuthDTO.getPassword()+user.getSalt()).getBytes()));
        if (!user.getPassword().equals(accountAuthDTO.getPassword()))
            throw new BusinessException(400,"密码错误");
        // 将用户信息复制到授权对象
        AuthorizationVO authorizationVO = BeanUtils.copyProperties(user, AuthorizationVO.class);
        // 生成访问令牌和刷新令牌并返回
        return  new CredentialsVO()
                .setAccessToken(JwtUtils.generateToken(authorizationVO, TokenType.ACCESS_TOKEN))
                .setRefreshToken(JwtUtils.generateToken(authorizationVO, TokenType.REFRESH_TOKEN));
    }

    @Override
    public void addUser(UserAddDTO userAddDTO) {
        User user = BeanUtils.copyProperties(userAddDTO, User.class);
        user.setSalt(RandomUtils.getRandomString(6));
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword()+user.getSalt()).getBytes()));
        baseMapper.insert(user);
    }

    @Override
    public void getPhoneCode(String phone) {
        String code = RandomUtils.getRandomNumber();
        redisUtils.set("library:phone:code:"+phone, code);
        MessageBO messageBO = new MessageBO();
        messageBO.setType(NotifyType.SMS);
        SmsBO smsBO = new SmsBO();
        smsBO.setMobile(phone);
        smsBO.setCode(code);
        messageBO.setObj(smsBO);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, messageBO);
    }

    @Override
    public CredentialsVO phoneLogin(String phone, String code) {
        // 检查手机号对应的验证码是否存在
        if (redisUtils.hasKey("library:phone:code:" + phone)) {
            // 验证输入的验证码是否正确
            if (redisUtils.get("library:phone:code:" + phone).equals(code)) {
                // 根据手机号查询用户信息
                User user = baseMapper.selectByPhone(phone);
                // 如果用户不存在，则创建新用户
                if (user == null) {
                    User user1 = new User();
                    user1.setPhone(phone);
                    user1.setSalt(RandomUtils.getRandomString(6)); // 生成随机盐值
                    user1.setPassword(DigestUtils.md5DigestAsHex(("123456" + user1.getSalt()).getBytes())); // 设置默认密码并加密
                    baseMapper.insert(user1); // 插入新用户数据
                    user = user1;
                }
                // 用户存在，将用户信息复制到授权对象
                AuthorizationVO authorizationVO = BeanUtils.copyProperties(user, AuthorizationVO.class);
                // 生成访问令牌和刷新令牌并返回
                return new CredentialsVO()
                        .setAccessToken(JwtUtils.generateToken(authorizationVO, TokenType.ACCESS_TOKEN))
                        .setRefreshToken(JwtUtils.generateToken(authorizationVO, TokenType.REFRESH_TOKEN));
            } else {
                // 验证码不匹配，抛出异常
                throw new BusinessException(400, "验证码错误");
            }
        } else {
            // 验证码不存在或已过期，抛出异常
            throw new BusinessException(400, "验证码已过期");
        }
    }
}
