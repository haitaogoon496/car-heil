package com.mljr.heil.form;

import com.mljr.heil.base.form.BaseRuleForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotNull;

/**
 * @description: 人身保险费规则Form类
 * @Date : 下午5:48 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Data
public class PdFeeRuleForm extends BaseRuleForm {
    private static final long serialVersionUID = 5712562973876418082L;
    @ApiModelProperty(name="classify",value="费用类别",required = true,dataType="Integer")
    @NotNull(message = "[费用类别]不能为空")
    private Integer classify;

    private Integer status;
    @ApiModelProperty(value = "tag标签对应的业务类型")
    private Byte buzType;


}
