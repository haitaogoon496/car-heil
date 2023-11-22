package com.mljr.heil.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.mljr.heil.common.consts.CommonConstant;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.exception.AlertException;
import com.mljr.heil.common.util.HttpUtils;
import com.mljr.heil.common.util.SystemUtil;
import com.mljr.heil.config.CheckResult;
import com.mljr.heil.entity.SyUser;
//import com.mljr.munchee.dto.TokenDto;
//import com.mljr.munchee.interceptor.LoginInterceptor;
import com.mljr.redis.service.RedisUtil;
import io.jsonwebtoken.*;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

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
        JWTCreator.Builder builder = JWT.create();
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    public static String createJWT(String id, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)   // 主题
                .setIssuer("Java1234")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey); // 签名算法以及密匙
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate); // 过期时间
        }
        return builder.compact();
    }

    /**
     * 验证JWT
     * @param jwtStr
     * @return
     */
    public static CheckResult validateJWT(String jwtStr) {
        CheckResult checkResult = new CheckResult();
        Claims claims = null;
        try {
            claims = parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
            checkResult.setErrCode(CommonConstant.JWT_ERRCODE_EXPIRE);
            checkResult.setSuccess(false);
        } catch (SignatureException e) {
            checkResult.setErrCode(CommonConstant.JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        } catch (Exception e) {
            checkResult.setErrCode(CommonConstant.JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    /**
     * 生成加密Key
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(CommonConstant.JWT_SECERT);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析JWT字符串
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
