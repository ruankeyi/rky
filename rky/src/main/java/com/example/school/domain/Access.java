package com.example.school.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 
 * @TableName t_access
 */
@TableName(value ="t_access")
@Data
public class Access implements Serializable {
    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    @NotNull(message = "出入记录Id不为空")
    @ApiModelProperty(value = "出入记录id", required = true)
    private Integer id;

    /**
     * 人员信息id
     */
    @NotNull(message = "人员信息Id不为空")
    @ApiModelProperty(value = "人员信息id", required = true)
    private Integer userId;

    /**
     * 出入配置id
     */
    @NotNull(message = "人员类型Id不为空")
    @ApiModelProperty(value = "人员类型id", required = true)
    private Integer qrId;

    /**
     * 扫码时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "扫码时间", required = true)
    private LocalDateTime scanCodeTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}