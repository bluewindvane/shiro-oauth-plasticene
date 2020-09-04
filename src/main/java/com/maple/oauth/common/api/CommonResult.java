package com.maple.oauth.common.api;

/**
 * 通用返回对象
 */
public class CommonResult<T> {
    private int code;
    private String message;
    private T result;

    protected CommonResult() {
    }

    protected CommonResult(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    protected CommonResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 成功返回结果
     *
     * @param result 获取的数据
     */
    public static <T> CommonResult<T> success(T result) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), result);
    }

    /**
     * 成功返回结果
     *
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }


    /**
     * 成功返回结果
     *
     * @param result  获取的数据
     * @param message 提示信息
     */
    public static <T> CommonResult<T> success(T result, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, result);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> CommonResult<T> failed(ResultCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T result) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), result);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T result) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), result);
    }

    public static <T> CommonResult<T> wrap(int code, String message, T o) {
        return new CommonResult<T>(code, message, o);
    }

    public static <T> CommonResult<T> wrap(int code, String message) {
        return new CommonResult<T>(code, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getresult() {
        return result;
    }

    public void setresult(T result) {
        this.result = result;
    }
}
