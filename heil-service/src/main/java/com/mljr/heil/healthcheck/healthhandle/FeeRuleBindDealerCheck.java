package com.mljr.heil.healthcheck.healthhandle;

import com.lyqc.base.page.PageForm;
import com.lyqc.transfer.dto.DealerDTO;
import com.lyqc.transfer.re.DealerRe;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.common.enums.DictionaryConstant;
import com.mljr.heil.common.enums.HealthCheckIndexEnum;
import com.mljr.heil.common.enums.RuleBindDealerCheckTableEnum;
import com.mljr.heil.component.DealerComponent;
import com.mljr.heil.service.common.SyDealerService;
import com.mljr.heil.service.rule.AccountRuleService;
import com.mljr.heil.service.rule.GpsRuleService;
import com.mljr.heil.service.rule.PdFeeRuleService;
import com.mljr.heil.service.rule.RateRuleService;
import com.mljr.heil.service.rule.SerFinRuleService;
import com.mljr.heil.service.rule.YanbaoRuleService;
import com.mljr.heil.vo.healthCheck.HealthCheckMainPartVo;
import com.mljr.util.CollectionsTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:   校验哪些规则没有绑定门店， （检查既没有绑定门店，又没有绑定产品的 平台费规则）
 * @Date : 2018/7/23 17:39
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Service
public class FeeRuleBindDealerCheck extends AbstractHealthCheck {

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

    @Autowired
    DealerComponent dealerComponent;

    @Autowired
    SyDealerService syDealerService;

    @Override
    public void doCheck() {
        List<HealthCheckMainPartVo> healthCheckMainPartVos = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        // 查询有效的门店code列表
        List<DealerRe> dealerRelist = dealerComponent.queryList(PageForm.newInstance(Boolean.FALSE, DealerDTO.builder().status("1").build()));
        List<Integer> validDealerCodelist = dealerRelist.stream().map(DealerRe::getDealerCode).collect(Collectors.toList());
        // 利率规则校验
        List<BaseDealerRes> dealerResList = syDealerService.queryRuleDealerListByParam(RuleBindDealerCheckTableEnum.RATE_RULE);
        ruleCheckFilter(dealerResList, validDealerCodelist, tempList);
        dealData(tempList,healthCheckMainPartVos, DictionaryConstant.RuleTypeEnum.RATE_RULE);

        // gps规则校验
        dealerResList = syDealerService.queryRuleDealerListByParam(RuleBindDealerCheckTableEnum.GPS_RULE);
        ruleCheckFilter(dealerResList, validDealerCodelist, tempList);
        dealData(tempList,healthCheckMainPartVos, DictionaryConstant.RuleTypeEnum.GPS_RULE);

        // serFin规则校验
        dealerResList = syDealerService.queryRuleDealerListByParam(RuleBindDealerCheckTableEnum.SER_FIN_RULE);
        ruleCheckFilter(dealerResList, validDealerCodelist, tempList);
        dealData(tempList,healthCheckMainPartVos, DictionaryConstant.RuleTypeEnum.SER_FIN_RULE);

        // 第二年延保费规则
        dealerResList = syDealerService.queryRuleDealerListByParam(RuleBindDealerCheckTableEnum.YAN_BAO_RULE_SECIBD);
        ruleCheckFilter(dealerResList, validDealerCodelist, tempList);
        dealData(tempList,healthCheckMainPartVos, DictionaryConstant.RuleTypeEnum.SECOND_YANBAO);

        // 第三年延保费规则
        dealerResList = syDealerService.queryRuleDealerListByParam(RuleBindDealerCheckTableEnum.YAN_BAO_RULE_THIRD);
        ruleCheckFilter(dealerResList, validDealerCodelist, tempList);
        dealData(tempList,healthCheckMainPartVos, DictionaryConstant.RuleTypeEnum.THIRD_YANBAO);

        // 账户管理费规则
        dealerResList = syDealerService.queryRuleDealerListByParam(RuleBindDealerCheckTableEnum.ACCOUNT_RULE);
        ruleCheckFilter(dealerResList, validDealerCodelist, tempList);
        dealData(tempList,healthCheckMainPartVos, DictionaryConstant.RuleTypeEnum.ACCOUNT_RULE);

        // 人身保险费规则
        dealerResList = syDealerService.queryRuleDealerListByParam(RuleBindDealerCheckTableEnum.FEE_RULE_LIFE_INSURANCE);
        ruleCheckFilter(dealerResList, validDealerCodelist, tempList);
        dealData(tempList,healthCheckMainPartVos, DictionaryConstant.RuleTypeEnum.PD_FEE_RULE);

        // 续保押金规则
        dealerResList = syDealerService.queryRuleDealerListByParam(RuleBindDealerCheckTableEnum.FEE_RULE_RENEWAL_COMMISSION);
        ruleCheckFilter(dealerResList, validDealerCodelist, tempList);
        dealData(tempList,healthCheckMainPartVos, DictionaryConstant.RuleTypeEnum.RENEWALCOMMISSION_RULE);
        healthCheckMap.put(HealthCheckIndexEnum.FEE_RULE_UNBOUND_DEALER,healthCheckMainPartVos);
    }

