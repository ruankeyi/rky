package com.example.school.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
    @Excel(name = "出入记录id",width = 10)
    @ApiModelProperty(value = "出入记录id", required = true)
    private Integer id;

    /**
     * 姓名
     */
    @Excel(name = "姓名",width = 10)
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    /**
     * 学号/工号/身份证号
     */
    @Excel(name = "学号/工号/身份证号",width = 10)
    @ApiModelProperty(value = "学号/工号/身份证号", required = true)
    private String card;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话",width = 10)
    @ApiModelProperty(value = "联系电话", required = true)
    private String phone;

    /**
     * 类型名称
     */
    @Excel(name = "类型名",width = 10)
    @ApiModelProperty(value = "类型名", required = true)
    private String typeName;

    /**
     * 打卡地点
     */
    @Excel(name = "打卡地点",width = 10)
    @ApiModelProperty(value = "打卡地点", required = true)
    private String pip;

    /**
     * 打卡类型
     */
    @Excel(name = "打卡类型",width = 10)
    @ApiModelProperty(value = "打卡类型", required = true)
    private String ctype;

    /**
     * 扫码时间
     */
    @Excel(name = "扫码时间",width = 10)
    @ApiModelProperty(value = "扫码时间", required = true)
    private LocalDateTime scanCodeTime;

    /**
     * 备注
     */
    @Excel(name = "备注",width = 10)
    @ApiModelProperty(value = "备注")
    private String remark;
}
