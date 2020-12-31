package com.mljr.heil.base.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @description: Entity基类
 * @Date : 下午3:38 2018/2/5
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -1830697402725915593L;

    private Integer id;

    @ApiModelProperty(name="userName",value="创建人姓名",dataType="String")
    private String userName;
    @ApiModelProperty(name="userId",value="创建人id",dataType="Integer")
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 是否新增对象操作
     * @return
     */
    public boolean isInsert(){
        return null == this.id || 0 == this.id;
    }
}
