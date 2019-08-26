package com.bxg.pyg.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bxg.pyg.pojo.TbUser;
import com.bxg.pyg.sellgoods.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //构建角色
        List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        //查询用户
        TbUser tbUser = userService.findByusername(username);
        if (tbUser!=null) {
            if (tbUser.getStatus().equals("1")) {
                return new User(username,bCryptPasswordEncoder.encode(tbUser.getPassword()), authorities);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

}
