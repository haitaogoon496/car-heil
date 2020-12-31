package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import net.sf.oval.constraint.NotNull;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
/**
 * @description: 产品分类
 * @Date : 2018/3/3 下午7:46
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class ProductType extends BaseEntity{
    @ApiModelProperty(value = "主键id")
    private Integer pTypeId;
    @NotNull(message = "贷款产品类型编码不能为空")
    @ApiModelProperty(value = "贷款产品类型编码")
    private String pClassCode;
    @ApiModelProperty(value = "贷款产品类型名称")
    @NotNull(message = "贷款产品类型名称不能为空")
    private String pClassName;
    @ApiModelProperty(value = "描述")
    private String pDesc;
    @ApiModelProperty(value = "状态 0 停用； 1启用")
    @NotNull(message = "产品类型状态不能为空")
    private String status;

    private String createUser;

    private Date createTime;

    public Integer getpTypeId() {
        return pTypeId;
    }

    public void setpTypeId(Integer pTypeId) {
        this.pTypeId = pTypeId;
    }

    public String getpClassCode() {
        return pClassCode;
    }

    public void setpClassCode(String pClassCode) {
        this.pClassCode = pClassCode == null ? null : pClassCode.trim();
    }

    public String getpClassName() {
        return pClassName;
    }

    public void setpClassName(String pClassName) {
        this.pClassName = pClassName == null ? null : pClassName.trim();
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc == null ? null : pDesc.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}