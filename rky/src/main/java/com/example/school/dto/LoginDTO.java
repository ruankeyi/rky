package com.example.school.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/29/14:09
 * @Description:
 */
@Data
public class LoginDTO {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
