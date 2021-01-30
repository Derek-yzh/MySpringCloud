package com.example.csrf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Author: Derek
 * @DateTime: 2021/1/5 21:56
 * @Description: Security Configuration 适合单点
 */
@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {

    /**
     * web 相关
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //super.configure(http);

        http.
        authorizeRequests() //哪些地址需要登录
        .anyRequest().authenticated() //所有界面都验证
        .and()
        .formLogin().loginPage("/login.html") //自定义登录表单

        .loginProcessingUrl("/login").permitAll() //permitAll 这个页面不需要登录

        .failureForwardUrl("/login.html?error") //登录失败页面

        .defaultSuccessUrl("/",true) //登录成功跳转的页面 true:强制跳转

        //配置登录页的表单name
        .passwordParameter("password")
        .usernameParameter("username")

        .failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                e.printStackTrace();

                //TODO 判断异常信息 instanceof根据不同异常 进行不同跳转
                //TODO 记录失败次数 禁止登录

                request.getRequestDispatcher(request.getRequestURL().toString()).forward(request,response);
            }
        })

        .and() // 默认post请求都拦截
        .csrf()//.csrfTokenRepository(new HttpSessionCsrfTokenRepository())
        ;
    }


    /**
     * 权限相关
     * session 登录
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
        //包含加密算法
        .withUser("123").password(new BCryptPasswordEncoder().encode("123")).roles("admin")
        .and()
        .withUser("321").password("321").roles("user")



        ;

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
