package com.kane.entity.bo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 图书简介业务对象
 * 用于 AI 服务生成图书简介后传递给图书服务
 * 
 * @author kane
 * @version 1.0
 * @since 2025-12-15
 */
@Data
public class BookIntroductionBO implements Serializable {
    
    /**
     * 序列化版本 UID
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 图书 ID
     */
    private Long bookId;

    /**
     * 图书简介内容
     */
    private String introduction;

    /**
     * 默认构造函数
     */
    public BookIntroductionBO(){}

    /**
     * 带参构造函数
     * 
     * @param bookId 图书 ID
     * @param introduction 图书简介内容
     */
    public BookIntroductionBO(Long bookId, String introduction){
        this.bookId = bookId;
        this.introduction = introduction;
    }

}
