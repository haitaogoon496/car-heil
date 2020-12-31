package com.mljr.heil.base.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 规则与门店/产品关系查询条件对象，数据来源于枚举
 * 规则&门店关系 {@link com.mljr.heil.common.enums.RuleBindDealerCheckTableEnum}
 * 规则&产品关系 {@link com.mljr.heil.common.enums.RuleBindProductCheckTableEnum}
 * @Date : 2019/3/19 0019 15:39
 * @Author : 尚凌宇
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RuleRefForm {

    private String masterTable;
    private String slaveTable;
    private String masterRefKey;
    private String slaveRefKey;
    private Integer classify;

}
