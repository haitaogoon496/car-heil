package com.mljr.heil.mapper;

import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.YanbaoTc;
import com.mljr.heil.form.YanbaoTcForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 第二/三年保险费规则 套餐 配置
 * @Date : 2018/2/11 下午3:29
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface YanbaoTcMapper extends BaseMapper<YanbaoTc,Integer,YanbaoTcForm>{
    /**
     * 批量插入
     * @param list
     */
    void insertBatch(List<YanbaoTc> list);
    /**
     * 根据规则id删除相关记录
     * @param ruleSeq
     */
    void deleteByRuleSeq(@Param("ruleSeq") Integer ruleSeq);
}