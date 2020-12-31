package com.mljr.heil.mapper;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.form.SerFinDealerForm;
import com.mljr.heil.form.SerFinRuleForm;

import java.util.List;

public interface SerFinDealerMapper extends BaseMapper<BaseDealerRes,BaseDealerRes,SerFinRuleForm>{

    /**
     *  删除平台费规则下的门店
     * @param form
     * @return
     */
    int deleteByParams(SerFinDealerForm form);
    /**
     * 批量插入(没有即插入，有的话不做处理)
     * @param baseDealerResList
     * @return
     */
    int batchInsertIgnore(List<BaseDealerRes> baseDealerResList);

}