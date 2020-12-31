package com.mljr.heil.base.entity;

import com.mljr.heil.base.settable.NullSettable;
import com.mljr.util.NumberUtil;
import com.mljr.util.TimeTools;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;

import java.util.Date;

/**
 * @description: 相关费用规则基类
 * @Date : 上午10:23 2018/2/10
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public abstract class BaseRule extends BaseEntity implements NullSettable {
    private static final long serialVersionUID = 3115820418329996630L;
    @ApiModelProperty(name="ruleSeq",value="主键",required = false,dataType="Integer")
    private Integer ruleSeq;
    @ApiModelProperty(name="ruleSeqName",value="规则名称",required = true,dataType="String")
    @NotNull(message = "[规则名称]不能为空")
    private String ruleSeqName;
    @ApiModelProperty(name="isOld",value="是否二手车",dataType="String")
    private String isOld;
    @ApiModelProperty(name="isLcv",value="车类",dataType="String")
    private String isLcv;
    @ApiModelProperty(name="loanAmountMiin",value="贷款最小金额",dataType="Double")
    @Length(max = 10000000,message = "贷款最大金额必须0~10000000期间")
    private Double loanAmountMiin;
    @Length(max = 10000000,message = "贷款最大金额必须0~10000000期间")
    @ApiModelProperty(name="loanAmountMax",value="贷款最大金额",dataType="Double")
    private Double loanAmountMax;
    @ApiModelProperty(name="initScaleMin",value="最小首付比例",dataType="Double")
    @Length(max = 100,message = "最小首付比例必须0~100期间")
    private Double initScaleMin;
    @ApiModelProperty(name="initScaleMax",value="最大首付比例",dataType="Double")
    @Length(max = 100,message = "最大首付比例必须0~100期间")
    private Double initScaleMax;
    @ApiModelProperty(name="rLoanPeriods",value="贷款期限",dataType="String")
    private String rLoanPeriods;
    @ApiModelProperty(name="isAllDealer",value="是否使用所有经销商",required = true,dataType="Integer")
    @NotNull(message = "[是否使用所有经销商]不能为空")
    private Integer isAllDealer;
    @ApiModelProperty(name="beginDate",value="生效日期",dataType="String")
    private String beginDate;
    @ApiModelProperty(name="endDate",value="失效日期",dataType="String")
    private String endDate;
    @NotNull(message = "[优先级]不能为空")
    @ApiModelProperty(name="proSeq",value="优先级",required = true,dataType="Integer")
    private Integer proSeq;
    private Integer var;
    private Date createTime;
    private Date updateTime;
    @ApiModelProperty(name="userId",value="userId",dataType="Integer")
    private Integer userId;
    @ApiModelProperty(name="userName",value="userName",dataType="String")
    private String userName;
    @ApiModelProperty(name="remarks",value="备注",dataType="String")
    private String remarks;
    @ApiModelProperty(name="rule1",value="是否提供房产证明",dataType="String")
    private String rule1;
    private String rule2;
    private String rule3;
    private String rule4;
    @ApiModelProperty(value = "状态-1：可用 0：不可用")
    private Byte status;


    public Integer getRuleSeq() {
        return ruleSeq;
    }

    public void setRuleSeq(Integer ruleSeq) {
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

    public Double getLoanAmountMiin() {
        return loanAmountMiin;
    }

    public void setLoanAmountMiin(Double loanAmountMiin) {
        this.loanAmountMiin = loanAmountMiin;
    }

    public Double getLoanAmountMax() {
        return loanAmountMax;
    }

    public void setLoanAmountMax(Double loanAmountMax) {
        this.loanAmountMax = loanAmountMax;
    }

    public Double getInitScaleMin() {
        return initScaleMin;
    }

    public void setInitScaleMin(Double initScaleMin) {
        this.initScaleMin = initScaleMin;
    }

    public Double getInitScaleMax() {
        return initScaleMax;
    }

    public void setInitScaleMax(Double initScaleMax) {
        this.initScaleMax = initScaleMax;
    }

    public String getrLoanPeriods() {
        return rLoanPeriods;
    }

    public void setrLoanPeriods(String rLoanPeriods) {
        this.rLoanPeriods = rLoanPeriods;
    }

    public Integer getIsAllDealer() {
        return isAllDealer;
    }

    public void setIsAllDealer(Integer isAllDealer) {
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

    public Integer getProSeq() {
        return proSeq;
    }

    public void setProSeq(Integer proSeq) {
        this.proSeq = proSeq;
    }

    public Integer getVar() {
        return var;
    }

    public void setVar(Integer var) {
        this.var = var;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public void setterIfNull() {
        Date currentTime = TimeTools.createNowTime();
        this.setBeginDate(null == this.getBeginDate() ? TimeTools.format4YYYYMMDDHHMISS(currentTime) : this.getBeginDate());
        this.setLoanAmountMiin(NumberUtil.isNull(this.getLoanAmountMiin(),0.00));
        this.setLoanAmountMax(NumberUtil.isNull(this.getLoanAmountMax(),10000000.0));
        this.setInitScaleMin(NumberUtil.isNull(this.getInitScaleMin(),0.0));
        this.setInitScaleMax(NumberUtil.isNull(this.getInitScaleMax(),100.00));
        this.setUpdateTime(currentTime);
        if(null == this.getRuleSeq()){
            this.setCreateTime(currentTime);
        }
    }
}