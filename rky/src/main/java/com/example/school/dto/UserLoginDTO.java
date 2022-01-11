package com.example.school.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/13/10:44
 * @Description:
 */
@Data
public class UserLoginDTO {
    /**
     * 姓名
     */
    @NotBlank(message = "姓名不为空")
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 学号/工号/身份证号
     */
    @NotBlank(message = "学号/工号/身份证号不为空")
    @ApiModelProperty(value = "学号/工号/身份证号")
    private String card;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @ApiModelProperty(value = "联系电话")
    private String phone;

    /**
     * 出入配置id
     */
    @ApiModelProperty(value = "出入配置id")
    private Integer qrId;

    /**
     * 二次扫码校验id
     */
    @ApiModelProperty(value = "二次扫码校验id")
    private Integer openId;
}
