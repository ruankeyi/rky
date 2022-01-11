package com.example.school.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/17/9:02
 * @Description:
 */
@Data
public class MonthVO {
    /**
     * 时间段
     */
    @ApiModelProperty(value = "时间段")
    private Integer MonthTime;

    /**
     * 教职工人数
     */
    @ApiModelProperty(value = "教职工人数")
    private Integer teach;

    /**
     * 学生人数
     */
    @ApiModelProperty(value = "学生人数")
    private Integer student;

    /**
     * 访客人数
     */
    @ApiModelProperty(value = "访客人数")
    private Integer visit;

    /**
     * 总人数
     */
    @ApiModelProperty(value = "总人数")
    private Integer headcount;
}
