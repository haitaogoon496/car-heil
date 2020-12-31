package com.mljr.heil.entity.fund;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description:
 * @Date : 2018/6/12 20:28
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public class FundProduct extends BaseEntity {
    private static final long serialVersionUID = -6412216905242825947L;

    @ApiModelProperty(name = "fundId", value = "资金方id", required = true, dataType = "Integer")
    private Integer fundId;

    @ApiModelProperty(name = "productId", value = "产品id", required = true, dataType = "Integer")
    private Integer productId;

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
