package com.mljr.heil.mapper;

import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.SerFinRate;
import com.mljr.heil.entity.YanbaoTc;
import com.mljr.heil.form.SerFinRateForm;
import com.mljr.heil.form.YanbaoTcForm;

import java.util.List;

public interface SerFinRateMapper extends BaseMapper<SerFinRate,Integer,SerFinRateForm>{

    /**
     * @description: 根据多条件删除
     * @Date : 2018/3/5 16:48
     * @Author : lihaitao
     */
    int delByForm(SerFinRateForm serFinRateForm);

    /**
     * @description: 批量插入
     * @Date : 2018/3/5 16:50
     * @Author : lihaitao
     */
    int insertBatch(List<SerFinRate> serFinRates);

    /**
     * @description: 查询
     * @Date : 2018/6/11 16:50
     * @Author : zhaoxin
     */
    List<SerFinRate> queryList(SerFinRateForm serFinRateForm);

}