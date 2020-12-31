package com.mljr.heil.base.entity;

import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.util.Date;

/**
 * @description: 相关门店管理表的基类
 * @Date : 上午11:34 2018/2/5
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class BaseDealerRes extends BaseEntity{

    private static final long serialVersionUID = 5784557331967726755L;
    /**
     * 关联规则主键id
     */
    @ApiModelProperty(name="ruleSeq",value="关联规则主键id",required = true,dataType="Integer")
    @NotNull(message = "[规则id]不能为空")
    private Integer ruleSeq;
    /**
     * 门店code
     */
    @ApiModelProperty(name="dealerCode",value="门店code",required = true,dataType="Integer")
    @NotNull(message = "[门店code]不能为空")
    private Integer dealerCode;
    /**
     * 门店名称
     */
    @ApiModelProperty(name="dealerName",value="门店名称",required = false,dataType="String")
    private String dealerName;
    /**
     * 供应商名称
     */
    @ApiModelProperty(name="companyName",value="供应商名称",required = false,dataType="String")
    private String companyName;
    private Date updateTime;
    /**
     * 标示是否关联门店，适用于VIEW视图显示添加还是删除
     */
    @ApiModelProperty(name="linked",value="标示是否关联门店",required = false,dataType="Integer")
    private Integer linked;

    @ApiModelProperty(name="pId",value = "产品门店关联产品表pid")
    private Integer pId;

    public BaseDealerRes() {
    }

    public BaseDealerRes(Integer ruleSeq, Integer dealerCode) {
        this.ruleSeq = ruleSeq;
        this.dealerCode = dealerCode;
    }

    public BaseDealerRes(Integer ruleSeq, Integer dealerCode, Date updateTime) {
        this.ruleSeq = ruleSeq;
        this.dealerCode = dealerCode;
        this.updateTime = updateTime;
    }

    public Integer getRuleSeq() {
        return ruleSeq;
    }

    public void setRuleSeq(Integer ruleSeq) {
        this.ruleSeq = ruleSeq;
    }

    public Integer getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(Integer dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLinked() {
        return linked;
    }

    public void setLinked(Integer linked) {
        this.linked = linked;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
}
