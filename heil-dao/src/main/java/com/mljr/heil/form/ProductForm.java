package com.mljr.heil.form;

import com.mljr.heil.base.form.BaseRuleForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 车贷产品Form类
 * @Date : 下午5:48 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductForm extends BaseRuleForm {
    private static final long serialVersionUID = 5712562973876418082L;
    /**
     * 产品状态
     */
    private Integer status;
    /**
     * 产品状态
     */
    private List<String> statusScope;
    /**
     * 产品所属年份
     */
    private Integer year;
    @ApiModelProperty(value = "产品对应合同id")
    private Integer productContractId;
    @ApiModelProperty(value = "产品类型")
    private Integer type;
    @ApiModelProperty(value = "产品分类")
    private String typeName;
    @ApiModelProperty(name="isOld",value="是否二手车",dataType="String")
    private String isOld;
    @ApiModelProperty(name="isLcv",value="车类",dataType="String")
    private String isLcv;
    @ApiModelProperty(name = "productIdList",value = "主键集合")
    private List<Integer> productIdList;
    @ApiModelProperty(name = "fundId", value = "资金方id")
    private Integer fundId;
    @ApiModelProperty(value = "资金方名称")
    private String fundName;
    @ApiModelProperty(name="tag",value="标签",dataType="String")
    private String tag;
    @ApiModelProperty(name = "isAllDealer", value = "是否适用所有经销商", dataType = "String")
    private String isAllDealer;
}
