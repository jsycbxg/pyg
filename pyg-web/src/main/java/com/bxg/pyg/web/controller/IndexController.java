package com.bxg.pyg.web.controller;




import com.alibaba.dubbo.config.annotation.Reference;
import com.bxg.pyg.service.PygService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class IndexController {
    @Reference(version = "1.0.0")
    private PygService demoService;

    @RequestMapping("/abc")
    public String test() {

        String a = demoService.test();
        return a;
    }
}
