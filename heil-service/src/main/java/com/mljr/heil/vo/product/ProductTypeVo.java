package com.mljr.heil.vo.product;

import com.mljr.heil.base.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: 产品分类VO
 * @Date : 下午7:52 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class ProductTypeVo extends BaseVo {
    @ApiModelProperty(name="pTypeId",value="主键id",required = true,dataType="Integer")
    private Integer pTypeId;
    @ApiModelProperty(name="pClassCode",value="分类code",required = true,dataType="String")
    private String pClassCode;
    @ApiModelProperty(name="pClassName",value="分类名称",required = true,dataType="String")
    private String pClassName;
    @ApiModelProperty(name="pDesc",value="描述",required = true,dataType="String")
    private String pDesc;
    @ApiModelProperty(name="status",value="状态",dataType="String")
    private String status;
    @ApiModelProperty(name="createUser",value="创建人",dataType="String")
    private String createUser;
    @ApiModelProperty(name="createTime",value="创建时间",dataType="String")
    private String createTime;

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
        this.pClassCode = pClassCode;
    }

    public String getpClassName() {
        return pClassName;
    }

    public void setpClassName(String pClassName) {
        this.pClassName = pClassName;
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
