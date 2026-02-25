package com.kane.aspect;

import com.kane.annotation.Authorization;
import com.kane.entity.vo.CredentialsVO;
import com.kane.enums.ErrorType;
import com.kane.enums.TokenType;
import com.kane.exception.BusinessException;
import com.kane.utils.JwtUtils;
import com.kane.entity.vo.AuthorizationVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 包名: com.kane.aspect
 * 说明：
 * 创建时间： 2025-11-21
 */
@Slf4j
@Aspect
@Component
public class AuthAspect {

    /**
     * 权限校验前置方法
     * 在执行带有@Authorization注解的方法前进行拦截
     *
     * @param point 切点对象，包含目标方法的信息
     * @throws BusinessException 当用户未登录或权限不足时抛出业务异常
     */
    @Before("@annotation(com.kane.annotation.Authorization)")
    public void before(JoinPoint point) {
        // 获取目标方法对象
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        // 获取方法上的Authorization注解
        Authorization annotation = method.getAnnotation(Authorization.class);
        // 如果注解不存在，直接返回
        if (annotation == null) return;
        // 如果注解的value属性为true，进行登录校验
        if (annotation.value())
            checkLogin();
        // 如果注解的role属性不为0，进行角色权限校验
        if (annotation.role() != 0)
            checkRole(annotation.role());
    }

    /**
     * 校验用户登录状态
     * 从请求头中获取Authorization token并验证其有效性
     * 如果access token过期但refresh token有效，则生成新的access token并设置到响应头中
     *
     * @throws BusinessException 当token为空、无效或验证失败时抛出401异常
     */
    private void checkLogin() {
        // 获取当前请求的认证凭证
        CredentialsVO token = getToken();
        // 检查access token是否已过期
        if (JwtUtils.isTokenExpired(token.getAccessToken())) {
            // 检查refresh token是否仍然有效
            if (!JwtUtils.isTokenExpired(token.getRefreshToken())) {
                // 获取当前HTTP响应对象
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    HttpServletResponse response = attributes.getResponse();
                    if (response != null) {
                        // 再次确认access token确实已过期
                        if (JwtUtils.isTokenExpired(token.getAccessToken())) {
                            // 解析refresh token获取用户信息
                            AuthorizationVO authorizationVO = JwtUtils.parseToken(token.getRefreshToken());
                            // 生成新的access token并设置到响应头
                            response.setHeader("access_token", JwtUtils.generateToken(authorizationVO, TokenType.ACCESS_TOKEN));
                            // 抛出异常通知前端需要重新认证
                            throw new BusinessException(ErrorType.USER_LOGIN_EXPIRED);
                        }
                    }
                }
            } else {
                // refresh token也已过期，无法自动刷新
                throw new BusinessException(ErrorType.USER_NOT_LOGIN);
            }
        }
    }


    /**
     * 校验用户角色权限
     * 从token中解析用户信息并验证其角色等级是否满足要求
     *
     * @param role 需要的最低角色等级
     * @throws BusinessException 当用户未登录(401)或权限不足(403)时抛出业务异常
     */
    private void checkRole(int role) {
        // 获取当前请求的认证凭证
        CredentialsVO token = getToken();
        // 解析access token获取用户授权信息
        AuthorizationVO authorizationVO = JwtUtils.parseToken(token.getAccessToken());
        // 校验用户角色权限，若用户角色等级低于所需等级则抛出权限不足异常
        if (authorizationVO.getUserType() < role) {
            throw new BusinessException(ErrorType.USER_NOT_PERMITTED);
        }
    }

    /**
     * 获取access-token和refresh-token
     *
     * @return CredentialsVO 对象
     */
    private CredentialsVO getToken() {
        // 从请求上下文中获取HttpServletRequest对象
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 获取token
        // 从请求头中获取访问令牌和刷新令牌
        String authorization = request.getHeader("Authorization");
        String refreshToken = request.getHeader("Refresh-Token");
        // 校验令牌是否存在且非空，若任一令牌缺失则抛出未登录异常
        if (authorization == null || refreshToken == null ||
                authorization.isEmpty() || refreshToken.isEmpty()) {
            throw new BusinessException(ErrorType.USER_NOT_LOGIN);
        }
        // 将获取到的令牌封装到CredentialsVO对象中并返回
        return new CredentialsVO()
                .setAccessToken(authorization)
                .setRefreshToken(refreshToken);
    }
}
