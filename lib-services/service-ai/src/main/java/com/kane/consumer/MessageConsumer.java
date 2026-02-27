package com.kane.consumer;

import com.kane.config.RabbitMQConfig;
import com.kane.entity.bo.BookBO;
import com.kane.entity.bo.BookIntroductionBO;
import com.kane.feign.BookFeignClient;
import com.kane.utils.AiUtils;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    @Resource
    private BookFeignClient bookFeignClient;

    @Resource
    private AiUtils aiUtils;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
    public void handleBookMessage(BookBO book) {
        String introduction = aiUtils.callWithText(book);
        BookIntroductionBO bo = new BookIntroductionBO(book.getBookId(), introduction);
        bookFeignClient.updateBookIntroduction(bo);
    }
}
