package com.mljr.heil.healthcheck;

import com.lyqc.base.common.Result;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.heil.common.enums.ApplyDealerTableEnum;
import com.mljr.heil.facade.config.DealerFeeRuleSetFacade;
import com.mljr.heil.form.DealerRuleSetForm;
import com.mljr.heil.vo.healthCheck.HealthCheckMainPartVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:  健康检查数据操作类
 * @Date : 2018/10/17 11:15
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Component
public class HealthCheckFacade {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckFacade.class);

    @Autowired
    DealerFeeRuleSetFacade dealerFeeRuleSetFacade;

    /**
     *
     * 删除失效门店绑定的所有规则
     * @param dealerRules
     * @return
     */
    public Result<String> deleteDealerBindRules(HealthCheckMainPartVo<List<HealthCheckMainPartVo>> dealerRules){
        /* 参数结构
        {
            "event": 19002002,
            "eventName": "无用",
            "eventDetail": [{
               "event": 3,
                    "eventDetail": "137",
                    "eventName": "利率规则"
               }, {
               "event": 10,
                    "eventDetail": "321",
                    "eventName": "车贷产品"
             }]
        }*/
        try {
            for (HealthCheckMainPartVo feeRules:dealerRules.getEventDetail()) {
                String ids = (String)feeRules.getEventDetail();
                if(ids != null && ids.length() > 0) {
                    List<Integer> idList = Arrays.asList(ids.split(",")).stream().map(s -> Integer.valueOf(s.trim())).collect(Collectors.toList());
                    DealerRuleSetForm dealerRuleSetForm = new DealerRuleSetForm();
                    dealerRuleSetForm.setBuzType(feeRules.getEvent());
                    dealerRuleSetForm.setDealerCode(dealerRules.getEvent());
                    TagConstant.BuzTypeEnum buzTypeEnum = TagConstant.BuzTypeEnum.getByIndex(feeRules.getEvent());
                    dealerRuleSetForm.setDealerTableIndex(getDealerTableIndexByBuzType(buzTypeEnum));
                    dealerRuleSetForm.setRuleIds(idList);
                    dealerFeeRuleSetFacade.delRulesForDealer(dealerRuleSetForm);
                }
            }
        }catch (Exception e){
            logger.error("删除失效门店下所有绑定规则失败。",e);
            return Result.failInServer("删除失效门店下所有绑定规则失败");
        }
        return Result.suc(null,0,"取消成功，请重新生成检测结果");
    }
    //通过规则枚举获取
    private Integer getDealerTableIndexByBuzType(TagConstant.BuzTypeEnum buzTypeEnum){
        Integer tableIndex = null;
        switch (buzTypeEnum){
            case SER_FIN_RULE:
                tableIndex = ApplyDealerTableEnum.SER_FIN_RULE.getIndex();
                break;
            case GPS_RULE:
                tableIndex = ApplyDealerTableEnum.GPS_RULE.getIndex();
                break;
            case RATE_RULE:
                tableIndex = ApplyDealerTableEnum.RATE_RULE.getIndex();
                break;
            case INSURANSE_SECOND_YEAR:
                tableIndex = ApplyDealerTableEnum.SECOND_INSURANCE_RULE.getIndex();
                break;
            case INSURANSE_THIRD_YEAR:
                tableIndex = ApplyDealerTableEnum.THIRD_INSURANCE_RULE.getIndex();
                break;
            case ACCOUNT_RULE:
                tableIndex = ApplyDealerTableEnum.ACCOUNT_RULE.getIndex();
                break;
            case YAN_BAO_RULE:
                tableIndex = ApplyDealerTableEnum.EXTEND_SAFE_RULE.getIndex();
                break;
            case LIFE_INSURANSE:
                tableIndex = ApplyDealerTableEnum.PD_FEE_RULE.getIndex();
                break;
            case PRODUCT:
                tableIndex = ApplyDealerTableEnum.PRODUCT_RULE.getIndex();
                break;
        }
        return tableIndex;
    }
}
