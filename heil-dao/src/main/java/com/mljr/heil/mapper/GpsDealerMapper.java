package com.mljr.heil.mapper;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.base.mapper.BaseMapper;

import java.util.List;
/**
 * @description: GPS规则配置 门店管理 Mapper
 * @Date : 2018/2/11 下午3:27
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface GpsDealerMapper extends BaseMapper<BaseDealerRes,BaseDealerRes,DealerQueryForm> {
    /**
     * 根据主表规则id查询相应配置门店
     * @param ruleId 规则id
     * @return 门店code列表集合
     */
    List<Integer> queryDealerCodesByRuleId(Integer ruleId);
    /**
     * 根据规则id删除相关记录
     * @param ruleId 关联主键id
     * @return
     */
    int deleteByRuleId(Integer ruleId);
    /**
     * 批量插入(没有即插入，有的话不做处理)
     * @param baseDealerResList
     * @return
     */
    int batchInsertIgnore(List<BaseDealerRes> baseDealerResList);
}