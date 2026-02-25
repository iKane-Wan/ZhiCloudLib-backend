package com.kane.entity.bo;

import lombok.Data;

import java.util.Date;

@Data
public class BookBO {
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
}
