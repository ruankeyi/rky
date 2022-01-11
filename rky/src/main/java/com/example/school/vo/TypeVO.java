package com.example.school.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/12/15:41
 * @Description:
 */
@Data
public class TypeVO {
    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名")
    private String typeName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 录入人数
     */
    @ApiModelProperty(value = "录入人数")
    private Integer number;
}
