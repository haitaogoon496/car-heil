package com.mljr.heil.form;

import com.lyqc.base.common.BaseForm;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.mljr.common.constraint.EnumConstraint;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description: 用户操作日志Form
 * @Date : 下午6:23 2018/3/2
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class UserLogForm extends BaseForm {
    /**
     * 业务类型
     * {@link UserOperateLogConstant.AuthModelEnumForProduct#getIndex()}
     */
    @ApiModelProperty(name="authModel",value="authModel",dataType="Integer")
    @EnumConstraint(enumClass = "com.lyqc.base.enums.UserOperateLogConstant$AuthModelEnum",message = "[authModel]非法枚举值")
    private Integer authModel;
    /**
     * 操作人
     */
    @ApiModelProperty(name="userName",value="userName",dataType="String")
    private String userName;


    public Integer getAuthModel() {
        return authModel;
    }

    public void setAuthModel(Integer authModel) {
        this.authModel = authModel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
