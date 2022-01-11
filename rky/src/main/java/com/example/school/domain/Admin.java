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
 * @Date: 2022/01/09/17:02
 * @Description:
 */
@TableName(value ="t_u")
@Data
public class Admin {
    /**
     *
     */
    @TableId(value = "tu_id",type = IdType.AUTO)
    @NotNull(message = "后台用户Id不为空")
    @ApiModelProperty(value = "后台用户id", required = true)
    private Integer tuId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
