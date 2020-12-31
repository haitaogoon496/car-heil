package com.mljr.heil.service.calc.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.CalcLog;
import com.mljr.heil.form.CalcLogForm;
import com.mljr.heil.mapper.CalcLogMapper;
import com.mljr.heil.service.calc.CalcLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 产品计算logserviceimpl
 * @Date : 2018/3/30 16:58
 * @Author : lihaitao
 */
@Service
public class CalcLogServiceImpl implements CalcLogService {
    @Autowired
    private CalcLogMapper calcLogMapper;


    @Override
    public List<CalcLog> queryByPage(PageForm<CalcLogForm> form) {
        return calcLogMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<CalcLogForm> form) {
        return calcLogMapper.queryCount(form);
    }

    @Override
    public CalcLog queryRecord(Integer primaryKey) {
        return calcLogMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(CalcLog record) {
        calcLogMapper.insert(record);
        return record.getId();
    }

    @Override
    public int updateRecord(CalcLog record) {
        return calcLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return calcLogMapper.deleteByPrimaryKey(primaryKey);
    }

}
