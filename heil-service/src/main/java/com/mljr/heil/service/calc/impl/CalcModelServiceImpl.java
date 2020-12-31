package com.mljr.heil.service.calc.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.CalcModel;
import com.mljr.heil.form.CalcModelForm;
import com.mljr.heil.mapper.CalcModelMapper;
import com.mljr.heil.service.calc.CalcModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 产品公式设置
 * @Date : 2018/3/30 16:58
 * @Author : lihaitao
 */
@Service
public class CalcModelServiceImpl implements CalcModelService {
    @Autowired
    private CalcModelMapper calcModelMapper;


    @Override
    public List<CalcModel> queryByPage(PageForm<CalcModelForm> form) {
        return calcModelMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<CalcModelForm> form) {
        return calcModelMapper.queryCount(form);
    }

    @Override
    public CalcModel queryRecord(Integer primaryKey) {
        return calcModelMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(CalcModel record) {
        calcModelMapper.insert(record);
        return record.getId();
    }

    @Override
    public int updateRecord(CalcModel record) {
        return calcModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return calcModelMapper.deleteByPrimaryKey(primaryKey);
    }

}
