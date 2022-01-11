package com.example.school.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/13/10:57
 * @Description:
 */
@Data
public class TypeSelectDTO {
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
     * 类型名称
     */
    @ApiModelProperty(value = "类型名")
    private String typeName;

    /**
     * 开始时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime airTime;

    /**
     * 结束时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime stopTime;
}
