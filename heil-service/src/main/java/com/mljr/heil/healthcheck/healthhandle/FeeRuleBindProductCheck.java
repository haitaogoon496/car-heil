package com.mljr.heil.healthcheck.healthhandle;

import com.lyqc.base.enums.ProductConstant;
import com.mljr.heil.common.enums.DictionaryConstant;
import com.mljr.heil.common.enums.HealthCheckIndexEnum;
import com.mljr.heil.entity.*;
import com.mljr.heil.service.rule.*;
import com.mljr.heil.vo.healthCheck.HealthCheckMainPartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @Date : 2018/10/18 18:42
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Service
public class FeeRuleBindProductCheck extends AbstractHealthCheck{
    @Autowired
    RateRuleService rateRuleService;

    @Autowired
    GpsRuleService gpsRuleService;

    @Autowired
    SerFinRuleService serFinRuleService;

    @Autowired
    YanbaoRuleService yanbaoRuleService;

    @Autowired
    AccountRuleService accountRuleService;

    @Autowired
    PdFeeRuleService pdFeeRuleService;

    @Override
    public void doCheck() {
        List<HealthCheckMainPartVo> healthCheckMainPartVos = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        //利率规则校验
        List<RateRule> rateRules= rateRuleService.queryRulesUnBindProduct();
        if(rateRules != null && rateRules.size() > 0) {
            rateRules.forEach(per -> {
                tempList.add(String.valueOf(per.getRuleSeq()));
            });
            dealData(tempList,healthCheckMainPartVos, DictionaryConstant.RuleTypeEnum.RATE_RULE);
        }

        //gps规则校验
        List<GpsRule> gpsRules = gpsRuleService.queryRulesUnBindProduct();
        if (gpsRules != null && gpsRules.size() > 0){
            gpsRules.forEach(per ->{
                tempList.add(String.valueOf(per.getRuleSeq()));
            });
            dealData(tempList,healthCheckMainPartVos, DictionaryConstant.RuleTypeEnum.GPS_RULE);
        }

        //serFin规则校验
        List<SerFinRule> serFinRules = serFinRuleService.queryRulesUnBindProduct();
        if(serFinRules != null && serFinRules.size() > 0){
            serFinRules.forEach(per ->{
                tempList.add(String.valueOf(per.getRuleSeq()));
            });
            dealData(tempList,healthCheckMainPartVos, DictionaryConstant.RuleTypeEnum.SER_FIN_RULE);
        }

        //续保押金规则
        List<PdFeeRule> renewalcommissionRules = pdFeeRuleService.queryRulesUnBindProduct(ProductConstant.FeeRuleClassifyEnum.RENEWAL_COMMISSION.getIndex());
        if(renewalcommissionRules != null && renewalcommissionRules.size() > 0){
            renewalcommissionRules.forEach(per ->{
                tempList.add(String.valueOf(per.getId()));
            });
            dealData(tempList, healthCheckMainPartVos, DictionaryConstant.RuleTypeEnum.RENEWALCOMMISSION_RULE);
        }
        healthCheckMap.put(HealthCheckIndexEnum.FEE_RULE_UNBOUND_PRODUCT,healthCheckMainPartVos);
    }

    /**
     *   处理每个规则校验集合，最后封装在总的集合中去
     * @param tempList
     * @param healthCheckMainPartVos
     * @param ruleTypeEnum
     */
    private void dealData(List<String> tempList, List<HealthCheckMainPartVo> healthCheckMainPartVos, DictionaryConstant.RuleTypeEnum ruleTypeEnum){
        String ruleSeqStr = String.join(",",tempList);
        //集合使用完毕后清空，以便下次使用。
        tempList.clear();
        HealthCheckMainPartVo healthCheckMainPartVo = new HealthCheckMainPartVo<String>();
        healthCheckMainPartVo.setEvent(ruleTypeEnum.getIndex());
        healthCheckMainPartVo.setEventName(ruleTypeEnum.getDesc());
        healthCheckMainPartVo.setEventDetail(ruleSeqStr);
        healthCheckMainPartVos.add(healthCheckMainPartVo);
    }
}
