package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 配置产品与规则关联（GPS、利率、平台费、账号管理费、第二年、第三年保险费）
 * {@link PdFeeProduct}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PdRuleProduct extends BaseEntity{
    private Integer id;

    private Byte classify;

    private Integer ruleId;

    private Integer productId;

    private Date updateTime;

}