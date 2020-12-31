package com.mljr.heil.base.service;

import com.lyqc.base.common.BaseForm;
import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.base.entity.BaseEntity;

/**
 * @description: Facade 接口基类
 * @Date : 下午6:06 2018/2/5
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface BaseFacade<V,E extends BaseEntity,FK,F extends BaseForm> {
    /**
     * 分页加载数据
     * @param form
     * @return
     */
    Result<PageVO<V>> loadRecords(PageForm<F> form);
    /**
     * 查询实体对象
     * @param primaryKey
     * @return
     */
    Result<V> queryRecord(FK primaryKey);
    /**
     * 保存实体对象
     * @param record
     * @return
     */
    Result<String> saveRecord(E record);
    /**
     * 删除对象
     * @param primaryKey
     * @return
     */
    Result<String> deleteRecord(FK primaryKey);
}
