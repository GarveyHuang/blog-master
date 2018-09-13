package com.jax.blog.handler;

import com.jax.blog.exception.BusinessException;
import com.jax.blog.utils.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 统一异常处理
 * @Author huangjw
 * @Date 2018/9/13 10:10
 * @Version 1.0
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public APIResponse businessException(Exception e) {
        String msg = "请求错误";
        if(e instanceof BusinessException) {
            msg = ((BusinessException) e).getErrorCode();
        }
        LOGGER.error("find exception:e={}", e.getMessage());
        e.printStackTrace();
        return APIResponse.fail(msg);
    }
}
