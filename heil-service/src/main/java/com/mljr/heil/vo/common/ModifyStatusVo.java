package com.mljr.heil.vo.common;

import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

/**
 * @description: 更新产品状态Param对象
 * @Date : 下午6:36 2018/3/1
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class ModifyStatusVo {
    /**
     * 产品id
     */
    @ApiModelProperty(name="productId",value="产品id",required = true,dataType="Integer")
    @NotNull(message = "产品id不能为空")
    private Integer productId;
    /**
     * 产品状态
     * {@link com.lyqc.base.enums.ProductConstant.ProductStatusEnum}
     */
    @ApiModelProperty(name="status",value="产品状态",required = true,dataType="Integer")
    @NotNull(message = "产品状态不能为空")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(name="remark",value="备注",dataType="String")
    private String remark;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
