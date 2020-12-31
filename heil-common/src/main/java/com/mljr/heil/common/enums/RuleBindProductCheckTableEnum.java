package com.mljr.heil.common.enums;

/**
 * @description: 元数据健康检测 - 费用规则&产品关系查询 - 表枚举
 * @Date : 2019/3/20 0020 15:16
 * @Author : 尚凌宇
 */
public enum RuleBindProductCheckTableEnum {

    GPS_RULE("ca_gps_rule", "pd_rule_product", "RULE_SEQ", "RULE_ID", 2),
    SER_FIN_RULE("ca_ser_fin_rule", "pd_rule_product", "RULE_SEQ", "RULE_ID", 1),
    RATE_RULE("ca_rate_rule", "pd_rule_product", "RULE_SEQ", "RULE_ID", 3);

    RuleBindProductCheckTableEnum(String masterTable, String slaveTable, String masterRefKey, String slaveRefKey, Integer classify) {
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
