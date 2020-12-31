package com.mljr.heil.common.enums;

import com.lyqc.base.enums.EnumValue;

/**
 * @description: 用户级别枚举类型
 * @Date : 2018/4/17 10:50
 * @Author : lihaitao
 */
public enum UserLevelEnum  {
    SA("SA", "系统管理员"),
    NORMAL("NORMAL", "普通"),
    GUEST("GUEST", "访客");

    UserLevelEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    /**
     * 根据索引获取名称
     * @param code 索引
     * @return
     */
    public static String getNameByCode(String code){
        for(UserLevelEnum e : UserLevelEnum.values()){
            if(e.getCode().equals(code)){
                return e.getName();
            }
        }
        return null;
    }

}
