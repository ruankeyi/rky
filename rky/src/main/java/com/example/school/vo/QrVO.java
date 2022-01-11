package com.example.school.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/15/15:33
 * @Description:
 */
@Data
public class QrVO {
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
     * 图片
     */
    @ApiModelProperty(value = "二维码")
    private String photo;

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
     * 打卡人次
     */
    @ApiModelProperty(value = "打卡人次")
    private Integer Clock;
}
