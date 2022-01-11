package com.example.school.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/10/16:53
 * @Description:
 */
@Data
public class TypeDTO {
    @NotNull(message = "人员类型Id不为空")
    @ApiModelProperty(value = "人员类型id", required = true)
    private Integer id;

    /**
     * 类型名称
     */
    @NotBlank(message = "类型名不为空")
    @ApiModelProperty(value = "类型名", required = true)
    private String typeName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
