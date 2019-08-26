package com.bxg.pyg.sellgoods.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.bxg.pyg.mapper.TbBrandMapper;
import com.bxg.pyg.pojo.TbBrand;
import com.bxg.pyg.pojo.TbBrandExample;
import com.bxg.pyg.sellgoods.service.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper mapper;

    @Override
    public Result add(TbBrand tbBrand) {

     try {
         mapper.insert(tbBrand);
         return new Result(true,"添加成功");
        }catch (Exception e){
         return new  Result(false,"添加失败");
     }

    }

    @Override
    public Result edit(TbBrand tbBrand) {
        try {
            mapper.updateByPrimaryKey(tbBrand);
            return new Result(true,"修改成功");
        }catch (Exception e){
            return new  Result(false,"修改失败");
        }
    }

    @Override
        public List<TbBrand>  findAll() {

        List<TbBrand> userlist=mapper.selectByExample(null);

        return userlist;
    }
    @Override
    public PageResult findAll(TbBrand tbBrand,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        TbBrandExample exaple=new TbBrandExample();
        TbBrandExample.Criteria criteria=exaple.createCriteria();
        if(tbBrand!=null){
            if(!StringUtils.isEmpty(tbBrand.getName())){

                criteria.andNameLike("%"+tbBrand.getName()+"%");
            }
            if(!StringUtils.isEmpty(tbBrand.getFirstChar())){

                criteria.andFirstCharLike("%"+tbBrand.getFirstChar()+"%");
            }
        }

        Page<TbBrand> userlist=(Page<TbBrand>) mapper.selectByExample(exaple);

        return new PageResult(userlist.getTotal(),userlist.getResult());
    }

    @Override
    public Result del(List<Integer> ids) {
        try {
            for(Integer id:ids){
                mapper.deleteByPrimaryKey(id.longValue());
            }

            return new Result(true,"删除成功");
        }catch (Exception e){
            return new  Result(false,"删除失败");
        }
    }

    @Override
    public TbBrand findOne(Integer id) {
        return mapper.selectByPrimaryKey(id.longValue());
    }
}
