package com.bxg.pyg.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/admin/brand")
    public String brand(){
        return "admin/brand";
    }
}
