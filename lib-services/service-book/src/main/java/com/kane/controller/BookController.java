package com.kane.controller;


import com.kane.R;
import com.kane.annotation.Authorization;
import com.kane.entity.bo.BookIntroductionBO;
import com.kane.entity.dto.BookDTO;
import com.kane.entity.dto.PaginationDTO;
import com.kane.entity.vo.PaginationVO;
import com.kane.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 图书控制器
 * 处理图书相关的HTTP请求
 */
@Tag(name = "图书管理", description = "图书相关接口")
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Resource
    private BookService bookService;

    /**
     * 添加图书
     * @param bookDTO 图书数据传输对象
     * @return R
     */
    @Operation(summary = "添加图书", description = "添加新图书到系统中")
    @PostMapping
    @Authorization(role = 1)
    public R<String> addBook(@RequestBody BookDTO bookDTO){
        bookService.addBook(bookDTO);
        return R.success("添加成功");
    }

    /**
     * 删除图书
     * @param id 图书ID
     * @return R
     */
    @Operation(summary = "删除图书", description = "根据ID删除图书")
    @Authorization(role = 1)
    @DeleteMapping("/{id}")
    public R<String> deleteBook(@Parameter(description = "图书ID") @PathVariable Long id){
        bookService.removeById(id);
        return R.success("删除成功");
    }

    /**
     * 更新图书
     * @param bookDTO 图书数据传输对象
     * @return R
     */
    @Operation(summary = "更新图书", description = "更新图书信息")
    @PutMapping
    @Authorization(role = 1)
    public R<String> updateBook(@RequestBody BookDTO bookDTO){
        bookService.updateBook(bookDTO);
        return R.success("更新成功");
    }

    /**
     * 分页查询图书列表
     * @param dto 分页查询条件
     * @return 分页结果
     */
    @Operation(summary = "分页查询图书列表", description = "根据条件分页查询图书列表")
    @GetMapping
    public R<PaginationVO<BookDTO>> listBook(PaginationDTO dto){
        PaginationVO<BookDTO> bookDTOPaginationVO = bookService.listBook(dto);
        return R.success(bookDTOPaginationVO);
    }

    /**
     * 根据ID获取图书详情
     * @param id 图书ID
     * @return 图书详情
     */
    @Operation(summary = "获取图书详情", description = "根据ID获取图书详细信息")
    @Authorization
    @GetMapping("/{id}")
    public R<BookDTO> getBook(@Parameter(description = "图书ID") @PathVariable Long id){
        BookDTO bookDTO = bookService.getBook(id);
        return R.success(bookDTO);
    }

    /**
     * 更新图书简介,内部接口不对外暴露
     * @param bo 图书简介数据传输对象
     * @return R
     */
    @PostMapping("/introduction")
    public R<String> updateBookIntroduction(@RequestBody  BookIntroductionBO bo){
        bookService.updateBookIntroduction(bo);
        return R.success("更新成功");
    }
}
