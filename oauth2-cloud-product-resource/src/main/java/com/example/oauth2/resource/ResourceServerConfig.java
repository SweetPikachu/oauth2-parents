package com.example.oauth2.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 资源服务器相关配置类
 */
@Configuration
@EnableResourceServer // 标识为资源服务器，请求服务中的资源，就要带着token过来，找不到token或token是无效访问不了资源
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法级别权限控制
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "product-server";


    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 当前资源服务器的资源id，认证服务会认证客户端有没有访问这个资源id的权限，有则可以访问当前服务
        resources.resourceId(RESOURCE_ID)
            .tokenStore(tokenStore)
//                .tokenServices(tokenService())
        ;
    }



    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                // SpringSecurity不会使用也不会创建HttpSession实例
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                // 授权规则配置
//                .antMatchers("/product/*").hasAuthority("product")
                // 所有请求，都需要有all范围（scope）
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                // 等价于上面
//                .anyRequest().access("#oauth2.hasScope('all')")
            ;
    }
}
