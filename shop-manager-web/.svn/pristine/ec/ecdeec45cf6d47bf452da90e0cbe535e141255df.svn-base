package com.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e3mall.content.service.ContentCategoryService;
import com.shop.pojo.EasyUITreeNode;
import com.shop.utils.E3Result;


@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 通过父id查询所有子节点
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(
			@RequestParam(name="id", defaultValue="0") Long parentId) {
		
		List<EasyUITreeNode> list = contentCategoryService.getContentCategoryList(parentId);
		return list;
	}
	
	/**
	 * 新增节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public E3Result createCategory(Long parentId, String name) {
		E3Result result = contentCategoryService.addContentCategory(parentId, name);
		return result;
	}
	
	
	/**
	 * 重命名节点
	 */
	@RequestMapping("/content/category/update")
	@ResponseBody
	public E3Result updateCategoryName(Long id , String name){
		
		contentCategoryService.updateCategoryName(id, name);
		
		return E3Result.ok();
	}
	
	/**
	 * 删除节点
	 */
	@RequestMapping("/content/category/delete/")
	@ResponseBody
	public E3Result deleteCategoryNode(long id){
		
		E3Result e3Result = contentCategoryService.deleteNode(null, id);
		
		return e3Result;
	}
	
	
	
	
}