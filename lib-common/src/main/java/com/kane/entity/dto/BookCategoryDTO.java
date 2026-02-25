package com.kane.entity.dto;

import lombok.Data;

/**
 * 图书分类数据传输对象
 * 用于前后端数据交互，隐藏敏感字段
 */
@Data
public class BookCategoryDTO {
    /**
     * 分类唯一标识（主键）
     */
    private Long categoryId;
    /**
     * 分类名称（如计算机、文学）
     */
    private String categoryName;
    /**
     * 父分类ID，顶级分类为0
     */
    private Long parentId;
    /**
     * 排序优先级，数值越小越靠前
     */
    private Integer sort;
    /**
     * 分类描述
     */
    private String description;
}
