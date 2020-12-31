package com.mljr.heil.entity;


import com.mljr.heil.base.entity.BaseRule;
import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.util.List;

/**
 * @description: 第二/三年保险费规则配置
 * @Date : 2018/2/11 下午3:18
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class YanbaoRule extends BaseRule {
    @ApiModelProperty(name="classify",value="类别",required = true,dataType="Integer")
    @NotNull(message = "[类别]不能为空")
    private Integer classify;
    @ApiModelProperty(name="tcList",value="套餐",required = true,dataType="List")
    @NotNull(message = "[套餐]不能为空")
    private List<YanbaoTc> tcList;

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public List<YanbaoTc> getTcList() {
        return tcList;
    }

    public void setTcList(List<YanbaoTc> tcList) {
        this.tcList = tcList;
    }

    @Override
    public void setterIfNull() {
        super.setterIfNull();
    }
}