package com.mljr.heil.entity;

import com.mljr.common.constraint.EnumConstraint;
import com.mljr.heil.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.NotNull;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PdTag extends BaseEntity {

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @NotNull(message = "[业务类型]不能为空")
    @ApiModelProperty(value = "业务类型")
    @EnumConstraint(enumClass="com.lyqc.heil.enums.TagConstant$BuzTypeEnum",message = "非法枚举值[buzType]")
    private Integer buzType;

    @ApiModelProperty(value = "关联id")
    private Integer sourceId;

    @ApiModelProperty(value = "标签，用逗号分割（比如：6厘6,6厘5）")
    private String tags;

    @NotNull(message = "[操作类型]不能为空")
    @ApiModelProperty(value = "操作类型")
    @EnumConstraint(enumClass="com.lyqc.base.enums.UserOperateLogConstant$AuthTypeEnum",message = "非法枚举值[optType]")
    private Integer optType;

    @NotNull(message = "[关联id]不能为空")
    @ApiModelProperty(value = "关联id")
    private List<Integer> sourceList;

    @NotNull(message = "[标签]不能为空")
    @ApiModelProperty(value = "标签，用逗号分割（比如：6厘6,6厘5）")
    private List<String> tagList;

    public Integer getOptType() {
        return optType;
    }

    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    public List<Integer> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<Integer> sourceList) {
        this.sourceList = sourceList;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuzType() {
        return buzType;
    }

    public void setBuzType(Integer buzType) {
        this.buzType = buzType;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }
}