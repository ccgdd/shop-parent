package com.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.pojo.EasyUIDataGridRestful;
import com.e3mall.pojo.TbItem;
import com.e3mall.service.ItemService;
import com.shop.utils.E3Result;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	
	public TbItem	getItemById(@PathVariable Long itemId){
		TbItem tbItem = itemService.getItemById(itemId);
		
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridRestful getItemList(Integer page, Integer rows){
		
		EasyUIDataGridRestful result = itemService.getItemList(page, rows);
		
		return result;
	}
	
	/**
	 * 商品添加
	 * @param item
	 * @param desc
	 * @return
	 */
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addIteam(TbItem item , String desc){
		
		E3Result e3Result = itemService.addItem(item, desc);
		
		return e3Result;
	}
	
	
	/**
	 * 根据商品id查询描述信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/query/item/desc/{id}")
	@ResponseBody
	public E3Result queryDescById(@PathVariable Long id){
		E3Result e3Result = itemService.queryDescByID(id);
		
		return e3Result;
		
	}
	
	/**
	 * 商品编辑
	 */
	@RequestMapping("/rest/item/update")
	@ResponseBody
	public E3Result editItem(TbItem item , String desc){
		E3Result e3Result = itemService.editItem(item, desc);
		return e3Result;
	}
	

	
	
}
