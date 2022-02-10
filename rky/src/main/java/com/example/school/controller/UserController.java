package com.example.school.controller;

import cn.hutool.poi.excel.ExcelReader;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.school.Result;
import com.example.school.domain.User;
import com.example.school.dto.*;
import com.example.school.exception.CommonException;
import com.example.school.service.UserService;
import com.example.school.utils.GetOpenIdUtil;
import com.example.school.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.util.List;

/**
 * @Author: ruankeyi
 * @Date: 2021/12/10/14:53
 * @Description:
 */
@Api(tags = "人员信息模块操作api")
@RestController
@RequestMapping("/user")
@EnableScheduling
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "教职工学生登陆接口")
    @GetMapping(value = "/userLogin")
    public Result<UserloginVO> login(@Validated UserLoginDTO userLoginDTO)
    {
        UserloginVO login = userService.login(userLoginDTO);
        return Result.ok(login);
    }

    @ApiOperation(value = "访客登陆接口")
    @GetMapping(value = "/visitorsLogin")
    public Result<VisitorsLoginVO> login(@Validated VisitorsLoginDTO visitorsLoginDTO)
    {
        VisitorsLoginVO login = userService.login(visitorsLoginDTO);
        return Result.ok(login);
    }

//    @ApiOperation(value = "用户登录（后台管理员）")
//    @GetMapping(value = "/Login")
//    public Result<LoginVO> login(LoginDTO loginDTO)
//    {
//        LoginVO login = userService.login(loginDTO);
//        return Result.ok(login);
//    }

    @ApiOperation(value = "人员信息添加接口")
    @PostMapping(value = "/addUser")
    public Result<?> insert(@RequestBody @Validated UserDTO userDTO){
        return Result.ok(userService.insert(userDTO));
    }

    @ApiOperation(value = "人员信息修改接口")
    @PutMapping(value = "/updateUser")
    public Result<?> update(@RequestBody @Validated UserDTO userDTO) {
        return Result.ok(userService.update(userDTO));
    }

    @ApiOperation(value = "人员信息单个删除接口")
    @DeleteMapping("/deleteUser")
    public Result<?> delete(@RequestParam(value = "id") @ApiParam(value = "人员信息id", required = true) @NotNull(message = "id不能为空") Integer id) {
        return Result.ok(userService.deleteById(id));
    }

    @ApiOperation(value = "人员信息分页模糊查询(对象)")
    @GetMapping("/findByPage")
    public Result<Page<UserVO>> selectByPage(@Validated UserSelectDTO userSelectDTO){
        return Result.ok(userService.selectPage(userSelectDTO));
    }

    @ApiOperation(value = "人员信息批量删除接口")
    @DeleteMapping("/deleteByIds")
    private Result<String> removeByIds(@RequestParam (value = "idlist") List<Integer> idlist){
        userService.removeByIds1(idlist);
        return  Result.ok("删除成功");
    }

    @ApiOperation(value = "人员信息批量导入")
    @PostMapping("/batchImport")
    public Result<?> insertUsers(@RequestPart @RequestParam("file") MultipartFile file){
        try {
            ExcelReader excelReader = new ExcelReader(file.getInputStream(), 0);
            //直接把Excel中的内容映射到实体类中
            List<User> users = excelReader.read(0, 0, User.class);
            //把映射的Excel中的数据添加到数据库中
            int i = userService.insertUsers(users);
            //返回影响的记录数
            return Result.ok(i);
        } catch ( IOException e) {
            e.printStackTrace();
            throw new CommonException("导入失败");
        }
    }

    @ApiOperation(value = "24H访客信息 定时删除")
    @GetMapping("/timeToDelete")
    public void timerRun(){
        userService.timerRun();
    }


    @ApiOperation(value = "获取openid")
    @GetMapping("/getOpenId")
    public Result<?> getWxOpenId(String code){
            GetOpenIdUtil getOpenIdUtil=new GetOpenIdUtil();
            String jsonId = getOpenIdUtil.getopenid("wx7fa962c9e357d54a",code,"29b8712293dc85c4513a5df2271152ec");
            JSONObject jsonObject = JSONObject.parseObject(jsonId);
            return Result.ok(jsonObject);
    }
}
