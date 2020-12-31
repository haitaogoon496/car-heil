package com.mljr.heil.entity.fund;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

public class PdFundRuleCensus extends BaseEntity{
    private Integer id;
    @ApiModelProperty(value = "资金准入规则id")
    private Integer fundRuleId;
    @ApiModelProperty(value = "户籍所在省份行政编码")
    private String provinceCode;
    @ApiModelProperty(value = "户籍所在省份行政名称")
    private String provinceName;
    @ApiModelProperty(value = "户籍所在城市行政编码")
    private String cityCode;
    @ApiModelProperty(value = "户籍所在城市行政名称")
    private String cityName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFundRuleId() {
        return fundRuleId;
    }

    public void setFundRuleId(Integer fundRuleId) {
        this.fundRuleId = fundRuleId;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }
}