package com.kane.entity.bo;

import com.kane.enums.NotifyType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 消息业务对象
 * 用于在消息队列中传递通知消息
 * 
 * @author kane
 * @version 1.0
 * @since 2025-12-15
 */
@Schema(description = "消息业务对象")
@Data
public class MessageBO {
    
    /**
     * 通知类型（短信、邮件等）
     */
    @Schema(description = "通知类型")
    private NotifyType type;
    
    /**
     * 消息内容对象（如验证码信息）
     */
    @Schema(description = "消息内容对象")
    private CodeBO bo;
}
