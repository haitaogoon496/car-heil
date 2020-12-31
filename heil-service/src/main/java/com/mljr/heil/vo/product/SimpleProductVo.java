package com.mljr.heil.vo.product;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @description: 产品实体类 简单字段
 * @Date : 2018/4/3$ 16:03$
 * @Author : liht
 */
public class SimpleProductVo implements Serializable {

    private static final long serialVersionUID = 116300705128185176L;
    @ApiModelProperty(name="pId",value="产品id",required = true,dataType="Integer")
    private String pId;
    @ApiModelProperty(name="productCode",value="产品编码",required = true,dataType="String")
    private String productCode;
    @ApiModelProperty(name="productName",value="产品名称",required = true,dataType="String")
    private String productName;
    @ApiModelProperty(name="statusDesc",value="产品状态",required = true,dataType="String")
    private String statusDesc;
    @ApiModelProperty(name="type",value="产品分类",required = true,dataType="Integer")
    private String type;
    @ApiModelProperty(value = "checkbox是否选中 1：选中，0：不选中")
    private String linked;

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getLinked() {
        return linked;
    }

    public void setLinked(String linked) {
        this.linked = linked;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
