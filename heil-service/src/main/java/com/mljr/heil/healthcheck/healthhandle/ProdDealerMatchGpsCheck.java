package com.mljr.heil.healthcheck.healthhandle;

import com.mljr.heil.common.enums.HealthCheckIndexEnum;
import com.mljr.heil.common.enums.RuleBindDealerCheckTableEnum;
import com.mljr.heil.common.enums.RuleBindProductCheckTableEnum;
import org.springframework.stereotype.Service;

/**
 * @description: 检查启用产品和有效门店有没有绑定相同gps费
 * @Date : 2018/9/20 15:41
 * @Author : lihaitao
 */
@Service
public class ProdDealerMatchGpsCheck extends AbstractProdDealerMatchCheck {

    @Override
    protected void perpare() {
        this.bindDealerTableEnum = RuleBindDealerCheckTableEnum.GPS_RULE;
        this.healthEnum = HealthCheckIndexEnum.PRODUCT_DEALER_UNMATCHED_GPS_RULE;
        this.bindProductTableEnum = RuleBindProductCheckTableEnum.GPS_RULE;
    }

}
