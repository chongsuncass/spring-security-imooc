package com.imooc.config;

import com.imooc.authentication.ImoocAuthenticationFailureHandler;
import com.imooc.authentication.ImoocAuthenticationSuccessHandler;
import com.imooc.imageCode.ValidataCodeFilter;
import com.imooc.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 浏览器端webSecurity配置类
 */
@Configuration
public class BroswerWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ImoocAuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
    @Autowired
    private ImoocAuthenticationFailureHandler imoocAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidataCodeFilter validataCodeFilter = new ValidataCodeFilter();
        validataCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);

        http.addFilterBefore(validataCodeFilter, UsernamePasswordAuthenticationFilter.class)
        .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(imoocAuthenticationSuccessHandler)
                .failureHandler(imoocAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/image/code/create").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
