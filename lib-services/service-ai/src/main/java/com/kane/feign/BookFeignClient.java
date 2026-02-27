package com.kane.feign;

import com.kane.R;
import com.kane.entity.bo.BookIntroductionBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "service-book",path = "/api/book")
public interface BookFeignClient {

    @PostMapping(value = "/introduction")
    R<String> updateBookIntroduction(@RequestBody BookIntroductionBO bo);
}
