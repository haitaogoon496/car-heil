package com.mljr.heil.service.calc.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.CalcFormulaParams;
import com.mljr.heil.form.CalcFormulaParamsForm;
import com.mljr.heil.mapper.CalcFormulaParamsMapper;
import com.mljr.heil.service.calc.CalcFormulaParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lingyu.shang
 */
@Service
public class CalcFormulaParamsServiceImpl implements CalcFormulaParamsService {

    @Autowired
    private CalcFormulaParamsMapper calcFormulaParamsMapper;

    @Override
    public List<CalcFormulaParams> queryByPage(PageForm<CalcFormulaParamsForm> form) {
        return calcFormulaParamsMapper.queryByPage(form);
    }

    @Override
    public int queryCount(PageForm<CalcFormulaParamsForm> form) {
        return calcFormulaParamsMapper.queryCount(form);
    }

    @Override
    public List<Integer> queryParamIdListByFormulaCode(String formulaCode) {
        return calcFormulaParamsMapper.queryParamIdListByFormulaCode(formulaCode);
    }

    @Override
    public int deleteByFormulaCode(String formulaCode) {
        return calcFormulaParamsMapper.deleteByFormulaCode(formulaCode);
    }

    @Override
    public int batchInsert(List<CalcFormulaParams> list) {
        return calcFormulaParamsMapper.batchInsert(list);
    }

}
