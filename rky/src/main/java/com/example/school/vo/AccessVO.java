package com.example.school.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/13/15:53
 * @Description:
 */
@Data
public class AccessVO {
    @ApiModelProperty(value = "出入记录id", required = true)
    private Integer id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    /**
     * 学号/工号/身份证号
     */
    @ApiModelProperty(value = "学号/工号/身份证号", required = true)
    private String card;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", required = true)
    private String phone;

    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名", required = true)
    private String typeName;

    /**
     * 打卡地点
     */
    @ApiModelProperty(value = "打卡地点", required = true)
    private String pip;

    /**
     * 打卡类型
     */
    @ApiModelProperty(value = "打卡类型", required = true)
    private String ctype;

    /**
     * 扫码时间
     */
    @ApiModelProperty(value = "扫码时间", required = true)
    private LocalDateTime scanCodeTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
