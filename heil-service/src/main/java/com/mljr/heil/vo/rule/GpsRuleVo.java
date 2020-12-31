package com.mljr.heil.vo.rule;

import com.mljr.heil.base.vo.BaseRuleVo;
import com.mljr.heil.base.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

/**
 * @description: GPS规则配置VO类
 * @Date : 下午5:13 2018/2/9
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class GpsRuleVo extends BaseRuleVo {
    private static final long serialVersionUID = -1600845940823715543L;
    @ApiModelProperty(name="gpsFee",value="GPS费用",required = true,dataType="String")
    @NotNull(message = "[GPS费用]不能为空")
    private String gpsFee;
    @ApiModelProperty(name="level",value="GPS档位",required = true,dataType="String")
    @NotNull(message = "[GPS档位]不能为空")
    private String level;
    @ApiModelProperty(name="levelDesc",value="GPS档位",required = true,dataType="String")
    private String levelDesc;
    @ApiModelProperty(name="rebate",value="返佣金额",required = false,dataType="String")
    private String rebate;
    @ApiModelProperty(name="gpsCount",value="GPS数量",required = false,dataType="String")
    private String gpsCount;
    @ApiModelProperty(name="gpsPro",value="GPS属性",required = false,dataType="String")
    private String gpsPro;
    @ApiModelProperty(name="gpsProDesc",value="GPS属性",required = false,dataType="String")
    private String gpsProDesc;

    public String getGpsFee() {
        return gpsFee;
    }

    public void setGpsFee(String gpsFee) {
        this.gpsFee = gpsFee;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelDesc() {
        return levelDesc;
    }

    public void setLevelDesc(String levelDesc) {
        this.levelDesc = levelDesc;
    }

    public String getRebate() {
        return rebate;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    public String getGpsCount() {
        return gpsCount;
    }

    public void setGpsCount(String gpsCount) {
        this.gpsCount = gpsCount;
    }

    public String getGpsPro() {
        return gpsPro;
    }

    public void setGpsPro(String gpsPro) {
        this.gpsPro = gpsPro;
    }

    public String getGpsProDesc() {
        return gpsProDesc;
    }

    public void setGpsProDesc(String gpsProDesc) {
        this.gpsProDesc = gpsProDesc;
    }
}
