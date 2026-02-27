package com.kane.pattern.chain;

import com.kane.entity.bo.MessageBO;
import com.kane.enums.NotifyType;
import org.springframework.stereotype.Component;

@Component
public class EmailHandler extends  AbstractRegisterHandler{
    @Override
    public void handle(MessageBO bo) {
        if (bo.getType() != NotifyType.EMAIL){
            return;
        }else {
            System.out.println("发送邮件");
        }
    }
}
