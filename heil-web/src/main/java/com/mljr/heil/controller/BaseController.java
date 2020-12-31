package com.mljr.heil.controller;

import com.alibaba.fastjson.JSON;
import com.mljr.redis.service.RedisUtil;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.exception.AlertException;
import com.mljr.heil.entity.SyUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * @Date : 2018/9/25$ 14:23$
 * @Author : liht
 */
@Slf4j
public abstract class BaseController {

    @Autowired
    private RedisUtil redisUtil;


    public SyUser getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user == null) {
            String sessionId = session.getId();
            user = redisUtil.get(sessionId,SyUser.class);
            if (user == null) {
                log.info("当前用户没有登录~已拦截");
                throw new AlertException(HeilCode.E_401, "请登录");
            }
        }
        SyUser syUser = (SyUser) user;
        return syUser;
    }

    public int getUserId(HttpServletRequest request) {
        SyUser syUser = getUser(request);
        if (syUser == null) {
            throw new AlertException(HeilCode.E_401, "请登录");
        }
        return syUser.getUserId();
    }
}
