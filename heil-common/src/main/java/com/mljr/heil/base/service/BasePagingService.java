package com.mljr.heil.base.service;

import com.lyqc.base.common.BaseForm;
import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.entity.BaseEntity;

import java.util.List;

/**
 * @description: Service接口基类(只涉及分页查询)
 * @Date : 下午6:06 2018/2/5
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface BasePagingService<E extends BaseEntity,F extends BaseForm> {
    /**
     * 分页加载数据
     * @param form
     * @return
     */
    List<E> queryByPage(PageForm<F> form);
    /**
     * 分页查询数量
     * @param form
     * @return
     */
    int queryCount(PageForm<F> form);
}
