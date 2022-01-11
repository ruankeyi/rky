package com.example.school.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/17/9:03
 * @Description:
 */
@Data
public class MonthDTO {
    /**
     * 页码
     */
    @NotNull(message = "页码不为空")
    @ApiModelProperty(value = "页码", required = true)
    Integer pageSize;

    /**
     * 条数
     */
    @NotNull(message = "条数不为空")
    @ApiModelProperty(value = "条数", required = true)
    Integer pageNumber;

    /**
     * QR表id
     */
    @ApiModelProperty(value = "出入配置id")
    private Integer id;
}
