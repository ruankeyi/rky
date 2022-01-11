package com.example.school.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.school.Result;
import com.example.school.dto.AccessSelectDTO;
import com.example.school.dto.UserSelectDTO;
import com.example.school.service.AccessService;
import com.example.school.vo.AccessVO;
import com.example.school.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/10/14:36
 * @Description:
 */
@Api(tags = "出入记录模块操作api")
@RestController
@RequestMapping("/access")
public class AccessController {
    @Autowired
    private AccessService accessService;

    @ApiOperation(value = "出入记录分页查询")
    @GetMapping("/findByPage")
    public Result<?> selectByPage(@Validated AccessSelectDTO accessSelectDTO){
        return Result.ok(accessService.selectPage(accessSelectDTO));
    }

    @ApiOperation(value = "出入记录导出")
    @PostMapping("/exportInfo")
    public void excelWriter(@RequestBody @Validated AccessSelectDTO accessSelectDTO){
       accessService.excelWriter(accessSelectDTO);
    }
}
