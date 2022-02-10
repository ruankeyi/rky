package com.example.school.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: ruankeyi
 * @Date: 2022/01/09/17:24
 * @Description:
 */
@TableName(value ="t_role_right")
@Data
public class RoleRight {
    /**
     * 用户角色关联表id
     */
    @TableId(value = "trr_id",type = IdType.AUTO)
    @ApiModelProperty(value = "用户角色关联表id", required = true)
    private Integer trrId;

    /**
     *
     */
    @NotNull(message = "角色Id不为空")
    @ApiModelProperty(value = "角色id", required = true)
    private Integer roleTrId;

    /**
     *
     */
    @NotNull(message = "权限Id不为空")
    @ApiModelProperty(value = "权限id", required = true)
    private Integer rightId;
}
