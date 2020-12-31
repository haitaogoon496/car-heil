package com.mljr.heil.form.fund;

import com.lyqc.base.common.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @Date : 2018/6/12 20:32
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Data
public class FundProductForm  extends BaseForm {

    private static final long serialVersionUID = -5966223779882615108L;

    @ApiModelProperty(name="id",value="主键id",dataType="Integer")
    private Integer id;

    @ApiModelProperty(name = "fundId", value = "资金方id", dataType = "Integer")
    private Integer fundId;

    @ApiModelProperty(name = "productId", value = "产品id", dataType = "Integer")
    private Integer productId;

    @ApiModelProperty(name = "productName", value = "产品名称", dataType = "String")
    private String productName;

    @ApiModelProperty(name = "productTypeId", value = "产品分类id", dataType = "Integer")
    private Integer productTypeId;

}
