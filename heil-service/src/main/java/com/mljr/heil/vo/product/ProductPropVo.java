package com.mljr.heil.vo.product;

import com.lyqc.base.enums.RuleConditionConstant;
import com.lyqc.util.EnumConvert;
import com.mljr.heil.base.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: 产品属性VO
 * @Date : 下午2:40 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class ProductPropVo extends BaseVo {
    /**
     * 车龄(月)
     */
    @ApiModelProperty(name="carAgeMax",value="车龄(月)",required = true,dataType="String")
    private String carAgeMax;
    /**
     * 车龄(月)
     */
    @ApiModelProperty(name="carAgeMin",value="车龄(月)",required = true,dataType="String")
    private String carAgeMin;
    /**
     * 车辆贷款金额
     */
    @ApiModelProperty(name="carloanAmountMax",value="车辆贷款金额",required = true,dataType="String")
    private String carloanAmountMax;
    /**
     * 车辆贷款金额
     */
    @ApiModelProperty(name="carloanAmountMin",value="车辆贷款金额",required = true,dataType="String")
    private String carloanAmountMin;
    /**
     * 里程
     */
    @ApiModelProperty(name="courseMax",value="里程",required = true,dataType="String")
    private String courseMax;
    /**
     * 里程
     */
    @ApiModelProperty(name="courseMin",value="里程",required = true,dataType="String")
    private String courseMin;
    /**
     * 首付比
     */
    @ApiModelProperty(name="initScaleMax",value="首付比",required = true,dataType="String")
    private String initScaleMax;
    /**
     * 首付比
     */
    @ApiModelProperty(name="initScaleMin",value="首付比",required = true,dataType="String")
    private String initScaleMin;
    /**
     * 是否提供房产
     */
    @ApiModelProperty(name="isHouse",value="是否提供房产",required = true,dataType="String")
    private String isHouse;
    /**
     * 车类
     */
    @ApiModelProperty(name="isLcv",value="车类",required = true,dataType="String")
    private String isLcv;
    /**
     * 是否二手车
     */
    @ApiModelProperty(name="isOld",value="是否二手车",required = true,dataType="String")
    private String isOld;
    /**
     * 总贷款金额
     */
    @ApiModelProperty(name="loanAmountMax",value="总贷款金额",required = true,dataType="String")
    private String loanAmountMax;
    /**
     * 总贷款金额
     */
    @ApiModelProperty(name="loanAmountMin",value="总贷款金额",required = true,dataType="String")
    private String loanAmountMin;
    /**
     * 还款期限
     */
    @ApiModelProperty(name="loanPeriods",value="还款期限",required = true,dataType="String")
    private String loanPeriods;
    /**
     * 实际销售价
     */
    @ApiModelProperty(name="salePriceMax",value="实际销售价",required = true,dataType="String")
    private String salePriceMax;
    /**
     * 实际销售价
     */
    @ApiModelProperty(name="salePriceMin",value="实际销售价",required = true,dataType="String")
    private String salePriceMin;
    /**
     * 是否支持平台费自主金额比例
     */
    @ApiModelProperty(name="commFloatFeeEnable",value="是否支持GPS金额自主配置",required = true,dataType="String")
    private String commFloatFeeEnable;
    /**
     * 是否支持GPS金额自主配置
     */
    @ApiModelProperty(name="gpsFloatEnable",value="是否支持GPS金额自主配置",required = true,dataType="String")
    private String gpsFloatEnable;
    /**
     * 平台费率自主金额比例范围
     */
    @ApiModelProperty(name="commFloatFeeRateMax",value="平台费率自主金额比例范围",required = true,dataType="String")
    private String commFloatFeeRateMax;
    /**
     * 平台费率自主金额比例范围
     */
    @ApiModelProperty(name="commFloatFeeRateMin",value="平台费率自主金额比例范围",required = true,dataType="String")
    private String commFloatFeeRateMin;
    /**
     * 自主配置GPS金额范围
     */
    @ApiModelProperty(name="gpsFloatFeeMax",value="自主配置GPS金额范围",required = true,dataType="String")
    private String gpsFloatFeeMax;
    /**
     * 自主配置GPS金额范围
     */
    @ApiModelProperty(name="gpsFloatFeeMin",value="自主配置GPS金额范围",required = true,dataType="String")
    private String gpsFloatFeeMin;

    @ApiModelProperty(name="isOldDesc",value="是否二手车",dataType="String")
    private String isOldDesc;
    @ApiModelProperty(name="isLcvDesc",value="车类",dataType="String")
    private String isLcvDesc;
    @ApiModelProperty(name="loanPeriodsDesc",value="贷款期限",dataType="String")
    private String loanPeriodsDesc;

    public String getCarAgeMax() {
        return carAgeMax;
    }

    public void setCarAgeMax(String carAgeMax) {
        this.carAgeMax = carAgeMax;
    }

    public String getCarAgeMin() {
        return carAgeMin;
    }

    public void setCarAgeMin(String carAgeMin) {
        this.carAgeMin = carAgeMin;
    }

    public String getCarloanAmountMax() {
        return carloanAmountMax;
    }

    public void setCarloanAmountMax(String carloanAmountMax) {
        this.carloanAmountMax = carloanAmountMax;
    }

    public String getCarloanAmountMin() {
        return carloanAmountMin;
    }

    public void setCarloanAmountMin(String carloanAmountMin) {
        this.carloanAmountMin = carloanAmountMin;
    }

    public String getCourseMax() {
        return courseMax;
    }

    public void setCourseMax(String courseMax) {
        this.courseMax = courseMax;
    }

    public String getCourseMin() {
        return courseMin;
    }

    public void setCourseMin(String courseMin) {
        this.courseMin = courseMin;
    }

    public String getInitScaleMax() {
        return initScaleMax;
    }

    public void setInitScaleMax(String initScaleMax) {
        this.initScaleMax = initScaleMax;
    }

    public String getInitScaleMin() {
        return initScaleMin;
    }

    public void setInitScaleMin(String initScaleMin) {
        this.initScaleMin = initScaleMin;
    }

    public String getIsHouse() {
        return isHouse;
    }

    public void setIsHouse(String isHouse) {
        this.isHouse = isHouse;
    }

    public String getIsLcv() {
        return isLcv;
    }

    public void setIsLcv(String isLcv) {
        this.isLcv = isLcv;
    }

    public String getIsOld() {
        return isOld;
    }

    public void setIsOld(String isOld) {
        this.isOld = isOld;
    }

    public String getLoanAmountMax() {
        return loanAmountMax;
    }

    public void setLoanAmountMax(String loanAmountMax) {
        this.loanAmountMax = loanAmountMax;
    }

    public String getLoanAmountMin() {
        return loanAmountMin;
    }

    public void setLoanAmountMin(String loanAmountMin) {
        this.loanAmountMin = loanAmountMin;
    }

    public String getLoanPeriods() {
        return loanPeriods;
    }

    public void setLoanPeriods(String loanPeriods) {
        this.loanPeriods = loanPeriods;
    }

    public String getSalePriceMax() {
        return salePriceMax;
    }

    public void setSalePriceMax(String salePriceMax) {
        this.salePriceMax = salePriceMax;
    }

    public String getSalePriceMin() {
        return salePriceMin;
    }

    public void setSalePriceMin(String salePriceMin) {
        this.salePriceMin = salePriceMin;
    }

    public String getCommFloatFeeEnable() {
        return commFloatFeeEnable;
    }

    public void setCommFloatFeeEnable(String commFloatFeeEnable) {
        this.commFloatFeeEnable = commFloatFeeEnable;
    }

    public String getGpsFloatEnable() {
        return gpsFloatEnable;
    }

    public void setGpsFloatEnable(String gpsFloatEnable) {
        this.gpsFloatEnable = gpsFloatEnable;
    }

    public String getCommFloatFeeRateMax() {
        return commFloatFeeRateMax;
    }

    public void setCommFloatFeeRateMax(String commFloatFeeRateMax) {
        this.commFloatFeeRateMax = commFloatFeeRateMax;
    }

    public String getCommFloatFeeRateMin() {
        return commFloatFeeRateMin;
    }

    public void setCommFloatFeeRateMin(String commFloatFeeRateMin) {
        this.commFloatFeeRateMin = commFloatFeeRateMin;
    }

    public String getGpsFloatFeeMax() {
        return gpsFloatFeeMax;
    }

    public void setGpsFloatFeeMax(String gpsFloatFeeMax) {
        this.gpsFloatFeeMax = gpsFloatFeeMax;
    }

    public String getGpsFloatFeeMin() {
        return gpsFloatFeeMin;
    }

    public void setGpsFloatFeeMin(String gpsFloatFeeMin) {
        this.gpsFloatFeeMin = gpsFloatFeeMin;
    }

    public String getIsOldDesc() {
        return isOldDesc;
    }

    public void setIsOldDesc(String isOldDesc) {
        this.isOldDesc = isOldDesc;
    }

    public String getIsLcvDesc() {
        return isLcvDesc;
    }

    public void setIsLcvDesc(String isLcvDesc) {
        this.isLcvDesc = isLcvDesc;
    }

    public String getLoanPeriodsDesc() {
        return loanPeriodsDesc;
    }

    public void setLoanPeriodsDesc(String loanPeriodsDesc) {
        this.loanPeriodsDesc = loanPeriodsDesc;
    }

    /**
     * VO 相关枚举索引值转换 枚举名称
     */
    public void  convertEnum(){
        this.setIsOldDesc(EnumConvert.convertIndex2String(RuleConditionConstant.IsOldEnum.values(),isOld,"不限"));
        this.setIsLcvDesc(EnumConvert.convertIndex2String(RuleConditionConstant.IsLcvEnum.values(),isLcv,"不限"));
        this.setLoanPeriodsDesc(EnumConvert.convertIndex2String(RuleConditionConstant.LoanPeriodsEnum.values(),loanPeriods,"不限"));
    }
}
