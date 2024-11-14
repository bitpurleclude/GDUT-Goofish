package org.gdutgoodfish.goodfish.handler;

import org.gdutgoodfish.goodfish.exception.BaseException;
import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public Result userExistEX(Exception e) {
        return Result.error(e.getMessage());
    }
}
