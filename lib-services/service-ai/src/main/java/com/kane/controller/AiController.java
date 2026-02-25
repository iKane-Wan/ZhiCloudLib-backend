package com.kane.controller;

import com.kane.R;
import com.kane.entity.bo.BookBO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @PostMapping
    public R<String> generateBookDescription(@RequestBody BookBO bookBO) {
        return R.success();
    }
}
