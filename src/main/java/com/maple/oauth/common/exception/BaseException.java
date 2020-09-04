package com.maple.oauth.common.exception;

import cn.hutool.core.util.StrUtil;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 6057602589533840889L;

    /**
     * 状态
     */
    private int code;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }

    public BaseException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(int code, Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    public BaseException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
