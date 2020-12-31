package com.mljr.heil.common.enums;

import com.lyqc.base.enums.AutoApprConstant;
import com.lyqc.base.enums.CarLoanConstant;
import com.lyqc.base.enums.ContractConstant;
import com.lyqc.base.enums.DealerTypeEnum;
import com.lyqc.base.enums.EnumValue;
import com.lyqc.base.enums.ProductConstant;
import com.lyqc.base.enums.ProductEngineConstant;
import com.lyqc.base.enums.SerFinConstant;
import com.lyqc.base.enums.SystemCodeEnum;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.heil.enums.LicenceTypeEnum;
import com.lyqc.heil.enums.TagConstant;
import com.lyqc.product.enums.ConstEnum;
import com.lyqc.product.enums.FundNoEnum;

/**
 * @description: 前端页面所需数据字典接口
 * @Date : 下午9:07 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public enum DictionaryEnum {
    IS_OLD(DictionaryConstant.YesOrNoEnum.values(),"isOld"),
    IS_HOUSE(DictionaryConstant.YesOrNoEnum.values(),"isHouse"),
    IS_DEALER(DictionaryConstant.YesOrNoEnum.values(),"isDealer"),
    IS_PRODUCT(DictionaryConstant.YesOrNoEnum.values(),"isProduct"),
    PRIORITY(DictionaryConstant.PriorityEnum.values(),"priority"),
    CARRY(DictionaryConstant.CarryEnum.values(),"carry"),
    PRECISIONS(DictionaryConstant.PrecisionsEnum.values(),"precisions"),
    PRECISIONS_FOR_CAR_PRODUCT(DictionaryConstant.PrecisionsForCarProductEnum.values(),"precisionsForCarProduct"),
    COM_FLAG(DictionaryConstant.ComFlagEnum.values(),"comFlag"),
    RULE_CLASS_NAME(DictionaryConstant.RuleClassNameEnum.values(), "ruleClassName"),
    PRODUCT_STATUS(ProductConstant.ProductStatusEnum.values(), "productStatus"),
    TAKEN_MODE(SerFinConstant.TakenModeEnum.values(), "takenMode"),
    COMPONENT_CLASS_NAME(DictionaryConstant.ComponentClassNameEnum.values(),"componentClassName"),
    IS_VALID(DictionaryConstant.ValidOrNoValidEnum.values(),"isVaild"),
    CONTRACT_STEP(ContractConstant.ContractStepEnum.values(),"contractStep"),
    IS_COMPANY_LICENSE(CarLoanConstant.IsCompanyLicenseEnum.values(), "isCompanyLicense"),
    FORMULA_NAME(DictionaryConstant.FormulaEnum.values(), "formulaName"),
    SYSTEM_CODE(SystemCodeEnum.values(), "systemCode"),
    GRAYSCALE_STRATEGY(DictionaryConstant.GrayscaleStrategyEnum.values(), "grayscaleStrategy"),
    RULE_TYPE(DictionaryConstant.RuleTypeEnum.values(),"ruleType"),
    RULE_TYPE_NAME(AutoApprConstant.RuleTypeEnum.values(),"autoApprRuleTypeName"),
    BELONG_NAME(AutoApprConstant.BelongNameEnum.values(),"belongName"),
    OP_IDN(AutoApprConstant.OpIdnEnum.values(),"opIdn"),
    PROP_VALUE_TYPE(AutoApprConstant.PropValueTypeEnum.values(),"propValueType"),
    PROP_NAME(AutoApprConstant.PropNameEnum.values(),"propName"),
    CalcLogBuzType(ProductEngineConstant.CalcLogBuzType.values(),"calcLogBuzType"),
    dataStatus(DictionaryConstant.YesOrNoEnum.values(),"dataStatus"),
    dealerType(DealerTypeEnum.values(),"dealerType"),
    LICENCE_TYPE(LicenceTypeEnum.values(), "licenceType"),
    AUTH_MODEL(UserOperateLogConstant.AuthModelEnumForProduct.values(), "authModelEnum"),
    FOUND_NO(FundNoEnum.values(), "fundNoEnum"),
    CREDIT_AUTH_TYPE(ConstEnum.CreditAuthTypeEnum.values(), "creditAuthType"),
    TAG_BUZ_TYPE(TagConstant.BuzTypeEnum.values(), "tagBuzType"),
    PD_FEE_CLASSIFY(ProductConstant.FeeRuleClassifyEnum.values(), "pdFeeClassify"),
    INTERST_CALCULATE_METHOD(DictionaryConstant.InterstCalculateMethodEnum.values(), "interstCalculateMethod"),
    APPLY_RULE_STATUS_CLASSIFY(ApplyRuleTableEnum.values(), "applyRuleStatusClassify"),
    AUTO_APPR_RULE_CLASSIFY(AutoApprConstant.ClassifyEnum.values(), "autoApprRuleClassify"),
    CONFIG_PARAM_TYPE(ConstEnum.ParamTypeEnum.values(), "configParamType"),
    ENJOY_PACK_ITEM(ConstEnum.EnjoyPackItemEnum.values(),"enjoyPackItem"),
    ;
    /**
     * 枚举数组集合
     */
    private EnumValue[] enums;
    /**
     * 枚举代码值，用于前端查询的编码
     */
    private String code;

    /**
     * 构造函数
     * @param enums
     * @param code
     */
    DictionaryEnum(EnumValue[] enums, String code) {
        this.enums = enums;
        this.code = code;
    }

    public EnumValue[] getEnums() {
        return enums;
    }

    public String getCode() {
        return code;
    }

    /**
     * 根据名称获取枚举数组
     * @param code
     * @return
     */
    public static EnumValue[] getEnumsByCode(String code){
        for(DictionaryEnum e : DictionaryEnum.values()){
            if(e.getCode().equals(code)){
                return e.getEnums();
            }
        }
        return null;
    }
}
