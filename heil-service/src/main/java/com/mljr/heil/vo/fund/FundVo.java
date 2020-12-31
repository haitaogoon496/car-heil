package com.mljr.heil.vo.fund;

import com.mljr.heil.base.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @description: xia
 * @Date : 2018/6/12 19:06
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public class FundVo extends BaseVo{
    private static final long serialVersionUID = -5303858043040087384L;
    @ApiModelProperty(name="id",value="主键id",required = true,dataType="Integer")
    private Integer id;

    @ApiModelProperty(name = "fundNo", value = "资金方代码", required = false, dataType = "String")
    private String fundNo;

    @ApiModelProperty(name = "fundName", value = "资金方名称", required = false, dataType = "String")
    private String fundName;

    @ApiModelProperty(name = "status", value = "状态(1:有效 0:无效)", required = false, dataType = "Integer")
    private Integer status;
    @ApiModelProperty(value = "状态描述")
    private String statusDesc;

    @ApiModelProperty(name = "remark", value = "备注", required = false, dataType = "String")
    private String remark;

    @ApiModelProperty(name = "createTime", value = "创建日期", required = false, dataType = "Date")
    private Date createTime;

    @ApiModelProperty(name = "updateTime", value = "修改日期", required = false, dataType = "Date")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFundNo() {
        return fundNo;
    }

    public void setFundNo(String fundNo) {
        this.fundNo = fundNo;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
