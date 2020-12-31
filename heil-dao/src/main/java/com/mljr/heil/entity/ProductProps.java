package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: 产品属性
 * @Date : 2018/3/3 下午2:27
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class ProductProps extends BaseEntity{
    @ApiModelProperty(name="pId",value="产品id",dataType="Integer")
    private Integer pId;
    @ApiModelProperty(name="propName",value="产品属性",required = true,dataType="String")
    private String propName;
    @ApiModelProperty(name="propValue",value="属性值",required = true,dataType="String")
    private String propValue;
    @ApiModelProperty(name="desp",value="备注",dataType="String")
    private String desp;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName == null ? null : propName.trim();
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue == null ? null : propValue.trim();
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp == null ? null : desp.trim();
    }
}