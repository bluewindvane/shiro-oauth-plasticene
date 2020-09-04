package com.maple.oauth.common.api;

/**
 * 枚举了一些常用API操作码
 */
public enum ResultCode {
    SUCCESS(200, "success"),
    FAILED(500, "fail"),
    VALIDATE_FAILED(404, "params failed"),
    UNAUTHORIZED(401, "token is unavailable or has expired"),
    FORBIDDEN(403, "no auth"),
    ACCOUNT_NOT_FOUND(500, "account is not found");

    private int code;
    private String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
