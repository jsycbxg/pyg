package com.bxg.pyg.sellgoods.service.impl;
import java.util.List;

import com.bxg.pyg.mapper.TbSpecificationOptionMapper;
import com.bxg.pyg.pojo.TbSpecificationOption;
import com.bxg.pyg.pojo.TbSpecificationOptionExample;
import entity.SpecificationResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.bxg.pyg.mapper.TbSpecificationMapper;
import com.bxg.pyg.pojo.TbSpecification;
import com.bxg.pyg.pojo.TbSpecificationExample;
import com.bxg.pyg.pojo.TbSpecificationExample.Criteria;
import com.bxg.pyg.sellgoods.service.SpecificationService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(SpecificationResult specification) {
		TbSpecification tbSpecification=specification.getSpecification();
		specificationMapper.insert(tbSpecification);
		if(specification.getSpecificationOptionList()!=null){
			for(TbSpecificationOption tbSpecificationOption:specification.getSpecificationOptionList()){
				tbSpecificationOption.setSpecId(tbSpecification.getId());
				specificationOptionMapper.insert(tbSpecificationOption);
			}
		}

	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(SpecificationResult specification){

		TbSpecification tbSpecification=specification.getSpecification();
		specificationMapper.updateByPrimaryKey(tbSpecification);
		TbSpecificationOptionExample example=new TbSpecificationOptionExample();
		example.createCriteria().andSpecIdEqualTo(tbSpecification.getId());

		specificationOptionMapper.deleteByExample(example);
		if(specification.getSpecificationOptionList()!=null){
			for(TbSpecificationOption tbSpecificationOption:specification.getSpecificationOptionList()){
				tbSpecificationOption.setSpecId(tbSpecification.getId());
				specificationOptionMapper.insert(tbSpecificationOption);
			}
		}

	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbSpecification findOne(Long id){
		return specificationMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			specificationMapper.deleteByPrimaryKey(id);

			TbSpecificationOptionExample example=new TbSpecificationOptionExample();
			example.createCriteria().andSpecIdEqualTo(id);
			specificationOptionMapper.deleteByExample(example);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
