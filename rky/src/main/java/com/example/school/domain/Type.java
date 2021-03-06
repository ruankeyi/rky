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
 * @TableName t_type
 */
@TableName(value ="t_type")
@Data
public class Type implements Serializable {
    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
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
     * 创建时间
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}