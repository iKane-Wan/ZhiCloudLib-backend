package com.kane.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kane.enums.ErrorType;
import com.kane.enums.TokenType;
import com.kane.exception.BusinessException;
import com.kane.entity.vo.AuthorizationVO;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 包名: com.kane.utils
 * 说明：Jwt工具类
 * 创建时间： 2025-12-16
 */
@Slf4j
public class JwtUtils {

    public static final String JWT_SECRET = "kittyrun-system-secret";

    public static final long ACCESS_JWT_EXPIRE = 1000;// * 60 * 30;

    public static final long REFRESH_JWT_EXPIRE = 1000 * 60 * 60 * 24 * 7;

    /**
     * 生成JWT令牌
     *
     * @param auth 授权信息对象
     * @return 生成的JWT令牌字符串
     */
    public static String generateToken(AuthorizationVO auth, TokenType type) {
        //把auth对象转为JSON字符串
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(auth);
        } catch (JsonProcessingException e) {
            log.error("json processing error: {}", e.getMessage());
            throw new BusinessException(ErrorType.FAIL);
        }
        if (type == TokenType.ACCESS_TOKEN) {
            return JWT.create()
                    .withClaim("auth", json)
                    // 签发时间
                    .withIssuedAt(new Date())
                    // 过期时间
                    .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_JWT_EXPIRE))
                    // 签名
                    .sign(Algorithm.HMAC256(JWT_SECRET));
        } else {
            return JWT.create()
                    .withClaim("auth", json)
                    // 签发时间
                    .withIssuedAt(new Date())
                    // 过期时间
                    .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_JWT_EXPIRE))
                    // 签名
                    .sign(Algorithm.HMAC256(JWT_SECRET));
        }
    }

    /**
     * 解析JWT令牌
     *
     * @param token JWT令牌字符串
     * @return 解析后的授权信息对象
     * @throws BusinessException 当令牌无效或未登录时抛出
     */
    public static AuthorizationVO parseToken(String token) {
        verify(token);
        // 获取令牌中的Claim
        String json = JWT.decode(token).getClaim("auth").asString();
        // 把JSON字符串转为auth对象
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, AuthorizationVO.class);
        } catch (JsonProcessingException e) {
            log.error("json processing error: {}", e.getMessage());
            throw new BusinessException(ErrorType.FAIL);
        }
    }

    /**
     * 验证JWT令牌是否有效
     *
     * @param token JWT令牌字符串
     * @throws BusinessException 当令牌为空、为空串或验证失败时抛出
     */
    public static boolean verify(String token) {
        //判断token是否为空或为空串
        if (token == null || token.isEmpty())
            return false;
        //判断token是否正确
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_SECRET)).build();
        try {
            DecodedJWT jwt = verifier.verify(token);
        } catch (Exception e) {
            return  false;
        }
        return true;
    }

    /**
     * 验证JWT令牌是否过期
     *
     * @param token JWT令牌字符串
     * @return true表示已过期，false表示未过期
     */
    public static boolean isTokenExpired(String token) {
        if (token == null || token.isEmpty()) {
            return true;
        }
        try {
            DecodedJWT jwt = JWT.decode(token);
            Date expiresAt = jwt.getExpiresAt();
            if (expiresAt == null) {
                return true;
            }
            return expiresAt.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }


}
