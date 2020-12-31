package com.mljr.heil.common.context;


import javax.servlet.http.HttpSession;

public class UserSessionContextHelper {

    private static final ThreadLocal<HttpSession> contextThreadLocal = new ThreadLocal();

    public static HttpSession getContext(){
        return contextThreadLocal.get();
    }

    public static void set(HttpSession session){
        contextThreadLocal.set(session);
    }

    public static void reset(){
        contextThreadLocal.remove();
    }

}
