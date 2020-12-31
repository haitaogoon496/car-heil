package com.mljr.heil.param.config;

import com.lyqc.base.common.validation.EnumValidation;
import com.lyqc.base.enums.EnumValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 批量关联设置 参数
 * @Date : 2018/6/7$ 18:04$
 * @Author : liht
 */
@Data
public class BatchLinkParam {
    /**
     * dealerScope、productScope
     */
    @ApiModelProperty(value = "业务类型")
    @EnumValidation(enums = ChooseTypeEnum.class,message = "业务类型非法")
    private Integer chooseType;

    @ApiModelProperty(value = "门店code")
    private List<Integer> dealerCodes;
    @ApiModelProperty(value = "gps规则id")
    private List<Integer> gpsRuleIds;
    @ApiModelProperty(value = "利率规则id")
    private List<Integer> rateRuleIds;
    @ApiModelProperty(value = "平台费规则id")
    private List<Integer> serFinRuleIds;
    @ApiModelProperty(value = "账号管理费规则id")
    private List<Integer> accountRuleIds;
    @ApiModelProperty(value = "第二年保险费规则id")
    private List<Integer> secondYearPremiumRuleIds;
    @ApiModelProperty(value = "第三年保险费规则id")
    private List<Integer> thirdYearPremiumRuleIds;
    @ApiModelProperty(value = "盗抢险规则id")
    private List<Integer> theftProtectionRuleIds;
    @ApiModelProperty(value = "盗抢超享包规则id")
    private List<Integer> enjoyPackRuleIds;
    @ApiModelProperty(value = "续保押金规则id")
    private List<Integer> renewalCommissionRuleIds;
    @ApiModelProperty(value = "人身保险费规则id")
    private List<Integer> lifeInsuranceRuleIds;
    @ApiModelProperty(value = "车辆保险规则id")
    private List<Integer> carInsuranceRuleIds;
    @ApiModelProperty(value = "车辆贴息规则id")
    private List<Integer> carDiscountRuleIds;
    @ApiModelProperty(value = "产品id")
    private List<Integer> pIds;

    public List<Integer> getpIds() {
        return pIds;
    }

    public void setpIds(List<Integer> pIds) {
        this.pIds = pIds;
    }
    /**
     * @description: 业务类型
     * @Date : 2019/2/12 下午5:35
     * @Author : 石冬冬-Seig Heil
     */
    public enum ChooseTypeEnum implements EnumValue {
        dealerScope(1,"dealerScope"),
        productScope(2,"productScope");
        ChooseTypeEnum(int index, String name) {
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
            for(ChooseTypeEnum e : ChooseTypeEnum.values()){
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
        public static ChooseTypeEnum getByIndex(int index){
            for(ChooseTypeEnum e : ChooseTypeEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }
    }

    /**
     * 是否门店维度绑定
     * @return
     */
    public boolean isBindDealer(){
        return null != this.chooseType && ChooseTypeEnum.dealerScope == ChooseTypeEnum.getByIndex(this.chooseType);
    }

    /**
     * 是否产品维度绑定
     * @return
     */
    public boolean isBindProduct(){
        return null != this.chooseType && ChooseTypeEnum.productScope == ChooseTypeEnum.getByIndex(this.chooseType);
    }
}
