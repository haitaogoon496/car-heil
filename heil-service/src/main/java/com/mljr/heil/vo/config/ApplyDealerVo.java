package com.mljr.heil.vo.config;

import com.mljr.common.constraint.EnumConstraint;
import com.mljr.heil.base.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.util.List;

/**
 * @description: 管理所属门店
 * @Date : 2018/5/29 下午8:07
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class ApplyDealerVo extends BaseVo{
    /**
     * 规则id
     */
    @ApiModelProperty(name="ruleSeq",value="关联规则主键id",required = true,dataType="Integer")
    @NotNull(message = "[规则id]不能为空")
    private Integer ruleSeq;
    /**
     * 规则类型
     * {@link com.mljr.heil.common.enums.ApplyDealerTableEnum#getIndex()}
     */
    @ApiModelProperty(name="classify",value="规则类型",required = true,dataType="Integer")
    @NotNull(message = "[classify]不能为空")
    @EnumConstraint(enumClass = "com.mljr.heil.common.enums.ApplyDealerTableEnum")
    private Integer classify;
    /**
     * 新增门店列表
     */
    @ApiModelProperty(name="insertList",value="新增门店列表",dataType="List<Integer>")
    @NotNull(message = "[新增门店列表]不能为空")
    private List<Integer> insertList;
    /**
     * 删除门店列表
     */
    @ApiModelProperty(name="deleteList",value="删除门店列表",dataType="List<Integer>")
    @NotNull(message = "[删除门店列表]不能为空")
    private List<Integer> deleteList;

    public Integer getRuleSeq() {
        return ruleSeq;
    }

    public void setRuleSeq(Integer ruleSeq) {
        this.ruleSeq = ruleSeq;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public List<Integer> getInsertList() {
        return insertList;
    }

    public void setInsertList(List<Integer> insertList) {
        this.insertList = insertList;
    }

    public List<Integer> getDeleteList() {
        return deleteList;
    }

    public void setDeleteList(List<Integer> deleteList) {
        this.deleteList = deleteList;
    }
}
