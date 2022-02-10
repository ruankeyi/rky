package com.example.school.filter;

import com.example.school.domain.Right;
import com.example.school.domain.Role;
import com.example.school.mapper.RightMapper;
import com.example.school.service.RightService;
import com.example.school.vo.RightVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @Author: ruankeyi
 * @Date: 2022/02/09/10:53
 * @Description:
 */
@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {
    @Autowired
    RightService rightService;
    @Autowired
    RightMapper rightMapper;
    //路径匹配符 直接用以匹配路径
    AntPathMatcher pathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        /*根据请求地址 分析请求该地址需要什么角色*/
        String url = ((FilterInvocation) o).getRequestUrl();    //获取请求地址
        List<Right> allMenus =  rightService.getAllMenus();
        for (Right right:allMenus
        ) {
            //正则校验
            if(pathMatcher.match(right.getUrl() , url)){
                List<Role> roles = right.getRoles();
                String[] rolesStr = new String[roles.size()];
                for(int i = 0;i<roles.size();i++){
                    rolesStr[i] = roles.get(i).getRoleName();
                }
                return SecurityConfig.createList(rolesStr);     //返回请求地址所需的角色
            }
        }
        //请求地址没有匹配数据库中的地址则返回默认值，以作标识
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        //是否支持该方法，返回true即可
        return true;
  }
}
