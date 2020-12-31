package com.mljr.heil.common.util;

import com.mljr.util.PropertiesReader;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 系统工具类
 * @Date : 上午11:28 2018/3/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class SystemUtil {
    public static final String SYSTEM_CODE = PropertiesReader.getProperties("config").getProperty("system_code");
    /**
     * 判断是否直营
     * @return
     */
    public static boolean isZy(){
        return "100000".equals(SYSTEM_CODE);
    }
    /**
     * 判断是否渠道
     * @return
     */
    public static boolean isQd(){
        return "200000".equals(SYSTEM_CODE);
    }
    /**
     * 获取用户IP
     * @param request
     * @return
     */
    public static String getRemoteIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    /**
     * 获取用户浏览器代理对象
     * @param request
     * @return
     */
    public static UserAgent getUserAgent(HttpServletRequest request){
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        return userAgent;
    }

    /**
     * 获取浏览器
     * @param request
     * @return
     */
    public static String getBrowser(HttpServletRequest request){
        UserAgent userAgent = getUserAgent(request);
        return userAgent.getBrowser().getName();
    }
}
