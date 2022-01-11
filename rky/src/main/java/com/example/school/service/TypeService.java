package com.example.school.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.school.domain.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.school.dto.TypeDTO;
import com.example.school.dto.TypeSelectDTO;
import com.example.school.vo.TypeVO;

/**
 *
 */
public interface TypeService extends IService<Type> {
    /**
     * 人员类型添加
     * @param typeDTO
     * @return
     */
    Integer insert(TypeDTO typeDTO);

    /**
     * 人员类型修改
     * @param typeDTO
     * @return
     */
    Integer update(TypeDTO typeDTO);

    /**
     * 人员类型删除
     * @param id
     * @return
     */
    Integer deleteById(Integer id);

    /**
     * 人员类型分页查找
     * @param typeSelectDTO
     * @return
     */
     Page<TypeVO> selectListPage(TypeSelectDTO typeSelectDTO);
}
