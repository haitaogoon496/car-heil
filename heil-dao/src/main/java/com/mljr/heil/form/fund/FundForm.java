package com.mljr.heil.form.fund;

import com.lyqc.base.common.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @Date : 2018/6/12 17:21
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Data
public class FundForm extends BaseForm {
    private static final long serialVersionUID = -1495258444034035840L;

    @ApiModelProperty(name="id",value="主键id",required = true,dataType="Integer")
    private Integer id;

    @ApiModelProperty(name = "fundNo", value = "资金方代码", required = false, dataType = "String")
    private String fundNo;

    @ApiModelProperty(name = "fundName", value = "资金方名称", required = false, dataType = "String")
    private String fundName;

    @ApiModelProperty(name = "status", value = "状态(1:有效 0:无效)", required = false, dataType = "Integer")
    private Integer status;

    @ApiModelProperty(name = "remark", value = "备注", required = false, dataType = "String")
    private String remark;

    @ApiModelProperty(name = "createTime", value = "创建日期", required = false, dataType = "Date")
    private Date createTime;

    @ApiModelProperty(name = "updateTime", value = "修改日期", required = false, dataType = "Date")
    private Date updateTime;

    @ApiModelProperty(name = "productId", value = "产品id", dataType = "Integer")
    private Integer productId;

    @ApiModelProperty(name = "productName", value = "产品名称", dataType = "String")
    private String productName;

    @ApiModelProperty(name = "productTypeId", value = "产品分类id", dataType = "Integer")
    private Integer productTypeId;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

}
