package com.example.school.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.school.domain.Qr;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.school.domain.Type;
import com.example.school.dto.QrDTO;
import com.example.school.dto.QrSelectDTO;
import com.example.school.dto.UserDTO;
import com.example.school.vo.QrVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public interface QrService extends IService<Qr> {
    /**
     * 出入配置新增
     * @param qrDTO
     * @return
     */
    Integer insert(QrDTO qrDTO);

    /**
     * 出入配置修改
     * @param qrDTO
     * @return
     */
    Integer update(QrDTO qrDTO);

    /**
     * 出入配置删除
     * @param id
     * @return
     */
    Integer deleteById(Integer id);

    /**
     * 出入配置分页查找
     * @param qrSelectDTO
     * @return
     */
    Page<QrVO> selectListPage(QrSelectDTO qrSelectDTO);

    /**
     * 二维码下载
     * @param response
     * @throws IOException
     */
     void downloadlocal(HttpServletResponse response) throws IOException;
}
