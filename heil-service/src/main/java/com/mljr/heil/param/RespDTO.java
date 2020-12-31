package com.mljr.heil.param;

/**
 * @description:
 * @Date : 2018/6/28$ 17:00$
 * @Author : liht
 */
public class RespDTO<T> {
    private static final long serialVersionUID = 1952646475069061286L;
    private int status;
    private String msg;
    private T data;

    private RespDTO() {
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
