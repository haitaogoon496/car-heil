package com.mljr.heil.service.rule.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.GpsRule;
import com.mljr.heil.form.GpsRuleForm;
import com.mljr.heil.mapper.GpsRuleMapper;
import com.mljr.heil.service.rule.GpsRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: GPS规则配置Service
 * @Date : 下午6:14 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class GpsRuleServiceImpl implements GpsRuleService {
    @Autowired
    private GpsRuleMapper gpsRuleMapper;


    @Override
    public List<GpsRule> queryByPage(PageForm<GpsRuleForm> form) {
        return gpsRuleMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<GpsRuleForm> form) {
        return gpsRuleMapper.queryCount(form);
    }

    @Override
    public GpsRule queryRecord(Integer primaryKey) {
        return gpsRuleMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(GpsRule record) {
        gpsRuleMapper.insertSelective(record);
        return record.getRuleSeq();
    }

    @Override
    public int updateRecord(GpsRule record) {
        return gpsRuleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return gpsRuleMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public List<GpsRule> queryList(GpsRuleForm form) {
        return gpsRuleMapper.queryList(form);
    }

    @Override
    public List<GpsRule> queryRulesUnBindProduct() {
        return gpsRuleMapper.queryRulesUnBindProduct();
    }
}
