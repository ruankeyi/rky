package com.example.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

/**
 * @Author: ruankeyi
 * @Date: 2022/01/13/22:08
 * @Description:
 */
@Repository
public interface LoginMapper  extends BaseMapper<User> {
}
