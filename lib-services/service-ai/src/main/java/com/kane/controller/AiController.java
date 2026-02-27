package com.kane.controller;

import com.kane.R;
import com.kane.entity.bo.BookBO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AI 控制器
 * 处理 AI 相关的 HTTP 请求
 */
@Tag(name = "AI 服务", description = "AI 相关接口")
@RestController
@RequestMapping("/api/ai")
public class AiController {

    /**
     * 生成图书描述
     * 调用 AI 服务根据图书信息生成图书简介
     * @param bookBO 图书业务对象，包含图书基本信息
     * @return 操作结果
     */
    @Operation(summary = "生成图书描述", description = "根据图书信息调用 AI 生成图书描述")
    @PostMapping
    public R<String> generateBookDescription(@RequestBody BookBO bookBO) {
        return R.success("生成成功");
    }
}
