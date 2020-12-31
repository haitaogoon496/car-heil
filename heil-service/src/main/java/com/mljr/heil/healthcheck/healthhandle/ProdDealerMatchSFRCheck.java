package com.mljr.heil.healthcheck.healthhandle;

import com.mljr.heil.common.enums.HealthCheckIndexEnum;
import com.mljr.heil.common.enums.RuleBindDealerCheckTableEnum;
import com.mljr.heil.common.enums.RuleBindProductCheckTableEnum;
import org.springframework.stereotype.Service;

/**
 * @description: 检查启用产品和有效门店有没有绑定相同平台费规则
 * @Date : 2018/7/26 17:49
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Service
public class ProdDealerMatchSFRCheck extends AbstractProdDealerMatchCheck {

    @Override
    protected void perpare() {
        this.bindDealerTableEnum = RuleBindDealerCheckTableEnum.SER_FIN_RULE;
        this.healthEnum = HealthCheckIndexEnum.PRODUCT_DEALER_UNMATCHED_SERFIN_RULE;
        this.bindProductTableEnum = RuleBindProductCheckTableEnum.SER_FIN_RULE;
    }

}
