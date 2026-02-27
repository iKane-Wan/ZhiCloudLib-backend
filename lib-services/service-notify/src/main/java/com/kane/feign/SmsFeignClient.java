package com.kane.feign;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 短信 Feign 客户端
 * 用于调用第三方短信服务 API
 */
@FeignClient(name = "sms-service", url = "https://gyytz.market.alicloudapi.com/sms")
@Tag(name = "短信服务 Feign 客户端")
public interface SmsFeignClient {

    /**
     * 发送短信
     * 调用阿里云短信服务发送验证码或通知短信
     * @param mobile 接收短信的手机号
     * @param templateId 短信模板 ID
     * @param signName 短信签名
     * @param param 短信参数（如验证码）
     * @param token API 授权令牌
     * @return 发送结果
     */
    @PostMapping("/smsSend")
    @Operation(summary = "发送短信", description = "调用阿里云短信服务发送短信")
    String sendSms(@RequestParam("mobile") String mobile,
                   @RequestParam("templateId") String templateId,
                   @RequestParam("smsSignId") String signName,
                   @RequestParam("param") String param,
                   @RequestHeader("Authorization") String token);
}
