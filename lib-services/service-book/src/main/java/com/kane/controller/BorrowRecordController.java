package com.kane.controller;

import com.kane.R;
import com.kane.annotation.Authorization;
import com.kane.entity.dto.BorrowRecordDTO;
import com.kane.entity.dto.PaginationDTO;
import com.kane.entity.vo.PaginationVO;
import com.kane.service.BorrowRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 借阅记录控制器
 * 处理图书借阅相关的HTTP请求
 */
@Tag(name = "借阅记录管理", description = "图书借阅相关接口")
@RestController
@RequestMapping("/api/book/borrowRecord")
public class BorrowRecordController {

    @Resource
    private BorrowRecordService borrowRecordService;

    /**
     * 借阅图书
     *
     * @param dto 借阅记录数据传输对象
     * @return 操作结果
     */
    @Operation(summary = "借阅图书", description = "登记图书借阅记录")
    @PostMapping
    @Authorization(role = 1)
    public R<String> borrowBook(@RequestBody BorrowRecordDTO dto) {
        borrowRecordService.borrowBook(dto);
        return R.success("借阅成功");
    }

    /**
     * 归还图书
     *
     * @param recordId 借阅记录ID
     * @return 操作结果
     */
    @Operation(summary = "归还图书", description = "登记图书归还")
    @PutMapping("/return/{recordId}")
    @Authorization(role = 1)
    public R<String> returnBook(@Parameter(description = "借阅记录ID") @PathVariable Long recordId) {
        borrowRecordService.returnBook(recordId);
        return R.success("归还成功");
    }

    /**
     * 续借图书
     *
     * @param recordId 借阅记录ID
     * @param days     续借天数，默认为30天
     * @return 操作结果
     */
    @Operation(summary = "续借图书", description = "续借图书，延长归还时间")
    @PutMapping("/renew/{recordId}")
    @Authorization(role = 1)
    public R<String> renewBook(@Parameter(description = "借阅记录ID") @PathVariable Long recordId,
                               @Parameter(description = "续借天数") @RequestParam(defaultValue = "30") Integer days) {
        borrowRecordService.renewBook(recordId, days);
        return R.success("续借成功");
    }

    /**
     * 分页查询借阅记录列表
     *
     * @param dto 分页查询条件
     * @return 分页结果
     */
    @Operation(summary = "分页查询借阅记录", description = "分页查询所有借阅记录列表")
    @GetMapping
    @Authorization(role = 1)
    public R<PaginationVO<BorrowRecordDTO>> listRecord(@RequestBody PaginationDTO dto) {
        PaginationVO<BorrowRecordDTO> result = borrowRecordService.listRecord(dto);
        return R.success(result);
    }

    /**
     * 获取当前用户借阅记录列表
     *
     * @param token 访问令牌
     * @param dto 分页查询条件
     * @return 分页结果
     */
    @Operation(summary = "获取当前用户借阅记录", description = "获取当前登录用户的借阅记录列表")
    @GetMapping("/currentUser")
    @Authorization
    public R<PaginationVO<BorrowRecordDTO>> listCurrentUserRecord(@RequestHeader("Authorization") String token, @RequestBody PaginationDTO dto) {
        PaginationVO<BorrowRecordDTO> result = borrowRecordService.listCurrentUserRecord(token,dto);
        return R.success(result);
    }

    /**
     * 根据ID获取借阅记录详情
     *
     * @param id 记录ID
     * @return 借阅记录详情
     */
    @Operation(summary = "获取借阅记录详情", description = "根据ID获取借阅记录详细信息")
    @GetMapping("/{id}")
    @Authorization
    public R<BorrowRecordDTO> getRecord(@Parameter(description = "借阅记录ID") @PathVariable Long id) {
        BorrowRecordDTO dto = borrowRecordService.getRecord(id);
        return R.success(dto);
    }
}
