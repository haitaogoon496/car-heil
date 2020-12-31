package com.mljr.heil.service.rule.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.YanbaoRule;
import com.mljr.heil.form.YanbaoRuleForm;
import com.mljr.heil.mapper.YanbaoRuleMapper;
import com.mljr.heil.service.rule.YanbaoRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 第二/三年保险费规则Service
 * @Date : 下午3:34 2018/2/11
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class YanbaoRuleServiceImpl implements YanbaoRuleService {
    @Autowired
    private YanbaoRuleMapper yanbaoRuleMapper;
    @Override
    public List<YanbaoRule> queryByPage(PageForm<YanbaoRuleForm> form) {
        return yanbaoRuleMapper.pageQuery(form);
    }

    @Override
    public List<YanbaoRule> queryList(YanbaoRuleForm form) {
        return yanbaoRuleMapper.queryList(form);
    }

    @Override
    public int queryCount(PageForm<YanbaoRuleForm> form) {
        return yanbaoRuleMapper.queryCount(form);
    }

    @Override
    public YanbaoRule queryRecord(Integer primaryKey) {
        return yanbaoRuleMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(YanbaoRule record) {
        yanbaoRuleMapper.insertSelective(record);
        return record.getRuleSeq();
    }

    @Override
    public int updateRecord(YanbaoRule record) {
        return yanbaoRuleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return yanbaoRuleMapper.deleteByPrimaryKey(primaryKey);
    }

}
