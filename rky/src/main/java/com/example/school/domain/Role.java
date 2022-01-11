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
 * @Date: 2022/01/09/17:06
 * @Description:
 */
@TableName(value ="t_role")
@Data
public class Role {
    /**
     *
     */
    @TableId(value = "tr_id",type = IdType.AUTO)
    @NotNull(message = "角色Id不为空")
    @ApiModelProperty(value = "角色id", required = true)
    private Integer trId;

    /**
     * 角色名
     */
    @NotBlank(message = "角色名不为空")
    @ApiModelProperty(value = "角色名", required = true)
    private String roleName;

    /**
     * 角色描述
     */
    @NotBlank(message = "角色描述不为空")
    @ApiModelProperty(value = "角色描述", required = true)
    private String roleDesc;
}
