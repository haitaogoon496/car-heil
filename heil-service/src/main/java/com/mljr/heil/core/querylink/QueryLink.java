package com.mljr.heil.core.querylink;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.entity.BaseEntity;
import com.mljr.heil.form.PdRuleProductForm;

import java.util.List;

/**
 * @description: 查询费用规则关联产品接口
 * @Date : 2018/11/20 下午2:07
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface QueryLink<E extends BaseEntity,F extends PdRuleProductForm> {
    /**
     * 查询已关联集合
     * @param form
     * @return
     */
    List<E> queryLink(PageForm<F> form);
    /**
     * 查询已关联条数
     * @param form
     * @return
     */
    int queryLinkCount(PageForm<F> form);
}
