package com.mljr.heil.service.config.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.AutoApprRule;
import com.mljr.heil.entity.AutoApprRuleKey;
import com.mljr.heil.form.AutoApprRuleForm;
import com.mljr.heil.mapper.AutoApprRuleMapper;
import com.mljr.heil.service.config.AutoApprRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 前置规则 - CA系统自动审批规则表
 * @Date : 2018/3/30 16:58
 * @Author : lihaitao
 */
@Service
public class AutoApprRuleServiceImpl implements AutoApprRuleService {
    @Autowired
    private AutoApprRuleMapper autoApprRuleMapper;


    @Override
    public List<AutoApprRule> queryByPage(PageForm<AutoApprRuleForm> form) {
        return autoApprRuleMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<AutoApprRuleForm> form) {
        return autoApprRuleMapper.queryCount(form);
    }

    @Override
    public AutoApprRule queryRecord(AutoApprRuleKey primaryKey) {
        return autoApprRuleMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(AutoApprRule record) {
        autoApprRuleMapper.insertSelective(record);
        return 1;
    }

    @Override
    public int updateRecord(AutoApprRule record) {
        return autoApprRuleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(AutoApprRuleKey primaryKey) {
        return autoApprRuleMapper.deleteByPrimaryKey(primaryKey);
    }

}
