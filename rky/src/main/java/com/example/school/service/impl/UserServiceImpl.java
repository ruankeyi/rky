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
     * ?????????/????????????
     * @param userLoginDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserloginVO login(UserLoginDTO userLoginDTO) {
        //??????????????????openid
        LambdaQueryWrapper<User> userLambda = new QueryWrapper<User>().lambda()
                .eq(User::getOpenId,userLoginDTO.getOpenId());
        Integer userCount = userMapper.selectCount(userLambda);
        if (userCount > 0) {
            User user1 = userMapper.selectOne(userLambda);

            //???????????????????????????????????????
            Access access = new Access();
            access.setUserId(user1.getId());
            access.setQrId(userLoginDTO.getQrId());
            access.setScanCodeTime(LocalDateTime.now());
            accessMapper.insert(access);

            UserloginVO userloginVO = new UserloginVO();
            //??????????????????
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
            throw new CommonException("????????????");
        }
        User user = userMapper.selectOne(lambda);
        user.setOpenId(userLoginDTO.getOpenId());
        userMapper.updateById(user);

        //???????????????????????????????????????
        Access access = new Access();
        access.setUserId(user.getId());
        access.setQrId(userLoginDTO.getQrId());
        access.setScanCodeTime(LocalDateTime.now());
        accessMapper.insert(access);

        UserloginVO userloginVO = new UserloginVO();
        //??????????????????
        userloginVO.setName(user.getName());
        userloginVO.setCard(user.getCard());
        userloginVO.setPhone(user.getPhone());
        userloginVO.setModifyTime(LocalDateTime.now());
        userloginVO.setPip(qrService.getById(userLoginDTO.getQrId()).getPip());
        return userloginVO;
    }

    /**
     * ????????????
     * @param visitorsLoginDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public VisitorsLoginVO login(VisitorsLoginDTO visitorsLoginDTO) {
        //??????????????????openid
        LambdaQueryWrapper<User> userLambda = new QueryWrapper<User>().lambda()
                .eq(User::getOpenId,visitorsLoginDTO.getOpenId());

        Integer userCount = userMapper.selectCount(userLambda);
        if (userCount > 0) {
            User users = userMapper.selectOne(userLambda);

            VisitorsLoginVO visitorsLoginVO = new VisitorsLoginVO();
            //??????????????????
            visitorsLoginVO.setModifyTime(LocalDateTime.now());
            visitorsLoginVO.setVisitorsName(users.getName());
            visitorsLoginVO.setVisitorsCard(users.getCard());
            visitorsLoginVO.setVisitorsPhone(users.getPhone());
            visitorsLoginVO.setReason(users.getInfo());
            visitorsLoginVO.setPip(qrService.getById(visitorsLoginDTO.getQrId()).getPip());

            //??????????????????????????????
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
            throw new CommonException("????????????");
        }
        User user = userMapper.selectOne(lambda);

        VisitorsLoginVO visitorsLoginVO = new VisitorsLoginVO();
        //??????????????????
        visitorsLoginVO.setName(visitorsLoginDTO.getName());
        visitorsLoginVO.setCard(visitorsLoginDTO.getCard());
        visitorsLoginVO.setPhone(visitorsLoginDTO.getPhone());
        visitorsLoginVO.setModifyTime(LocalDateTime.now());
        visitorsLoginVO.setVisitorsName(visitorsLoginDTO.getVisitorsName());
        visitorsLoginVO.setVisitorsCard(visitorsLoginDTO.getVisitorsCard());
        visitorsLoginVO.setVisitorsPhone(visitorsLoginDTO.getVisitorsPhone());
        visitorsLoginVO.setReason(visitorsLoginDTO.getReason());
        visitorsLoginVO.setPip(qrService.getById(visitorsLoginDTO.getQrId()).getPip());

        //??????????????????
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
        user1.setInfo("???????????????:"+visitorsLoginDTO.getName()+","+"??????????????????/??????/????????????:"+visitorsLoginDTO.getCard()+","+"????????????????????????"+visitorsLoginDTO.getPhone()+","+"????????????:"+visitorsLoginDTO.getReason());
        userMapper.insert(user1);

        //??????????????????????????????
        Access access = new Access();
        access.setUserId(user1.getId());
        access.setQrId(visitorsLoginDTO.getQrId());
        access.setScanCodeTime(LocalDateTime.now());
        accessMapper.insert(access);

        return visitorsLoginVO;
    }

//    /**
//     * ?????????????????????????????????
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
//            throw new CommonException("???????????????????????????");
//        }
//        User user = userMapper.selectOne(lambda);
//        LoginVO loginVO = new LoginVO();
//        loginVO.setName(user.getName());
//        loginVO.setTime(LocalDateTime.now());
//        return loginVO;
//    }

    /**
     * ??????????????????
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
            throw new CommonException("??????/??????/?????????????????????");
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setModifyTime(LocalDateTime.now());
        return userMapper.insert(user);
    }

    /**
     * ??????????????????
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
            throw new CommonException("?????????????????????");
        }

        //????????????/??????/????????????????????????
        User user = userMapper.selectById(userDTO.getId());
        if (!user.getCard().equals(userDTO.getCard())){
            throw new CommonException("??????/??????/????????????????????????");
        }
        BeanUtils.copyProperties(userDTO,user);
        user.setModifyTime(LocalDateTime.now());
        return userMapper.updateById(user);
    }

    /**
     * ??????????????????
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
            throw new CommonException("?????????????????????");
        }
        return userMapper.deleteById(id);
    }

    /**
     * ????????????????????????????????????
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
     * ????????????????????????
     * @param idlist
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeByIds1(List<Integer> idlist) {
        this.removeByIds(idlist);
    }

    /**
     *????????????????????????
     * @param users
     * @return
     */
    @Override
    public Integer insertUsers(List<User> users) {
        Integer insert= 0;
        for (User user : users){

            //????????????
            boolean matches = user.getPhone().matches("^[1][3,4,5,6,7,8,9][0-9]{9}$");
            if (matches){
                insert += userMapper.insert(user);
            }else {
                throw new CommonException("??????????????????");
            }

        }
        return insert;
    }

    /**
     * ??????????????????
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
     * ????????????????????????
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
     * ??????????????????
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
     * ?????????????????????
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
     * 24H???????????? ????????????
     */
    @Scheduled(cron = "0/5 * *  * * ?")
    @Override
    public void timerRun() {
        // ??????????????????
        long daySpan = 24 * 60 * 60 * 1000;
        // ???????????????????????????
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 15:33:00");
        // ??????????????????
        try {
            Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf.format(new Date()));
            // ??????????????????????????? ?????????????????????????????????
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
                        System.out.println("???????????????");
                    }
                    List<User> userList = userService.list(typeId);
                    for (User user : userList) {
                        //???LocalDateTime?????????Date??????
                        Instant instant = user.getCreateTime().atZone( ZoneId.systemDefault()).toInstant();
                        Date start = Date.from(instant);

                        //??????????????????
                        Date end = new Date();

                        long cha = end.getTime() - start.getTime();
                        double result = cha * 1.0 / (1000 * 60 * 60);
                        if (result < 24) {
                            System.out.println("?????????????????????");
                        } else {
                            user.setState(0);
                            user.setOpenId(0);
                            userMapper.updateById(user);
                        }
                    }
                }
            };
            // ??????24??????????????????
            t.schedule(task, startTime, daySpan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




