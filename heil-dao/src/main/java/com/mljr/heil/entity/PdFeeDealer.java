package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: 人身保险费规则 管理门店
 * @Date : 2018/2/27 下午2:41
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class PdFeeDealer extends BaseEntity {
    private static final long serialVersionUID = 3868875380034443202L;

    private Integer resId;
    /**
     * 门店code
     */
    @ApiModelProperty(name="dealerCode",value="门店code",required = false,dataType="Integer")
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
    /**
     * 标示是否关联门店，适用于VIEW视图显示添加还是删除
     */
    @ApiModelProperty(name="linked",value="标示是否关联门店",required = false,dataType="Integer")
    private Integer linked;

    private String remarks;

    public PdFeeDealer() {
    }

    public PdFeeDealer(Integer resId, Integer dealerCode) {
        this.resId = resId;
        this.dealerCode = dealerCode;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
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

    public Integer getLinked() {
        return linked;
    }

    public void setLinked(Integer linked) {
        this.linked = linked;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}