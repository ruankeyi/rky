package com.example.school.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.school.domain.Access;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.school.dto.AccessSelectDTO;
import com.example.school.dto.UserSelectDTO;
import com.example.school.vo.AccessVO;
import com.example.school.vo.UserVO;

/**
 *
 */
public interface AccessService extends IService<Access> {

    /**
     * 出入记录分页查询
     * @param accessSelectDTO
     * @return
     */
    Page<AccessVO> selectPage(AccessSelectDTO accessSelectDTO);

    /**
     * 出入记录导出
     * @param accessSelectDTO
     */
    void excelWriter(AccessSelectDTO accessSelectDTO);
}
