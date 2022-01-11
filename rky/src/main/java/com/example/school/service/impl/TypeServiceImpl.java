package com.example.school.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.school.domain.Type;
import com.example.school.domain.User;
import com.example.school.dto.TypeDTO;
import com.example.school.dto.TypeSelectDTO;
import com.example.school.exception.CommonException;
import com.example.school.mapper.UserMapper;
import com.example.school.service.TypeService;
import com.example.school.mapper.TypeMapper;
import com.example.school.vo.TypeVO;
import com.example.school.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private UserMapper userMapper;


    /**
     * 人员类型添加
     * @param typeDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insert(TypeDTO typeDTO) {
        LambdaQueryWrapper<Type> typeQueryWrapper = new QueryWrapper<Type>()
                .lambda().eq(Type::getTypeName,typeDTO.getTypeName());
        Integer count = typeMapper.selectCount(typeQueryWrapper);
        if(count > 0){
            throw new CommonException("类型名称已存在");
        }
        Type type = new Type();
        BeanUtils.copyProperties(typeDTO,type);
        type.setCreateTime(LocalDateTime.now());
        return typeMapper.insert(type);
    }

    /**
     * 人员类型修改
     * @param typeDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer update(TypeDTO typeDTO) {
        LambdaQueryWrapper<Type> typeQueryWrapper = new QueryWrapper<Type>()
                .lambda().eq(Type::getId,typeDTO.getId());
        Integer counttype = typeMapper.selectCount(typeQueryWrapper);
        if(counttype == 0){
            throw new CommonException("人员类型不存在");
        }
        Type type = new Type();
        BeanUtils.copyProperties(typeDTO,type);
        return typeMapper.updateById(type);
    }

    /**
     * 人员类型删除
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteById(Integer id) {
        LambdaQueryWrapper<Type> typeQueryWrapper = new QueryWrapper<Type>()
                .lambda().eq(Type::getId,id);
        Integer counttype = typeMapper.selectCount(typeQueryWrapper);
        if(counttype == 0){
            throw new CommonException("人员类型不存在");
        }
        LambdaQueryWrapper<User> usertypeId = new QueryWrapper<User>().lambda()
                .eq(User::getTypeId,id);
        Integer countuser = userMapper.selectCount(usertypeId);
        if (countuser > 0){
            throw new CommonException("人员类型被使用，无法删除");
        }
        return typeMapper.deleteById(id);
    }

    /**
     * 人员类型分页查找
     * @param typeSelectDTO
     * @return
     */
    @Override
    public Page<TypeVO> selectListPage(TypeSelectDTO typeSelectDTO) {
        Page<Type> page = new Page<>(typeSelectDTO.getPageSize(), typeSelectDTO.getPageNumber());
        Page<TypeVO> productPage =typeMapper.dyGetTypeList(page,typeSelectDTO);
        return productPage;
    }
}




