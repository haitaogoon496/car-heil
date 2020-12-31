package com.mljr.heil.service.config;

import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.AutoApprRuleProp;
import com.mljr.heil.form.AutoApprRulePropForm;

/**
 * @description: 前置规则 - CA系统自动审批规则属性表
 * @Date : 2018/3/30 16:57
 * @Author : lihaitao
 */
public interface AutoApprRulePropService extends BaseService<AutoApprRuleProp,Integer,AutoApprRulePropForm> {
    int modifyStatus(AutoApprRuleProp record);
}
