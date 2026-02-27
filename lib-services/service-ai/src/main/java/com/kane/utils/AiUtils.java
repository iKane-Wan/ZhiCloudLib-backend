package com.kane.utils;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.kane.entity.bo.BookBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class AiUtils {

    @Value("${alibaba.bigModel.api-key}")
    private String apiKey;

    private GenerationResult callWithMessage(String text) throws ApiException, NoApiKeyException, InputRequiredException {
        Generation gen = new Generation();
        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content("You are a librarian, and your job is to write book descriptions.")
                .build();
        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content(text)
                .build();
        GenerationParam param = GenerationParam.builder()
                // 若没有配置环境变量，请用百炼API Key将下行替换为：.apiKey("sk-xxx")
                .apiKey(apiKey)
                // 此处以qwen-plus为例，可按需更换模型名称。模型列表：https://help.aliyun.com/zh/model-studio/getting-started/models
                .model("qwen-plus")
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();
        return gen.call(param);
    }

    public String callWithText(BookBO bo) {
        StringBuilder sb = new StringBuilder();
        String prompt = sb.append("Could you please help me write a brief introduction to a book titled ")
                .append(bo.getBookName())
                .append(" by ")
                .append(bo.getAuthor())
                .append(" published by ")
                .append(bo.getPublisher())
                .append("on")
                .append(bo.getPublishDate())
                .append(" with ISBN number ")
                .append(bo.getBookIsbn())
                .append(".You only need to generate a brief introduction to this book, without generating any other words.")
                .toString();
        try {
            GenerationResult result = callWithMessage(prompt);
            return result.getOutput().getChoices().getFirst().getMessage().getContent();
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            // 使用日志框架记录异常信息
            log.error("An error occurred while calling the generation service: " + e.getMessage());
        }
        return "生成失败，请重新生成";
    }
}
