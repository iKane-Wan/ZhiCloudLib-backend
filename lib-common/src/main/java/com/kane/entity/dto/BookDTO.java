package com.kane.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 图书数据传输对象
 */
@Schema(description = "图书数据传输对象")
@Data
public class BookDTO {
    /**
     * 图书唯一标识（主键）
     */
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
}
