package com.lizhb.config;

import com.lizhb.security.AuthProvider;
import com.lizhb.security.LoginAuthFailHandler;
import com.lizhb.security.LoginUrlEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by lizhb.
 * Date: 2018/11/6
 * Time: 11:38
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * HTTP权限控制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置资源访问权限
        http.authorizeRequests()
                //管理员登录入口
                .antMatchers("/admin/login").permitAll()
                //用户登录入口
                .antMatchers("/user/login").permitAll()
                //静态资源
                .antMatchers("/static/**").permitAll()
                //管理员权限页面
                .antMatchers("/admin/**").hasRole("ADMIN")
                //用户权限页面
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/user/**").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginProcessingUrl("/login")//配置角色登录处理入口
                .failureHandler(authFailHandler()) // 登录失败处理器
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/page")
                .deleteCookies("JSESSIONID")  //登出后删除cookie
                .invalidateHttpSession(true)  //session失效
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(urlEntryPoint())
                .accessDeniedPage("/403");
        //防御策略，为方便开发，暂时关闭
        http.csrf().disable();
        //同源策略，huiadmin使用iframe
        http.headers().frameOptions().sameOrigin();
    }

    /**
     * 自定义认证策略(内存认证）
     * @param auth
     */
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN").and();
        auth.authenticationProvider(authProvider()).eraseCredentials(true);
    }

    @Bean
    public AuthProvider authProvider(){
        return new AuthProvider();
    }

    @Bean
    public LoginUrlEntryPoint urlEntryPoint(){
        return new LoginUrlEntryPoint("/user/login");
    }

    @Bean
    public LoginAuthFailHandler authFailHandler(){
        return new LoginAuthFailHandler(urlEntryPoint());
    }
}
