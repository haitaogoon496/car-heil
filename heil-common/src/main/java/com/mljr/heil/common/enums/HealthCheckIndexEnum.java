package com.mljr.heil.common.enums;

/**
 * @description:    产品中心健康检查指标
 * @Date : 2018/7/23 11:03
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public enum HealthCheckIndexEnum {
    USELESSDEALER_BINDCHECK(1,"无效门店绑定规则"),
    USELESSPRODUCT_BINDCHECK(2,"失效产品绑定门店检查"),
    FEE_RULE_UNBOUND_PRODUCT(3,"费用项规则未绑定（启用中）产品"),
    PRODUCT_DEALER_UNMATCHED_SERFIN_RULE(4,"产品与绑定门店无相同平台费规则"),
    PRODUCT_DEALER_UNMATCHED_GPS_RULE(5,"产品与绑定门店无相同GPS规则"),
    PRODUCT_DEALER_UNMATCHED_RATE_RULE(6,"产品与绑定门店无相同利率规则"),
    FEE_RULE_UNBOUND_DEALER(7,"费用项规则未绑定（启用中）门店"),
    PRODUCT_NO_NECESSARY_RULE(8,"启用中产品未绑定必要规则（平台费/利率/gps/门店）"),
    PRODUCT_SERFIN_MUTEX(9,"产品与绑定平台费互斥");

    HealthCheckIndexEnum(int index ,String desc){
        this.index = index;
        this.desc = desc;
    }

    private int index;
    private String desc;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
