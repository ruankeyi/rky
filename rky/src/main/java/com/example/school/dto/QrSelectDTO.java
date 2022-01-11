package com.example.school.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/13/14:27
 * @Description:
 */
@Data
public class QrSelectDTO {
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
     * 打卡地点
     */
    @ApiModelProperty(value = "打卡地点")
    private String pip;

    /**
     * 打卡类型
     */
    @ApiModelProperty(value = "打卡类型")
    private String ctype;

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