    /**
     * 获取没有配置有效规则的规则列表ID
     * @param dealerResList 规则门店关系对象集合
     * @param validDealerCodelist 有效的门店列表
     * @param tempList 临时列表，没有配置有效规则的规则列表ID
     */
    private void ruleCheckFilter(List<BaseDealerRes> dealerResList, List<Integer> validDealerCodelist, List<Integer> tempList) {
        if(CollectionsTools.isNotEmpty(dealerResList)) {
            // 过滤出没有关联门店的规则 ID 类表，放到待处理列表中
            List<Integer> noBindIdList = dealerResList.stream().filter(dealerRes -> dealerRes.getDealerCode() == null).map(BaseDealerRes::getRuleSeq).collect(Collectors.toList());
            tempList.addAll(noBindIdList);
            // 过滤出有关联的规则，进行分组，筛选出没有配置有效门店的规则，放到待处理列表中
            Map<Integer, List<BaseDealerRes>> bindDealerGroup = dealerResList.stream().filter(dealerRes -> dealerRes.getDealerCode() != null).collect(Collectors.groupingBy(BaseDealerRes::getRuleSeq));
            bindDealerGroup.forEach((key, list) -> {
                // 是否包含有效规则
                boolean isValid = false;
                for(BaseDealerRes dealerRef : list) {
                    if(validDealerCodelist.contains(dealerRef.getDealerCode())){
                        isValid = true;
                        break;
                    }
                }
                if(!isValid) {
                    tempList.add(key);
                }
            });
        }
    }

    /**
     *   处理每个规则校验集合，最后封装在总的集合中去
     * @param tempList
     * @param healthCheckMainPartVos
     * @param ruleTypeEnum
     */
    private void dealData(List<Integer> tempList, List<HealthCheckMainPartVo> healthCheckMainPartVos, DictionaryConstant.RuleTypeEnum ruleTypeEnum){
        if(CollectionsTools.isNotEmpty(tempList)) {
            String ruleSeqStr = tempList.stream().map(id -> id.toString()).collect(Collectors.joining(","));
            //集合使用完毕后清空，以便下次使用。
            tempList.clear();
            HealthCheckMainPartVo healthCheckMainPartVo = new HealthCheckMainPartVo<String>();
            healthCheckMainPartVo.setEvent(ruleTypeEnum.getIndex());
            healthCheckMainPartVo.setEventName(ruleTypeEnum.getDesc());
            healthCheckMainPartVo.setEventDetail(ruleSeqStr);
            healthCheckMainPartVos.add(healthCheckMainPartVo);
        }
    }
}
