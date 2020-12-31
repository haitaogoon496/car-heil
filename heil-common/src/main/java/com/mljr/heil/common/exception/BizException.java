package com.mljr.heil.common.exception;
/**
 * @Author：rongss
 * @Description
 * @Date：Created in 下午10:40 2018/1/28
 */
public class BizException extends RuntimeException{

    private Integer code;
    private String msg;


    public BizException() {
        super();
    }

    public BizException(String msg) {
        super(msg);
    }

    public BizException(Integer code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}