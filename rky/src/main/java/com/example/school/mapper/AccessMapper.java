package com.example.school.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.school.domain.Access;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.school.domain.User;
import com.example.school.dto.AccessSelectDTO;
import com.example.school.dto.UserSelectDTO;
import com.example.school.vo.AccessVO;
import com.example.school.vo.UserVO;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.example.demo.domain.Access
 */
@Repository
public interface AccessMapper extends BaseMapper<Access> {
    Page<AccessVO> dyGetAccessList(Page<Access> page, AccessSelectDTO accessSelectDTO);
}




