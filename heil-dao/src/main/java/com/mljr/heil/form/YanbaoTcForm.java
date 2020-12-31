package com.mljr.heil.form;

import com.mljr.heil.base.form.BaseRuleForm;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 第二/三年保险费规则 套餐 配置
 * @Date : 下午3:31 2018/2/11
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class YanbaoTcForm extends BaseRuleForm {
    private static final long serialVersionUID = -5443522004077919272L;
    /**
     * 规则id
     */
    private Integer ruleSeq;

    private List<Integer> ruleSeqList;

    public YanbaoTcForm() {
    }

    public YanbaoTcForm(Integer ruleSeq) {
        this.ruleSeq = ruleSeq;
    }

    public Integer getRuleSeq() {
        return ruleSeq;
    }

    public void setRuleSeq(Integer ruleSeq) {
        this.ruleSeq = ruleSeq;
    }

    public List<Integer> getRuleSeqList() {
        return ruleSeqList;
    }

    public void setRuleSeqList(List<Integer> ruleSeqList) {
        this.ruleSeqList = ruleSeqList;
    }

}
