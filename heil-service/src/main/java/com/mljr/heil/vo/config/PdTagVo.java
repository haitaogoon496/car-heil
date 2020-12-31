package com.mljr.heil.vo.config;

import com.mljr.common.constraint.EnumConstraint;
import com.mljr.heil.base.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotNull;
import java.util.List;

/**
 * @description 标签Vo
 * @author zhaoxin
 * @date 2018/6/1 下午7:17
 **/
@Data
public class PdTagVo extends BaseVo {
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @NotNull(message = "[业务类型]不能为空")
    @ApiModelProperty(name="buzType",value="业务类型",required = true,dataType="Integer")
    @EnumConstraint(enumClass="com.lyqc.heil.enums.TagConstant$BuzTypeEnum",message = "非法枚举值[buzType]")
    private Integer buzType;

    @NotNull(message = "[关联id]不能为空")
    @ApiModelProperty(name="sourceId",value="关联id",required = true,dataType="Integer")
    private Integer sourceId;

    @ApiModelProperty(name="tagList",value="标签",dataType="List<String>")
    private List<String> tagList;

    @ApiModelProperty(name="buzTypeName", value = "业务类型名称", dataType = "String")
    private String buzTypeName;
}
