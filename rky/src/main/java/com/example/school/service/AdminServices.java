package com.example.school.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.example.school.domain.Admin;
import com.example.school.dto.LoginDTO;
import com.example.school.vo.LoginVO;


/**
 * @Author: ruankeyi
 * @Date: 2022/01/09/16:27
 * @Description:
 */
public interface AdminServices extends IService<Admin> {
     /**
     * 用户登录（后台用户）
     * @param loginDTO
     * @return
     */
     Page<LoginVO> login(LoginDTO loginDTO);
}
