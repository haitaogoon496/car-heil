package com.mljr.heil.common.enums;

/**
 * @description:
 * @Date : 2018/7/27 16:16
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public enum ProductMutexSerFinRuleEnum {
    NO_MUTEX(0,"无冲突"),
    PRODUCT_MUTEX_LOANAMOUNT(1,"产品与平台费贷款金额区间冲突"),
    PRODUCT_MUTEX_INITSCALE(2,"产品与平台费首付比区间冲突"),
    PRODUCT_OR_RULE_RANGE_FILE_NULL(3,"产品或平台费区间规则字段为空"),
    PRODUCT_MUTEX_IS_OLD(4,"产品与平台费是否二手车无相同"),
    PRODUCT_MUTEX_IS_LCV(5,"产品与平台费车类型冲突"),
    PRODUCT_MUTEX_LOANPERIODS(6,"产品与平台费贷款期数冲突"),
    PRODUCT_MUTEX_IS_HOUSE(7,"产品与平台费是否提供房产冲突");

    ProductMutexSerFinRuleEnum(Integer index, String name){
        this.index = index;
        this.name = name;
    };
    private Integer index;
    private String name;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
