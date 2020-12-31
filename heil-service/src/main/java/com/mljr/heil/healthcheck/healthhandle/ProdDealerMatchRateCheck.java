package com.mljr.heil.healthcheck.healthhandle;

import com.mljr.heil.common.enums.HealthCheckIndexEnum;
import com.mljr.heil.common.enums.RuleBindDealerCheckTableEnum;
import com.mljr.heil.common.enums.RuleBindProductCheckTableEnum;
import org.springframework.stereotype.Service;

/**
 * @description: 检查启用产品和有效门店有没有绑定相同利率规则
 * @Date : 2018/9/20 15:25
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Service
public class ProdDealerMatchRateCheck extends AbstractProdDealerMatchCheck{

    @Override
    protected void perpare() {
        this.bindDealerTableEnum = RuleBindDealerCheckTableEnum.RATE_RULE;
        this.healthEnum = HealthCheckIndexEnum.PRODUCT_DEALER_UNMATCHED_RATE_RULE;
        this.bindProductTableEnum = RuleBindProductCheckTableEnum.RATE_RULE;
    }

}
