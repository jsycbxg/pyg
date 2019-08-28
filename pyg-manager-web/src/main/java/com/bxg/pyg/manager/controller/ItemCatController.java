package com.bxg.pyg.manager.controller;
import java.security.acl.LastOwnerException;
import java.util.List;

import com.bxg.pyg.pojo.TbItem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.bxg.pyg.pojo.TbItemCat;
import com.bxg.pyg.sellgoods.service.ItemCatService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

	@Reference
	private ItemCatService itemCatService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll.do")
	public List<TbItemCat> findAll(){			
		return itemCatService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage.do")
	public PageResult  findPage(int page,int rows){			
		return itemCatService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/add.do")
	public Result add(@RequestBody TbItemCat itemCat){
		try {
			itemCatService.add(itemCat);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param itemCat
	 * @return
	 */
	@RequestMapping("/update.do")
	public Result update(@RequestBody TbItemCat itemCat){
		try {
			itemCatService.update(itemCat);
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
	public TbItemCat findOne(Long id){
		return itemCatService.findOne(id);		
	}

	@RequestMapping("/findList.do")
	public Result findList(Long id){
		StringBuffer list=new StringBuffer();
		Long pid=id;
		while (true){
			TbItemCat tbItemCat=itemCatService.findOne(pid);
			if(tbItemCat!=null){
				list.append(tbItemCat.getName());
				pid=tbItemCat.getParentId();

			}else{
				break;
			}
			list.append(" >> ");

		}

		return new Result(true, list.toString());
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete.do")
	public Result delete(Long [] ids){
		try {
			for(Long id:ids){
				TbItemCat tbItemCat=new TbItemCat();
				tbItemCat.setParentId(id);
				if(itemCatService.findPage(tbItemCat,1,1).getTotal()!=0){
					return new Result(false, "请先删除该分类下的分类");
				}
			}
			itemCatService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	

	@RequestMapping("/search.do")
	public PageResult search(@RequestBody TbItemCat itemCat, int page, int rows  ){
		return itemCatService.findPage(itemCat, page, rows);		
	}
	
}
