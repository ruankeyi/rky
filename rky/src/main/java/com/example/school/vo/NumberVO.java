package com.example.school.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/17/10:21
 * @Description:
 */
@Data
public class NumberVO {
    /**
     * 累计管理校内总人数
     */
    @ApiModelProperty(value = "累计管理校内总人数")
    private Integer number1;

    /**
     * 累计服务人数
     */
    @ApiModelProperty(value = "累计服务人数")
    private Integer number2;

    /**
     * 当前管理教职工人数
     */
    @ApiModelProperty(value = "当前管理教职工人数")
    private Integer number3;

    /**
     * 当前管理学生人数
     */
    @ApiModelProperty(value = "当前管理学生人数")
    private Integer number4;
}
