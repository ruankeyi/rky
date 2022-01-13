package com.example.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.school.domain.Admin;
import com.example.school.domain.Right;
import com.example.school.domain.Role;
import com.example.school.domain.Type;
import com.example.school.dto.LoginDTO;
import com.example.school.dto.TypeSelectDTO;
import com.example.school.vo.LoginVO;
import com.example.school.vo.TypeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ruankeyi
 * @Date: 2022/01/09/17:53
 * @Description:
 */
@Repository
public interface RightMapper extends BaseMapper<Right> {
    Admin dyGetusername(String username);
    List<Role> byGetRoleList(Integer tuId);
    Page<LoginVO> dyGetList(Page<LoginVO> page,LoginDTO loginDTO);
    List<Right> byGetRightList(Integer tuId);
}
