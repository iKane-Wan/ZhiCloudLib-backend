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
 * AI控制器
 * 处理AI相关的HTTP请求
 */
@Tag(name = "AI服务", description = "AI相关接口")
@RestController
@RequestMapping("/api/ai")
public class AiController {

    /**
     * 生成图书描述
     * @param bookBO 图书业务对象
     * @return 生成的描述
     */
    @Operation(summary = "生成图书描述", description = "根据图书信息生成AI描述")
    @PostMapping
    public R<String> generateBookDescription(@RequestBody BookBO bookBO) {
        return R.success();
    }
}
