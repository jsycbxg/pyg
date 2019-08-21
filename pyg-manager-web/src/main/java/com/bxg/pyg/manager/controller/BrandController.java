package com.bxg.pyg.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bxg.pyg.pojo.TbBrand;
import com.bxg.pyg.sellgoods.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;
    @RequestMapping("/findall")
    public List<TbBrand> findALl(){
        List<TbBrand> a=brandService.findAll();
        return a;
    }
}
