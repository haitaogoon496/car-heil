package com.mljr.heil.form;

import com.lyqc.base.common.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description:健康检查数据报告
 * @Date : 2018/9/19$ 16:08$
 * @Author : liht
 */
@Data
public class PdHealthReportForm extends BaseForm{
    @ApiModelProperty(value = "主键id")
    private Integer id;
    @ApiModelProperty(value = "报告生成时间")
    private Date reportTime;

}
