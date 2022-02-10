package com.example.school.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.school.Result;
import com.example.school.dto.AdminDTO;
import com.example.school.dto.LoginDTO;
import com.example.school.dto.RightDTO;
import com.example.school.dto.RoleDTO;
import com.example.school.service.AdminServices;
import com.example.school.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Author: ruankeyi
 * @Date: 2022/01/09/22:08
 * @Description:
 */
@Api(tags = "后台用户api")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminServices adminServices;

    @ApiOperation(value = "后台账号新增")
    @PostMapping(value = "/addAdmin")
    public Result<AdminDTO> insert(@Validated AdminDTO adminDTO) {
        return Result.ok(adminServices.insert(adminDTO));
    }

    @ApiOperation(value = "后台账号删除")
    @DeleteMapping(value = "/deleteAdmin")
    public Result<?> deleteAdmin(@RequestParam(value = "id") @ApiParam(value = "后台账号id", required = true) @NotNull(message = "后台账号Id不为空") Integer id) {
        return Result.ok(adminServices.deleteById(id));
    }

    @ApiOperation(value = "后台角色新增")
    @PostMapping(value = "/addRole")
    public Result<RoleDTO> insert(@Validated RoleDTO roleDTO) {
        return Result.ok(adminServices.insert(roleDTO));
    }

    @ApiOperation(value = "后台角色删除")
    @DeleteMapping(value = "/deleteRole")
    public Result<?> deleteRole(@RequestParam(value = "id") @ApiParam(value = "角色id", required = true) @NotNull(message = "角色Id不为空") Integer id) {
        return Result.ok(adminServices.deleteByRoleId(id));
    }

    @ApiOperation(value = "后台权限新增")
    @PostMapping(value = "/addRight")
    public Result<RightDTO> insert(@Validated RightDTO rightDTO) {
        return Result.ok(adminServices.insert(rightDTO));
    }

    @ApiOperation(value = "后台权限删除")
    @DeleteMapping(value = "/deleteRight")
    public Result<?> deleteRight(@RequestParam(value = "id") @ApiParam(value = "权限id", required = true) @NotNull(message = "权限id不为空") Integer id) {
        return Result.ok(adminServices.deleteByRightId(id));
    }

    @ApiOperation(value = "用户所属角色权限信息查询）")
    @GetMapping(value = "/findByPage")
    public Result<Page<LoginVO>> login(LoginDTO loginDTO)
    {
        Page<LoginVO> login = adminServices.login(loginDTO);
        return Result.ok(login);
    }
}
