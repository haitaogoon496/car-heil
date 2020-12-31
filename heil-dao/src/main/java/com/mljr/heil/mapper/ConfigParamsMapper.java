package com.mljr.heil.mapper;

import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.ConfigParams;
import com.mljr.heil.form.ConfigParamsForm;

import java.util.List;
/**
 * @description: 产品全局配置参数Mapper
 * @Date : 2018/4/23 下午4:42
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface ConfigParamsMapper extends BaseMapper<ConfigParams, Integer, ConfigParamsForm> {
    /**
     * 根据参数key查询
     * @param paramKey
     * @return
     */
    ConfigParams queryByParamKey(String paramKey);
    /**
     * 获取所有数据用于缓存到redis中
     * @return
     */
    List<ConfigParams> queryListForFlushRedis();
}