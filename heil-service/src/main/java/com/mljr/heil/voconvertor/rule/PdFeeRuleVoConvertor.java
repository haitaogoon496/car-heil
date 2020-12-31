package com.mljr.heil.voconvertor.rule;

import com.lyqc.base.enums.ProductConstant;
import com.lyqc.heil.enums.LicenceTypeEnum;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.PdFeeRule;
import com.mljr.heil.vo.rule.PdFeeRuleVo;
import com.mljr.heil.voconvertor.VoConvertor;

import java.util.Arrays;
import java.util.List;

/**
 * @description: GPS规则VO转换
 * @Date : 2018/2/9 下午5:17
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class PdFeeRuleVoConvertor extends VoConvertor<PdFeeRuleVo, PdFeeRule> {

    @Override
    public PdFeeRuleVo convert(PdFeeRule bo) {
        PdFeeRuleVo vo = new PdFeeRuleVo();
        vo.setId(StringUtils.killNull(bo.getId()));
        vo.setClassify(bo.getClassify());
        vo.setRuleName(StringUtils.killNull(bo.getRuleName()));
        vo.setRateValue(bo.getRateValue());
        vo.setLoanPeriod(StringUtils.killNull(bo.getLoanPeriod()));
        vo.setLicenseType(StringUtils.killNull(bo.getLicenseType()));
        if (!StringUtils.isEmpty(bo.getLicenseType())) {
            List<String> list = Arrays.asList(bo.getLicenseType().split(","));
            StringBuffer licenseTypeBuf = new StringBuffer();
            list.stream().forEach(str -> {
                licenseTypeBuf.append(LicenceTypeEnum.getNameByIndex(Integer.parseInt(str)));
                licenseTypeBuf.append(",");
            });
            String licenseTypeStr = licenseTypeBuf.toString();
            vo.setLicenseTypeVal(licenseTypeStr.endsWith(",") ? licenseTypeStr.substring(0, licenseTypeStr.length() - 1) : licenseTypeStr);
        }
        vo.setIsAllDealer(StringUtils.killNull(bo.getIsAllDealer()));
        vo.setIsAllProduct(StringUtils.killNull(bo.getIsAllProduct()));
        vo.setSalePriceMax(bo.getSalePriceMax());
        vo.setSalePriceMin(bo.getSalePriceMin());
        vo.setCarLoanAmountMax(bo.getCarLoanAmountMax());
        vo.setCarLoanAmountMin(bo.getCarLoanAmountMin());
        vo.setPaymentScaleMax(bo.getPaymentScaleMax());
        vo.setPaymentScaleMin(bo.getPaymentScaleMin());
        vo.setVehicleMilesMax(bo.getVehicleMilesMax());
        vo.setVehicleMilesMin(bo.getVehicleMilesMin());
        vo.setIsOld(StringUtils.killNull(bo.getIsOld()));
        vo.setIsLcv(StringUtils.killNull(bo.getIsLcv()));
        vo.setExtendProps(bo.getExtendProps());
        vo.setVehicleAgeMax(bo.getVehicleAgeMax());
        vo.setVehicleAgeMin(bo.getVehicleAgeMin());
        vo.setrLoanPeriods(bo.getLoanPeriod());
        vo.setClassifyDesc(ProductConstant.FeeRuleClassifyEnum.getNameByIndex(bo.getClassify()));
        vo.setRebateValue(StringUtils.bigDecimal2String(bo.getRebateValue()));
        vo.setClassifyDesc(ProductConstant.FeeRuleClassifyEnum.getNameByIndex(bo.getClassify()));
        vo.setStatus(StringUtils.killNull(bo.getStatus()));
        vo.convertEnum();
        return vo;
    }
}
