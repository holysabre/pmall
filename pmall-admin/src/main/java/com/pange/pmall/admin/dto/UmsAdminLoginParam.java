package com.pange.pmall.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @auther Pange
 * @description
 * @date {2024/3/20}
 */
@Data
public class UmsAdminLoginParam {
    @NotEmpty
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
