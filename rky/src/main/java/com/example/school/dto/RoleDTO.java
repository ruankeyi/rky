package com.example.school.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: ruankeyi
 * @Date: 2022/02/07/9:14
 * @Description:
 */
@Data
public class RoleDTO {
    /**
     * 角色名
     */
    @NotBlank(message = "角色名不为空")
    @ApiModelProperty(value = "角色名", required = true)
    private String roleName;

    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述", required = true)
    private String roleDesc;

    /**
     * 状态 1:启用  0:禁用
     */
    @NotNull(message = "状态 1:启用  0:禁用")
    @ApiModelProperty(value = "状态 1:启用  0:禁用", required = true)
    private Integer state;

    /**
     * 权限id集合
     */
    @ApiModelProperty(value = "权限id信息")
    private List<Integer> integerList;
}
