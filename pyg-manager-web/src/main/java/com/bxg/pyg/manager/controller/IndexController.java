package com.bxg.pyg.manager.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/admin/brand")
    public String brand(){
        return "admin/brand";
    }
    @GetMapping("/admin/specification")
    public String specification(){
        return "admin/specification";
    }
    @GetMapping("/admin/type_template")
    public String type_template(){
        return "admin/type_template";
    }
    @GetMapping("/admin/index")
    public String index(){
        return "admin/index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/admin/home")
    public String home(){
        return "admin/home";
    }
    @GetMapping("/admin/logout")
    public String logout(){
        return "logout";
    }
    @GetMapping("/admin/seller_1")
    public String seller_1(){
        return "admin/seller_1";
    }
    @GetMapping("/admin/item_cat")
    public String item_cat(){
        return "admin/item_cat";
    }

}
