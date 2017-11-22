package com.ck.utils;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Result<T> implements Serializable {

    protected int code;

    protected String message;

    protected T data;

    /* 提供快捷方法 */
    public static <T> Result<T> ok() {
        return Result.<T>builder().code(ResultCode.OK).build();
    }

    public static <T> Result<T> ok(String message) {
        return Result.<T>builder().code(ResultCode.OK).message(message).build();
    }

    public static <T> Result<T> ok(T data) {
        return Result.<T>builder().code(ResultCode.OK).data(data).build();
    }

    public static <T> Result<T> data(T data) {
        return ok(data);
    }

    public static interface ResultCode {

        int OK = 200;

        int BAD_REQUEST = 400;
        int UNAUTHENTICATED = 401;
        int UNAUTHORIZED = 403;

    }

}


