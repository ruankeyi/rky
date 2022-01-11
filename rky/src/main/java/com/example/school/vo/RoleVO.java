package com.example.school.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/16/17:19
 * @Description:
 */
@Data
public class RoleVO {
    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名")
    private String typeName;

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
