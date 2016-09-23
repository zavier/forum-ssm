package com.forum.util.entity;

import javax.validation.UnexpectedTypeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class UnifyExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(UnifyExceptionHandler.class);
    
    /**
     * bean校验未通过异常
     */
    @ExceptionHandler(UnexpectedTypeException.class)
    private String illegalParamsExceptionHandler(UnexpectedTypeException e) {
        log.error("--------->请求参数不合法!", e);
        return "error/error";
    }
}
