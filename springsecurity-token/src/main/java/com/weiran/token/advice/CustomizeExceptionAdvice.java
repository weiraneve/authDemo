package com.weiran.token.advice;

import com.weiran.token.exception.CustomizeException;
import com.weiran.token.response.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CustomizeExceptionAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CustomizeException.class)
    public ResultVO nonceExpiredException(CustomizeException exception) {
        return ResultVO.fail(exception.getResponseEnum());
    }
}
