package com.mljr.heil.healthcheck.healthhandle;

import com.lyqc.base.page.PageForm;
import com.lyqc.heil.enums.TagConstant;
import com.lyqc.transfer.dto.DealerDTO;
import com.lyqc.transfer.re.DealerRe;
import com.mljr.heil.common.enums.HealthCheckIndexEnum;
import com.mljr.heil.component.DealerComponent;
import com.mljr.heil.entity.AccountRule;
import com.mljr.heil.entity.GpsRule;
import com.mljr.heil.entity.PdFeeRule;
import com.mljr.heil.entity.Product;
import com.mljr.heil.entity.RateRule;
import com.mljr.heil.entity.SerFinRule;
import com.mljr.heil.entity.YanbaoRule;
import com.mljr.heil.form.DealerRuleSetForm;
import com.mljr.heil.mapper.AccountRuleMapper;
import com.mljr.heil.mapper.GpsRuleMapper;
import com.mljr.heil.mapper.PdFeeRuleMapper;
import com.mljr.heil.mapper.ProductMapper;
import com.mljr.heil.mapper.RateRuleMapper;
import com.mljr.heil.mapper.SerFinRuleMapper;
import com.mljr.heil.mapper.YanbaoRuleMapper;
import com.mljr.heil.vo.healthCheck.HealthCheckMainPartVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:  无效门店绑定规则检查
 * @Date : 2018/10/17 10:34
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Service
@Slf4j
public class UselessDealerBindRuleCheck extends AbstractHealthCheck {
    private static final String LOG_TITLE = "获取门店信息";

    @Autowired
    private RateRuleMapper rateRuleMapper;

    @Autowired
    private GpsRuleMapper gpsRuleMapper;

    @Autowired
    private SerFinRuleMapper serFinRuleMapper;

    @Autowired
    private AccountRuleMapper accountRuleMapper;

    @Autowired
    private YanbaoRuleMapper yanbaoRuleMapper;

    @Autowired
    private PdFeeRuleMapper pdFeeRuleMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private DealerComponent dealerComponent;

