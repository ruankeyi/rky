package com.example.school.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.school.domain.AdminRole;
import com.example.school.mapper.AdminRoleMapper;
import com.example.school.service.AdminRoleService;
import org.springframework.stereotype.Service;

/**
 * @Author: ruankeyi
 * @Date: 2022/01/09/19:24
 * @Description:
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {
}
