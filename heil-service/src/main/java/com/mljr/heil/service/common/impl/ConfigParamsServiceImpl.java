package com.mljr.heil.service.common.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.ConfigParams;
import com.mljr.heil.form.ConfigParamsForm;
import com.mljr.heil.mapper.ConfigParamsMapper;
import com.mljr.heil.service.common.ConfigParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:  产品全局配置参数Service
 * @Date : 2018/4/23 下午4:43
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Service
public class ConfigParamsServiceImpl implements ConfigParamsService {
    @Autowired
    private ConfigParamsMapper configParamsMapper;


    @Override
    public List<ConfigParams> queryByPage(PageForm<ConfigParamsForm> form) {
        return configParamsMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<ConfigParamsForm> form) {
        return configParamsMapper.queryCount(form);
    }

    @Override
    public ConfigParams queryRecord(Integer primaryKey) {
        return configParamsMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(ConfigParams record) {
        configParamsMapper.insertSelective(record);
        return record.getId();
    }

    @Override
    public int updateRecord(ConfigParams record) {
        return configParamsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return configParamsMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public ConfigParams queryByParamKey(String paramKey) {
        return configParamsMapper.queryByParamKey(paramKey);
    }

    @Override
    public List<ConfigParams> queryListForFlushRedis() {
        return configParamsMapper.queryListForFlushRedis();
    }
}
