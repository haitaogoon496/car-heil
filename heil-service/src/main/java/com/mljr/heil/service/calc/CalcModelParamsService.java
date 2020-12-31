package com.mljr.heil.service.calc;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.CalcModelParams;
import com.mljr.heil.form.CalcModelParamsForm;

import java.util.List;

/**
 * @description: 产品计算模型参数service
 * @Date : 2018/3/30 18:17
 * @Author : lihaitao
 */
public interface CalcModelParamsService extends BaseService<CalcModelParams,Integer,CalcModelParamsForm> {

    /**
     * 根据公式名称查询对应的参数
     * @param form
     * @return List<E>
     */
    List<CalcModelParams> getParamByFormulaCode(PageForm<CalcModelParamsForm> form);
}
