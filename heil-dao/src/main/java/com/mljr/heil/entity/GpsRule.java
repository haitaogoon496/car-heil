package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseRule;
import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.NotNull;

import java.math.BigDecimal;
/**
 * @description: GPS规则配置
 * @Date : 2018/2/11 下午3:18
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class GpsRule extends BaseRule {

    private static final long serialVersionUID = -1216044762219383380L;
    @ApiModelProperty(name="gpsFee",value="GPS费用",required = true,dataType="Double")
    @NotNull(message = "[GPS费用]不能为空")
    @Length(max = 10000000,message = "GPS费用必须在0~10000000之间")
    @Max(value = 10000000,message = "GPS费用必须在0~10000000之间")
    private Double gpsFee;
    @ApiModelProperty(name="level",value="GPS档位",required = true,dataType="Integer")
    @NotNull(message = "[GPS档位]不能为空")
    private Integer level;
    @ApiModelProperty(name="rebate",value="返佣金额",required = false,dataType="BigDecimal")
    @Length(max = 10000000,message = "返佣金额必须在0~10000000之间")
    @Max(value = 10000000,message = "返佣金额必须在0~10000000之间")
    private BigDecimal rebate;
    @ApiModelProperty(name="gpsCount",value="GPS数量",required = false,dataType="Integer")
    private Integer gpsCount;
    @ApiModelProperty(name="gpsPro",value="GPS属性",required = false,dataType="String")
    private String gpsPro;

    public Double getGpsFee() {
        return gpsFee;
    }

    public void setGpsFee(Double gpsFee) {
        this.gpsFee = gpsFee;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getRebate() {
        return rebate;
    }

    public void setRebate(BigDecimal rebate) {
        this.rebate = rebate;
    }

    public Integer getGpsCount() {
        return gpsCount;
    }

    public void setGpsCount(Integer gpsCount) {
        this.gpsCount = gpsCount;
    }

    public String getGpsPro() {
        return gpsPro;
    }

    public void setGpsPro(String gpsPro) {
        this.gpsPro = gpsPro;
    }

    @Override
    public void setterIfNull() {
        super.setterIfNull();
    }
}