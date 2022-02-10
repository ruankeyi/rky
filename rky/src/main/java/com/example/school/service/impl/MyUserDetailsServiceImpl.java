package com.example.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.school.domain.Admin;
import com.example.school.domain.Right;
import com.example.school.domain.Role;
import com.example.school.mapper.LoginMapper;
import com.example.school.mapper.RightMapper;
import com.example.school.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ruankeyi
 * security重写登录
 * @Date: 2022/01/13/22:00
 * @Description:
 */
@Service
@Transactional
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
//    private QueryWrapper<User> wrapper;
//    private LoginMapper loginMapper;
//
//    @Autowired
//    public MyUserDetailsService(LoginMapper loginMapper) {
//        this.loginMapper = loginMapper;
//        this.wrapper = new QueryWrapper<User>();
//    }
    @Autowired
    private RightMapper rightMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        //模拟通过数据库查询到用户名密码
//        String userName = "lchh";
//        String password = "123456";
//        //权限集合
//        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
//        return new User(userName,new BCryptPasswordEncoder().encode(password),auths);



        // 查询数据库
        try {
            Admin admin = rightMapper.dyGetusername(username);
            if (admin == null) {
                return null;
            }
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            List<Role> list = rightMapper.byGetRoleList(admin.getTuId());
            for (Role role : list) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            //封装 SpringSecurity  需要的UserDetails 对象并返回
            UserDetails userDetails = new User(admin.getUsername(),new BCryptPasswordEncoder().encode( admin.getPassword()), authorities);
            return userDetails;
        } catch (Exception e) {
            e.printStackTrace();
            //返回null即表示认证失败
            return null;
        }
    }
}
