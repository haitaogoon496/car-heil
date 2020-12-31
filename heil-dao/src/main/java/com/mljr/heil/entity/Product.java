package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import com.mljr.heil.common.context.UserContextHelper;
import com.mljr.heil.common.user.CreateUserSettable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotNull;

import java.util.Date;
import java.util.List;

@Data
public class Product extends BaseEntity {
    private static final long serialVersionUID = -7892728284411664371L;
    @ApiModelProperty(name = "pId", value = "产品id", required = true, dataType = "Integer")
    private Integer pId;
    @ApiModelProperty(name = "productCoe", value = "产品编码", required = true, dataType = "String")
    private String productCoe;
    @ApiModelProperty(name = "year", value = "产品年份", required = true, dataType = "String")
    private Integer year;
    @ApiModelProperty(name = "pName", value = "产品名称", required = true, dataType = "String")
    private String pName;
    @ApiModelProperty(name = "type", value = "产品分类", required = true, dataType = "Integer")
    @NotNull(message = "产品类型不能为空")
    private Integer type;
    @ApiModelProperty(name = "typeName", value = "产品分类名称", required = true, dataType = "String")
    private String typeName;
    @ApiModelProperty(name = "createTime", value = "产品创建时间", dataType = "Date")
    private Date createTime;
    @ApiModelProperty(name = "lastUpdate", value = "产品修改时间", dataType = "Date")
    private Date lastUpdate;
    @ApiModelProperty(name = "beginTime", value = "产品生效时间", required = true, dataType = "Date")
    @NotNull(message = "产品生效时间不能为空")
    private Date beginTime;
    @ApiModelProperty(name = "endTime", value = "产品失效时间", required = true, dataType = "Date")
    @NotNull(message = "产品失效时间不能为空")
    private Date endTime;
    @ApiModelProperty(name = "createTime", value = "数据版本", required = true, dataType = "Integer")
    private Integer ver;
    @ApiModelProperty(name = "status", value = "产品状态", required = true, dataType = "Integer")
    private Integer status;
    @ApiModelProperty(name = "desp", value = "产品简述", required = true, dataType = "String")
    private String desp;
    @ApiModelProperty(name = "isAllDealer", value = "是否适用所有经销商", required = true, dataType = "String")
    private String isAllDealer;
    @ApiModelProperty(name = "isBrand", value = "是否适用所有品牌", required = true, dataType = "String")
    private String isBrand;
    @ApiModelProperty(name = "isSeries", value = "是否适用所有车系", required = true, dataType = "String")
    private String isSeries;
    @ApiModelProperty(name = "isStyles", value = "是否适用所有车型", required = true, dataType = "String")
    private String isStyles;

    @ApiModelProperty(name = "carry", value = "费用进位方式", required = true, dataType = "String")
    @NotNull(message = "费用进位方式不能为空")
    private String carry;
    @ApiModelProperty(name = "precisions", value = "费用进位精度", required = true, dataType = "Long")
    @NotNull(message = "费用进位精度不能为空")
    private Long precisions;
    @ApiModelProperty(name = "propList", value = "产品属性VO", required = true, dataType = "List<ProductProps>")
    private List<ProductProps> propList;
    @ApiModelProperty(value = "资金方名称")
    private String fundName;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }
}