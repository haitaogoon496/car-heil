package com.mljr.heil.service.rule.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.YanbaoTc;
import com.mljr.heil.form.YanbaoTcForm;
import com.mljr.heil.mapper.YanbaoTcMapper;
import com.mljr.heil.service.rule.YanbaoTcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 第二/三年保险费规则 套餐Service
 * @Date : 下午4:26 2018/2/11
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class YanbaoTcServiceImpl implements YanbaoTcService {
    @Autowired
    private YanbaoTcMapper yanbaoTcMapper;

    @Override
    public List<YanbaoTc> queryByPage(PageForm<YanbaoTcForm> form) {
        return yanbaoTcMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<YanbaoTcForm> form) {
        return yanbaoTcMapper.queryCount(form);
    }

    @Override
    public YanbaoTc queryRecord(Integer primaryKey) {
        return yanbaoTcMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(YanbaoTc record) {
        yanbaoTcMapper.insert(record);
        return record.getId();
    }

    @Override
    public int updateRecord(YanbaoTc record) {
        return yanbaoTcMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return yanbaoTcMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public List<YanbaoTc> queryList(YanbaoTcForm form) {
        return yanbaoTcMapper.queryList(form);
    }

    @Override
    public void insertBatch(List<YanbaoTc> list) {
        yanbaoTcMapper.insertBatch(list);
    }

    @Override
    public void deleteByRuleSeq(Integer ruleSeq) {
        yanbaoTcMapper.deleteByRuleSeq(ruleSeq);
    }
}
