package com.bxg.pyg.sellgoods.service;

import com.bxg.pyg.pojo.TbBrand;
import com.github.pagehelper.Page;
import entity.PageResult;
import entity.Result;

import java.util.List;

public interface BrandService {
    public List<TbBrand> findAll();
    public Result add(TbBrand tbBrand);

    public Result edit(TbBrand tbBrand);

    public  TbBrand findOne(Integer id);

    public Result del(List<Integer> ids);
    public PageResult findAll(TbBrand tbBrand,Integer pageNum, Integer pageSize);

}
