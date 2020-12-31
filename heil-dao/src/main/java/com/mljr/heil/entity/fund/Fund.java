package com.mljr.heil.entity.fund;

import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @description: 资金方实体类
 * @Date : 2018/6/12 17:11
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public class Fund extends BaseEntity {

    private static final long serialVersionUID = -224979269475108321L;

    @ApiModelProperty(name = "fundNo", value = "资金方代码", required = false, dataType = "String")
    private String fundNo;

    @ApiModelProperty(name = "fundName", value = "资金方名称", required = false, dataType = "String")
    private String fundName;

    @ApiModelProperty(name = "status", value = "状态(1:有效 0:无效)", required = false, dataType = "Integer")
    private Integer status;

    @ApiModelProperty(name = "remark", value = "备注", required = false, dataType = "String")
    private String remark;

    @ApiModelProperty(name = "createTime", value = "创建日期", required = false, dataType = "Date")
    private Date createTime;

    @ApiModelProperty(name = "updateTime", value = "修改日期", required = false, dataType = "Date")
    private Date updateTime;


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
