package com.kane.entity.po;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

import java.util.Date;
import lombok.Data;

/**
* 基础认证表
* @TableName t_user
*/
@Schema(description = "用户实体")
@Data
public class User implements Serializable {

    /**
    * 用户唯一ID（主键）
    */
    @Schema(description = "用户唯一ID（主键）")
    private Long userId;
    /**
    * 手机号（唯一）
    */
    @Schema(description = "手机号（唯一）")
    private String phone;
    /**
    * 邮箱（唯一）
    */
    @Schema(description = "邮箱（唯一）")
    private String email;
    /**
    * 加密密码（MD5）
    */
    @Schema(description = "加密密码（MD5）")
    private String password;
    /**
    * 盐值
    */
    @Schema(description = "盐值")
    private String salt;
    /**
    * 微信小程序openid
    */
    @Schema(description = "微信小程序openid")
    private String openId;
    /**
    * 用户类型：0-用户，1-管理员
    */
    @Schema(description = "用户类型：0-用户，1-管理员")
    private Integer userType;
    /**
    * 状态：0-正常，1-禁用
    */
    @Schema(description = "状态：0-正常，1-禁用")
    private Integer status;
    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    private Date createTime;
    /**
    * 更新时间
    */
    @Schema(description = "更新时间")
    private Date updateTime;

}
