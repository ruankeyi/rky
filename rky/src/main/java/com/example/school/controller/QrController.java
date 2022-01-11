package com.example.school.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.school.Result;
import com.example.school.domain.Qr;
import com.example.school.domain.Type;
import com.example.school.dto.QrDTO;
import com.example.school.dto.QrSelectDTO;
import com.example.school.dto.UserDTO;
import com.example.school.service.QrService;
import com.example.school.vo.QrVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/10/16:03
 * @Description:
 */
@Api(tags = "出入配置模块操作api")
@RestController
@RequestMapping("/qr")
public class QrController {
    @Autowired
    private QrService qrService;

    @ApiOperation(value = "出入配置添加接口")
    @PostMapping(value = "/addQr")
    public Result<Integer> insert(@RequestBody @Validated QrDTO qrDTO){
        return Result.ok(qrService.insert(qrDTO));
    }

    @ApiOperation(value = "出入配置修改接口")
    @PutMapping(value = "/updateQr")
    public Result<?> update(@RequestBody @Validated QrDTO qrDTO) {
        return Result.ok(qrService.update(qrDTO));
    }

    @ApiOperation(value = "出入配置删除接口")
    @DeleteMapping("/deleteById")
    public Result<?> delete(@RequestParam(value = "id") @ApiParam(value = "出入配置id", required = true) @NotNull(message = "id不能为空") Integer id) {
        return Result.ok(qrService.deleteById(id));
    }

    @ApiOperation(value = "出入配置分页查询")
    @GetMapping("/findByPage")
    public Result<Page<QrVO>> selectByPage(@Validated QrSelectDTO qrSelectDTO){
        return Result.ok(qrService.selectListPage(qrSelectDTO));
    }

    @ApiOperation(value = "二维码下载")
    @GetMapping("/saveImg")
    public void downloadlocal(HttpServletResponse response) throws IOException{
       qrService.downloadlocal(response);
    }
}
