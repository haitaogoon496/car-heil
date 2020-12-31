package com.mljr.heil.service.common;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.PdTag;
import com.mljr.heil.form.PdTagForm;

import java.util.List;

/**
 * @description 标签操作service
 * @author zhaoxin
 * @date 2018/6/4 下午2:10
 **/
public interface PdTagService {

    /**
     * 查询标签
     * @param form
     * @return
     */
    List<PdTag> queryList(PdTagForm form);


    /**
     * 修改标签
     * @param record
     * @return
     */
    int updateRecord(PdTag record);

    /**
     * 插入标签
     * @param record
     * @return
     */
    int insertRecord(PdTag record);

    /**
     * 删除标签
     * @param record
     * @return
     */
    int deleteRecord(PdTag record);

    /**
     * 分页加载数据
     * @param form
     * @return
     */
    List<PdTag> queryByPage(PageForm<PdTagForm> form);

    /**
     * 分页查询条数
     * @param form
     * @return
     */
    int queryCount(PageForm<PdTagForm> form);

    /**
     * 批量删除标签记录
     * @param list
     * @return
     */
    int batchDelete(List<PdTag> list);
}