    @Override
    public void doCheck() {
        List<HealthCheckMainPartVo> dealerList = new ArrayList<>();
        try {
            List<DealerRe> list = dealerComponent.queryList(PageForm.newInstance(Boolean.FALSE, DealerDTO.builder().status("0").build()));
            for(DealerRe per: list) {
                Integer dealerCode = per.getDealerCode();
                HealthCheckMainPartVo perDealer = new HealthCheckMainPartVo();
                perDealer.setEvent(per.getDealerCode());
                perDealer.setEventName(per.getDealerName());
                //存放每个门店下的规则
                List<HealthCheckMainPartVo> perDealerRules = new ArrayList<>();
                //只要有一个绑定的费用规则，则为true;
                boolean haveRule = false;

                //基本查询参数准备
                DealerRuleSetForm dealerRuleSetForm = new DealerRuleSetForm();
                dealerRuleSetForm.setDealerCode(dealerCode);
                PageForm<DealerRuleSetForm> form = new PageForm<>(dealerRuleSetForm);
                form.setPaging(false);

                //查询该门店绑定的利率规则集合
                form.getForm().setBuzType(TagConstant.BuzTypeEnum.RATE_RULE.getIndex());
                List<RateRule> rateRules = rateRuleMapper.queryRateRulesForDealer(form);
                //排除掉全量绑定的利率规则
                String rateIds = StringUtils.join(rateRules.stream().filter(p -> p.getIsAllDealer() == 0).map(RateRule::getRuleSeq).collect(Collectors.toList()),",");
                if(rateIds != null && rateIds.length() > 0) {
                    haveRule = true;
                    currentDealerSearchRuleSeqs(TagConstant.BuzTypeEnum.RATE_RULE, rateIds, perDealerRules);
                }

                //查询该门店绑定的gps规则集合
                form.getForm().setBuzType(TagConstant.BuzTypeEnum.GPS_RULE.getIndex());
                List<GpsRule> gpsRules = gpsRuleMapper.queryGpsRulesForDealer(form);
                //排除掉全量绑定的gps规则集合
                String gpsIds = StringUtils.join(gpsRules.stream().filter(p -> p.getIsAllDealer() == 0).map(GpsRule::getRuleSeq).collect(Collectors.toList()),",");
                if(gpsIds != null && gpsIds.length() > 0) {
                    haveRule = true;
                    currentDealerSearchRuleSeqs(TagConstant.BuzTypeEnum.GPS_RULE, gpsIds, perDealerRules);
                }

                //查询该门店绑定的平台费规则集合
                form.getForm().setBuzType(TagConstant.BuzTypeEnum.SER_FIN_RULE.getIndex());
                List<SerFinRule> serFinRules = serFinRuleMapper.querySerRulesForDealer(form);
                //排除掉全量绑定的平台费规则集合
                String serFinIds = StringUtils.join(serFinRules.stream().filter(p -> p.getIsAllDealer() == 0).map(SerFinRule::getRuleSeq).collect(Collectors.toList()),",");
                if(serFinIds != null && serFinIds.length() > 0) {
                    haveRule = true;
                    currentDealerSearchRuleSeqs(TagConstant.BuzTypeEnum.SER_FIN_RULE, serFinIds, perDealerRules);
                }

                //查询该门店绑定的账户管理费规则集合
                form.getForm().setBuzType(TagConstant.BuzTypeEnum.ACCOUNT_RULE.getIndex());
                List<AccountRule> accountRules = accountRuleMapper.queryAccountRulesForDealer(form);
                //排除掉全量绑定的账户管理费规则集合
                String accountRuleIds = StringUtils.join(accountRules.stream().filter(p -> p.getIsAllDealer() == 0).map(AccountRule::getRuleSeq).collect(Collectors.toList()),",");
                if(accountRuleIds != null && accountRuleIds.length() > 0) {
                    haveRule = true;
                    currentDealerSearchRuleSeqs(TagConstant.BuzTypeEnum.ACCOUNT_RULE, accountRuleIds, perDealerRules);
                }

                //查询该门店绑定的第二年保险费规则集合
                form.getForm().setBuzType(TagConstant.BuzTypeEnum.INSURANSE_SECOND_YEAR.getIndex());
                form.getForm().setClassify(2);
                List<YanbaoRule> yanbaoSecondRules = yanbaoRuleMapper.queryYanbaoRulesForDealer(form);
                //排除掉全量绑定的第二年保险费规则集合
                String yanbaoSecondRuleIds = StringUtils.join(yanbaoSecondRules.stream().filter(p -> p.getIsAllDealer() == 0).map(YanbaoRule::getRuleSeq).collect(Collectors.toList()),",");
                if(yanbaoSecondRuleIds != null && yanbaoSecondRuleIds.length() > 0) {
                    haveRule = true;
                    currentDealerSearchRuleSeqs(TagConstant.BuzTypeEnum.INSURANSE_SECOND_YEAR, yanbaoSecondRuleIds, perDealerRules);
                }

                //查询该门店绑定的第三年保险费规则集合
                form.getForm().setBuzType(TagConstant.BuzTypeEnum.INSURANSE_THIRD_YEAR.getIndex());
                form.getForm().setClassify(3);
                List<YanbaoRule> yanbaoThirddRules = yanbaoRuleMapper.queryYanbaoRulesForDealer(form);
                //排除掉全量绑定的第三年保险费规则集合
                String yanbaoThirdRuleIds = StringUtils.join(yanbaoThirddRules.stream().filter(p -> p.getIsAllDealer() == 0).map(YanbaoRule::getRuleSeq).collect(Collectors.toList()),",");
                if(yanbaoThirdRuleIds != null && yanbaoThirdRuleIds.length() > 0) {
                    haveRule = true;
                    currentDealerSearchRuleSeqs(TagConstant.BuzTypeEnum.INSURANSE_THIRD_YEAR, yanbaoThirdRuleIds, perDealerRules);
                }

                //查询该门店绑定的人身保险费规则集合
                form.getForm().setBuzType(TagConstant.BuzTypeEnum.LIFE_INSURANSE.getIndex());
                List<PdFeeRule> pdFeeRules = pdFeeRuleMapper.queryPdFeeRulesForDealer(form);
                //排除掉全量绑定的第三年保险费规则集合
                String pdFeeRuleIds = StringUtils.join(pdFeeRules.stream().filter(p -> p.getIsAllDealer() == 0).map(PdFeeRule::getId).collect(Collectors.toList()),",");
                if(pdFeeRuleIds != null && pdFeeRuleIds.length() > 0) {
                    haveRule = true;
                    currentDealerSearchRuleSeqs(TagConstant.BuzTypeEnum.LIFE_INSURANSE, pdFeeRuleIds, perDealerRules);
                }

                //查询该门店绑定的车贷产品集合
                form.getForm().setBuzType(TagConstant.BuzTypeEnum.PRODUCT.getIndex());
                List<Product> products = productMapper.queryProductsForDealer(form);
                //排除掉全量绑定的第三年保险费规则集合
                String productIds = StringUtils.join(products.stream().filter(p -> "0".equals(p.getIsAllDealer())).map(Product::getpId).collect(Collectors.toList()),",");
                if(productIds != null && productIds.length() > 0) {
                    haveRule = true;
                    currentDealerSearchRuleSeqs(TagConstant.BuzTypeEnum.PRODUCT, productIds, perDealerRules);
                }
                //将规则信息集合放到门店对象中去
                perDealer.setEventDetail(perDealerRules);
                //将每个门店的信息存到门店集合中
                if(haveRule) {
                    dealerList.add(perDealer);
                }
            }
        }catch (Exception e){
            log.error("{}查询异常",LOG_TITLE,e);
        }
        healthCheckMap.put(HealthCheckIndexEnum.USELESSDEALER_BINDCHECK,dealerList);
    }

    private void  currentDealerSearchRuleSeqs(TagConstant.BuzTypeEnum buzTypeEnum,String perFeeIds,List<HealthCheckMainPartVo> dealerRules){
        //将该门店绑定的利率规则放到该门店下
        HealthCheckMainPartVo rateRuleIds = new HealthCheckMainPartVo();
        rateRuleIds.setEvent(buzTypeEnum.getIndex());
        rateRuleIds.setEventName(buzTypeEnum.getName());
        rateRuleIds.setEventDetail(perFeeIds);
        dealerRules.add(rateRuleIds);
    }
}
