package com.kane.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sms-service",url = "https://gyytz.market.alicloudapi.com/sms")
public interface SmsFeignClient {

    @PostMapping("/smsSend")
    String sendSms(@RequestParam("mobile") String mobile,
                   @RequestParam("templateId") String templateId,
                   @RequestParam("smsSignId") String signName,
                   @RequestParam("param") String param,
                   @RequestHeader("Authorization") String token);
}
