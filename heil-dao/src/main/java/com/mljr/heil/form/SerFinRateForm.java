package com.mljr.heil.form;

import com.mljr.heil.base.form.BaseRuleForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import java.io.Serializable;

/**
 * @description:
 * @Date : 2018/3/5$ 15:56$
 * @Author : liht
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SerFinRateForm extends BaseRuleForm implements Serializable{

    private static final long serialVersionUID = -7061472519839765454L;
    private Integer ruleSeq;

    private List<Integer> ruleSeqList;

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
