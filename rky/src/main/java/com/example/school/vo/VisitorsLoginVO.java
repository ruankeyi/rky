package com.example.school.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/14/17:28
 * @Description:
 */
@Data
public class VisitorsLoginVO {
    /**
     * 访客姓名
     */
    @NotBlank(message = "访客姓名不为空")
    @ApiModelProperty(value = "访客姓名", required = true)
    private String VisitorsName;

    /**
     * 访客身份证号
     */
    @NotBlank(message = "访客身份证号不为空")
    @ApiModelProperty(value = "访客身份证号", required = true)
    private String VisitorsCard;

    /**
     * 访客联系电话
     */
    @NotBlank(message = "访客联系电话不为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @ApiModelProperty(value = "访客联系电话", required = true)
    private String VisitorsPhone;

    /**
     * 拜访对象姓名
     */
    @NotBlank(message = "拜访对象姓名不为空")
    @ApiModelProperty(value = "拜访对象姓名", required = true)
    private String name;

    /**
     * 拜访对象学号/工号/身份证号
     */
    @NotBlank(message = "拜访对象学号/工号/身份证号不为空")
    @ApiModelProperty(value = "拜访对象学号/工号/身份证号", required = true)
    private String card;

    /**
     * 拜访对象联系电话
     */
    @NotBlank(message = "拜访对象联系电话不为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @ApiModelProperty(value = "拜访对象联系电话", required = true)
    private String phone;

    /**
     * 入校事由
     */
    @ApiModelProperty(value = "入校事由", required = true)
    private String reason;

    /**
     * 登陆时间
     */
    @ApiModelProperty(value = "登陆时间", required = true)
    private LocalDateTime ModifyTime;

    /**
     * 扫码地点
     */
    @ApiModelProperty(value = "扫码地点", required = true)
    private String pip;
}
