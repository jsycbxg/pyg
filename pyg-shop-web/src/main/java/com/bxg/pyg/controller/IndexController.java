package com.bxg.pyg.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sun.security.util.SecurityConstants;

@Controller
public class IndexController {
    @GetMapping("/login")
    public String login(){
        return "shoplogin";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/admin/index")
    public String index(Model model){
        model.addAttribute("loginname", SecurityContextHolder.getContext().getAuthentication().getName());
        return "admin/index";
    }
    @GetMapping("/admin/logout")
    public String logout(){
        return "logout";
    }
    @GetMapping("/admin/home")
    public String home(){
        return "admin/home";
    }
    @GetMapping("/admin/password")
    public String password(){
        return "admin/password";
    }
    @GetMapping("/admin/seller")
    public String seller(){
        return "admin/seller";
    }
    @GetMapping("/admin/goods")
    public String goods(){
        return "admin/goods";
    }
    @GetMapping("/admin/goods_edit")
    public String goods_edit(){
        return "admin/goods_edit";
    }

}
