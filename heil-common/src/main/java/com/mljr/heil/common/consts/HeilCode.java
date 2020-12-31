package com.mljr.heil.common.consts;

/**
 * Author : LI-JIAN
 * Date   : 2017-02-21
 * 全局code定义
 */
public class HeilCode {

    public static final int SUCCESS = 0;//成功处理
    public static final int E_100 = 100;//继续,请求者应当继续提出请求
    public static final int E_204 = 204;//无数据
    public static final int E_400 = 400;//错误请求/处理失败
    public static final int E_401 = 401;//（未授权） 请求要求身份验证, 认证失败，需要登录(APP使用)
    public static final int E_403 = 403;//（禁止） 服务器拒绝请求
    public static final int E_409 = 409;//冲突
    public static final int E_500 = 500;//系统异常
    public static final int E_502 = 502;//Bad Gateway
    public static final int E_503 = 503;//Service Unavailable
    public static final int E_504 = 504;//Gateway Timeout
}
