package com.example.school.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/12/19:08
 * @Description:
 */
@Data
public class UserloginVO {
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
