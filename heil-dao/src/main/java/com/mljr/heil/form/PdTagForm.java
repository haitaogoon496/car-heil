package com.mljr.heil.form;

import com.lyqc.base.common.BaseForm;
import com.mljr.common.constraint.EnumConstraint;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotNull;

/**
 * @description 标签Form
 * @author zhaoxin
 * @date 2018/6/1 下午7:14
 **/
@Data
public class PdTagForm extends BaseForm {

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @NotNull(message = "[业务类型]不能为空")
    @ApiModelProperty(value = "业务类型")
    @EnumConstraint(enumClass="com.lyqc.heil.enums.TagConstant$BuzTypeEnum",message = "非法枚举值[buzType]")
    private Integer buzType;

    @ApiModelProperty(name="sourceId",value="关联id",dataType="Integer")
    private Integer sourceId;

    @ApiModelProperty(name="tags",value="标签，用逗号分割（比如：6厘6,6厘5）",dataType="String")
    private String tags;

    @ApiModelProperty(name="tagName",value="标签名称（查询使用）",dataType="String")
    private String tagName;
}
