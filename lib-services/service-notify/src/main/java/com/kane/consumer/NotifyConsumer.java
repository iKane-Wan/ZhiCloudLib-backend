package com.kane.consumer;

import com.kane.config.RabbitMQConfig;
import com.kane.entity.bo.MessageBO;
import com.kane.pattern.chain.SmsHandler;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotifyConsumer {

    @Resource
    private SmsHandler smsHandler;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME,containerFactory = "rabbitListenerContainerFactory")
    public void handelNotifyConsume(MessageBO bo) {
        smsHandler.handle(bo);
    }
}
