package com.bxg.pyg.sellgoods.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.bxg.pyg.mapper.TbBrandMapper;
import com.bxg.pyg.pojo.TbBrand;
import com.bxg.pyg.sellgoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper mapper;

    @Override
    public List<TbBrand> findAll() {
        List<TbBrand> a=mapper.selectByExample(null);
        return a;
    }
}
