package com.example.school.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.school.domain.Right;
import com.example.school.mapper.RightMapper;
import com.example.school.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ruankeyi
 * @Date: 2022/01/09/23:54
 * @Description:
 */
@Service
public class RightServiceImpl extends ServiceImpl<RightMapper, Right> implements RightService {
    @Autowired
    public RightMapper rightMapper;

    @Override
    public List<Right> getAllMenus() {
        return rightMapper.byGetRoles();
    }
}
