package com.mljr.heil.vo.product;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ProductContractVo{
    @ApiModelProperty(value = "主键id")
    private String id;
    @ApiModelProperty(value = "适用产品")
    private String productIds;
    @ApiModelProperty(value = "区分直营渠道")
    private String systemCode;
    @ApiModelProperty(value = "产品描述")
    private String productDes;
    @ApiModelProperty(value = "状态，0：停用，1：可用")
    private String status;
    @ApiModelProperty(value = "合同key")
    private String contractKey;
    @ApiModelProperty(value = "合同名")
    private String contractName;
    @ApiModelProperty(value = "合同类型")
    private String contractType;
    @ApiModelProperty(value = "合同签署的阶段，10：提交阶段，20：贷前阶段")
    private String step;
    @ApiModelProperty(value = "公牌，1：是公牌，其他：非公牌")
    private String isCompanyLicense;
    @ApiModelProperty(value = "查询规则1（保留字段）")
    private String rule01;
    @ApiModelProperty(value = "查询规则2（保留字段）")
    private String rule02;
    @ApiModelProperty(value = "查询规则3（保留字段）")
    private String rule03;
    @ApiModelProperty(value = "查询规则4（保留字段）")
    private String rule04;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;
    @ApiModelProperty(value = "创建人")
    private String creater;
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds == null ? null : productIds.trim();
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes == null ? null : productDes.trim();
    }



    public String getContractKey() {
        return contractKey;
    }

    public void setContractKey(String contractKey) {
        this.contractKey = contractKey == null ? null : contractKey.trim();
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getIsCompanyLicense() {
        return isCompanyLicense;
    }

    public void setIsCompanyLicense(String isCompanyLicense) {
        this.isCompanyLicense = isCompanyLicense;
    }

    public String getRule01() {
        return rule01;
    }

    public void setRule01(String rule01) {
        this.rule01 = rule01;
    }

    public String getRule02() {
        return rule02;
    }

    public void setRule02(String rule02) {
        this.rule02 = rule02;
    }

    public String getRule03() {
        return rule03;
    }

    public void setRule03(String rule03) {
        this.rule03 = rule03;
    }

    public String getRule04() {
        return rule04;
    }

    public void setRule04(String rule04) {
        this.rule04 = rule04;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}