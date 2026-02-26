package com.kane;

import com.kane.enums.ErrorType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 包名: com.kane.com.kane.utils
 * 说明：统一返回结果类
 * 创建时间： 2025-12-15
 */
@Schema(description = "统一返回结果")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "状态码")
    private Integer code;
    @Schema(description = "消息")
    private String msg;
    @Schema(description = "数据")
    private T data;

    public static R<String> success() {
        return new R<>(200, "success", null);
    }

    public static <T> R<T> success(T data) {
        return new R<>(200, "success", data);
    }

    public static R<String> success(String msg) {
        return new R<>(200, msg, null);
    }

    public static <T> R<T> success(String msg, T data) {
        return new R<>(200, msg, data);
    }

    public static R<String> error(Integer code, String msg) {
        return new R<>(code, msg, null);
    }

    public static R<String> error(ErrorType errorType) {
        return new R<>(errorType.getCode(), errorType.getMsg(), null);
    }
}
