package com.example.school.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.school.Result;
import com.example.school.domain.Access;
import com.example.school.domain.Qr;
import com.example.school.domain.User;
import com.example.school.dto.*;
import com.example.school.exception.CommonException;
import com.example.school.mapper.AccessMapper;
import com.example.school.mapper.QrMapper;
import com.example.school.service.QrService;
import com.example.school.service.UserService;
import com.example.school.mapper.UserMapper;
import com.example.school.utils.WeChatUtil;
import com.example.school.vo.*;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *
 */
@Service
@Component
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private AccessMapper accessMapper;

    @Autowired
    private QrMapper qrMapper;

    @Autowired
    private QrService qrService;

    /**
     * 教职工/学生登录
     * @param userLoginDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserloginVO login(UserLoginDTO userLoginDTO) {
        //校验是否存在openid
        LambdaQueryWrapper<User> userLambda = new QueryWrapper<User>().lambda()
                .eq(User::getOpenId,userLoginDTO.getOpenId());
        Integer userCount = userMapper.selectCount(userLambda);
        if (userCount > 0) {
            User user1 = userMapper.selectOne(userLambda);

            //添加教职工学生出入记录信息
            Access access = new Access();
            access.setUserId(user1.getId());
            access.setQrId(userLoginDTO.getQrId());
            access.setScanCodeTime(LocalDateTime.now());
            accessMapper.insert(access);

            UserloginVO userloginVO = new UserloginVO();
            //添加返回参数
            userloginVO.setName(user1.getName());
            userloginVO.setCard(user1.getCard());
            userloginVO.setPhone(user1.getPhone());
            userloginVO.setModifyTime(LocalDateTime.now());
            userloginVO.setPip(qrService.getById(userLoginDTO.getQrId()).getPip());
            return userloginVO;
        }
        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda()
                .eq(User::getName,userLoginDTO.getName())
                .eq(User::getCard,userLoginDTO.getCard())
                .eq(User::getPhone,userLoginDTO.getPhone());
        Integer count = userMapper.selectCount(lambda);
        if (count == 0) {
            throw new CommonException("查无此人");
        }
        User user = userMapper.selectOne(lambda);
        user.setOpenId(userLoginDTO.getOpenId());
        userMapper.updateById(user);

        //添加教职工学生出入记录信息
        Access access = new Access();
        access.setUserId(user.getId());
        access.setQrId(userLoginDTO.getQrId());
        access.setScanCodeTime(LocalDateTime.now());
        accessMapper.insert(access);

        UserloginVO userloginVO = new UserloginVO();
        //添加返回参数
        userloginVO.setName(user.getName());
        userloginVO.setCard(user.getCard());
        userloginVO.setPhone(user.getPhone());
        userloginVO.setModifyTime(LocalDateTime.now());
        userloginVO.setPip(qrService.getById(userLoginDTO.getQrId()).getPip());
        return userloginVO;
    }

    /**
     * 访客登录
     * @param visitorsLoginDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public VisitorsLoginVO login(VisitorsLoginDTO visitorsLoginDTO) {
        //校验是否存在openid
        LambdaQueryWrapper<User> userLambda = new QueryWrapper<User>().lambda()
                .eq(User::getOpenId,visitorsLoginDTO.getOpenId());

        Integer userCount = userMapper.selectCount(userLambda);
        if (userCount > 0) {
            User users = userMapper.selectOne(userLambda);

            VisitorsLoginVO visitorsLoginVO = new VisitorsLoginVO();
            //添加返回参数
            visitorsLoginVO.setModifyTime(LocalDateTime.now());
            visitorsLoginVO.setVisitorsName(users.getName());
            visitorsLoginVO.setVisitorsCard(users.getCard());
            visitorsLoginVO.setVisitorsPhone(users.getPhone());
            visitorsLoginVO.setReason(users.getInfo());
            visitorsLoginVO.setPip(qrService.getById(visitorsLoginDTO.getQrId()).getPip());

            //添加访客出入记录信息
            Access access = new Access();
            access.setUserId(users.getId());
            access.setQrId(visitorsLoginDTO.getQrId());
            access.setScanCodeTime(LocalDateTime.now());
            accessMapper.insert(access);

            return visitorsLoginVO;
        }

        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda()
                .eq(User::getName,visitorsLoginDTO.getName())
                .eq(User::getCard,visitorsLoginDTO.getCard())
                .eq(User::getPhone,visitorsLoginDTO.getPhone());
        Integer count = userMapper.selectCount(lambda);
        if (count == 0) {
            throw new CommonException("查无此人");
        }
        User user = userMapper.selectOne(lambda);

        VisitorsLoginVO visitorsLoginVO = new VisitorsLoginVO();
        //添加返回参数
        visitorsLoginVO.setName(visitorsLoginDTO.getName());
        visitorsLoginVO.setCard(visitorsLoginDTO.getCard());
        visitorsLoginVO.setPhone(visitorsLoginDTO.getPhone());
        visitorsLoginVO.setModifyTime(LocalDateTime.now());
        visitorsLoginVO.setVisitorsName(visitorsLoginDTO.getVisitorsName());
        visitorsLoginVO.setVisitorsCard(visitorsLoginDTO.getVisitorsCard());
        visitorsLoginVO.setVisitorsPhone(visitorsLoginDTO.getVisitorsPhone());
        visitorsLoginVO.setReason(visitorsLoginDTO.getReason());
        visitorsLoginVO.setPip(qrService.getById(visitorsLoginDTO.getQrId()).getPip());

        //添加访客信息
        User user1 = new User();
        user1.setOpenId(visitorsLoginDTO.getOpenId());
        user1.setName(visitorsLoginDTO.getVisitorsName());
        user1.setCard(visitorsLoginDTO.getVisitorsCard());
        user1.setPhone(visitorsLoginDTO.getPhone());
        user1.setTypeId(4);
        user1.setExpiry("24h");
        user1.setState(1);
        user1.setCreateTime(LocalDateTime.now());
        user1.setModifyTime(LocalDateTime.now());
        user1.setInfo("拜访者姓名:"+visitorsLoginDTO.getName()+","+"拜访对象学号/工号/身份证号:"+visitorsLoginDTO.getCard()+","+"拜访对象联系电话"+visitorsLoginDTO.getPhone()+","+"入校事由:"+visitorsLoginDTO.getReason());
        userMapper.insert(user1);

        //添加访客出入记录信息
        Access access = new Access();
        access.setUserId(user1.getId());
        access.setQrId(visitorsLoginDTO.getQrId());
        access.setScanCodeTime(LocalDateTime.now());
        accessMapper.insert(access);

        return visitorsLoginVO;
    }

//    /**
//     * 用户登录（后台管理员）
//     * @param loginDTO
//     * @return
//     */
//    @Override
//    public LoginVO login(LoginDTO loginDTO) {
//        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda()
//                .eq(User::getName,loginDTO.getName())
//                .eq(User::getCard,loginDTO.getCard());
//        Integer count = userMapper.selectCount(lambda);
//        if (count == 0) {
//            throw new CommonException("用户名或密码不正确");
//        }
//        User user = userMapper.selectOne(lambda);
//        LoginVO loginVO = new LoginVO();
//        loginVO.setName(user.getName());
//        loginVO.setTime(LocalDateTime.now());
//        return loginVO;
//    }

    /**
     * 人员信息添加
     * @param userDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insert(UserDTO userDTO) {
        LambdaQueryWrapper<User> userQueryWrapper = new QueryWrapper<User>()
                .lambda().eq(User::getCard,userDTO.getCard());
        Integer count = userMapper.selectCount(userQueryWrapper);
        if(count > 0){
            throw new CommonException("学号/工号/身份证号已存在");
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setModifyTime(LocalDateTime.now());
        return userMapper.insert(user);
    }

    /**
     * 人员信息修改
     * @param userDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer update(UserDTO userDTO) {
        LambdaQueryWrapper<User> userId = new QueryWrapper<User>().lambda()
                .eq(User::getId,userDTO.getId());
        Integer count = userMapper.selectCount(userId);
        if (count == 0){
            throw new CommonException("人员信息不存在");
        }

        //判断学号/工号/身份证号是否更改
        User user = userMapper.selectById(userDTO.getId());
        if (!user.getCard().equals(userDTO.getCard())){
            throw new CommonException("学号/工号/身份证号不可更改");
        }
        BeanUtils.copyProperties(userDTO,user);
        user.setModifyTime(LocalDateTime.now());
        return userMapper.updateById(user);
    }

    /**
     * 人员信息删除
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteById(Integer id) {
        LambdaQueryWrapper<User> userId = new QueryWrapper<User>().lambda()
                .eq(User::getId,id);
        Integer count = userMapper.selectCount(userId);
        if (count == 0){
            throw new CommonException("人员信息不存在");
        }
        return userMapper.deleteById(id);
    }

    /**
     * 人员信息查询（传输对象）
     * @param userSelectDTO
     * @return
     */
    @Override
    public Page<UserVO> selectPage(UserSelectDTO userSelectDTO) {
        Page<User> page = new Page<>(userSelectDTO.getPageSize(), userSelectDTO.getPageNumber());
        Page<UserVO> productPage =userMapper.dyGetUserList(page,userSelectDTO);
        return productPage;
    }

    /**
     * 人员信息批量删除
     * @param idlist
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeByIds1(List<Integer> idlist) {
        this.removeByIds(idlist);
    }

    /**
     *人员信息批量导入
     * @param users
     * @return
     */
    @Override
    public Integer insertUsers(List<User> users) {
        Integer insert= 0;
        for (User user : users){

            //导入校验
            boolean matches = user.getPhone().matches("^[1][3,4,5,6,7,8,9][0-9]{9}$");
            if (matches){
                insert += userMapper.insert(user);
            }else {
                throw new CommonException("手机格式有误");
            }

        }
        return insert;
    }

    /**
     * 按小时统计表
     * @param hoursDTO
     * @return
     */
    @Override
    public Page<HoursVO> selectHours(HoursDTO hoursDTO) {
        Page<HoursVO> page = new Page<>(hoursDTO.getPageSize(), hoursDTO.getPageNumber());
        Page<HoursVO> productPage =userMapper.dyGetHoursList(page,hoursDTO);
        return productPage;
    }

    /**
     * 按人员类型统计表
     * @param hoursDTO
     * @return
     */
    @Override
    public Page<RoleVO> selectRole(HoursDTO hoursDTO) {
        Page<RoleVO> page = new Page<>(hoursDTO.getPageSize(), hoursDTO.getPageNumber());
        Page<RoleVO> productPage =userMapper.dyGetRoleList(page,hoursDTO);
        return productPage;
    }

    /**
     * 按月份统计表
     * @param monthDTO
     * @return
     */
    @Override
    public Page<MonthVO> selectMonth(MonthDTO monthDTO) {
        Page<MonthVO> page = new Page<>(monthDTO.getPageSize(), monthDTO.getPageNumber());
        Page<MonthVO> productPage =userMapper.dyGetMonthList(page,monthDTO);
        return productPage;
    }

    /**
     * 对人数进行统计
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @Override
    public Page<NumberVO> selectNumber(Integer pageSize, Integer pageNumber) {
        Page<NumberVO> page = new Page<>(pageSize,pageNumber);
        Page<NumberVO> productPage =userMapper.dyGetNumberList(page);
        return productPage;
    }

    /**
     * 24H访客信息 定时删除
     */
    @Scheduled(cron = "0/5 * *  * * ?")
    @Override
    public void timerRun() {
        // 一天的毫秒数
        long daySpan = 24 * 60 * 60 * 1000;
        // 规定的每天几点运行
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 15:33:00");
        // 首次运行时间
        try {
            Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf.format(new Date()));
            // 如果今天的已经过了 首次运行时间就改为明天
            if (System.currentTimeMillis() > startTime.getTime()){
                startTime = new Date(startTime.getTime() + daySpan);
            }
            Timer t = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    LambdaQueryWrapper<User> typeId = new QueryWrapper<User>().lambda()
                            .eq(User::getTypeId,4);
                    Integer count = userMapper.selectCount(typeId);
                    if (count == 0){
                        System.out.println("无访客信息");
                    }
                    List<User> userList = userService.list(typeId);
                    for (User user : userList) {
                        //把LocalDateTime转换成Date类型
                        Instant instant = user.getCreateTime().atZone( ZoneId.systemDefault()).toInstant();
                        Date start = Date.from(instant);

                        //获取当前时间
                        Date end = new Date();

                        long cha = end.getTime() - start.getTime();
                        double result = cha * 1.0 / (1000 * 60 * 60);
                        if (result < 24) {
                            System.out.println("访客信息不符合");
                        } else {
                            user.setState(0);
                            user.setOpenId(0);
                            userMapper.updateById(user);
                        }
                    }
                }
            };
            // 以每24小时执行一次
            t.schedule(task, startTime, daySpan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




