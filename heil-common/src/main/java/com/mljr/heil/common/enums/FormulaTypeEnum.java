package com.mljr.heil.common.enums;

import com.lyqc.base.enums.EnumValue;

/**
 * @description: 产品公式类型
 * @Date : 下午12:48 2018/2/5
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public enum FormulaTypeEnum implements EnumValue {
    DEFAULT(1, "缺省"),
    CUSTOM(2, "自定义");

    FormulaTypeEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    private int index;
    private String name;

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * 根据索引获取名称
     * @param index 索引
     * @return
     */
    public static String getNameByIndex(int index){
        for(FormulaTypeEnum e : FormulaTypeEnum.values()){
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
    public static FormulaTypeEnum getByIndex(int index){
        for(FormulaTypeEnum e : FormulaTypeEnum.values()){
            if(e.getIndex() == index){
                return e;
            }
        }
        return null;
    }
}
