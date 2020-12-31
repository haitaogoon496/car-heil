package com.mljr.heil.common.enums;

import com.lyqc.base.enums.EnumDesc;
import com.lyqc.base.enums.ProductProviderApiEnum;

/**
 * @description:
 * @Date : 2018/6/28$ 14:47$
 * @Author : liht
 */
public enum ClientUrlApiEnum implements EnumDesc {

    getProvinceApi(1, "/dicReg/getProvinceList", "CarStaff调用户籍省份接口"),
    getCityApi(2, "/dicReg/getCityByProvinceCode", "CarStaff调用户籍城市接口");

    private Integer index;
    private String name;
    private String value;

    ClientUrlApiEnum(Integer index, String name, String desc) {
        this.index = index;
        this.name = name;
        this.value = desc;
    }

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
        return this.value;
    }


    public static String getNameByIndex(int index) {
        for (ProductProviderApiEnum e : ProductProviderApiEnum.values()) {
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
    public static ProductProviderApiEnum getByIndex(int index) {
        for (ProductProviderApiEnum e : ProductProviderApiEnum.values()) {
            if (e.getIndex() == index) {
                return e;
            }
        }
        return null;
    }

    /**
     * 根据索引获取枚举对象
     *
     * @param desc 索引
     * @return
     */
    public static ProductProviderApiEnum getByDesc(String desc) {
        for (ProductProviderApiEnum e : ProductProviderApiEnum.values()) {
            if (e.getDesc().equals(desc)) {
                return e;
            }
        }
        return null;
    }
    }
