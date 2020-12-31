package com.mljr.heil.service.rule.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.PdFeeProduct;
import com.mljr.heil.entity.PdFeeRule;
import com.mljr.heil.entity.Product;
import com.mljr.heil.form.PdFeeProductForm;
import com.mljr.heil.mapper.PdFeeProductMapper;
import com.mljr.heil.service.rule.PdFeeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @description: 续保押金和产品关联设置service
 * @Date : 2018/6/25 13:52
 * @Author : lihaitao
 */
@Service
public class PdFeeProductServiceImpl implements PdFeeProductService {

    @Autowired
    private PdFeeProductMapper pdFeeProductMapper;

    @Override
    public List<PdFeeProduct> queryByPage(PageForm<PdFeeProductForm> form) {
        return pdFeeProductMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<PdFeeProductForm> form) {
        return pdFeeProductMapper.queryCount(form);
    }

    @Override
    public PdFeeProduct queryRecord(Integer primaryKey) {
        return pdFeeProductMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(PdFeeProduct record) {
        pdFeeProductMapper.insert(record);
        return record.getId();
    }

    @Override
    public int updateRecord(PdFeeProduct record) {
        return pdFeeProductMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return pdFeeProductMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public int batchDelete(PdFeeProductForm form) {
        return this.pdFeeProductMapper.batchDelete(form);
    }

    @Override
    public int batchInsert(List<PdFeeProduct> list) {
        return pdFeeProductMapper.batchInsert(list);
    }


    /**
     * 查询出当前续保押金绑定的产品列表
     * @param form
     * @return
     */
    @Override
    public List<Product> selectBindProductByParams(PageForm<PdFeeProductForm> form) {
        return pdFeeProductMapper.selectBindProductByParams(form);
    }

    /**
     * 查询出当前续保押金尚未绑定的产品列表
     * @param form
     * @return
     */
    @Override
    public List<Product> selectNotBindProductByParams(PageForm<PdFeeProductForm> form) {
        return pdFeeProductMapper.selectNotBindProductByParams(form);
    }

    /**
     * 查询出当前续保押金绑定的产品个数
     * @param form
     * @return
     */
    @Override
    public int getBindProductCount(PageForm<PdFeeProductForm> form) {
        return pdFeeProductMapper.getBindProductCount(form);
    }

    /**
     * 查询出当前续保押金尚未绑定的产品个数
     * @param form
     * @return
     */
    @Override
    public int getNotBindProductCount(PageForm<PdFeeProductForm> form) {
        return pdFeeProductMapper.getNotBindProductCount(form);
    }

    @Override
    public int deleteByRuleId(Integer ruleId) {
        return this.pdFeeProductMapper.deleteByRuleId(ruleId);
    }

    @Override
    public List<PdFeeRule> queryPdFeeRulesForProduct(PageForm<PdFeeProductForm> form) {
        return this.pdFeeProductMapper.queryPdFeeRulesForProduct(form);
    }

    @Override
    public int queryPdFeeRulesForProductCount(PageForm<PdFeeProductForm> form) {
        return this.pdFeeProductMapper.queryPdFeeRulesForProductCount(form);
    }

    @Override
    public int batchInsertIgnore(List<PdFeeProduct> baseDealerResList) {
        return pdFeeProductMapper.batchInsertIgnore(baseDealerResList);
    }

    @Override
    public int batchDelete(List<PdFeeProduct> baseDealerResList) {
        return pdFeeProductMapper.batchDelete(baseDealerResList);
    }
}
