package com.kane.pattern.chain;

import com.kane.entity.bo.MessageBO;
import com.kane.entity.bo.SmsBO;
import com.kane.enums.NotifyType;
import com.kane.feign.SmsFeignClient;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsHandler extends AbstractRegisterHandler{

    @Resource
    private EmailHandler emailHandel;

    @Resource
    private SmsFeignClient smsFeignClient;

    @Value("${sms.aliyun.smsSignId}")
    private String templateId;
    @Value("${sms.aliyun.templateId}")
    private String smsSignId;
    @Value("${sms.aliyun.appCode}")
    private String appCode;


    @Override
    public void handle(MessageBO bo) {

        if (bo.getType() != NotifyType.SMS){
            super.setSuccessor(emailHandel);
            successor.handle(bo);
        }else{
            SmsBO smsBO = null;
            if (bo.getObj() instanceof SmsBO){
                smsBO = (SmsBO) bo.getObj();
            }
            if (smsBO == null) return;
            String param = "**code**:"+smsBO.getCode()+",**minute**:5";
            smsFeignClient.sendSms(smsBO.getMobile(),templateId,smsSignId,param,appCode);
        }
    }
}
