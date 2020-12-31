package com.mljr.heil.common.enums;

import com.lyqc.base.enums.EnumDesc;
import com.lyqc.base.enums.EnumValue;

/**
 * @description: 数据字典枚举
 * 业务场景：适用于 前端下拉选项、多选项、单选项 等相关枚举
 * @Date : 下午6:47 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class DictionaryConstant {
    /**
     * @description: 是否二手车、是否提供房产、是否使用所有经销商 枚举
     * @Date : 下午12:48 2018/2/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     */
    public enum YesOrNoEnum implements EnumValue {
        YES(1, "是"),
        NO(0, "否");

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
    /**
     * @description: 费用进位方式 枚举
     * 例如2.3；round=2;ceil=3;floor=2
     * @Date : 下午12:48 2018/2/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     */
    public enum CarryEnum implements EnumValue {
        ROUND(0,"四舍五入"),
        CEIL(1,"向上取整"),
        FLOOR(2,"向下取整");

        CarryEnum(int index, String name) {
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
            for(CarryEnum e : CarryEnum.values()){
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
        public static CarryEnum getByIndex(int index){
            for(CarryEnum e : CarryEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }
    }
    /**
     * @description: 费用进位精度 枚举
     * @Date : 下午12:48 2018/2/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     */
    public enum PrecisionsEnum implements EnumValue {
        GW(1,"精确到个位"),
        SW(10,"精确到十位"),
        BW(100,"精确到百位"),
        QW(1000,"精确到千位"),
        WW(10000,"精确到万位");

        PrecisionsEnum(int index, String name) {
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
            for(PrecisionsEnum e : PrecisionsEnum.values()){
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
        public static PrecisionsEnum getByIndex(int index){
            for(PrecisionsEnum e : PrecisionsEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }
    }


    /**
     * @description: 费用进位精度 枚举 产品公式计算所用
     * @Date : 下午12:48 2018/2/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     */
    public enum PrecisionsForCarProductEnum implements EnumValue {
        XSD_LIU_W(6, "保留小数点后六位"),
        XSD_WU_W(5, "保留小数点后五位"),
        XSD_SI_W(4, "保留小数点后四位"),
        XSD_SAN_W(3, "保留小数点后三位"),
        XSDLW(2, "保留小数点后两位"),
        XSDYW(1, "保留小数点后一位"),
        GW(0, "精确到整数"),
        SW(-1, "精确到十位"),
        BW(-2, "精确到百位"),
        QW(-3, "精确到千位"),
        WW(-4, "精确到万位");

        PrecisionsForCarProductEnum(int index, String name) {
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
         *
         * @param index 索引
         * @return
         */
        public static String getNameByIndex(int index) {
            for (PrecisionsForCarProductEnum e : PrecisionsForCarProductEnum.values()) {
                if (e.getIndex() == index) {
                    return e.getName();
                }
            }
            return null;
        }
    }
    /**
     * @description: 规则配置优先级 枚举
     * @Date : 下午12:48 2018/2/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     */
    public enum PriorityEnum implements EnumValue {
        ONE(1, "1"),
        TWO(2, "2"),
        THIRD(3, "3"),
        FORTH(4, "4"),
        FIVE(5, "5"),
        ;

        PriorityEnum(int index, String name) {
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
            for(PriorityEnum e : PriorityEnum.values()){
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
        public static PriorityEnum getByIndex(int index){
            for(PriorityEnum e : PriorityEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }
    }
    /**
     * @description: 贴息计算方式 枚举
     * @Date : 下午12:48 2018/2/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     */
    public enum ComFlagEnum implements EnumValue {
        TJE(1, "贴金额"),
        TDS(2, "贴点数"),
        ;

        ComFlagEnum(int index, String name) {
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
            for(ComFlagEnum e : ComFlagEnum.values()){
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
        public static ComFlagEnum getByIndex(int index){
            for(ComFlagEnum e : ComFlagEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }
    }
    /**
     * @description: 车贷产品管理-规则配置-规则类型 枚举
     * @Date : 2018/8/1 下午4:00
     * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
     */
    public enum RuleClassNameEnum implements EnumDesc {
        NULL(0, "", "--请选择--"),
        R_INTE(1, "R_INTE", "贴息规则"),
        //R_GPS(2, "R_GPS", "GPS规则"),
        R_SECURE(3, "R_SECURE", "保险费用规则"),
        //R_BOND(4, "R_BOND", "保证金费用规则")
        ;

        RuleClassNameEnum(Integer index, String name, String desc) {
            this.index = index;
            this.name = name;
            this.desc = desc;
        }

        private int index;
        private String name;
        private String desc;

        @Override
        public int getIndex() {
            return index;
        }

        @Override
        public String getName() {
            return name;
        }
        @Override
        public String getDesc() {
            return desc;
        }

        /**
         * 根据索引获取名称
         * @param index 索引
         * @return
         */
        public static String getNameByIndex(int index){
            for(RuleClassNameEnum e : RuleClassNameEnum.values()){
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
        public static RuleClassNameEnum getByIndex(int index){
            for(RuleClassNameEnum e : RuleClassNameEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }

        /**
         * 根据索引获取枚举对象
         * @param desc 索引
         * @return
         */
        public static RuleClassNameEnum getByDesc(String desc){
            for(RuleClassNameEnum e : RuleClassNameEnum.values()){
                if(e.getDesc().equals(desc)){
                    return e;
                }
            }
            return null;
        }

        /**
         * 根据名称获取枚举对象描述
         * @param name 名称
         * @return
         */
        public static String getDescByName(String name){
            for(RuleClassNameEnum e : RuleClassNameEnum.values()){
                if(e.getName().equals(name)){
                    return e.getDesc();
                }
            }
            return null;
        }
    }

    /**
     * @description: 车贷产品管理-组件配置-组件类型 枚举
     * @Date : 2018/8/1 下午4:00
     * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
     */
    public enum ComponentClassNameEnum implements EnumDesc {

        NULL(0, "", "--请选择--"),
        C_COM_FEE(1, "C_COMFEE", "平台费规则"),
        /*C_RATE(2, "C_RATE", "利率规则类组件"),
        C_GPS(3, "C_GPS", "GPS规则类组件")*/

        ;

        private Integer index;
        private String name;
        private String value;

        //根据索引获得名称
        public static String getOneName(String index) {
            for (ComponentClassNameEnum e : ComponentClassNameEnum.values()) {
                if (String.valueOf(e.getIndex()).equals(index)) {
                    return e.getName();
                }
            }
            return "";
        }

        //根据索引获得名称
        public static String getDescByName(String name) {
            for (ComponentClassNameEnum e : ComponentClassNameEnum.values()) {
                if (e.getName().equals(name)) {
                    return e.getDesc();
                }
            }
            return "";
        }

        private ComponentClassNameEnum(Integer index,String name,String desc) {
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
    }

    /**
     * @description: 规则类型
     * @Date : 2018/4/4 14:10
     * @Author : lihaitao
     */
    public enum RuleTypeEnum implements EnumDesc {

        GPS_RULE(1, "gpsRule", "GPS规则"),
        RATE_RULE(2, "rateRule", "利率规则"),
        SER_FIN_RULE(3, "serFinRule", "平台费规则"),
        SECOND_YANBAO(4, "secondRule", "第二年保险费规则"),
        THIRD_YANBAO(5, "thirdRule", "第三年保险费规则"),
        ACCOUNT_RULE(6, "accountRule", "账号管理费规则"),
        EXTEND_SAFE_RULE(7, "extendSafeRule", "延保费规则"),
        PD_FEE_RULE(8, "pdFeeRule", "人身保险费规则"),
        RENEWALCOMMISSION_RULE(9,"renewalCommissionRule","续保押金规则");

        private Integer index;
        private String name;
        private String value;

        private RuleTypeEnum(Integer index,String name,String desc) {
            this.index = index;
            this.name = name;
            this.value = desc;
        }
        //根据索引获得名称
        public static String getOneName(String index) {
            for (RuleTypeEnum e : RuleTypeEnum.values()) {
                if (String.valueOf(e.getIndex()).equals(index)) {
                    return e.getName();
                }
            }
            return "";
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


        /**
         * 根据索引获取名称
         * @param index 索引
         * @return
         */
        public static String getNameByIndex(int index){
            for(RuleTypeEnum e : RuleTypeEnum.values()){
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
        public static RuleTypeEnum getByIndex(int index){
            for(RuleTypeEnum e : RuleTypeEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }

        /**
         * 根据索引获取枚举对象
         * @param desc 索引
         * @return
         */
        public static RuleTypeEnum getByDesc(String desc){
            for(RuleTypeEnum e : RuleTypeEnum.values()){
                if(e.getDesc().equals(desc)){
                    return e;
                }
            }
            return null;
        }
    }

    /**
     * @description: 有效，无效 枚举
     * @Date : 下午12:48 2018/2/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     */
    public enum ValidOrNoValidEnum implements EnumValue {
        YES(1, "有效"),
        NO(0, "无效");

        ValidOrNoValidEnum(int index, String name) {
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
            for(ValidOrNoValidEnum e : ValidOrNoValidEnum.values()){
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
        public static ValidOrNoValidEnum getByIndex(int index){
            for(ValidOrNoValidEnum e : ValidOrNoValidEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }
    }

    /**
     * @description: 公式名称
     * @Date : 下午12:48 2018/2/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     */
    public enum FormulaEnum implements EnumDesc {
        BASE_COM_FEE(1, "baseComFee","基础平台费",RuleTypeEnum.SER_FIN_RULE),
        COM_FLOAT_FEE(2, "comFloatFee","平台浮动费",RuleTypeEnum.SER_FIN_RULE),
        PD_FEE(3, "lifeInsurance","人身险",RuleTypeEnum.PD_FEE_RULE),
        NOTARIAL_FEE(4, "notarialFee","公证费",null),
        SER_FIN_BACK_FEE(5, "serFinRebate","平台费返佣",RuleTypeEnum.SER_FIN_RULE),
        MONTHLY_PAY(6, "monthFee","月供",null),
        INIT_PAY_RATE(7, "initScale","首付比",null),
        INIT_PAY(8, "initPayment","首付款",null),
        SER_LOAN_AFTER_MNG_RATE(9, "serLoanAfterMngRate","服务人贷后管理费率(月)",null),
        OVER_FINANCE_AMOUNT(10, "overFinanceAmount","超融总金额",null),
        LOAN_AMOUNT(11,"loanAmount","贷款总金额",null),
        PRE_INTEREST_AMOUNT(12,"preInterestAmount","前置总利息",null),
        GROSS_INTEREST(13,"grossInterest","总利息",null),
        ;

        FormulaEnum(int index, String name,String value,RuleTypeEnum ruleTypeEnum) {
            this.index = index;
            this.name = name;
            this.value = value;
            this.ruleTypeEnum = ruleTypeEnum;
        }

        private int index;
        private String name;
        private String value;
        private RuleTypeEnum ruleTypeEnum;

        @Override
        public int getIndex() {
            return this.index;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public RuleTypeEnum getRuleTypeEnum() {
            return this.ruleTypeEnum;
        }

        /**
         * 根据索引获取名称
         * @param index 索引
         * @return
         */
        public static String getNameByIndex(int index){
            for(FormulaEnum e : FormulaEnum.values()){
                if(e.getIndex() == index){
                    return e.getName();
                }
            }
            return null;
        }

        /**
         * 通过code获取公式对应的费用规则
         * @param formulaCode
         * @return
         */
        public static RuleTypeEnum getByName(String formulaCode) {
            for (FormulaEnum e : FormulaEnum.values()) {
                if (e.getName().equals(formulaCode)) {
                    return e.getRuleTypeEnum();
                }
            }
            return null;
        }
        /**
         * 通过code获取公式对应的费用规则
         * @param formulaCode
         * @return
         */
        public static String getDescByCode(String formulaCode) {
            for (FormulaEnum e : FormulaEnum.values()) {
                if (e.getName().equals(formulaCode)) {
                    return e.getDesc();
                }
            }
            return null;
        }

        /**
         * 根据索引获取枚举对象
         * @param index 索引
         * @return
         */
        public static FormulaEnum getByIndex(int index){
            for(FormulaEnum e : FormulaEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }
        /**
         * 根据val获取名称
         * @param val 名称
         * @return
         */
        public static String getByVal(String val){
            for(FormulaEnum e : FormulaEnum.values()){
                if (e.getDesc().equals(val)) {
                    return e.getName();
                }
            }
            return null;
        }

        @Override
        public String getDesc() {
            return this.value;
        }
    }
    /**
     * @description: 产品类型
     * @Date : 下午12:48 2018/2/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     */
    public enum ProductTypeEnum implements EnumValue {
        WAIT_REVIEW(0, "待复核"),
        ENABLE(1, "启用"),
        REVIEW_PASS(2, "复核通过"),
        REVIEW_NOT_PASS(3, "复核不通过"),
        CANCEL(4, "撤销"),
        PAUSE(5, "暂停"),
        WAIT_SUBMIT(6, "待提交");

        ProductTypeEnum(int index, String name) {
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
            for(ProductTypeEnum e : ProductTypeEnum.values()){
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
        public static ProductTypeEnum getByIndex(int index){
            for(ProductTypeEnum e : ProductTypeEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }
    }

    /**
     * @description: 灰度策略枚举
     * @Date : 下午12:48 2018/2/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     */
    public enum GrayscaleStrategyEnum implements EnumValue {
        ORDER_NUM(1, "按照单量"),
        PRODUCT_TYPE(2, "按照产品类型");

        GrayscaleStrategyEnum(int index, String name) {
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
            for(GrayscaleStrategyEnum e : GrayscaleStrategyEnum.values()){
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
        public static GrayscaleStrategyEnum getByIndex(int index){
            for(GrayscaleStrategyEnum e : GrayscaleStrategyEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }
    }

    /**
     * @description: 是否是自定义类型
     * @Date : 下午12:48 2018/2/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     */
    public enum CustomTypeEnum implements EnumValue {
        NO(0, "否"),
        YES(1, "是");

        CustomTypeEnum(int index, String name) {
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
            for(CustomTypeEnum e : CustomTypeEnum.values()){
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
        public static CustomTypeEnum getByIndex(int index){
            for(CustomTypeEnum e : CustomTypeEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }
    }

    public enum InterstCalculateMethodEnum implements EnumValue {
        MONEY(1, "贴金额"),
        POINT(2, "贴点数")
        ;

        InterstCalculateMethodEnum(int index, String name) {
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
            for(InterstCalculateMethodEnum e : InterstCalculateMethodEnum.values()){
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
        public static InterstCalculateMethodEnum getByIndex(int index){
            for(InterstCalculateMethodEnum e : InterstCalculateMethodEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }
    }
}
