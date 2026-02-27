package com.kane.pattern.chain;

import com.kane.entity.bo.MessageBO;
import com.kane.entity.bo.CodeBO;
import com.kane.enums.NotifyType;
import com.kane.feign.SmsFeignClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsHandler extends AbstractRegisterHandler{

    @Resource
    private EmailHandler emailHandler;

    @Resource
    private SmsFeignClient smsFeignClient;

    @Value("${sms.aliyun.templateId}")
    private String templateId;
    @Value("${sms.aliyun.smsSignId}")
    private String smsSignId;
    @Value("${sms.aliyun.appCode}")
    private String appCode;


    @Override
    public void handle(MessageBO bo) {
        if (bo.getType() != NotifyType.SMS) {
            super.setSuccessor(emailHandler); // 修正变量名
            successor.handle(bo);
            return;
        }
        try {
            CodeBO smsBO = bo.getBo();
            if (smsBO.getAccount() == null || smsBO.getCode() == null) {
                log.error("SmsBO数据不完整: mobile={}, code={}",
                        smsBO.getAccount(), smsBO.getCode());
                return;
            }
            String param = String.format("**code**:%s,**minute**:%d",
                    smsBO.getCode(), 5); // 可配置化
            log.info("准备发送短信验证码到手机号: {}", smsBO.getAccount());
            String res = smsFeignClient.sendSms(smsBO.getAccount(), templateId, smsSignId,
                    param, "APPCODE " + appCode);
            log.info("手机号：{}, 短信发送结果: {}",smsBO.getAccount(), res);
        } catch (Exception e) {
            log.error("短信发送失败: 手机号={}, 错误={}", bo.getBo().getAccount(), e.getMessage(), e);
        }
    }

}
