package com.example.school.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/12/16:55
 * @Description:
 */
@Data
public class QrDTO {
    @NotNull(message = "出入Id不为空")
    @ApiModelProperty(value = "出入id", required = true)
    private Integer id;

    /**
     * 打卡地点
     */
    @NotBlank(message = "打卡地点不为空")
    @ApiModelProperty(value = "打卡地点", required = true)
    private String pip;

    /**
     * 打卡类型
     */
    @NotBlank(message = "打卡类型不为空")
    @ApiModelProperty(value = "打卡类型", required = true)
    private String ctype;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
