package com.maple.oauth.configutation;


import com.maple.oauth.common.api.CommonResult;
import com.maple.oauth.common.api.ResultCode;
import com.maple.oauth.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * description: 全局的的异常拦截器<br>
 * version: 1.0 <br>
 * date: 2019/2/14 11:19 <br>
 * author: vnaLc <br>
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数非法异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult authorityException(BaseException e) {
        log.info("保存全局异常信息 ex={}", e.getMessage(), e);
        return CommonResult.wrap(e.getCode(), e.getMessage(), null);
    }

    /**
     * 请求体验证拦截
     *
     * @param e
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public CommonResult<Object> MethodArgumentNotValidException(ConstraintViolationException e) {
        String result = e.getMessage();
        return CommonResult.failed(result);
    }

    @ExceptionHandler({AuthenticationException.class})
    public CommonResult<Object> AuthenticationException(AuthenticationException e) {
        return CommonResult.wrap(ResultCode.ACCOUNT_NOT_FOUND.getCode(), ResultCode.ACCOUNT_NOT_FOUND.getMessage());
    }

    @ExceptionHandler({AuthorizationException.class})
    public CommonResult<Object> AuthorizationException(AuthorizationException e) {
        return CommonResult.wrap(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage());
    }

    /**
     * 全局异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult exception(Exception e) {
        log.info("保存全局异常信息 ex={}", e.getMessage(), e);
        return CommonResult.failed(e.getMessage());
    }

}
