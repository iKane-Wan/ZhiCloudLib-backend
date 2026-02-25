package com.kane.entity.po;

import java.io.Serializable;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;

/**
* 借阅记录表
* @TableName t_borrow_record
*/
@Data
public class BorrowRecord implements Serializable {

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
    /**
    * 记录创建时间
    */
    private Date createTime;
    /**
    * 记录更新时间
    */
    private Date updateTime;
    /**
    * 逻辑删除标识：0-未删除 1-已删除
    */
    private Integer deleteFlag;
}
