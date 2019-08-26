package com.bxg.pyg.manager.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.SpecificationResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.bxg.pyg.pojo.TbSpecification;
import com.bxg.pyg.sellgoods.service.SpecificationService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/specification")

public class SpecificationController {

	@Reference
	private SpecificationService specificationService;

	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll.do")
	public List findAll(){
		List<TbSpecification>  a =specificationService.findAll();
		List c=new ArrayList();

		for(TbSpecification tbSpecification:a){
			Map b=new HashMap();

			b.put("id",tbSpecification.getId());
			b.put("text",tbSpecification.getSpecName());
			c.add(b);
		}

		return c;
	}

	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage.do")
	public PageResult  findPage(int page,int rows){			
		return specificationService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param specification
	 * @return
	 */
	@RequestMapping("/add.do")
	public Result add(@RequestBody SpecificationResult specification){
		try {
			specificationService.add(specification);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param specification
	 * @return
	 */
	@RequestMapping("/update.do")
	public Result update(@RequestBody SpecificationResult specification){
		try {
			specificationService.update(specification);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne.do")
	public TbSpecification findOne(Long id){
		return specificationService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete.do")
	public Result delete(Long [] ids){
		try {
			specificationService.delete(ids);

			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	

	@RequestMapping("/search.do")
	public PageResult search(@RequestBody TbSpecification specification, int page, int rows  ){
		PageResult pageResult=specificationService.findPage(specification, page, rows);
		return pageResult;
	}

}
