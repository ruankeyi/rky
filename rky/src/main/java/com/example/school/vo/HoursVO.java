package com.example.school.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/16/16:50
 * @Description:
 */
@Data
public class HoursVO {
    /**
     * 时间段
     */
    @ApiModelProperty(value = "时间段")
    private Integer PeriodTime;
    /**
     * 出口1
     */
    @ApiModelProperty(value = "出口1")
    private Integer OutStand1;
    /**
     * 入口1
     */
    @ApiModelProperty(value = "入口1")
    private Integer IntStand1;

    /**
     * 出口2
     */
    @ApiModelProperty(value = "出口2")
    private Integer OutStand2;

    /**
     * 入口2
     */
    @ApiModelProperty(value = "入口2")
    private Integer IntStand2;
}
