package com.example.school.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.example.school.domain.Admin;
import com.example.school.dto.*;
import com.example.school.vo.LoginVO;


/**
 * @Author: ruankeyi
 * @Date: 2022/01/09/16:27
 * @Description:
 */
public interface AdminServices extends IService<Admin> {
     /**
      * 后台账号新增（多表）
      * @param adminDTO
      * @return
      */
     Integer insert(AdminDTO adminDTO);

     /**
      * 后台账号删除(逻辑删除)
      * @param id
      * @return
      */
     Integer deleteById(Integer id);

     /**
      * 后台角色新增（多表）
      * @param roleDTO
      * @return
      */
     Integer insert(RoleDTO roleDTO);

     /**
      * 后台角色删除(逻辑删除)
      * @param id
      * @return
      */
     Integer deleteByRoleId(Integer id);

     /**
      * 后台权限新增
      * @param rightDTO
      * @return
      */
     Integer insert(RightDTO rightDTO);

     /**
      * 后台权限删除(逻辑删除)
      * @param id
      * @return
      */
     Integer deleteByRightId(Integer id);

     /**
     * 用户查询
     * @param loginDTO
     * @return
     */
     Page<LoginVO> login(LoginDTO loginDTO);
}
