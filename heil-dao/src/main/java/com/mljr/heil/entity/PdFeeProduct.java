package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 配置产品与规则关联（续保押金、盗抢险、车辆保险、车辆贴息、超享包 ）
 * {@link PdRuleProduct}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PdFeeProduct extends BaseEntity{

    private Integer resId;

    private Integer productId;
}