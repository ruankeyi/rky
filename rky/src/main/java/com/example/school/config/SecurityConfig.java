package com.example.school.config;

import com.example.school.domain.Admin;
import com.example.school.service.MyUserDetailsService;
import com.example.school.service.impl.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author HuiXing
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//   1
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String pwd = bCryptPasswordEncoder.encode("123");
//        auth.inMemoryAuthentication().withUser("lucy").password(pwd).roles("admin");
//    }
//
//    /**
//     *  当进行密码的加密操作的时候需要这个bean
//     *  new BCryptPasswordEncoder()实现 PasswordEncoder
//     */
//    @Bean
//    PasswordEncoder password(){
//        return new BCryptPasswordEncoder();
//    }
//   2
    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()//单表登陆
                .and()
                .authorizeRequests() //认证配置
                .anyRequest()       //任何请求
                .authenticated();   //都需要身份认证
    }
}

