package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;

public class SyDictionaryReg extends BaseEntity{
    private String regCode;

    private String regName;

    private String regCodePar;

    private String regLevel;

    private String memo;

    private String gbCode;

    private String aliasName;

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode == null ? null : regCode.trim();
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName == null ? null : regName.trim();
    }

    public String getRegCodePar() {
        return regCodePar;
    }

    public void setRegCodePar(String regCodePar) {
        this.regCodePar = regCodePar == null ? null : regCodePar.trim();
    }

    public String getRegLevel() {
        return regLevel;
    }

    public void setRegLevel(String regLevel) {
        this.regLevel = regLevel == null ? null : regLevel.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getGbCode() {
        return gbCode;
    }

    public void setGbCode(String gbCode) {
        this.gbCode = gbCode == null ? null : gbCode.trim();
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName == null ? null : aliasName.trim();
    }
}