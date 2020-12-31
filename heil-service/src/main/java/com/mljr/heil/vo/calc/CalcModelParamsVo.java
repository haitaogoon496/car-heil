package com.mljr.heil.vo.calc;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @Date : 2018/3/30$ 18:11$
 * @Author : liht
 */
public class CalcModelParamsVo implements Serializable{

    private static final long serialVersionUID = -4871295269644626168L;
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "参数名称")
    private String paramName;
    @ApiModelProperty(value = "参数描述")
    private String paramDesc;
    @ApiModelProperty(value = "参数状态（0:停用 ，1:启用）")
    private String status;
    @ApiModelProperty(value = "是否自定义参数（0:否 ，1:是）")
    private String custom;
    @ApiModelProperty(value = "插入时间")
    private String createTime;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
