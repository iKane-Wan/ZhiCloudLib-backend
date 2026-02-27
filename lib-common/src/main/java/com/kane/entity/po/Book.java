package com.kane.entity.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

import java.util.Date;
import java.math.BigDecimal;

/**
* 图书表
* @TableName t_book
*/
@Schema(description = "图书实体")
@Data
@TableName("t_book")
public class Book implements Serializable {

    /**
    * 图书唯一标识（主键）
    */
    @TableId(type = IdType.AUTO)
    @Schema(description = "图书唯一标识（主键）")
    private Long bookId;
    /**
    * ISBN编号（唯一）
    */
    @Schema(description = "ISBN编号（唯一）")
    private String bookIsbn;
    /**
    * 图书名称
    */
    @Schema(description = "图书名称")
    private String bookName;
    /**
    * 作者
    */
    @Schema(description = "作者")
    private String author;
    /**
    * 出版社
    */
    @Schema(description = "出版社")
    private String publisher;
    /**
    * 出版日期
    */
    @Schema(description = "出版日期")
    private Date publishDate;
    /**
    * 关联图书分类表t_book_category的外键
    */
    @Schema(description = "关联图书分类表t_book_category的外键")
    private Long categoryId;
    /**
    * 子分类（如：计算机-Java）
    */
    @Schema(description = "子分类（如：计算机-Java）")
    private String subCategory;
    /**
    * 图书状态：1-可借 2-借出 3-维修 4-丢失
    */
    @Schema(description = "图书状态：1-可借 2-借出 3-维修 4-丢失")
    private Integer bookStatus;
    /**
    * 总馆藏数量
    */
    @Schema(description = "总馆藏数量")
    private Integer totalNum;
    /**
    * 可借数量
    */
    @Schema(description = "可借数量")
    private Integer availableNum;
    /**
    * 图书定价
    */
    @Schema(description = "图书定价")
    private BigDecimal price;
    /**
    * 图书简介
    */
    @Schema(description = "图书简介")
    private String introduction;
    /**
    * 封面图片地址
    */
    @Schema(description = "封面图片地址")
    private String coverUrl;
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
