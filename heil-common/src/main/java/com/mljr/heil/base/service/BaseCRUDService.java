package com.mljr.heil.base.service;

import com.lyqc.base.common.BaseForm;
import com.mljr.heil.base.entity.BaseEntity;

import java.util.List;

/**
 * @description: 增删改查基础操作Service接口（不包括分页查询）
 * @Date : 2018/6/3 下午6:05
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface BaseCRUDService<E extends BaseEntity,FK,F extends BaseForm> {
    /**
     * 查询实体对象
     * @param primaryKey
     * @return
     */
    E queryRecord(FK primaryKey);
    /**
     * 新增实体对象
     * @param record
     * @return
     */
    int insertRecord(E record);
    /**
     * 修改实体对象
     * @param record
     * @return
     */
    int updateRecord(E record);
    /**
     * 删除对象
     * @param primaryKey
     * @return
     */
    int deleteRecord(FK primaryKey);
    /**
     * 批量新增
     * @param list
     * @return
     */
    default int batchInsert(List<E> list){
        return 0;
    }
}
