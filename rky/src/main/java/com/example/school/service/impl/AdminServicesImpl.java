package com.example.school.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.school.domain.*;
import com.example.school.dto.AdminDTO;
import com.example.school.dto.LoginDTO;
import com.example.school.dto.RightDTO;
import com.example.school.dto.RoleDTO;
import com.example.school.exception.CommonException;
import com.example.school.mapper.*;
import com.example.school.service.AdminRoleService;
import com.example.school.service.AdminServices;
import com.example.school.service.RightService;
import com.example.school.service.RoleRightService;
import com.example.school.vo.LoginVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private RoleMapper roleMapper;

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

    /**
     * 后台账号新增
     * @param adminDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insert(AdminDTO adminDTO) {
        LambdaQueryWrapper<Admin> userQueryWrapper = new QueryWrapper<Admin>()
                .lambda().eq(Admin::getUsername,adminDTO.getUsername());
        Integer count = adminMapper.selectCount(userQueryWrapper);
        if(count > 0){
            throw new  CommonException("用户名已存在");
        }
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminDTO,admin);
        adminMapper.insert(admin);
        //获取用户中的角色信息
        List<Integer> roleList = adminDTO.getIntegerList();
        //插入用户角色的关联信息
        for (Integer roleVO :roleList){
            LambdaQueryWrapper<Role> wrapper = new QueryWrapper<Role>()
                    .lambda().eq(Role::getTrId,roleVO);
            int roleId = roleMapper.selectOne(wrapper).getTrId();
            int userId = adminMapper.selectOne(userQueryWrapper).getTuId();
            AdminRole adminRole = new AdminRole();
            adminRole.setUTuId(userId);
            adminRole.setRoleTrId(roleId);
            adminRoleMapper.insert(adminRole);
        }
        return 1;
    }

    /**
     * 后台账号删除(逻辑删除)
     * @param id
     * @return
     */
    @Override
    public Integer deleteById(Integer id) {
        LambdaQueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<Admin>()
                .lambda().eq(Admin::getTuId,id);
        Integer countAdmin = adminMapper.selectCount(adminQueryWrapper);
        if(countAdmin == 0){
            throw new CommonException("账号不存在");
        }
        LambdaQueryWrapper<AdminRole> adminRoleIds = new QueryWrapper<AdminRole>()
                .lambda().eq(AdminRole::getUTuId,id);
        Integer countAdminRole = adminRoleMapper.selectCount(adminRoleIds);
        if (countAdminRole > 0){
            throw new CommonException("后台账号被使用，无法删除");
        }
        Admin admin = adminMapper.selectById(id);
        admin.setState(0);
        return adminMapper.updateById(admin);
    }

    /**
     * 后台角色新增
     * @param roleDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insert(RoleDTO roleDTO) {
        LambdaQueryWrapper<Role> roleLambdaQueryWrapper = new QueryWrapper<Role>()
                .lambda().eq(Role::getRoleName,roleDTO.getRoleName());
        Integer count = roleMapper.selectCount(roleLambdaQueryWrapper);
        if(count > 0){
            throw new  CommonException("角色名已存在");
        }
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO,role);
        roleMapper.insert(role);
        //获取角色传参中的权限信息
        List<Integer> rightList = roleDTO.getIntegerList();
        //插入角色权限的关联信息
        for (Integer rightVO :rightList){
            LambdaQueryWrapper<Right> wrapper = new QueryWrapper<Right>()
                    .lambda().eq(Right::getId,rightVO);
            int rightId = rightMapper.selectOne(wrapper).getId();
            int roleId = roleMapper.selectOne(roleLambdaQueryWrapper).getTrId();
            RoleRight roleRight = new RoleRight();
            roleRight.setRightId(rightId);
            roleRight.setRoleTrId(roleId);
            roleRightMapper.insert(roleRight);
        }
        return 1;
    }

    /**
     * 后台角色删除(逻辑删除)
     * @param id
     * @return
     */
    @Override
    public Integer deleteByRoleId(Integer id) {
        LambdaQueryWrapper<Role> roleQueryWrapper = new QueryWrapper<Role>()
                .lambda().eq(Role::getTrId,id);
        Integer countRole = roleMapper.selectCount(roleQueryWrapper);
        if(countRole == 0){
            throw new CommonException("角色不存在");
        }
        LambdaQueryWrapper<RoleRight> roleRightIds = new QueryWrapper<RoleRight>()
                .lambda().eq(RoleRight::getRoleTrId,id);
        Integer countRoleRight = roleRightMapper.selectCount(roleRightIds);
        if (countRoleRight > 0){
            throw new CommonException("角色被使用，无法删除");
        }
        LambdaQueryWrapper<AdminRole> adminRoleIds = new QueryWrapper<AdminRole>()
                .lambda().eq(AdminRole::getRoleTrId,id);
        Integer countAdminRole = adminRoleMapper.selectCount(adminRoleIds);
        if (countAdminRole > 0){
            throw new CommonException("角色被使用，无法删除");
        }
        Role role = roleMapper.selectById(id);
        role.setState(0);
        return roleMapper.updateById(role);
    }

    /**
     * 后台权限新增
     * @param rightDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insert(RightDTO rightDTO) {
        LambdaQueryWrapper<Right> rightLambdaQueryWrapper = new QueryWrapper<Right>()
                .lambda().eq(Right::getRightName,rightDTO.getRightName());
        Integer count = rightMapper.selectCount(rightLambdaQueryWrapper);
        if(count > 0){
            throw new  CommonException("权限名已存在");
        }
        Right right = new Right();
        BeanUtils.copyProperties(rightDTO,right);
        return rightMapper.insert(right);
    }

    /**
     * 后台权限删除(逻辑删除)
     * @param id
     * @return
     */
    @Override
    public Integer deleteByRightId(Integer id) {
        LambdaQueryWrapper<Right> rightQueryWrapper = new QueryWrapper<Right>()
                .lambda().eq(Right::getId,id);
        Integer countRight = rightMapper.selectCount(rightQueryWrapper);
        if(countRight == 0){
            throw new CommonException("权限不存在");
        }
        LambdaQueryWrapper<RoleRight> roleRightIds = new QueryWrapper<RoleRight>()
                .lambda().eq(RoleRight::getRightId,id);
        Integer countRoleRight = roleRightMapper.selectCount(roleRightIds);
        if (countRoleRight > 0){
            throw new CommonException("权限被使用，无法删除");
        }
        Right right = rightMapper.selectById(id);
        right.setState(0);
        return rightMapper.updateById(right);
    }

    /**
     * 用户所属角色权限信息查询
     * @param loginDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Page<LoginVO> login(LoginDTO loginDTO) {
        Page<LoginVO> page = new Page<>(loginDTO.getPageSize(),loginDTO.getPageNumber());
        Page<LoginVO> loginVO = rightMapper.dyGetList(page,loginDTO);
        List<LoginVO> records = loginVO.getRecords();

        //所属角色信息
        for (LoginVO role : records){
            Integer id = role.getTuId();
            List<Role> roleList = rightMapper.byGetRoleList(id);
            role.setRoleList(roleList);
        }

        //所属权限信息
        for (LoginVO right : records){
            Integer id = right.getTuId();
            List<Right> rightList = rightMapper.byGetRightList(id);
            right.setRightList(rightList);
        }

        return loginVO;
    }
}
