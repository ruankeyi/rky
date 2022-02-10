package com.example.school.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.school.domain.Right;

import java.util.List;

/**
 * @Author: ruankeyi
 * @Date: 2022/01/09/23:54
 * @Description:
 */
public interface RightService extends IService<Right> {
     List<Right> getAllMenus();
}
