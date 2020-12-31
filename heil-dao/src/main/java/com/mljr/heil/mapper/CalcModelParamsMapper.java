package com.mljr.heil.mapper;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.CalcModelParams;
import com.mljr.heil.form.CalcModelParamsForm;

import java.util.List;

public interface CalcModelParamsMapper extends BaseMapper<CalcModelParams,Integer,CalcModelParamsForm>{

    /**
     * 根据公式名称查询对应的参数
     * @param form
     * @return List<E>
     */
    List<CalcModelParams> getParamByFormulaCode(PageForm<CalcModelParamsForm> form);
}