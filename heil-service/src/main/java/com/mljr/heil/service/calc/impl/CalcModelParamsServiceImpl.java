package com.mljr.heil.service.calc.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.CalcModelParams;
import com.mljr.heil.form.CalcModelParamsForm;
import com.mljr.heil.mapper.CalcModelParamsMapper;
import com.mljr.heil.service.calc.CalcModelParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 产品计算模型参数
 * @Date : 2018/3/30 16:58
 * @Author : lihaitao
 */
@Service
public class CalcModelParamsServiceImpl implements CalcModelParamsService {
    @Autowired
    private CalcModelParamsMapper calcModelParamsMapper;


    @Override
    public List<CalcModelParams> queryByPage(PageForm<CalcModelParamsForm> form) {
        return calcModelParamsMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<CalcModelParamsForm> form) {
        return calcModelParamsMapper.queryCount(form);
    }

    @Override
    public CalcModelParams queryRecord(Integer primaryKey) {
        return calcModelParamsMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(CalcModelParams record) {
        calcModelParamsMapper.insert(record);
        return record.getId();
    }

    @Override
    public int updateRecord(CalcModelParams record) {
        return calcModelParamsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return calcModelParamsMapper.deleteByPrimaryKey(primaryKey);
    }

    /**
     * 根据公式名称查询对应的参数
     * @param form
     * @return List<E>
     */
    @Override
    public List<CalcModelParams> getParamByFormulaCode(PageForm<CalcModelParamsForm> form) {
        return calcModelParamsMapper.getParamByFormulaCode(form);
    }

}
