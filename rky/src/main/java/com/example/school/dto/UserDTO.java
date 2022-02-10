package com.example.school.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/10/16:15
 * @Description:
 */
@Data
public class UserDTO {
    @ApiModelProperty(value = "人员信息id")
    private Integer id;

    /**
     * 人员类型id
     */
    @NotNull(message = "人员类型id不为空")
    @ApiModelProperty(value = "人员类型id", required = true)
    private Integer typeId;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不为空")
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    /**
     * 学号/工号/身份证号
     */
    @NotBlank(message = "学号/工号/身份证号不为空")
    @ApiModelProperty(value = "学号/工号/身份证号", required = true)
    private String card;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @ApiModelProperty(value = "联系电话", required = true)
    private String phone;

    /**
     * 其他信息（访客）/(备注)
     */
    @ApiModelProperty(value = "其他信息(访客)/(备注)")
    private String info;

    /**
     * 有效期限
     */
    @NotNull(message = "有效期限")
    @ApiModelProperty(value = "有效期限", required = true)
    private String expiry;

    /**
     * 状态 1:在校  0:离校
     */
    @ApiModelProperty(value = "状态 1:在校  0:离校", required = true)
    private Integer state;

    /**
     * 入校时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "入校时间", required = true)
    private LocalDateTime createTime;
}
