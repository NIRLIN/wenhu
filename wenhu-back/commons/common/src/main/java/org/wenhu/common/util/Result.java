package org.wenhu.common.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author CK
 * @date 2020/10/11 10:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private String code;

    private String message;

    private T data;

    public static <T> Result<T> succeed() {
        return succeed(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    public static <T> Result<T> succeed(String message) {
        return succeed(ResultCode.SUCCESS.getCode(), message, null);
    }

    public static <T> Result<T> succeed(String code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> succeed(String message, T data) {
        return succeed(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static <T> Result<T> succeed(T data) {
        return succeed(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> succeed(String code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> failed() {
        return failed(ResultCode.UNKNOWN_ERROR.getMessage());
    }

    public static <T> Result<T> failed(String message) {
        return failed(ResultCode.UNKNOWN_ERROR.getCode(), message, null);
    }

    public static <T> Result<T> failed(String code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> failed(String message, T data) {
        return failed(ResultCode.UNKNOWN_ERROR.getCode(), message, data);
    }

    public static <T> Result<T> failed(T data) {
        return succeed(ResultCode.UNKNOWN_ERROR.getCode(), "", data);
    }

    public static <T> Result<T> failed(String code, String message, T data) {
        return new Result<>(code, message, data);
    }
}
