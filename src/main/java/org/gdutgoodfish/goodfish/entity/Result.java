package org.gdutgoodfish.goodfish.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(200);
        result.setData(data);
        return result;
    }
    public static <T> Result<T> success(T data, String msg) {
        Result<T> result = new Result<T>();
        result.setCode(200);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }
    public static <T> Result<T> success(String msg) {
        Result<T> result = new Result<T>();
        result.setCode(200);
        result.setMsg(msg);
        return result;
    }
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<T>();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }
}
