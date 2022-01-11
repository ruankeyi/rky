package com.example.school.vo;

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
 * @Date: 2021/12/10/16:58
 * @Description:
 */
@Data
public class UserVO {
    /**
     * 人员信息id
     */
    @ApiModelProperty(value = "人员信息id", required = true)
    private Integer id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    /**
     * 学号/工号/身份证号
     */
    @ApiModelProperty(value = "学号/工号/身份证号", required = true)
    private String card;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", required = true)
    private String phone;

    /**
     * 有效期限
     */
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
    @ApiModelProperty(value = "入校时间", required = true)
    private LocalDateTime createTime;

//    /**
//     * 人员类型id
//     */
//    @ApiModelProperty(value = "人员类型id", required = true)
//    private Integer typeId;

    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名", required = true)
    private String typeName;

    /**
     * 其他信息（访客）/(备注)
     */
    @ApiModelProperty(value = "其他信息(访客)/(备注)")
    private String info;
}
