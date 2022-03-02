package com.weiran.token.advice;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.weiran.token.enums.ResponseEnum;
import com.weiran.token.response.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AuthorizationExceptionAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(JWTDecodeException.class)
    public ResultVO nonceExpiredException(JWTDecodeException exception) {
        return ResultVO.fail(ResponseEnum.TOKEN_PARSING_ERROR);
    }

}
