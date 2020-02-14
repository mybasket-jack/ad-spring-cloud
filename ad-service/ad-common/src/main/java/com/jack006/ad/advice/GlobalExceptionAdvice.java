package com.jack006.ad.advice;

import com.jack006.ad.exception.AdException;
import com.jack006.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用异常处理
 *
 * @Author jack
 * @Since 1.0 2020/2/14 13:42
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdException(HttpServletRequest request, AdException ex){
        CommonResponse<String> response = new CommonResponse<>(-1,"bussiness error");
        response.setData(ex.getMessage());
        return response;
    }
}
