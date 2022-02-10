package com.example.school.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.school.domain.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: ruankeyi
 * @Date: 2022/02/09/11:25
 * @Description:
 */
@Data
public class RightVO {
    /**
     *
     */
    @ApiModelProperty(value = "权限id", required = true)
    private Integer id;

    /**
     * 权限名
     */
    @ApiModelProperty(value = "权限名", required = true)
    private String rightName;

    /**
     * 路径
     */
    @ApiModelProperty(value = "路径", required = true)
    private String url;

    /**
     * 状态 1:启用  0:禁用
     */
    @ApiModelProperty(value = "状态 1:启用  0:禁用", required = true)
    private Integer state;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String info;

    /**
     * 角色名
     */
    @NotBlank(message = "角色名不为空")
    @ApiModelProperty(value = "角色名", required = true)
    private List<Role> roles;
}
