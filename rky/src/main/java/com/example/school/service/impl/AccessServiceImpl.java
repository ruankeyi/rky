package com.example.school.service.impl;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.school.domain.Access;
import com.example.school.domain.User;
import com.example.school.dto.AccessSelectDTO;
import com.example.school.service.AccessService;
import com.example.school.mapper.AccessMapper;
import com.example.school.vo.AccessVO;
import com.example.school.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void excelWriter(AccessSelectDTO accessSelectDTO) {
        Page<Access> page = new Page<>(accessSelectDTO.getPageSize(), accessSelectDTO.getPageNumber());
        Page<AccessVO> productPage =accessMapper.dyGetAccessList(page,accessSelectDTO);
        List<AccessVO> list = productPage.getRecords();
        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("F:/access/出入信息表.xlsx");
        //一次性写出内容，强制输出标题
        writer.write(list, true);
        //关闭writer，释放内存
        writer.close();
    }
}




