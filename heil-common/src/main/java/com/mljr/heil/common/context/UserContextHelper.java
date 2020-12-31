package com.mljr.heil.common.context;


public class UserContextHelper {

    private static final ThreadLocal contextThreadLocal = new ThreadLocal();

    public static <E> E getContext(){
        return (E)contextThreadLocal.get();
    }

    public static <E> void set(E syUser){
        contextThreadLocal.set(syUser);
    }

    public static void reset(){
        contextThreadLocal.remove();
    }

}
