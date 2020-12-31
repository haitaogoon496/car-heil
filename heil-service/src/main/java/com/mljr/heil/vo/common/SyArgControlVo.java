package com.mljr.heil.vo.common;

import com.mljr.heil.base.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @description: 系统配置数据字典VO
 * @Date : 下午6:34 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class SyArgControlVo extends BaseVo{
    private static final long serialVersionUID = 6568917065333718600L;
    /**
     * 主键id
     */
    @ApiModelProperty(name="id",value="id",dataType="Integer")
    private Integer id;
    /**
     * 参数名称
     */
    @ApiModelProperty(name="name",value="参数名称",dataType="String")
    private String name;
    /**
     * 参数编码
     */
    @ApiModelProperty(name="code",value="参数编码",dataType="String")
    private String code;
    /**
     * 参数值
     */
    @ApiModelProperty(name="value",value="参数值",dataType="String")
    private String value;



    @ApiModelProperty(name="conArgName",value="参数名",dataType="String")
    private String conArgName;
    @ApiModelProperty(name="conArgCode",value="控制参数编码",dataType="String")
    private String conArgCode;
    @ApiModelProperty(name="conArgValue",value="参数值",dataType="String")
    private String conArgValue;
    @ApiModelProperty(name="conArgStatus",value="控制参数状态 1 有效；  0 无效",dataType="String")
    private String conArgStatus;
    @ApiModelProperty(name="conArgType",value="参数类型",dataType="String")
    private String conArgType;
    @ApiModelProperty(name="conArgStartDate",value="参数生效期",dataType="String")
    private String conArgStartDate;
    @ApiModelProperty(name="conArgStopDate",value="参数失效期",dataType="String")
    private String conArgStopDate;
    @ApiModelProperty(value = "标签")
    private List<String> tags;


    private SyArgControlVo relevance;

    public SyArgControlVo() {
    }

    public SyArgControlVo(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public SyArgControlVo(Integer id, String name, String code, String value) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getConArgStatus() {
        return conArgStatus;
    }

    public void setConArgStatus(String conArgStatus) {
        this.conArgStatus = conArgStatus;
    }

    public String getConArgType() {
        return conArgType;
    }

    public void setConArgType(String conArgType) {
        this.conArgType = conArgType;
    }

    public String getConArgStartDate() {
        return conArgStartDate;
    }

    public void setConArgStartDate(String conArgStartDate) {
        this.conArgStartDate = conArgStartDate;
    }

    public String getConArgStopDate() {
        return conArgStopDate;
    }

    public void setConArgStopDate(String conArgStopDate) {
        this.conArgStopDate = conArgStopDate;
    }

    public String getConArgName() {
        return conArgName;
    }

    public void setConArgName(String conArgName) {
        this.conArgName = conArgName;
    }

    public String getConArgCode() {
        return conArgCode;
    }

    public void setConArgCode(String conArgCode) {
        this.conArgCode = conArgCode;
    }

    public String getConArgValue() {
        return conArgValue;
    }

    public void setConArgValue(String conArgValue) {
        this.conArgValue = conArgValue;
    }

    public SyArgControlVo getRelevance() {
        return relevance;
    }

    public void setRelevance(SyArgControlVo relevance) {
        this.relevance = relevance;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
