package com.mljr.heil.mapper;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.PdFeeProduct;
import com.mljr.heil.entity.PdFeeRule;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.PdFeeProductForm;

import java.util.List;

public interface PdFeeProductMapper extends BaseMapper<PdFeeProduct,Integer,PdFeeProductForm>{
    /**
     * 批量删除，取消关联操作
     * @param form
     * @return
     */
    int batchDelete(PdFeeProductForm form);

    /**
     * 分页查询出当前续保押金绑定的产品信息
     * @param form
     * @return
     */
    List<Product> selectBindProductByParams(PageForm<PdFeeProductForm> form);

    /**
     * 查询出当前续保押金绑定的产品个数
     * @param form
     * @return
     */
    int getBindProductCount(PageForm<PdFeeProductForm> form);

    /**
     * 分页查询出当前续保押金尚未绑定的产品列表
     * @param form
     * @return
     */
    List<Product> selectNotBindProductByParams(PageForm<PdFeeProductForm> form);

    /**
     * 查询出当前续保押金尚未绑定的产品个数
     * @param form
     * @return
     */
    int getNotBindProductCount(PageForm<PdFeeProductForm> form);

    /**
     * 根据规则id删除对应的数据
     * @param ruleId
     * @return
     */
    int deleteByRuleId(Integer ruleId);

    List<PdFeeRule> queryPdFeeRulesForProduct(PageForm<PdFeeProductForm> form);
    int queryPdFeeRulesForProductCount(PageForm<PdFeeProductForm> form);
}