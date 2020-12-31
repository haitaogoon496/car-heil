package com.mljr.heil.common.enums;

import com.lyqc.base.enums.ProductConstant;

/**
 * @description: 元数据健康检测 - 费用规则&门店关系查询 - 表枚举
 * @Date : 2019/3/19 0019 15:00
 * @Author : 尚凌宇
 */
public enum RuleBindDealerCheckTableEnum {

    YAN_BAO_RULE_SECIBD("ca_yan_bao_rule", "ca_yan_bao_dealer", "RULE_SEQ", "RULE_SEQ", ProductConstant.YbtcClassifyEnum.SECOND_YEAR.getIndex()),
    YAN_BAO_RULE_THIRD("ca_yan_bao_rule", "ca_yan_bao_dealer", "RULE_SEQ", "RULE_SEQ", ProductConstant.YbtcClassifyEnum.THIRD_YEAR.getIndex()),
    ACCOUNT_RULE("ca_account_rule", "ca_account_dealer", "RULE_SEQ", "RULE_SEQ", null),
    FEE_RULE_LIFE_INSURANCE("pd_fee_rule", "pd_fee_dealer", "id", "res_id", ProductConstant.FeeRuleClassifyEnum.LIFE_INSURANCE.getIndex()),
    FEE_RULE_RENEWAL_COMMISSION("pd_fee_rule", "pd_fee_dealer", "id", "res_id", ProductConstant.FeeRuleClassifyEnum.RENEWAL_COMMISSION.getIndex()),
    GPS_RULE("ca_gps_rule", "ca_gps_dealer", "RULE_SEQ", "RULE_SEQ", null),
    SER_FIN_RULE("ca_ser_fin_rule", "ca_ser_fin_dealer", "RULE_SEQ", "RULE_SEQ", null),
    RATE_RULE("ca_rate_rule", "ca_rate_dealer", "RULE_SEQ", "RULE_SEQ", null);

    RuleBindDealerCheckTableEnum(String masterTable, String slaveTable, String masterRefKey, String slaveRefKey, Integer classify) {
        this.masterTable = masterTable;
        this.slaveTable = slaveTable;
        this.masterRefKey = masterRefKey;
        this.slaveRefKey = slaveRefKey;
        this.classify = classify;
    }

    private String masterTable;
    private String slaveTable;
    private String masterRefKey;
    private String slaveRefKey;
    private Integer classify;

    public String getMasterTable() {
        return masterTable;
    }

    public String getSlaveTable() {
        return slaveTable;
    }

    public String getMasterRefKey() {
        return masterRefKey;
    }

    public String getSlaveRefKey() {
        return slaveRefKey;
    }

    public Integer getClassify() {
        return classify;
    }
}
