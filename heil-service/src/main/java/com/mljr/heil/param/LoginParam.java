package com.mljr.heil.param;

import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;
/**
 * @description: 登录认证Param
 * @Date : 2018/3/4 上午10:02
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class LoginParam {

    @ApiModelProperty(name="username",value="用户名",required = true,dataType="String")
    @NotNull(message = "[用户名]不能为空")
    private String username;

    @ApiModelProperty(name="password",value="密码",required = true,dataType="String")
    @NotNull(message = "[密码]不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
