package com.bxg.pyg.manager.config;

import com.bxg.pyg.manager.controller.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置用户名及密码及角色
        auth.userDetailsService(userDetailsServiceImpl()).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置拦截规则----配置访问所有地址均需要ADMIN权限,使用内存用户使用hasAnyRole验证用户角色，使用数据库用户，使用hasAnyAuthority验证用户权限
        http.authorizeRequests().antMatchers("/admin/**").hasAnyAuthority("ADMIN");

        //配置登录页面及退出等相关页面
        http.formLogin().loginPage("/login")//登录页面
                .usernameParameter("username").passwordParameter("password")//配置用户名密码参数名称
                .loginProcessingUrl("/login")//配置登录请求路径
                .defaultSuccessUrl("/admin/index",true)//登录成功跳转,并且始终跳转到/admin/index.html
                .failureUrl("/login?error")//登录失败跳转
                .and().logout().logoutUrl("/logout")//退出登录访问地址
                .logoutSuccessUrl("/login")//退出成功后访问页面
                .and().csrf().disable()//配置不进行csrf拦截
                .headers().frameOptions().sameOrigin();//配置可以加载框架页面  如iframe

        //自动登录
        http.rememberMe()
                .tokenValiditySeconds(432000);//设置cookie保存时间 单位秒
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/*.html","/css/**","/img/**","/js/**");
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsServiceImpl userDetailsServiceImpl() {
        return new UserDetailsServiceImpl();
    }

}

