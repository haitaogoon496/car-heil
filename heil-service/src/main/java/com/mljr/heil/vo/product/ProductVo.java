package com.mljr.heil.vo.product;

import com.mljr.heil.base.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @description: 车贷产品 VO
 * @Date : 2018/2/27 下午6:24
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Data
public class ProductVo extends BaseVo {
    private static final long serialVersionUID = -1600845940823715543L;
    @ApiModelProperty(name="pId",value="产品id",required = true,dataType="Integer")
    private Integer pId;
    @ApiModelProperty(name="year",value="产品年份",required = true,dataType="String")
    private String year;
    @ApiModelProperty(name="productCode",value="产品编码",required = true,dataType="String")
    private String productCode;
    @ApiModelProperty(name="productName",value="产品名称",required = true,dataType="String")
    private String productName;
    @ApiModelProperty(name="statusDesc",value="产品状态",required = true,dataType="String")
    private String statusDesc;
    @ApiModelProperty(name="type",value="产品分类",required = true,dataType="Integer")
    private Integer type;
    @ApiModelProperty(name="typeName",value="产品分类名称",required = true,dataType="String")
    private String typeName;
    @ApiModelProperty(name="createTime",value="产品创建时间",dataType="String")
    private String createTime;
    @ApiModelProperty(name="lastUpdate",value="产品修改时间",dataType="String")
    private String lastUpdate;
    @ApiModelProperty(name="beginTime",value="产品生效时间",required = true,dataType="String")
    private String beginTime;
    @ApiModelProperty(name="endTime",value="产品失效时间",required = true,dataType="String")
    private String endTime;
    @ApiModelProperty(name="createTime",value="数据版本",required = true,dataType="Integer")
    private Integer ver;
    @ApiModelProperty(name="status",value="产品状态",required = true,dataType="Integer")
    private Integer status;
    @ApiModelProperty(name="desp",value="产品简述",required = true,dataType="String")
    private String desp;
    @ApiModelProperty(name="isAllDealer",value="是否适用所有经销商",required = true,dataType="String")
    private String isAllDealer;
    @ApiModelProperty(name="isBrand",value="是否适用所有品牌",required = true,dataType="String")
    private String isBrand;
    @ApiModelProperty(name="isSeries",value="是否适用所有车系",required = true,dataType="String")
    private String isSeries;
    @ApiModelProperty(name="isStyles",value="是否适用所有车型",required = true,dataType="String")
    private String isStyles;
    @ApiModelProperty(name="userName",value="创建人姓名",dataType="String")
    private String userName;
    @ApiModelProperty(name="userId",value="创建人id",dataType="String")
    private String userId;
    @ApiModelProperty(name="carry",value="费用进位方式",required = true,dataType="String")
    private String carry;
    @ApiModelProperty(name="precisions",value="费用进位精度",required = true,dataType="Long")
    private Long precisions;

    @ApiModelProperty(name="propVo",value="产品属性VO",required = true,dataType="ProductPropVo")
    private ProductPropVo propVo;

    @ApiModelProperty(value = "checkbox是否选中 1：选中，0：不选中")
    private String linked;
    @ApiModelProperty(value = "资金方名称")
    private String fundName;
    @ApiModelProperty(name = "tags",value = "标签",dataType = "List<String>")
    private List<String> tags = Collections.emptyList();
}
