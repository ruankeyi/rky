package com.example.school.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: ruankeyi
 * @Date: 2022/01/09/17:10
 * @Description:
 */
@TableName(value ="t_tu_role")
@Data
public class AdminRole {
    /**
     * 用户角色关联表id
     */
    @TableId(value = "tur_id",type = IdType.AUTO)
    @ApiModelProperty(value = "用户角色关联表id", required = true)
    private Integer turId;

    /**
     *
     */
    @NotNull(message = "后台用户Id不为空")
    @ApiModelProperty(value = "后台用户id", required = true)
    private Integer uTuId;

    /**
     *
     */
    @NotNull(message = "角色Id不为空")
    @ApiModelProperty(value = "角色id", required = true)
    private Integer roleTrId;
}
