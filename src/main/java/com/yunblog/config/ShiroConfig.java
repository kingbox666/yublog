package com.yunblog.config;

import com.yunblog.entiy.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Description: shiro 配置
 */
@Configuration
public class ShiroConfig {

    /**
     *
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("ShiroFilter") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /*
           anon：无需验证就可以访问
           authc：必须认证才能访问
           user：必须拥有 记住我 功能才能使用
           perms：拥有对某个资源的权限才能访问
           role：拥有某个角色权限才能访问
        */
        LinkedHashMap<String,String> FilterMap = new LinkedHashMap<>();

        FilterMap.put("/admin/*","authc");
        FilterMap.put("/adminLogin/*","authc");
//        FilterMap.put("/admin/input","authc");
//        FilterMap.put("/admin/types","authc");
//        FilterMap.put("/admin/index","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(FilterMap);
        //设置登录请求
        shiroFilterFactoryBean.setLoginUrl("/adminLogin");
        return shiroFilterFactoryBean;
    }

    /**
     *
     * @param userRealm
     * @return
     */
    @Bean(name="ShiroFilter")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联userRealm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    /**
     * @Description: 自定义realm() 对象
     * @return UserRealm()
     */
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

}
