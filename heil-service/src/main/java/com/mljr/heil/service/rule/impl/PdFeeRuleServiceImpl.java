package com.mljr.heil.service.rule.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.common.enums.ApplyRuleTableEnum;
import com.mljr.heil.entity.PdFeeRule;
import com.mljr.heil.form.PdFeeRuleForm;
import com.mljr.heil.form.RuleStatusForm;
import com.mljr.heil.mapper.PdFeeRuleMapper;
import com.mljr.heil.service.rule.PdFeeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: GPS规则配置Service
 * @Date : 下午6:14 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class PdFeeRuleServiceImpl implements PdFeeRuleService {
    @Autowired
    private PdFeeRuleMapper pdFeeRuleMapper;

    @Override
    public List<PdFeeRule> queryByPage(PageForm<PdFeeRuleForm> form) {
        return pdFeeRuleMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<PdFeeRuleForm> form) {
        return pdFeeRuleMapper.queryCount(form);
    }

    @Override
    public PdFeeRule queryRecord(Integer primaryKey) {
        return pdFeeRuleMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(PdFeeRule record) {
        pdFeeRuleMapper.insertSelective(record);
        return record.getId();
    }

    @Override
    public int updateRecord(PdFeeRule record) {
        return pdFeeRuleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return pdFeeRuleMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public List<PdFeeRule> queryRulesUnBindProduct(Integer classify) {
        return pdFeeRuleMapper.queryRulesUnBindProduct(classify);
    }

    @Override
    public int modifyStatus(RuleStatusForm record) {
        if(record.getClassify() != null) {
            record.setTableName(ApplyRuleTableEnum.getNameByIndex(record.getClassify()));
        }
        return pdFeeRuleMapper.modifyStatus(record);
    }

    @Override
    public List<PdFeeRule> queryList(PdFeeRuleForm form) {
        return pdFeeRuleMapper.queryList(form);
    }
}
