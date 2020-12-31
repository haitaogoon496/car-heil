package com.mljr.heil.service.common;

import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.CalcLog;
import com.mljr.heil.entity.ConfigParams;
import com.mljr.heil.form.CalcLogForm;
import com.mljr.heil.form.ConfigParamsForm;

import java.util.List;

/**
 * @description:  产品全局配置参数Service
 * @Date : 2018/4/23 下午4:43
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface ConfigParamsService extends BaseService<ConfigParams,Integer,ConfigParamsForm> {
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
