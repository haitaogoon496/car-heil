package com.mljr.heil.common.enums;

import com.lyqc.base.enums.EnumDesc;

/**
 * 费用规则表枚举
 *
 * @author lingyu.shang
 */
public enum ApplyRuleTableEnum implements EnumDesc {

    CA_SER_FIN_RULE(1,"ca_ser_fin_rule","平台费规则信息"),

    CA_RATE_RULE(2,"ca_rate_rule","年利率规则"),

    CA_GPS_RULE(3,"ca_gps_rule","GPS费用规则"),

    CA_YAN_BAO_RULE(4,"ca_yan_bao_rule","第二/三年保险规则"),

    @Deprecated
    CA_EXTEND_SAFE_RULE(5,"ca_extend_safe_rule","延保费规则"),

    CA_ACCOUNT_RULE(6,"ca_account_rule","账户管理费规则"),

    PD_FEE_RULE(7,"pd_fee_rule","通用规则：人身险、续保押金、车辆保险、车辆贴息、盗抢险");

    ApplyRuleTableEnum(int index, String name, String desc) {
        this.index = index;
        this.name = name;
        this.desc = desc;
    }

    private int index;
    private String name;
    private String desc;

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    /**
     * 根据索引获取名称
     *
     * @param index 索引
     * @return
     */
    public static String getNameByIndex(int index) {
        for (ApplyRuleTableEnum e : ApplyRuleTableEnum.values()) {
            if (e.getIndex() == index) {
                return e.getName();
            }
        }
        return null;
    }

    /**
     * 根据索引获取枚举对象
     *
     * @param index 索引
     * @return
     */
    public static ApplyRuleTableEnum getByIndex(int index) {
        for (ApplyRuleTableEnum e : ApplyRuleTableEnum.values()) {
            if (e.getIndex() == index) {
                return e;
            }
        }
        return null;
    }

}
