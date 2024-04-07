package com.pange.pmall.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @auther Pange
 * @description 用户注册参数
 * @date {2024/3/20}
 */
@Getter
@Setter
public class UmsAdminParam {
    @NotEmpty
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "头像")
    private String icon;
    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty("备注")
    private String note;
}
