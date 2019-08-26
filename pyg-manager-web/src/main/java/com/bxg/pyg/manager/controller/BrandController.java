package com.bxg.pyg.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bxg.pyg.pojo.TbBrand;
import com.bxg.pyg.sellgoods.service.BrandService;
import com.github.pagehelper.Page;
import entity.PageResult;
import entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/brand")
public class BrandController {
    @Reference
    private BrandService brandService;
    @RequestMapping("/findall.do")
    public List findALl(){
        List<TbBrand>  a =brandService.findAll();
        List c=new ArrayList();

        for(TbBrand tbBrand:a){
            Map b=new HashMap();

            b.put("id",tbBrand.getId());
            b.put("text",tbBrand.getName());
            c.add(b);
        }

        return c;
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
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand tbBrand,@RequestParam("page")Integer page,@RequestParam("size")Integer size){
        PageResult a =brandService.findAll(tbBrand,page,size);
        return a;
    }


}
