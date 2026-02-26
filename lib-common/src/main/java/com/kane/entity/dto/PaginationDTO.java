package com.kane.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分页查询数据传输对象
 */
@Schema(description = "分页查询数据传输对象")
@Data
public class PaginationDTO {
    /**
     * 当前页码
     */
    @Schema(description = "当前页码", defaultValue = "1")
    private int pageNow;
    
    /**
     * 每页显示条数
     */
    @Schema(description = "每页显示条数", defaultValue = "10")
    private int pageSize;
    
    /**
     * 图书名称
     */
    @Schema(description = "图书名称")
    private String bookName;
    
    /**
     * 作者姓名
     */
    @Schema(description = "作者姓名")
    private String author;
    
    /**
     * 图书ISBN号
     */
    @Schema(description = "图书ISBN号")
    private String bookIsbn;
    
    /**
     * 分类ID
     */
    @Schema(description = "分类ID")
    private Long categoryId;
    
    /**
     * 子分类名称
     */
    @Schema(description = "子分类名称")
    private String subCategory;
}
