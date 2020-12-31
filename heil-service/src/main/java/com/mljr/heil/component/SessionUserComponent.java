package com.mljr.heil.component;

import com.mljr.heil.common.consts.CommonConstant;
import com.mljr.heil.entity.SyUser;
import com.mljr.redis.service.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description: 基于session user 工具类
 * @Date : 下午6:30 2018/3/2
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Component
public class SessionUserComponent {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取session user
     * @return
     */
    public SyUser getSessionUser(){
        SyUser user = new SyUser();
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            user = redisUtil.get(session.getId(),SyUser.class);
            user.setUserId(user.getUserId());
            user.setUserName(user.getUserName());
        } catch (Exception e) {
            user.setUserId(CommonConstant.SYSTEM_USER_ID);
            user.setUserName(CommonConstant.SYSTEM_USER_NAME);
        }
        return user;
    }

    /**
     * 获取session 用户id
     * @return
     */
    public Integer getSessionUserId(){
        return getSessionUser().getUserId();
    }
    /**
     * 获取session 用户名
     * @return
     */
    public String getSessionUserName(){
        return getSessionUser().getUserName();
    }

    /**
     * 获取userId
     * @return
     */
    public Integer getUserId() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if (session == null) {
            return null;
        }
        SyUser user = (SyUser) session.getAttribute("user");
        if (user == null) {
            user = redisUtil.get(session.getId(), SyUser.class);
        }
        if (user == null) {
            return null;
        }
        return user.getUserId();
    }
}
