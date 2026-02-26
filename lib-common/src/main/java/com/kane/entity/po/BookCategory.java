package com.kane.entity.po;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

import java.util.Date;
import lombok.Data;

/**
* 图书分类表
* @TableName t_book_category
*/
@Schema(description = "图书分类实体")
@Data
public class BookCategory implements Serializable {

    /**
    * 分类唯一标识（主键）
    */
    @Schema(description = "分类唯一标识（主键）")
    private Long categoryId;
    /**
    * 分类名称（如计算机、文学）
    */
    @Schema(description = "分类名称（如计算机、文学）")
    private String categoryName;
    /**
    * 父分类ID，顶级分类为0
    */
    @Schema(description = "父分类ID，顶级分类为0")
    private Long parentId;
    /**
    * 排序优先级，数值越小越靠前
    */
    @Schema(description = "排序优先级，数值越小越靠前")
    private Integer sort;
    /**
    * 分类描述
    */
    @Schema(description = "分类描述")
    private String description;
    /**
    * 创建时间
    */
    @Schema(description = "创建时间")
    private Date createTime;
    /**
    * 更新时间
    */
    @Schema(description = "更新时间")
    private Date updateTime;
    /**
    * 逻辑删除标识：0-未删除 1-已删除
    */
    @Schema(description = "逻辑删除标识：0-未删除 1-已删除")
    private Integer deleteFlag;
}
