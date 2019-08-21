package com.bxg.pyg.sellgoods.service.impl;

import com.bxg.pyg.mapper.TbBrandMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class BrandServiceImplTest {

    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Test
    public void findAll() {

        System.out.println(tbBrandMapper.selectByExample(null));
    }
}