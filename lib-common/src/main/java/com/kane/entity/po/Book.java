package com.kane.entity.po;


import lombok.Data;

import java.io.Serializable;

import java.util.Date;
import java.math.BigDecimal;

/**
* 图书表
* @TableName t_book
*/
@Data
public class Book implements Serializable {

    /**
    * 图书唯一标识（主键）
    */
    private Long bookId;
    /**
    * ISBN编号（唯一）
    */
    private String bookIsbn;
    /**
    * 图书名称
    */
    private String bookName;
    /**
    * 作者
    */
    private String author;
    /**
    * 出版社
    */
    private String publisher;
    /**
    * 出版日期
    */
    private Date publishDate;
    /**
    * 关联图书分类表t_book_category的外键
    */
    private Long categoryId;
    /**
    * 子分类（如：计算机-Java）
    */
    private String subCategory;
    /**
    * 图书状态：1-可借 2-借出 3-维修 4-丢失
    */
    private Integer bookStatus;
    /**
    * 总馆藏数量
    */
    private Integer totalNum;
    /**
    * 可借数量
    */
    private Integer availableNum;
    /**
    * 图书定价
    */
    private BigDecimal price;
    /**
    * 图书简介
    */
    private String introduction;
    /**
    * 封面图片地址
    */
    private String coverUrl;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 逻辑删除标识：0-未删除 1-已删除
    */
    private Integer deleteFlag;
}
