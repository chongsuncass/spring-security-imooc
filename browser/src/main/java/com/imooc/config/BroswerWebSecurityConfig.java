package com.imooc.config;

import com.imooc.authentication.ImoocAuthenticationFailureHandler;
import com.imooc.authentication.ImoocAuthenticationSuccessHandler;
import com.imooc.code.ValidataCodeFilter;
import com.imooc.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidataCodeFilter validataCodeFilter = new ValidataCodeFilter();
        validataCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
        validataCodeFilter.setSecurityProperties(securityProperties);
        validataCodeFilter.afterPropertiesSet();

        http.addFilterBefore(validataCodeFilter, UsernamePasswordAuthenticationFilter.class)
        .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(imoocAuthenticationSuccessHandler)
                .failureHandler(imoocAuthenticationFailureHandler)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememerberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
