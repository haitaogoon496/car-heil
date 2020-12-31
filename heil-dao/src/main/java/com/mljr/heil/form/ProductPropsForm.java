package com.mljr.heil.form;

import com.lyqc.base.common.BaseForm;
import java.util.List;
/**
 * @description: 产品属性Form
 * @Date : 下午2:25 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class ProductPropsForm extends BaseForm {
    private static final long serialVersionUID = -5768525330930026620L;
    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 产品idList
     */
    private List<Integer> productIdList;

    /**
     * 属性
     */
    private String propName;

    public ProductPropsForm() {
    }

    public ProductPropsForm(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public List<Integer> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<Integer> productIdList) {
        this.productIdList = productIdList;
    }
}
