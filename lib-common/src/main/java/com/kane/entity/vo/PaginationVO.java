package com.kane.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 包名: com.kane.dto
 * 说明：分页数据传输对象
 * 创建时间： 2026-01-23
 */
@Data
public class PaginationVO<T>  implements Serializable {
    /**
     * 总记录数
     */
    private Long total;
    
    /**
     * 当前页码
     */
    private Long pageNow;
    
    /**
     * 每页大小
     */
    private Long pageSize;

    /**
     * 总页数
     */
    private Long totalPage;
    
    /**
     * 分页数据
     */
    private List<T> data;
}
