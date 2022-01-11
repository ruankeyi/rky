package com.example.school.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.school.Result;
import com.example.school.domain.Type;
import com.example.school.domain.User;
import com.example.school.dto.TypeDTO;
import com.example.school.dto.TypeSelectDTO;
import com.example.school.dto.UserDTO;
import com.example.school.service.TypeService;
import com.example.school.vo.TypeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/10/15:54
 * @Description:
 */
@Api(tags = "人员类型模块操作api")
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @ApiOperation(value = "人员类型添加接口")
    @PostMapping(value = "/addType")
    public Result<Integer> insert(@RequestBody @Validated TypeDTO typeDTO){
        return Result.ok(typeService.insert(typeDTO));
    }

    @ApiOperation(value = "人员类型修改接口")
    @PutMapping(value = "/updateType")
    public Result<?> update(@RequestBody @Validated TypeDTO typeDTO) {
        return Result.ok(typeService.update(typeDTO));
    }

    @ApiOperation(value = "人员类型删除接口")
    @DeleteMapping("/deleteById")
    public Result<?> delete(@RequestParam(value = "id") @ApiParam(value = "人员类型id", required = true) @NotNull(message = "id不能为空") Integer id) {
        return Result.ok(typeService.deleteById(id));
    }

    @ApiOperation(value = "人员类型分页查询")
    @GetMapping("/findByPage")
    public Result<Page<TypeVO>> selectByPage(@Validated TypeSelectDTO typeSelectDTO){
        return Result.ok(typeService.selectListPage(typeSelectDTO));
    }
}
