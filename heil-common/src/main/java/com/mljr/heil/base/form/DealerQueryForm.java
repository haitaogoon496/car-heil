package com.mljr.heil.base.form;

import com.lyqc.base.common.BaseForm;
import com.mljr.heil.common.enums.DictionaryConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 适用门店查询Form类
 * 适用于：GPS规则配置、利率规则配置、平台费规则配置、账号管理费规则配置、延保费规则配置、人身保险规则配置、第X年保险费规则配置、账号管理费规则配置
 * @Date : 下午4:10 2018/2/5
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DealerQueryForm extends BaseForm{
    /**
     * 关联主表id
     */
    private Integer ruleId;
    /**
     * 规则类型
     * {@link DictionaryConstant.RuleTypeEnum#getIndex()}
     */
    private Integer classify;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 门店code集合
     */
    private List<Integer> dealerScopes;
    @ApiModelProperty(value = "销售地区")
    private String saleArea;
    @ApiModelProperty(value = "所在省份")
    private String province;
    @ApiModelProperty(value = "所选城市")
    private String city;
    @ApiModelProperty(value = "门店类型")
    private String dealerType;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "规则id")
    private List<Integer> ruleIds;
}
