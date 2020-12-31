package com.mljr.heil.service.config.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.AutoApprRuleProp;
import com.mljr.heil.form.AutoApprRulePropForm;
import com.mljr.heil.mapper.AutoApprRulePropMapper;
import com.mljr.heil.service.config.AutoApprRulePropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 前置规则 - CA系统自动审批规则属性表
 * @Date : 2018/3/30 16:58
 * @Author : lihaitao
 */
@Service
public class AutoApprRulePropServiceImpl implements AutoApprRulePropService {
    @Autowired
    private AutoApprRulePropMapper autoApprRulePropMapper;


    @Override
    public List<AutoApprRuleProp> queryByPage(PageForm<AutoApprRulePropForm> form) {
        return autoApprRulePropMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<AutoApprRulePropForm> form) {
        return autoApprRulePropMapper.queryCount(form);
    }

    @Override
    public AutoApprRuleProp queryRecord(Integer primaryKey) {
        return autoApprRulePropMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(AutoApprRuleProp record) {
        record.setPropValueBak(record.getPropValue());
        return autoApprRulePropMapper.insertSelective(record);
    }

    @Override
    public int updateRecord(AutoApprRuleProp record) {
        record.setPropValueBak(record.getPropValue());
        return autoApprRulePropMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return autoApprRulePropMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public int modifyStatus(AutoApprRuleProp record) {
        AutoApprRuleProp prop = new AutoApprRuleProp();
        prop.setId(record.getId());
        prop.setStatus(record.getStatus());
        return autoApprRulePropMapper.updateByPrimaryKeySelective(prop);
    }
}
