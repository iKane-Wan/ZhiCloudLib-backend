package com.kane.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.io.Serializable;

/**
 * 凭证视图对象
 */
@Schema(description = "凭证视图对象")
@Getter
public class CredentialsVO implements Serializable {
    /**
     * 访问令牌
     */
    @Schema(description = "访问令牌")
    private String accessToken;
    /**
     * 刷新令牌
     */
    @Schema(description = "刷新令牌")
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
