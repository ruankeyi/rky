package com.example.school.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @Author: ruankeyi
 * @Date: 2022/01/23/20:40
 * @Description:
 */
@Data
public class AdminDTO {
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

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不为空")
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @ApiModelProperty(value = "联系电话", required = true)
    private String phone;

    /**
     * 状态 1:启用  0:禁用
     */
    @NotNull(message = "状态 1:启用  0:禁用")
    @ApiModelProperty(value = "状态 1:启用  0:禁用", required = true)
    private Integer state;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String info;

    @ApiModelProperty(value = "角色id信息")
    private List<Integer> integerList;
}
