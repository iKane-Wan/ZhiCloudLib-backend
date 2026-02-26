package com.kane.entity.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 图书业务对象
 */
@Schema(description = "图书业务对象")
@Data
public class BookBO {
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
}
