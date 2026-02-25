package com.kane.entity.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 借阅记录数据传输对象
 * 用于前后端数据交互，隐藏敏感字段
 */
@Data
public class BorrowRecordDTO {
    /**
     * 借阅记录唯一标识（主键）
     */
    private Long recordId;
    /**
     * 关联图书表t_book的主键
     */
    private Long bookId;
    /**
     * 关联读者表t_reader的主键
     */
    private Long userId;
    /**
     * 借阅时间
     */
    private Date borrowTime;
    /**
     * 应归还时间
     */
    private Date dueTime;
    /**
     * 实际归还时间，未归还则为NULL
     */
    private Date returnTime;
    /**
     * 借阅状态：1-借阅中 2-已归还 3-超期 4-丢失
     */
    private Integer borrowStatus;
    /**
     * 滞纳金金额，未超期则为0
     */
    private BigDecimal fineAmount;
    /**
     * 经办人（管理员账号/姓名）
     */
    private String operator;
    /**
     * 备注（如续借、图书破损）
     */
    private String remark;
}
