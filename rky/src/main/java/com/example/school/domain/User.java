package com.example.school.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    @NotNull(message = "人员信息Id不为空")
    @ApiModelProperty(value = "人员信息id", required = true)
    private Integer id;

    /**
     * 人员类型id
     */
    @NotNull(message = "人员类型id不为空")
    @ApiModelProperty(value = "人员类型id", required = true)
    private Integer typeId;

    /**
     * 二次扫码校验id
     */
    @ApiModelProperty(value = "二次扫码校验id")
    private Integer openId;

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
    @NotBlank(message = "联系电话")
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
    @NotBlank(message = "有效期限")
    @ApiModelProperty(value = "有效期限", required = true)
    private String expiry;

    /**
     * 状态 1:在校  0:离校
     */
    @NotNull(message = "状态 1:在校  0:离校")
    @ApiModelProperty(value = "状态 1:在校  0:离校", required = true)
    private Integer state;

    /**
     * 入校时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "入校时间", required = true)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间", required = true)
    private LocalDateTime modifyTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}