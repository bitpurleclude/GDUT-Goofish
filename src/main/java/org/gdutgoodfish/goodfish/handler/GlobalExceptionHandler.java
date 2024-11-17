package org.gdutgoodfish.goodfish.handler;

import org.gdutgoodfish.goodfish.exception.BaseException;
import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public Result<String> userExistEX(Exception e) {
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Result<String> unkownException(Exception e) {
        return Result.error("发生未知错误");
    }
}
