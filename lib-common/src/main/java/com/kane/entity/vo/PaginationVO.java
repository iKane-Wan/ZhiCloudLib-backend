package com.kane.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 包名: com.kane.dto
 * 说明：分页视图对象
 * 创建时间： 2026-01-23
 */
@Schema(description = "分页视图对象")
@Data
public class PaginationVO<T>  implements Serializable {
    /**
     * 总记录数
     */
    @Schema(description = "总记录数")
    private Long total;
    
    /**
     * 当前页码
     */
    @Schema(description = "当前页码")
    private Long pageNow;
    
    /**
     * 每页大小
     */
    @Schema(description = "每页大小")
    private Long pageSize;

    /**
     * 总页数
     */
    @Schema(description = "总页数")
    private Long totalPage;
    
    /**
     * 分页数据
     */
    @Schema(description = "分页数据")
    private List<T> data;
}
