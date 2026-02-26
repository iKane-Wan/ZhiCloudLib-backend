package com.kane.controller;

import com.kane.R;
import com.kane.annotation.Authorization;
import com.kane.entity.dto.BookCategoryDTO;
import com.kane.service.BookCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图书分类控制器
 * 处理图书分类相关的HTTP请求
 */
@Tag(name = "图书分类管理", description = "图书分类相关接口")
@RestController
@RequestMapping("/api/book/category")
public class BookCategoryController {

    @Resource
    private BookCategoryService bookCategoryService;

    /**
     * 添加图书分类
     *
     * @param dto 分类数据传输对象
     * @return 操作结果
     */
    @Operation(summary = "添加图书分类", description = "添加新图书分类")
    @PostMapping
    @Authorization(role = 1)
    public R<String> addCategory(@RequestBody BookCategoryDTO dto) {
        bookCategoryService.addCategory(dto);
        return R.success("添加成功");
    }

    /**
     * 删除图书分类
     *
     * @param id 分类ID
     * @return 操作结果
     */
    @Operation(summary = "删除图书分类", description = "根据ID删除图书分类")
    @DeleteMapping("/{id}")
    @Authorization(role = 1)
    public R<String> deleteCategory(@Parameter(description = "分类ID") @PathVariable Long id) {
        bookCategoryService.removeById(id);
        return R.success("删除成功");
    }

    /**
     * 更新图书分类
     *
     * @param dto 分类数据传输对象
     * @return 操作结果
     */
    @Operation(summary = "更新图书分类", description = "更新图书分类信息")
    @PutMapping
    @Authorization(role = 1)
    public R<String> updateCategory(@RequestBody BookCategoryDTO dto) {
        bookCategoryService.updateCategory(dto);
        return R.success("更新成功");
    }

    /**
     * 查询所有图书分类列表
     *
     * @return 分类列表
     */
    @Operation(summary = "查询所有图书分类", description = "获取所有图书分类列表")
    @GetMapping
    public R<List<BookCategoryDTO>> listAllCategory() {
        List<BookCategoryDTO> list = bookCategoryService.listAllCategory();
        return R.success(list);
    }

    /**
     * 根据ID获取图书分类详情
     *
     * @param id 分类ID
     * @return 分类详情
     */
    @Operation(summary = "获取图书分类详情", description = "根据ID获取图书分类详细信息")
    @GetMapping("/{id}")
    @Authorization
    public R<BookCategoryDTO> getCategory(@Parameter(description = "分类ID") @PathVariable Long id) {
        BookCategoryDTO dto = bookCategoryService.getCategory(id);
        return R.success(dto);
    }
}
