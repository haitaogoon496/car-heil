package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

/**
 * @description: 人身保险费关联配置产品关系
 * @Date : 2018/2/27 下午2:49
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class PdInsuranceRes extends BaseEntity {
    @ApiModelProperty(name="classify",value="类别",required = true,dataType="Integer")
    @NotNull(message = "[类别]不能为空")
    private Integer classify;
    @ApiModelProperty(name="productIds",value="关联产品id",required = true,dataType="String")
    @NotNull(message = "[关联产品id]不能为空")
    private String productIds;

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds == null ? null : productIds.trim();
    }
}