package com.mljr.heil.common.enums;

import com.lyqc.base.enums.EnumDesc;

/**
 * @description: 模块LogTitle枚举
 * @Date : 下午12:48 2018/2/5
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public enum ApplyDealerTableEnum implements EnumDesc {
    GPS_RULE(1, "ca_gps_dealer","GPS规则", "rule_seq", "dealer_code"),
    RATE_RULE(2, "ca_rate_dealer","利率规则", "rule_seq", "dealer_code"),
    SER_FIN_RULE(3, "ca_ser_fin_dealer","平台费规则", "rule_seq", "dealer_code"),
    SECOND_INSURANCE_RULE(4, "ca_yan_bao_dealer","第二年保险费规则", "rule_seq", "dealer_code"),
    THIRD_INSURANCE_RULE(5, "ca_yan_bao_dealer","第三年保险费规则", "rule_seq", "dealer_code"),
    ACCOUNT_RULE(6, "ca_account_dealer","账号管理费规则", "rule_seq", "dealer_code"),
    @Deprecated
    EXTEND_SAFE_RULE(7, "ca_extend_safe_dealer","延保费规则", "rule_seq", "dealer_code"),
    PRODUCT_RULE(8, "ca_product_dealer","车贷产品管理", "P_ID", "dealer_code"),
    PD_FEE_RULE(9, "pd_fee_dealer","人身保险费规则", "res_id", "dealer_code");

    ApplyDealerTableEnum(int index, String name,String desc, String refIdName, String dealerName) {
        this.index = index;
        this.name = name;
        this.desc = desc;
        this.refIdName = refIdName;
        this.dealerName = dealerName;
    }

    private int index;
    private String name;
    private String desc;
    private String refIdName;
    private String dealerName;

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

    public String getRefIdName() {
        return refIdName;
    }

    public String getDealerName() {
        return dealerName;
    }

    /**
     * 根据索引获取名称
     * @param index 索引
     * @return
     */
    public static String getNameByIndex(int index){
        for(ApplyDealerTableEnum e : ApplyDealerTableEnum.values()){
            if(e.getIndex() == index){
                return e.getName();
            }
        }
        return null;
    }

    /**
     * 根据索引获取枚举对象
     * @param index 索引
     * @return
     */
    public static ApplyDealerTableEnum getByIndex(int index){
        for(ApplyDealerTableEnum e : ApplyDealerTableEnum.values()){
            if(e.getIndex() == index){
                return e;
            }
        }
        return null;
    }

}
