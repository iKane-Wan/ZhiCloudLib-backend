package com.kane.feign;

import com.kane.R;
import com.kane.entity.bo.BookIntroductionBO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 图书 Feign 客户端
 * 用于 AI 服务调用图书服务的接口
 */
@FeignClient(name = "service-book", path = "/api/book")
@Tag(name = "图书服务 Feign 客户端")
public interface BookFeignClient {

    /**
     * 更新图书简介
     * 供 AI 服务调用，更新图书的 AI 生成简介
     * @param bo 图书简介业务对象
     * @return 操作结果
     */
    @PostMapping(value = "/introduction")
    @Operation(summary = "更新图书简介", description = "更新图书的 AI 生成简介")
    R<String> updateBookIntroduction(@RequestBody BookIntroductionBO bo);
}
