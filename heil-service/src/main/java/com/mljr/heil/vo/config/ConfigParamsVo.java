package com.mljr.heil.vo.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @Date : 2018/4/12$ 18:12$
 * @Author : liht
 */
@Data
public class ConfigParamsVo {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "参数key")
    private String paramKey;
    @ApiModelProperty(value = "参数值")
    private String paramValue;
    @ApiModelProperty(value = "参数描述")
    private String paramDesc;
    @ApiModelProperty(value = "状态(1:启用 0:暂停)")
    private String status;
    @ApiModelProperty(value = "参数类型(1-开关,2-JSON,3-数值,4-字符串)")
    private Integer paramType;
    @ApiModelProperty(value = "参数类型名称(1-开关,2-JSON,3-数值,4-字符串)")
    private String paramTypeName;
    @ApiModelProperty(name = "tags",value = "标签列表")
    private List<String> tags = Collections.emptyList();

}
