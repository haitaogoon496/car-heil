package com.mljr.heil.service.rule;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.PdFeeProduct;
import com.mljr.heil.entity.PdFeeRule;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.PdFeeProductForm;

import java.util.List;

/**
 * @description: 续保押金和产品关联表
 * @Date : 2018/6/25 13:50
 * @Author : lihaitao
 */
public interface PdFeeProductService extends BaseService<PdFeeProduct,Integer,PdFeeProductForm> {

    int batchDelete(PdFeeProductForm form);
    /**
     * 查询出当前续保押金绑定的产品信息
     * @param form
     * @return
     */
    List<Product> selectBindProductByParams(PageForm<PdFeeProductForm> form);

    /**
     * 查询出当前续保押金尚未绑定的产品列表
     * @param form
     * @return
     */
    List<Product> selectNotBindProductByParams(PageForm<PdFeeProductForm> form);

    /**
     * 查询出当前续保押金绑定的产品个数
     * @param form
     * @return
     */
    int getBindProductCount(PageForm<PdFeeProductForm> form);

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

    /**
     * 车贷产品管理-查询当前产品绑定的费用规则
     * @param form
     * @return
     */
    List<PdFeeRule> queryPdFeeRulesForProduct(PageForm<PdFeeProductForm> form);
    int queryPdFeeRulesForProductCount(PageForm<PdFeeProductForm> form);
}
