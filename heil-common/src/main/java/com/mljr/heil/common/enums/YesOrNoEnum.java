package com.mljr.heil.common.enums;

import com.lyqc.base.enums.EnumValue;

/**
 * @description: 是否、有效/无效、枚举
 * @Date : 下午12:48 2018/2/5
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public enum YesOrNoEnum implements EnumValue {
    YES(0, "是"),
    NO(1, "否");

    YesOrNoEnum(int index, String name) {
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
        for(YesOrNoEnum e : YesOrNoEnum.values()){
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
    public static YesOrNoEnum getByIndex(int index){
        for(YesOrNoEnum e : YesOrNoEnum.values()){
            if(e.getIndex() == index){
                return e;
            }
        }
        return null;
    }
}
