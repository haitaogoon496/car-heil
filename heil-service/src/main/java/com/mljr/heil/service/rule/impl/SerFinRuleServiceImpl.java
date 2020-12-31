package com.mljr.heil.service.rule.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.SerFinRule;
import com.mljr.heil.form.SerFinRuleForm;
import com.mljr.heil.mapper.SerFinRuleMapper;
import com.mljr.heil.service.rule.SerFinRuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 平台费利率配置
 * @Date : 2018/2/11$ 17:01$
 * @Author : liht
 */
@Service
public class SerFinRuleServiceImpl implements SerFinRuleService {

    @Resource
    private SerFinRuleMapper serFinRuleMapper;

    @Override
    public List<SerFinRule> queryByPage(PageForm<SerFinRuleForm> form) {
        List<SerFinRule> list = serFinRuleMapper.pageQuery(form);
        return list;
    }

    @Override
    public int queryCount(PageForm<SerFinRuleForm> form) {
        return serFinRuleMapper.queryCount(form);
    }

    @Override
    public SerFinRule queryRecord(Integer primaryKey) {
        return serFinRuleMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(SerFinRule record) {
        serFinRuleMapper.insertSelective(record);
        return record.getRuleSeq();
    }

    @Override
    public int updateRecord(SerFinRule record) {
        return serFinRuleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return serFinRuleMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public List<SerFinRule> queryList(SerFinRuleForm form) {
        return serFinRuleMapper.queryList(form);
    }

    @Override
    public List<SerFinRule> queryRulesByProductId(Integer pId) {
        return serFinRuleMapper.queryRulesByProductId(pId);
    }

    @Override
    public List<SerFinRule> queryRulesUnBindProduct() {
        return serFinRuleMapper.queryRulesUnBindProduct();
    }

}
