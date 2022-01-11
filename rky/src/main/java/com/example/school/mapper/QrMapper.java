package com.example.school.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.school.domain.Qr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.school.domain.Type;
import com.example.school.dto.QrSelectDTO;
import com.example.school.dto.TypeSelectDTO;
import com.example.school.vo.QrVO;
import com.example.school.vo.TypeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.example.demo.domain.Qr
 */
@Repository
public interface QrMapper extends BaseMapper<Qr> {
    Page<QrVO> dyGetQrList(Page<Qr> page, QrSelectDTO qrSelectDTO);
}




