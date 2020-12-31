package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseRule;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class SerFinRule extends BaseRule {

    private static final long serialVersionUID = -924039318633553334L;
    private String products;
    @ApiModelProperty(value = "金融服务费率")
    private BigDecimal serFinFee;
    @ApiModelProperty(value = "工作流")
    private String workFlow;
    /**
     * {@link com.lyqc.base.enums.SerFinConstant.TakenModeEnum}
     */
    @ApiModelProperty(value = "收取方式（1，线上收取；2，线下收取）")
    private Integer takenMode;
    private Boolean commFloatFeeEnable;

    private BigDecimal commFloatFeeRateMin;

    private BigDecimal commFloatFeeRateMax;

    private List<SerFinRate> serFinRates;

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products == null ? null : products.trim();
    }

    public BigDecimal getSerFinFee() {
        return serFinFee;
    }

    public void setSerFinFee(BigDecimal serFinFee) {
        this.serFinFee = serFinFee;
    }

    public String getWorkFlow() {
        return workFlow;
    }

    public void setWorkFlow(String workFlow) {
        this.workFlow = workFlow == null ? null : workFlow.trim();
    }

    public Integer getTakenMode() {
        return takenMode;
    }

    public void setTakenMode(Integer takenMode) {
        this.takenMode = takenMode;
    }

    public Boolean getCommFloatFeeEnable() {
        return commFloatFeeEnable;
    }

    public void setCommFloatFeeEnable(Boolean commFloatFeeEnable) {
        this.commFloatFeeEnable = commFloatFeeEnable;
    }

    public BigDecimal getCommFloatFeeRateMin() {
        return commFloatFeeRateMin;
    }

    public void setCommFloatFeeRateMin(BigDecimal commFloatFeeRateMin) {
        this.commFloatFeeRateMin = commFloatFeeRateMin;
    }

    public BigDecimal getCommFloatFeeRateMax() {
        return commFloatFeeRateMax;
    }

    public void setCommFloatFeeRateMax(BigDecimal commFloatFeeRateMax) {
        this.commFloatFeeRateMax = commFloatFeeRateMax;
    }

    public List<SerFinRate> getSerFinRates() {
        return serFinRates;
    }

    public void setSerFinRates(List<SerFinRate> serFinRates) {
        this.serFinRates = serFinRates;
    }

    @Override
    public void setterIfNull() {
        super.setterIfNull();
    }
}