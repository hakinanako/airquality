package com.neu.airquality.handler;

import com.neu.airquality.common.BaseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public BaseResult<String> handlerException(Exception e) {
        e.printStackTrace();
        return BaseResult.fail(e.getMessage());
    }
}
