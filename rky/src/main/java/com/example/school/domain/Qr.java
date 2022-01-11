package com.example.school.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * @TableName t_qr
 */
@TableName(value ="t_qr")
@Data
public class Qr implements Serializable {
    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    @NotNull(message = "出入配置Id不为空")
    @ApiModelProperty(value = "出入配置id", required = true)
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
     * 图片
     */
    @ApiModelProperty(value = "二维码")
    private String photo;

    /**
     * 创建时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间", required = true)
    private LocalDateTime createTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}