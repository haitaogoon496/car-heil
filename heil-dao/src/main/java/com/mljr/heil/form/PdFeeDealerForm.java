package com.mljr.heil.form;

import com.lyqc.base.common.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 人身保险费规则 管理门店Form类
 * @Date : 下午5:48 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PdFeeDealerForm extends BaseForm {
    private static final long serialVersionUID = 5712562973876418082L;
    /**
     * 关联主表id
     */
    private Integer ruleId;
    /**
     * 规则id范围
     */
    private List<Integer> ruleIdScope;
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
}
