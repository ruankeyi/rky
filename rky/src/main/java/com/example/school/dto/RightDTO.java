package com.example.school.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: ruankeyi
 * @Date: 2022/02/07/10:21
 * @Description:
 */
@Data
public class RightDTO {
    /**
     * 权限名
     */
    @NotBlank(message = "权限名不为空")
    @ApiModelProperty(value = "权限名", required = true)
    private String rightName;

    /**
     * 路径
     */
    @NotBlank(message = "路径不为空")
    @ApiModelProperty(value = "路径", required = true)
    private String url;

    /**
     * 状态 1:启用  0:禁用
     */
    @NotNull(message = "状态 1:启用  0:禁用")
    @ApiModelProperty(value = "状态 1:启用  0:禁用", required = true)
    private Integer state;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String info;
}
