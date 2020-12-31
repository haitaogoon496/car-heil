package com.mljr.heil.service.rule;

import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.YanbaoTc;
import com.mljr.heil.form.YanbaoTcForm;

import java.util.List;

/**
 * @description: 第二/三年保险费规则 套餐Service
 * @Date : 下午3:33 2018/2/11
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface YanbaoTcService extends BaseService<YanbaoTc,Integer,YanbaoTcForm> {
    /**
     * 查询套餐
     * @param form
     * @return
     */
    List<YanbaoTc> queryList(YanbaoTcForm form);
    /**
     * 批量插入
     * @param list
     */
    void insertBatch(List<YanbaoTc> list);
    /**
     * 根据规则id删除相关记录
     * @param ruleSeq
     */
    void deleteByRuleSeq(Integer ruleSeq);
}
