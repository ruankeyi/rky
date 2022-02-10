package com.example.school.service.impl;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.school.domain.Access;
import com.example.school.domain.User;
import com.example.school.dto.AccessSelectDTO;
import com.example.school.exception.CommonException;
import com.example.school.service.AccessService;
import com.example.school.mapper.AccessMapper;
import com.example.school.utils.ExcelUtils;
import com.example.school.vo.AccessVO;
import com.example.school.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 */
@Service
public class AccessServiceImpl extends ServiceImpl<AccessMapper, Access> implements AccessService{
    @Autowired
    private AccessMapper accessMapper;


    /**
     * 出入记录分页查询
     * @param accessSelectDTO
     * @return
     */
    @Override
    public Page<AccessVO> selectPage(AccessSelectDTO accessSelectDTO) {
        Page<Access> page = new Page<>(accessSelectDTO.getPageSize(), accessSelectDTO.getPageNumber());
        Page<AccessVO> productPage =accessMapper.dyGetAccessList(page,accessSelectDTO);
        return productPage;
    }

    /**
     * 出入记录导出
     * @param accessSelectDTO
     */
    @Override
    public void excelWriter(HttpServletResponse response,AccessSelectDTO accessSelectDTO) {
        Page<Access> page = new Page<>(accessSelectDTO.getPageSize(), accessSelectDTO.getPageNumber());
        Page<AccessVO> productPage =accessMapper.dyGetAccessList(page,accessSelectDTO);
        List<AccessVO> list = productPage.getRecords();
        try {
            ExcelUtils.exportExcel(response,"出入记录",list,AccessVO.class);
        } catch (IOException e) {
            throw  new CommonException("下载失败");
        }
    }
}




