package com.example.school.config;

import com.example.school.domain.Admin;
import com.example.school.exception.MyAccessDecisionManager;
import com.example.school.filter.MyFilter;
import com.example.school.service.MyUserDetailsService;
import com.example.school.service.impl.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

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

    @Autowired
    private MyFilter myFilter;

    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    //重写登陆方法
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    //加密
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                        o.setAccessDecisionManager(myAccessDecisionManager);
//                        o.setSecurityMetadataSource(myFilter);
//                        return o;
//                    }
//                })
//                .and()
//                .formLogin()
//                .permitAll()
//                .and()
//                .csrf()
//                .disable();

        http.formLogin()//单表登陆
                .and()
                .authorizeRequests() //认证配置
                .anyRequest()        //任何请求
                .authenticated()
                // 路径匹配，参数是要处理的 url
                .antMatchers("/user/addUser").hasAnyRole("管理员","user");// 访问某路径时要具有某种权限中的一种
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/**",
//                "/admin/addAdmin",
//                "/admin/addRole",
//                "/admin/addRight",
//                "/access/exportInfo",
//                "/user/batchImport",
//                "/qr/addQr",
//                "/css/**",
//                "/js/**",
//                "index.html",
//                "favicon.ico",
//                "/doc.html",
//                "/webjars/**",
//                "/swagger-resources/**",
//                "/v2/api-docs/**",
//                "/home",
//                "/service-worker.js",
//                "/swagger-ui.html",
//                "/error",
//                "/v2/api-docs-ext/**"
                "/user/addUser"
        );
    }
}

