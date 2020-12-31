package com.mljr.heil.base.service;

import com.lyqc.base.common.BaseForm;
import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.entity.BaseEntity;
import com.mljr.util.CollectionsTools;

import java.util.Collections;
import java.util.List;

/**
 * @description: Service接口基类
 * @Date : 下午6:06 2018/2/5
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface BaseService<E extends BaseEntity,FK,F extends BaseForm> {
    /**
     * 分页加载数据
     * @param form
     * @return
     */
    default List<E> queryByPage(PageForm<F> form) {
        return CollectionsTools.emptyList();
    };
    /**
     * 分页查询数量
     * @param form
     * @return
     */
    default int queryCount(PageForm<F> form) {
        return 0;
    };
    /**
     * 查询实体对象
     * @param primaryKey
     * @return
     */
    default E queryRecord(FK primaryKey) {
        return null;
    };
    /**
     * 新增实体对象
     * @param record
     * @return
     */
    default int insertRecord(E record) {
        return 0;
    };
    /**
     * 修改实体对象
     * @param record
     * @return
     */
    default int updateRecord(E record) {
        return 0;
    };

    /**
     * 修改实体对象
     * @param record
     * @return
     */
    default int updateByPrimaryKeySelective(E record){
        return 0;
    };
    /**
     * 删除对象
     * @param primaryKey
     * @return
     */
    default int deleteRecord(FK primaryKey) {
        return 0;
    };
    /**
     * 批量新增
     * @param list
     * @return
     */
    default int batchInsert(List<E> list){
        return 0;
    }

    /**
     * 批量插入(没有即插入，有的话不做处理)
     * @param baseDealerResList
     * @return
     */
    default int batchInsertIgnore(List<E> baseDealerResList){
        return 0;
    }

    /**
     * 批量删除
     * @param baseDealerResList
     * @return
     */
    default int batchDelete(List<E> baseDealerResList){
        return 0;
    }
    /**
     * 查询列表
     * @param form
     * @return
     */
    default List<E> queryList(F form){return Collections.emptyList();}

}
