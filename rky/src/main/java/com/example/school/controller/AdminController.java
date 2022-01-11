package com.example.school.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.school.Result;
import com.example.school.dto.LoginDTO;
import com.example.school.service.AdminServices;
import com.example.school.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "用户登录（后台管理员）")
    @GetMapping(value = "/adminLogin")
    public Result<Page<LoginVO>> login(LoginDTO loginDTO)
    {
        Page<LoginVO> login = adminServices.login(loginDTO);
        return Result.ok(login);
    }
}
