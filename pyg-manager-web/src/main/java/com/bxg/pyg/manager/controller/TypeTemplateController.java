package com.bxg.pyg.manager.controller;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.bxg.pyg.pojo.TbTypeTemplate;
import com.bxg.pyg.sellgoods.service.TypeTemplateService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

	@Reference
	private TypeTemplateService typeTemplateService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll.do")
	public List<TbTypeTemplate> findAll(){			
		return typeTemplateService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage.do")
	public PageResult  findPage(int page,int rows){			
		return typeTemplateService.findPage(page, rows);
	}
	

	@RequestMapping("/add.do")
	public Result add(@RequestBody String a){
		try {
			ObjectMapper om = new ObjectMapper();
			JsonNode tree = om.readTree(a);
			TbTypeTemplate tbTypeTemplate=new TbTypeTemplate();
			tbTypeTemplate.setName(tree.path("name").asText());
			tbTypeTemplate.setSpecIds(tree.path("specIds").toString());
			tbTypeTemplate.setBrandIds(tree.path("brandIds").toString());
			tbTypeTemplate.setCustomAttributeItems(tree.path("customAttributeItems").toString());
			typeTemplateService.add(tbTypeTemplate);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	

	@RequestMapping("/update.do")
	public Result update(@RequestBody String a){
		try {
			ObjectMapper om = new ObjectMapper();
			JsonNode tree = om.readTree(a);
			TbTypeTemplate tbTypeTemplate=new TbTypeTemplate();
			tbTypeTemplate.setId(tree.path("id").asLong());
			tbTypeTemplate.setName(tree.path("name").asText());
			tbTypeTemplate.setSpecIds(tree.path("specIds").toString());
			tbTypeTemplate.setBrandIds(tree.path("brandIds").toString());
			tbTypeTemplate.setCustomAttributeItems(tree.path("customAttributeItems").toString());
			typeTemplateService.update(tbTypeTemplate);

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
	public TbTypeTemplate findOne(Long id){
		return typeTemplateService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete.do")
	public Result delete(Long [] ids){
		try {
			typeTemplateService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}

	@RequestMapping("/search.do")
	public PageResult search(@RequestBody TbTypeTemplate typeTemplate, int page, int rows  ){
		return typeTemplateService.findPage(typeTemplate, page, rows);		
	}
	
}
