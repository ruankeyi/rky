package com.example.school.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.school.domain.Qr;
import com.example.school.domain.Type;
import com.example.school.domain.User;
import com.example.school.dto.QrDTO;
import com.example.school.dto.QrSelectDTO;
import com.example.school.exception.CommonException;
import com.example.school.service.QrService;
import com.example.school.mapper.QrMapper;
import com.example.school.vo.QrVO;
import com.example.school.vo.TypeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;

/**
 *
 */
@Service
public class QrServiceImpl extends ServiceImpl<QrMapper, Qr> implements QrService {
    @Autowired
    private QrMapper qrMapper;

    /**
     * 出入配置新增
     * @param qrDTO
     * @return
     */
    @Override
    public Integer insert(QrDTO qrDTO) {
        LambdaQueryWrapper<Qr> qrQueryWrapper = new QueryWrapper<Qr>()
                .lambda().eq(Qr::getPip,qrDTO.getPip());
        Integer count = qrMapper.selectCount(qrQueryWrapper);
        if(count > 0){
            throw new CommonException("打卡地点名称已存在");
        }

        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
//        // 设置前景色，既二维码颜色（青色）
//        config.setForeColor(Color.CYAN.getRGB());
//        // 设置背景色（灰色）
//        config.setBackColor(Color.GRAY.getRGB());


        // 生成二维码到文件，也可以到流
        QrCodeUtil.generate("http://hutool.cn/", config, FileUtil.file("F:/qr/qrcode.jpg"));

        Qr qr = new Qr();
        BeanUtils.copyProperties(qrDTO,qr);
        qr.setCreateTime(LocalDateTime.now());
        qr.setPhoto("F:/qr/qrcode.jpg");
        return qrMapper.insert(qr);
    }

    /**
     * 出入配置修改
     * @param qrDTO
     * @return
     */
    @Override
    public Integer update(QrDTO qrDTO) {
        LambdaQueryWrapper<Qr> qrId = new QueryWrapper<Qr>().lambda()
                .eq(Qr::getId,qrDTO.getId());
        Integer count = qrMapper.selectCount(qrId);
        if (count == 0){
            throw new CommonException("出入配置不存在");
        }
        Qr qr = new Qr();
        BeanUtils.copyProperties(qrDTO,qr);
        qr.setCreateTime(LocalDateTime.now());
        return qrMapper.updateById(qr);
    }

    /**
     * 出入配置删除
     * @param id
     * @return
     */
    @Override
    public Integer deleteById(Integer id) {
        LambdaQueryWrapper<Qr> qrId = new QueryWrapper<Qr>().lambda()
                .eq(Qr::getId,id);
        Integer count = qrMapper.selectCount(qrId);
        if (count == 0){
            throw new CommonException("出入配置不存在");
        }
        return qrMapper.deleteById(id);
    }

    /**
     * 出入配置分页查找
     * @param qrSelectDTO
     * @return
     */
    @Override
    public Page<QrVO> selectListPage(QrSelectDTO qrSelectDTO) {
        Page<Qr> page = new Page<>(qrSelectDTO.getPageSize(),qrSelectDTO.getPageNumber());
        Page<QrVO> productPage =qrMapper.dyGetQrList(page,qrSelectDTO);
        return productPage;
    }

    /**
     * 二维码下载
     * @param response
     * @throws IOException
     */
    @Override
    public void downloadlocal(HttpServletResponse response) throws IOException {
        //读入到流中
        InputStream inputStream =new FileInputStream("F:/qr/qrcode.jpg");
        response.reset();
        response.setContentType("application/octet-stream");
        String filename = new File("F:/qr/qrcode.jpg").getName();
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] b =new  byte[1024];
        int len;
        while ((len = inputStream.read(b))>0){
            outputStream.write(b,0,len);
        }
        inputStream.close();
    }
}




