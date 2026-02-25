package com.kane.entity.vo;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class CredentialsVO implements Serializable {
    /**
     * 访问令牌
     */
    private String accessToken;
    /**
     * 刷新令牌
     */
    private String refreshToken;


    public CredentialsVO setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public CredentialsVO setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
