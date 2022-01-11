package com.example.school.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.school.domain.Qr;
import com.example.school.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.school.dto.HoursDTO;
import com.example.school.dto.MonthDTO;
import com.example.school.dto.UserSelectDTO;
import com.example.school.vo.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.example.demo.domain.User
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
     Page<UserVO> dyGetUserList(Page<User> page, UserSelectDTO userSelectDTO);//（传输对象）

     //按小时统计表
     Page<HoursVO> dyGetHoursList(Page<HoursVO> page, HoursDTO hoursDTO);

     //按人员类型统计表
     Page<RoleVO> dyGetRoleList(Page<RoleVO>page, HoursDTO hoursDTO);

     //按月份统计表
     Page<MonthVO> dyGetMonthList(Page<MonthVO>page, MonthDTO monthDTO);

     //对人数进行统计
     Page<NumberVO> dyGetNumberList(Page<NumberVO>page);
}




