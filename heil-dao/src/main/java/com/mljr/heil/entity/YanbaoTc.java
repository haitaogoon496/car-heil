package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.math.BigDecimal;
/**
 * @description: 第二/三年保险费规则配置 套餐
 * @Date : 2018/2/11 下午3:18
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class YanbaoTc extends BaseEntity{
    private static final long serialVersionUID = -5765128630204851189L;
    @ApiModelProperty(name="ruleSeq",value="规则id",required = true,dataType="Integer")
    @NotNull(message = "[规则id]不能为空")
    private Integer ruleSeq;
    @ApiModelProperty(name="tcId",value="套餐Id",required = true,dataType="Integer")
    @NotNull(message = "[套餐Id]不能为空")
    private Integer tcId;
    @ApiModelProperty(name="tcZh",value="套餐名称",required = true,dataType="String")
    @NotNull(message = "[套餐名称]不能为空")
    private String tcZh;
    @ApiModelProperty(name="tcFee",value="套餐金额",required = true,dataType="BigDecimal")
    @NotNull(message = "[套餐金额]不能为空")
    private BigDecimal tcFee;

    public Integer getRuleSeq() {
        return ruleSeq;
    }

    public void setRuleSeq(Integer ruleSeq) {
        this.ruleSeq = ruleSeq;
    }

    public Integer getTcId() {
        return tcId;
    }

    public void setTcId(Integer tcId) {
        this.tcId = tcId;
    }

    public String getTcZh() {
        return tcZh;
    }

    public void setTcZh(String tcZh) {
        this.tcZh = tcZh == null ? null : tcZh.trim();
    }

    public BigDecimal getTcFee() {
        return tcFee;
    }

    public void setTcFee(BigDecimal tcFee) {
        this.tcFee = tcFee;
    }
}