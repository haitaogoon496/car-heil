package com.mljr.heil.component;

import com.mljr.heil.base.entity.BaseEntity;
import com.mljr.heil.common.consts.CommonConstant;
import com.mljr.heil.entity.SyUser;
import com.mljr.redis.service.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.function.Consumer;

/**
 * @description: entity设置工具类
 * @Date : 下午4:15 2018/3/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Component
public class EntityComponent {
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 设置entity 创建人、创建时间相关信息
     * @param entity
     */
    public void setCreateInfo(BaseEntity entity){
        setCreateInfo(entity,syUser -> {});
    }
    /**
     * 设置entity 创建人、创建时间相关信息
     * @param entity
     * @param consumer
     */
    public void setCreateInfo(BaseEntity entity, Consumer<SyUser> consumer){
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            SyUser user = redisUtil.get(session.getId(),SyUser.class);
            entity.setUserId(user.getUserId());
            entity.setUserName(user.getUserName());
            consumer.accept(user);
        } catch (Exception e) {
            entity.setUserId(CommonConstant.SYSTEM_USER_ID);
            entity.setUserName(CommonConstant.SYSTEM_USER_NAME);
        }
    }
}
