package com.mljr.heil.base.form;

import com.lyqc.base.common.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 八大费用规则Form基类
 * @Date : 2018/6/10 上午10:31
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
public class BaseRuleForm extends BaseForm{
    @ApiModelProperty(name="tag",value="标签",dataType="String")
    private String tag;
    /**
     * 规则id
     */
    @ApiModelProperty(name="ruleIds",value="规则id",dataType="List<Integer>")
    private List<Integer> ruleIds;

    @ApiModelProperty(name="isOld",value="是否二手车",dataType="String")
    private String isOld;
    @ApiModelProperty(name="isLcv",value="车类",dataType="String")
    private String isLcv;
    @ApiModelProperty(name="loanPeriod",value="贷款期限",dataType="String")
    private String loanPeriod;
    private Integer dealerCode;
    @ApiModelProperty(value = "状态 1：可用 0：无效")
    private Integer status;

    @ApiModelProperty(value = "标签集合")
    private List<String> tagList;
}
