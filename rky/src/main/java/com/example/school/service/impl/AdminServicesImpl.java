package com.example.school.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.school.domain.*;
import com.example.school.dto.LoginDTO;
import com.example.school.exception.CommonException;
import com.example.school.mapper.AdminMapper;
import com.example.school.mapper.AdminRoleMapper;
import com.example.school.mapper.RightMapper;
import com.example.school.mapper.RoleRightMapper;
import com.example.school.service.AdminRoleService;
import com.example.school.service.AdminServices;
import com.example.school.service.RightService;
import com.example.school.service.RoleRightService;
import com.example.school.vo.LoginVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ruankeyi
 * @Date: 2022/01/09/16:27
 * @Description:
 */
@Service
public class AdminServicesImpl extends ServiceImpl<AdminMapper, Admin> implements AdminServices {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminServices adminServices;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private RoleRightMapper roleRightMapper;

    @Autowired
    private RoleRightService roleRightService;

    @Autowired
    private RightMapper rightMapper;

    @Autowired
    private RightService rightService;

    @Override
    public Page<LoginVO> login(LoginDTO loginDTO) {
        LambdaQueryWrapper<Admin> lambda = new QueryWrapper<Admin>().lambda()
                .eq(Admin::getUsername,loginDTO.getUsername())
                .eq(Admin::getPassword,loginDTO.getPassword());
        Integer count = adminMapper.selectCount(lambda);
        if (count == 0) {
            throw new CommonException("用户名或密码不正确");
        }

        Page<LoginVO> page = new Page<>(1,1);
        Page<LoginVO> loginVO = rightMapper.dyGetList(page,loginDTO);
        List<LoginVO> records = loginVO.getRecords();


                for (LoginVO right : records){
                    Integer id = right.getTuId();
                    List<Right> rightList = rightMapper.byGetRightList(id);
                    right.setRightList(rightList);
                }

        return loginVO;
    }
}
