package com.example.school.controller;

import com.example.school.Result;
import com.example.school.dto.HoursDTO;
import com.example.school.dto.MonthDTO;
import com.example.school.service.UserService;
import com.example.school.vo.HoursVO;
import com.example.school.vo.MonthVO;
import com.example.school.vo.NumberVO;
import com.example.school.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/17/17:09
 * @Description:
 */
@Api(tags = "首页模块操作api")
@RestController
@RequestMapping("/mainPage")
public class MainPage {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "首页时段统计")
    @GetMapping("/findHours")
    public Result<HoursVO> selectByPage(@Validated HoursDTO hoursDTO){
        return Result.ok(userService.selectHours(hoursDTO));
    }

    @ApiOperation(value = "首页人员类型统计表")
    @GetMapping("/findRole")
    public Result<RoleVO> selectByRole(@Validated HoursDTO hoursDTO){
        return Result.ok(userService.selectRole(hoursDTO));
    }

    @ApiOperation(value = "首页月份统计表")
    @GetMapping("/findMonths")
    public Result<MonthVO> selectByMonth(@Validated MonthDTO monthDTO){
        return Result.ok(userService.selectMonth(monthDTO));
    }

    @ApiOperation(value = "首页人数统计")
    @GetMapping("/findByData")
    public Result<NumberVO> selectByNumber(@RequestParam(value = "pageSize") @ApiParam(value = "页码", required = true) @NotNull(message = "页码不能为空") Integer pageSize,
                                           @RequestParam(value = "pageNumber") @ApiParam(value = "条数", required = true) @NotNull(message = "条数不能为空") Integer pageNumber){
        return Result.ok(userService.selectNumber(pageSize,pageNumber));
    }
}
