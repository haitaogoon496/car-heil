package com.mljr.heil.mapper;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.base.mapper.BaseMapper;
/**
 * @description: 第二/三年保险费规则配置 门店配置
 * @Date : 2018/2/11 下午3:29
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface YanbaoDealerMapper extends BaseMapper<BaseDealerRes,BaseDealerRes,DealerQueryForm> {

    /**
     * 根据规则id删除相关记录
     * @param ruleId 关联主键id
     * @return
     */
    int deleteByRuleId(Integer ruleId);
}