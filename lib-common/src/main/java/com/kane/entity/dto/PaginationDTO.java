package com.kane.entity.dto;

import lombok.Data;

@Data
public class PaginationDTO {
    /**
     * 当前页码
     */
    private int pageNow;
    
    /**
     * 每页显示条数
     */
    private int pageSize;
    
    /**
     * 图书名称
     */
    private String bookName;
    
    /**
     * 作者姓名
     */
    private String author;
    
    /**
     * 图书ISBN号
     */
    private String bookIsbn;
    
    /**
     * 分类ID
     */
    private Long categoryId;
    
    /**
     * 子分类名称
     */
    private String subCategory;
}
