package com.example.school.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: ruankeyi
 * @Date: 2022/01/09/17:14
 * @Description:
 */
@TableName(value ="t_right")
@Data
public class Right {
    /**
     *
     */
    @TableId(value = "tr_id",type = IdType.AUTO)
    @NotNull(message = "权限Id不为空")
    @ApiModelProperty(value = "权限id", required = true)
    private Integer trId;

    /**
     * 权限名
     */
    @NotBlank(message = "权限名不为空")
    @ApiModelProperty(value = "权限名", required = true)
    private String rightName;

    /**
     * 路径
     */
    @NotBlank(message = "路径不为空")
    @ApiModelProperty(value = "路径", required = true)
    private String url;
}
