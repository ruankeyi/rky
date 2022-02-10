package com.example.school.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.school.domain.Right;
import com.example.school.domain.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/29/14:10
 * @Description:
 */
@Data
public class LoginVO {
    /**
     *后台用户id
     */
    @ApiModelProperty(value = "后台用户id", required = true)
    private Integer tuId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    /**
     * 角色信息
     */
    @ApiModelProperty(value = "角色信息")
    private List<Role> roleList;

    /**
     * 权限信息
     */
    @ApiModelProperty(value = "权限信息")
    private List<Right> rightList;
}
