package com.example.school.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.school.domain.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.school.domain.User;
import com.example.school.dto.TypeSelectDTO;
import com.example.school.dto.UserSelectDTO;
import com.example.school.vo.TypeVO;
import com.example.school.vo.UserVO;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.example.demo.domain.Type
 */
@Repository
public interface TypeMapper extends BaseMapper<Type> {
    Page<TypeVO> dyGetTypeList(Page<Type> page, TypeSelectDTO typeSelectDTO);
}




