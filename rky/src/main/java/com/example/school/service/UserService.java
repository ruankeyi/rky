package com.example.school.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.school.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.school.dto.*;
import com.example.school.vo.*;

import java.util.List;

/**
 *
 */
public interface UserService extends IService<User> {

    /**
     * 教职工/学生登录
     * @param userLoginDTO
     * @return
     */
    UserloginVO login(UserLoginDTO userLoginDTO);

    /**
     * 访客登录
     * @param visitorsLoginDTO
     * @return
     */
    VisitorsLoginVO login(VisitorsLoginDTO visitorsLoginDTO);

//    /**
//     * 用户登录（后台管理员）
//     * @param loginDTO
//     * @return
//     */
//    LoginVO login(LoginDTO loginDTO);

    /**
     * 人员信息添加
     * @param userDTO
     * @return
     */
    Integer insert(UserDTO userDTO);

    /**
     * 人员信息修改
     * @param userDTO
     * @return
     */
    Integer update(UserDTO userDTO);

    /**
     * 人员信息删除
     * @param id
     * @return
     */
    Integer deleteById(Integer id);

    /**
     * 人员信息查询（传输对象）
     * @param userSelectDTO
     * @return
     */
    Page<UserVO> selectPage(UserSelectDTO userSelectDTO);

    /**
     * 人员信息批量删除
     * @param idlist
     */
    void removeByIds1(List<Integer> idlist);

    /**
     *人员信息批量导入
     * @param users
     * @return
     */
    Integer insertUsers(List<User> users);

    /**
     * 按小时统计表
     * @param hoursDTO
     * @return
     */
    Page<HoursVO> selectHours(HoursDTO hoursDTO);

    /**
     * 按人员类型统计表
     * @param hoursDTO
     * @return
     */
    Page<RoleVO> selectRole(HoursDTO hoursDTO);

    /**
     * 按月份统计表
     * @param hoursDTO
     * @return
     */
    Page<MonthVO> selectMonth(MonthDTO hoursDTO);

    /**
     * 对人数进行统计
     * @param pageSize
     * @param pageNumber
     * @return
     */
    Page<NumberVO> selectNumber(Integer pageSize, Integer pageNumber );

    /**
     * 24H访客信息 定时删除
     */
    void timerRun();
}
