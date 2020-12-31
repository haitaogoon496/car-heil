package com.mljr.heil.base.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link com.mljr.heil.common.enums.ApplyDealerTableEnum}
 * @author lingyu.shang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealerRefQueryForm {

    private String tableName;

    private String refIdName;

    private String queryName;

    private Integer refIdValue;

}
