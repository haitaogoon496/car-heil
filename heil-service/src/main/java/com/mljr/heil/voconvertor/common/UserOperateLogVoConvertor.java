package com.mljr.heil.voconvertor.common;

import com.lyqc.base.enums.UserOperateLogConstant;
import com.mljr.heil.entity.UserLog;
import com.mljr.heil.vo.common.UserLogVo;
import com.mljr.heil.voconvertor.VoConvertor;
import org.springframework.beans.BeanUtils;

/**
 * @description:
 * @Date : 2018/3/30$ 16:50$
 * @Author : liht
 */
public class UserOperateLogVoConvertor extends VoConvertor<UserLogVo,UserLog> {
    @Override
    public UserLogVo convert(UserLog bo) {
        UserLogVo vo = new UserLogVo();
        BeanUtils.copyProperties(bo,vo);
        vo.setAuthModelDesc(bo.getAuthModel()!=null?UserOperateLogConstant.AuthModelEnumForProduct.getNameByIndex(bo.getAuthModel()):"");
        return vo;
    }
}
