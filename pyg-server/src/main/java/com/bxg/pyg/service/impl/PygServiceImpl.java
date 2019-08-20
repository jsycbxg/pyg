package com.bxg.pyg.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.bxg.pyg.service.PygService;

@Service(version="1.0.0")

public class PygServiceImpl implements PygService {
    @Override
    public String test() {

        return "hello";
    }
}
