package com.mljr.heil.base.vo;

import com.lyqc.base.enums.RuleConditionConstant;
import com.lyqc.util.EnumConvert;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Primary;

import java.util.Collections;
import java.util.List;

/**
 * 相关费用规则配置的VO基类
 * @Date : 下午5:53 2018/2/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public abstract class BaseRuleVo extends BaseVo{

    private static final long serialVersionUID = 2370587848079790252L;
    @ApiModelProperty(name="ruleSeq",value="主键",dataType="String")
    private String ruleSeq;
    @ApiModelProperty(name="ruleSeqName",value="规则名称",required = true,dataType="String")
    private String ruleSeqName;
    @ApiModelProperty(name="isOld",value="是否二手车",dataType="String")
    private String isOld;
    @ApiModelProperty(name="isLcv",value="车类",dataType="String")
    private String isLcv;
    @ApiModelProperty(name="loanAmountMiin",value="贷款最小金额",dataType="String")
    private String loanAmountMiin;
    @ApiModelProperty(name="loanAmountMax",value="贷款最大金额",dataType="String")
    private String loanAmountMax;
    @ApiModelProperty(name="initScaleMin",value="最小首付比例",dataType="String")
    private String initScaleMin;
    @ApiModelProperty(name="initScaleMax",value="最大首付比例",dataType="String")
    private String initScaleMax;
    @ApiModelProperty(name="rLoanPeriods",value="贷款期限",dataType="String")
    private String rLoanPeriods;
    @ApiModelProperty(name="isAllDealer",value="是否使用所有经销商",required = true,dataType="String")
    private String isAllDealer;
    @ApiModelProperty(name="beginDate",value="生效日期",dataType="String")
    private String beginDate;
    @ApiModelProperty(name="endDate",value="失效日期",dataType="String")
    private String endDate;
    @ApiModelProperty(name="proSeq",value="优先级",required = true,dataType="String")
    private String proSeq;
    private String var;
    private String createTime;
    private String updateTime;
    private String userId;
    private String userName;
    @ApiModelProperty(name="remarks",value="备注",dataType="String")
    private String remarks;
    @ApiModelProperty(name="rule1",value="是否提供房产证明",dataType="String")
    private String rule1;
    private String rule2;
    private String rule3;
    private String rule4;
    @ApiModelProperty(name = "isLcvStr",value = "乘用车字符串显示")
    private String isLcvStr;
    @ApiModelProperty(name = "tags",value = "标签",dataType = "List<String>")
    private List<String> tags = Collections.emptyList();

    @ApiModelProperty(name="isOldDesc",value="是否二手车",dataType="String")
    private String isOldDesc;
    @ApiModelProperty(name="isLcvDesc",value="车类",dataType="String")
    private String isLcvDesc;
    @ApiModelProperty(name="rule1Desc",value="是否提供房产证明",dataType="String")
    private String rule1Desc;
    @ApiModelProperty(name="rLoanPeriodsDesc",value="贷款期限",dataType="String")
    private String rLoanPeriodsDesc;
    @ApiModelProperty(value = "状态 1：有效 0：无效")
    private String status;

    public String getIsLcvStr() {
        return isLcvStr;
    }

    public void setIsLcvStr(String isLcvStr) {
        this.isLcvStr = isLcvStr;
    }

    public String getRuleSeq() {
        return ruleSeq;
    }

    public void setRuleSeq(String ruleSeq) {
        this.ruleSeq = ruleSeq;
    }

    public String getRuleSeqName() {
        return ruleSeqName;
    }

    public void setRuleSeqName(String ruleSeqName) {
        this.ruleSeqName = ruleSeqName;
    }

    public String getIsOld() {
        return isOld;
    }

    public void setIsOld(String isOld) {
        this.isOld = isOld;
    }

    public String getIsLcv() {
        return isLcv;
    }

    public void setIsLcv(String isLcv) {
        this.isLcv = isLcv;
    }

    public String getLoanAmountMiin() {
        return loanAmountMiin;
    }

    public void setLoanAmountMiin(String loanAmountMiin) {
        this.loanAmountMiin = loanAmountMiin;
    }

    public String getLoanAmountMax() {
        return loanAmountMax;
    }

    public void setLoanAmountMax(String loanAmountMax) {
        this.loanAmountMax = loanAmountMax;
    }

    public String getInitScaleMin() {
        return initScaleMin;
    }

    public void setInitScaleMin(String initScaleMin) {
        this.initScaleMin = initScaleMin;
    }

    public String getInitScaleMax() {
        return initScaleMax;
    }

    public void setInitScaleMax(String initScaleMax) {
        this.initScaleMax = initScaleMax;
    }

    public String getrLoanPeriods() {
        return rLoanPeriods;
    }

    public void setrLoanPeriods(String rLoanPeriods) {
        this.rLoanPeriods = rLoanPeriods;
    }

    public String getIsAllDealer() {
        return isAllDealer;
    }

    public void setIsAllDealer(String isAllDealer) {
        this.isAllDealer = isAllDealer;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProSeq() {
        return proSeq;
    }

    public void setProSeq(String proSeq) {
        this.proSeq = proSeq;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRule1() {
        return rule1;
    }

    public void setRule1(String rule1) {
        this.rule1 = rule1;
    }

    public String getRule2() {
        return rule2;
    }

    public void setRule2(String rule2) {
        this.rule2 = rule2;
    }

    public String getRule3() {
        return rule3;
    }

    public void setRule3(String rule3) {
        this.rule3 = rule3;
    }

    public String getRule4() {
        return rule4;
    }

    public void setRule4(String rule4) {
        this.rule4 = rule4;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getIsOldDesc() {
        return isOldDesc;
    }

    public void setIsOldDesc(String isOldDesc) {
        this.isOldDesc = isOldDesc;
    }

    public String getIsLcvDesc() {
        return isLcvDesc;
    }

    public void setIsLcvDesc(String isLcvDesc) {
        this.isLcvDesc = isLcvDesc;
    }

    public String getRule1Desc() {
        return rule1Desc;
    }

    public void setRule1Desc(String rule1Desc) {
        this.rule1Desc = rule1Desc;
    }

    public String getrLoanPeriodsDesc() {
        return rLoanPeriodsDesc;
    }

    public void setrLoanPeriodsDesc(String rLoanPeriodsDesc) {
        this.rLoanPeriodsDesc = rLoanPeriodsDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * VO 相关枚举索引值转换 枚举名称
     */
    public void  convertEnum(){
        this.setIsOldDesc(EnumConvert.convertIndex2String(RuleConditionConstant.IsOldEnum.values(),isOld,"不限"));
        this.setIsLcvDesc(EnumConvert.convertIndex2String(RuleConditionConstant.IsLcvEnum.values(),isLcv,"不限"));
        this.setRule1Desc(EnumConvert.convertIndex2String(RuleConditionConstant.IsHouseEnum.values(),rule1,"不限"));
        this.setrLoanPeriodsDesc(EnumConvert.convertIndex2String(RuleConditionConstant.LoanPeriodsEnum.values(),rLoanPeriods,"不限"));
    }
}
