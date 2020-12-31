package com.mljr.heil.vo.rule;

import com.mljr.heil.base.vo.BaseRuleVo;
import com.mljr.heil.entity.YanbaoTc;
import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.util.List;

/**
 * @description: 第二/三年保险费规则VO类
 * @Date : 下午5:13 2018/2/9
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class YanbaoRuleVo extends BaseRuleVo {
    private static final long serialVersionUID = -1600845940823715543L;
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
}
