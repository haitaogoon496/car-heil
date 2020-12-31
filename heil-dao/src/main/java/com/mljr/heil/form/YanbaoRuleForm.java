package com.mljr.heil.form;

import com.mljr.heil.base.form.BaseRuleForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 第二/三年保险费规则配置
 * @Date : 下午3:29 2018/2/11
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Data
public class YanbaoRuleForm extends BaseRuleForm {
    private static final long serialVersionUID = 8333278872033470843L;
    /**
     * 类别 (2:第二年 3:第三年)
     */
    private Integer classify;

    @ApiModelProperty(name="tcFee",value="套餐金额",required = true,dataType="BigDecimal")
    private BigDecimal tcFee;

    @ApiModelProperty(value = "tag标签对应的业务类型")
    private Byte buzType;
}
