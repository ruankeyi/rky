package com.example.school.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/14/17:21
 * @Description:
 */
@Data
public class VisitorsLoginDTO {
    /**
     * 访客姓名
     */
    @NotBlank(message = "访客姓名不为空")
    @ApiModelProperty(value = "访客姓名")
    private String VisitorsName;

    /**
     * 访客身份证号
     */
    @NotBlank(message = "访客身份证号不为空")
    @ApiModelProperty(value = "访客身份证号")
    private String VisitorsCard;

    /**
     * 访客联系电话
     */
    @NotBlank(message = "访客联系电话不为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @ApiModelProperty(value = "访客联系电话")
    private String VisitorsPhone;

    /**
     * 拜访对象姓名
     */
    @NotBlank(message = "拜访对象姓名不为空")
    @ApiModelProperty(value = "拜访对象姓名")
    private String name;

    /**
     * 拜访对象学号/工号/身份证号
     */
    @NotBlank(message = "拜访对象学号/工号/身份证号不为空")
    @ApiModelProperty(value = "拜访对象学号/工号/身份证号")
    private String card;

    /**
     * 拜访对象联系电话
     */
    @NotBlank(message = "拜访对象联系电话不为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @ApiModelProperty(value = "拜访对象联系电话")
    private String phone;

    /**
     * 入校事由
     */
    @ApiModelProperty(value = "入校事由")
    private String reason;

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
