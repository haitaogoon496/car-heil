package com.mljr.heil.base.mapper;

import com.lyqc.base.common.BaseForm;
import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.entity.BaseEntity;

import java.util.List;

/**
 * @description: Mapper基类
 * @Date : 下午3:36 2018/2/5
 * @param <E> entity对象
 * @param <PK> 主键，1、返回自增ID；2：查询对象主键；3：删除对象主键(联合主键)
 * @param <F> 封装查询参数
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface BaseMapper<E extends BaseEntity,PK,F extends BaseForm>  {
    /**
     * 分页查询
     * @param form
     * @return List<E>
     */
    List<E> pageQuery(PageForm<F> form);

    /**
     * 分页查询条数
     * @param form
     * @return
     */
    int queryCount(PageForm<F> form);
    /**
     * 查询列表
     * @param form
     * @return
     */
    List<E> queryList(F form);
    /**
     * 根据主键删除
     * @param uniqueKey
     * @return
     */
    int deleteByPrimaryKey(PK uniqueKey);

    /**
     * 新增对象
     * @param record
     * @return 主键id
     */
    int insert(E record);

    /**
     * 有选择性的新增对象
     * @param record
     * @return 主键id
     */
    int insertSelective(E record);

    /**
     * 根据主键id查询对象
     * @param uniqueKey
     * @return E
     */
    E selectByPrimaryKey(PK uniqueKey);

    /**
     * 有选择性的更新对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(E record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(E record);
    /**
     * 批量新增
     * @param list
     * @return
     */
    default int batchInsert(List<E> list){
        return 0;
    }

    /**
     * 批量新增，忽略已存在数据
     * @param list
     * @return
     */
    default int batchInsertIgnore(List<E> list){
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
     * 根据条件删除
     * @param f
     * @return
     */
    default int deleteByForm(F f){
        return 0;
    }


}
