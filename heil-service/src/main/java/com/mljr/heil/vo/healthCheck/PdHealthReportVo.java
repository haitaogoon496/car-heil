package com.mljr.heil.vo.healthCheck;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:健康检查数据报告
 * @Date : 2018/9/19 16:11
 * @Author : lihaitao
 */
@Data
public class PdHealthReportVo {
    @ApiModelProperty(value = "主键id")
    private String id;
    @ApiModelProperty(value = "报告生成时间")
    private String reportTime;
    @ApiModelProperty(value = "报告详情")
    private JSON reportDetail;

}