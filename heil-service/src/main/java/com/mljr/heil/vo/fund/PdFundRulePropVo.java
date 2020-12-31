package com.mljr.heil.vo.fund;

import com.alibaba.fastjson.JSONObject;
import com.mljr.heil.base.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Date : 2018/6/13$ 11:09$
 * @Author : liht
 */
@Data
public class PdFundRulePropVo extends BaseVo {
    private String id;
    @ApiModelProperty(value = "资金准入规则id")
    private String fundRuleId;
    @ApiModelProperty("主贷人年龄(岁)最小值")
    private String personAgeMin;
    @ApiModelProperty(value = "主贷人年龄(岁)最大值")
    private String personAgeMax;
    @ApiModelProperty(value = "主贷人手机正则表达式")
    private String mobileRegex;
    @ApiModelProperty(value = "主贷人从事行业(数据字典码值)")
    private String industry;
    @ApiModelProperty(value = "主贷人税后月收入(元)最高")
    private String disposableSalaryHigh;
    @ApiModelProperty(value = "主贷人税后月收入(元)最低")
    private String disposableSalaryLow;
    @ApiModelProperty(value = "主贷人本人是否有驾照")
    private String driverLicenseFlag;
    @ApiModelProperty(value = "主贷人是否二手车(1:是 0:否)")
    private String isOld;
    @ApiModelProperty(value = "车牌类型(1:是 0:否)")
    private String vehicleLicenseType;
    @ApiModelProperty(value = "车型(1:是 0:否)")
    private String vehicleModel;
    @ApiModelProperty(value = "车龄(月)最小值")
    private String carAgeMin;
    @ApiModelProperty(value = "车龄(月)最大值")
    private String carAgeMax;
    @ApiModelProperty(value = "车辆里程(公里)最小值")
    private String carCourseMin;
    @ApiModelProperty(value = "车辆里程(公里)最大值")
    private String carCourseMax;
    @ApiModelProperty(value = "贷款期限")
    private String loanPeriods;
    @ApiModelProperty(value = "车辆贷款金额最小值")
    private String loanAmountMin;
    @ApiModelProperty(value = "车辆贷款金额最大值")
    private String loanAmountMax;
    @ApiModelProperty(value = "首付比最小值")
    private String initScaleMin;
    @ApiModelProperty(value = "首付比最大值")
    private String initScaleMax;
    @ApiModelProperty(value = "规则户籍")
    private Map<String, List<String>> pdFundRuleCensusMap;
    private JSONObject jsonObject;
    private String flowSeq;
    private String ageMin;
    private String ageMax;
    private String nowIndustry;
    private String incomeMonthMin;
    private String incomeMonthMax;
    private String driverLicenseOwner;
    private String carLicenseType;
    private String carType;
    private String carMilesMin;
    private String carMilesMax;
    private String applyLoanPeriods;
    private String applyCarLoanAmountMin;
    private String applyCarLoanAmountMax;

}
