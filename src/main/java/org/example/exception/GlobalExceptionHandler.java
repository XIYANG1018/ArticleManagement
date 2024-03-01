package org.example.exception;

import org.springframework.util.StringUtils;
import org.example.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // handle exception and response
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e) {
        e.printStackTrace();
        // if the error message is not null and its length is not 0, then show the message, otherwise show "Operation failed"
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage() : "Operation failed");
    }
}
