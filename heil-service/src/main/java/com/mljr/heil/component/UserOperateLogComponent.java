package com.mljr.heil.component;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.dto.log.UserOperateLogDTO;
import com.mljr.heil.entity.SyUser;
import com.mljr.heil.entity.UserLog;
import com.mljr.heil.service.common.UserLogService;
import com.mljr.redis.service.RedisUtil;
import com.mljr.util.CollectionsTools;
import com.mljr.util.TimeTools;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * @description: 用户操作日志 工具类
 * @Date : 下午6:30 2018/3/2
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Component
public class UserOperateLogComponent {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserLogService userOperateLogService;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 保存操作日志
     * @param log
     */
    public Result<String> log(UserOperateLogDTO log){
        try {
            LOGGER.info("userOperateLog - log : {}", JSON.toJSON(log));
            Validator validator = new Validator();
            List<ConstraintViolation> violations = validator.validate(log);
            if (CollectionsTools.isNotEmpty(violations)) {
                return Result.failWithEmptyParam(violations.get(0).getMessage());
            }
            UserLog record = new UserLog();
            record.setUserId(log.getUserId());
            record.setUserName(log.getUserName());
            record.setAuthDate(TimeTools.format4YYYYMMDDHHMISS(new Date()));
            record.setAuthDetail(log.getAuthDetail());
            record.setAuthType(String.valueOf(log.getAuthType().getName()));
            record.setAuthModel(log.getAuthModel().getIndex());
            userOperateLogService.insertRecord(record);
        } catch (Exception e) {
            LOGGER.error("用户操作日志保存异常,form={}", JSON.toJSON(log),e);
        }
        return Result.suc();
    }
    /**
     * 保存操作日志
     * @param consumer
     */
    public Result<String> log(Consumer<UserOperateLogDTO> consumer){
        LOGGER.info("userOperateLog - consumer : {}", JSON.toJSON(consumer));
        UserOperateLogDTO log = new UserOperateLogDTO();
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            if (session == null) {
                LOGGER.warn("session 取出为空---");
                return null;
            }
            SyUser user = (SyUser) session.getAttribute("user");
            if (user == null) {
                user = redisUtil.get(session.getId(), SyUser.class);
            }
            if (user == null) {
                LOGGER.warn("user 为空,不能记录日志,请核实~~");
                return null;
            }

            log.setUserId(user.getUserId());
            log.setUserName(user.getTrueName()); //设置真实姓名
            consumer.accept(log);
            log(log);
        } catch (Exception e) {
            LOGGER.error("用户操作日志保存异常,form={}", JSON.toJSON(log),e);
        }
        return Result.suc();
    }
}
