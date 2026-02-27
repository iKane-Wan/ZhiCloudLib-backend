package com.kane.entity.bo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data

public class BookIntroductionBO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long bookId;

    private String introduction;

    public BookIntroductionBO(){}

    public BookIntroductionBO(Long bookId, String introduction){
        this.bookId = bookId;
        this.introduction = introduction;
    }

}
