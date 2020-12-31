package com.mljr.heil.vo.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @Date : 2018/6/7$ 17:55$
 * @Author : liht
 */
@Data
public class DealerFeeRuleVo {

    @ApiModelProperty(value = "不存在的门店code")
    private List<Integer> dealerNotExist;

    @ApiModelProperty(value = "gps规则不存在的规则id")
    private List<Integer> gpsNotExist;

    @ApiModelProperty(value = "利率规则不存在的id")
    private List<Integer> rateNotExist;

    @ApiModelProperty(value = "平台费规则不存在的id")
    private List<Integer> serFinNotExist;

    @ApiModelProperty(value = "账号管理费规则不存在的id")
    private List<Integer> accountNotExist;

    @ApiModelProperty(value = "第二年保险费规则不存在的id")
    private List<Integer> secondYearPremiumNotExist;

    @ApiModelProperty(value = "第三年保险费规则不存在的id")
    private List<Integer> thirdYearPremiumNotExist;

    @ApiModelProperty(value = "盗抢险规则不存在的id")
    private List<Integer> theftProtectionNotExist;

    @ApiModelProperty(value = "盗抢超享包规则不存在的id")
    private List<Integer> enjoyPackNotExist;

    @ApiModelProperty(value = "续保押金规则不存在的id")
    private List<Integer> renewalCommissionNotExist;

    @ApiModelProperty(value = "车辆保险规则不存在的id")
    private List<Integer> carInsuranceNotExist;

    @ApiModelProperty(value = "车辆贴息规则不存在的id")
    private List<Integer> carDiscountNotExist;

    @ApiModelProperty(value = "人身保险费规则不存在的id")
    private List<Integer> lifeInsuranceNotExist;

    @ApiModelProperty(value = "车贷产品不存在的id")
    private List<Integer> pIdNotExist;

}
