package com.mljr.heil.interceptor;

import com.alibaba.fastjson.JSON;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.exception.AlertException;
import com.mljr.heil.common.util.HttpUtils;
import com.mljr.heil.common.util.SystemUtil;
import com.mljr.heil.entity.SyUser;
//import com.mljr.munchee.dto.TokenDto;
//import com.mljr.munchee.interceptor.LoginInterceptor;
import com.mljr.redis.service.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @description: 用户Session拦截器
 * @Date : 2019/2/28 上午10:47
 * @Author : 石冬冬-Seig Heil
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HandlerContextInterceptor implements HandlerInterceptor{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 把用户sesssion存储到redis，设置30分钟过期时间
     */
    private final long SESSION_EXPIRE = 30 * 60;

    @Autowired
    private RedisUtil redisUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String token = request.getHeader("token");
        //支持postMan测试使用
        if("suze".equals(token)){
            return true;
        }
        String ip = HttpUtils.getRemoteIP(request);
        request.setAttribute("ip", ip);
        logger.info("ip:[{}] method:{}:uri:{},执行开始", ip,request.getMethod(), request.getRequestURI());
//        TokenDto tokenDto = LoginInterceptor.getUserInfo(request);
//        logger.info("调用梦琪登录返回结果:{}", JSON.toJSON(tokenDto));
//        if (tokenDto == null || StringUtils.isEmpty(tokenDto.getUserId())) {
//            logger.info("当前用户没有登录~已拦截");
//            throw new AlertException(HeilCode.E_401, "请登录");
//        }

        HttpSession session = request.getSession();
//        SyUser syUser = getUserFromToken(tokenDto);
//        if (session.getAttribute("user") != null|| redisUtil.get(session.getId(), SyUser.class) != null) {
//            logger.info("登录用户id:{},userInfo:{}",syUser.getUserId(), JSON.toJSON(syUser));
//            return true;
//        }
//        String systemCode = SystemUtil.SYSTEM_CODE;
//        session.setAttribute("systemCode", systemCode);
//        session.setAttribute("user", syUser);
//        session.setAttribute("userId", syUser.getUserId());
//        redisUtil.set(session.getId(),syUser,SESSION_EXPIRE);
//        logger.info("用户信息已保存redis,登录用户id:{},userInfo:{}",syUser.getUserId(), JSON.toJSON(syUser));
        return true;
    }

//    private SyUser getUserFromToken(TokenDto tokenDto) {
//        SyUser syUser = new SyUser();
//        Integer systemUserId = Integer.parseInt(tokenDto.getSystemUserId());
//        syUser.setUserId(systemUserId);
//        syUser.setPhone(tokenDto.getPhone());
//        syUser.setEmail(tokenDto.getEmail());
//        syUser.setUserName(tokenDto.getUserName());
//        syUser.setTrueName(tokenDto.getUserName());
//        syUser.setUserType("Y");// YOOLI用户
//        return syUser;
//    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
