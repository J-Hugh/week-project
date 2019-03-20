package com.hugh.authentication.core.commons.exception;

/**
 * @Auther: luoyulin1
 * @Date: 2018/12/26 16:10
 * @Description:
 */
public class BusinessException extends RuntimeException {

    /**
     * 异常code
     */
    private int code;

    public BusinessException(int code) {
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message)  {
        super(message);
        this.code = code;
    }

    public BusinessException(int code, String message, Throwable throwable)  {
        super(message, throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
