package com.mljr.heil.common.exception;

/**
 * 提示性错误
 */
public class AlertException extends RuntimeException {

    int code = 400;
    String error;

    public AlertException(int code, String message) {
        super(message);
        this.code = code;
    }

    public AlertException(int code, String message, String error) {
        super(message);
        this.code = code;
        this.error = error;
    }

    public AlertException() {
        super();
    }

    public AlertException(String message) {
        super(message);
    }

    public AlertException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlertException(Throwable cause) {
        super(cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
