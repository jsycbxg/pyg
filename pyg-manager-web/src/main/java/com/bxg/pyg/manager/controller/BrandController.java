package com.bxg.pyg.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bxg.pyg.pojo.TbBrand;
import com.bxg.pyg.sellgoods.service.BrandService;
import com.github.pagehelper.Page;
import entity.PageResult;
import entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/brand")
public class BrandController {
    @Reference
    private BrandService brandService;
    @RequestMapping("/findall.do")
    public PageResult findALl(@RequestParam("page")Integer page,@RequestParam("size")Integer size){
        PageResult a =brandService.findAll(page,size);
        return a;
    }
    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand tbBrand){
        return brandService.add(tbBrand);
    }
    @RequestMapping("/edit")
    public Result edit(@RequestBody TbBrand tbBrand){
        return brandService.edit(tbBrand);
    }
    @RequestMapping("/findone")
    public TbBrand findone(@RequestParam("id") Integer id){
        return brandService.findOne(id);
    }
    @RequestMapping("/del")
    public Result del(@RequestParam("ids") List<Integer> ids){
        return brandService.del(ids);
    }


}
